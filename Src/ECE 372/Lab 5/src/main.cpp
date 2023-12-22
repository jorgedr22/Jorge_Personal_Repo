#include <avr/interrupt.h>
#include <avr/delay.h>
#include <avr/io.h>
#include <Arduino.h>
#include "i2c.h"
#include "spi.h"
#include "timer.h"
#include "pwm.h"
#include "switch.h"

#define SLA 0x68  // MPU_6050 address with PIN AD0 grounded
#define PWR_MGMT  0x6B
#define WAKEUP 0x00
#define ACCEL_XOUT_H  0x3B
#define ACCEL_XOUT_L  0x3C
#define ACCEL_YOUT_H  0x3D
#define ACCEL_YOUT_L  0x3E
#define ACCEL_ZOUT_H  0x3F
#define ACCEL_ZOUT_L  0x40



#define threshold 10000

//state machine for button
typedef enum button{
  wp, // wait press
  dp, // debouce press
  wr, // wait release
  dr  // debounce release
}button;

//state machine for LED matrix
typedef enum LED{
  waitSmile, // accelerameter is balanced
  smile, 
  waitFrown, // over 45 degrees in either x,y,z direction.
  frown 
}LED;

volatile button button_state = wp;

volatile LED LED_state = waitSmile;

int buzzer_chirp = 0; // when 0, buzzer should be off. When 1, buzzer should be on.

int main()
{
  Serial.begin(9600);
  SPI_MASTER_Init();
  InitI2C();
  PWM_init();
  Timer1_init();
  Switch_init();
  sei(); 
  
  buzzer_off();

  int x_cord, y_cord, z_cord;

  // look at MAX7219/MAX7221 datasheet
  //initialize the 8x8 LED array 
  write_execute (0x0A, 0x08); // table 7 - brightness control (medium)
  write_execute(0x0B, 0x07);  // table 8 - scaning all rows and columns (display everything)
  
  //when powers up, automatically goes into shutdown
  write_execute(0x0C, 0x01); // set shutdown register to normal operation (0x01)
  write_execute(0x0F, 0x00); // display test register - set to normal operation

  StartI2C_Trans(SLA);
  write(PWR_MGMT);
  write(WAKEUP);
  StopI2C_Trans();

  while(1)
  {
    Read_from(SLA,ACCEL_XOUT_H);
    x_cord = Read_data();
    Read_from(SLA, ACCEL_XOUT_L);
    x_cord = (x_cord << 8) | Read_data();

    Read_from(SLA,ACCEL_YOUT_H);
    y_cord = Read_data();
    Read_from(SLA, ACCEL_YOUT_L);
    y_cord = (y_cord << 8) | Read_data();

    Read_from(SLA,ACCEL_ZOUT_H);
    z_cord = Read_data();
    Read_from(SLA, ACCEL_ZOUT_L);
    z_cord = (z_cord << 8) | Read_data();

    if (buzzer_chirp == 1){
      for(int i = 1000; i < 4000; i++){changeFrequency(i);}
    }

    Serial.print("x coord:");
    Serial.print(abs(x_cord));
    Serial.print("y coord:");
    Serial.println(abs(y_cord));
    Serial.print("z coord:");
    Serial.println(abs(z_cord));

    switch (LED_state)
    {
      case waitSmile:
      // currently frowning
        if((abs(y_cord)>threshold) | (abs(x_cord)>threshold))
          LED_state = waitSmile;
        else
          LED_state = smile;
        break;

      case smile:
        // smiley face
        write_execute(0x01, 0b00000000); 
        write_execute(0x02, 0b00110110);  
        write_execute(0x03, 0b01000110); 
        write_execute(0x04, 0b01000000); 
        write_execute(0x05, 0b01000000); 
        write_execute(0x06, 0b01000110); 
        write_execute(0x07, 0b00110110); 
        write_execute(0x08, 0b00000000); 
        LED_state = waitFrown;
        break;

      case waitFrown:
        // accelerometer is triggered, so LED should frown. Is smiling before.
        if((abs(y_cord)>threshold) | (abs(x_cord)>threshold))
          LED_state = frown;
        else
          LED_state = waitFrown;
        break;

      case frown:
        // frowny face
        buzzer_chirp = 1;
        write_execute(0x01, 0b00000000);
        write_execute(0x02, 0b01100110);
        write_execute(0x03, 0b00010110);
        write_execute(0x04, 0b00010000);
        write_execute(0x05, 0b00010000);
        write_execute(0x06, 0b00010110);
        write_execute(0x07, 0b01100110);
        write_execute(0x08, 0b00000000);
        LED_state = waitSmile;
        break;

      default:
        LED_state = waitSmile;
        break;
    }

    switch (button_state)
    {
      case wp:
        break;

      case dp:
        delayMs(1);
        button_state = wr;
        break;

      case wr:
        //button_state = dr;
        break;

      case dr:
        delayMs(1);
        button_state = wp;
        break;
    }
    StopI2C_Trans();
  }
  return 0;
}

ISR(INT0_vect) // for the switch to stop the chirping noise from the buzzer
{
  if(button_state == wp)
    {
        buzzer_off();
        buzzer_chirp = 0;
        button_state = dp;
        
    }
  else if (button_state == wr)
    {     
      button_state = dr;
    }

}