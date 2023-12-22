// Authors:        Aliah Haigh, Jorge Del Rio, Alexa Hoover            
// Date:           12/7/2023     
// Assignment:     Final Project
//
//----------------------------------------------------------------------//


#include <Arduino.h>
#include <avr/io.h>
#include <util/delay.h>
#include <string.h>
#include "ADC.h"
#include "LCD.h"
#include "LED.h"
#include "waterpump.h"
#include "switch.h"
#include "timer.h"

// pump drives 220 mA
// rest of circuit drives 180-140mA
// while LED on, circuit drives 140 mA (100 mA before)

//state machine for button
typedef enum button{
  wp, // wait press
  dp, // debouce press
  wr, // wait release
  dr  // debounce release
}button;

//state machine for System
typedef enum Machine{
    wait_sixHours, // delay for 6 hours
    moisture_ON, // turn on the moisture sensor
    moisture_OFF, // turn off the moisture sensor
    moisture_Readings, // reading of the moisture sensor
    water_Servings, // check the number of servings
    pump_ON, // turn on the pump
    pump_OFF, // turn off the pump 
    empty_Tank // the tank is empty (not enough servings)
}Machine;

volatile button button_state = wp;
volatile Machine Machine_state = wait_sixHours;
int button_press = 0;

int main() {
    Serial.begin(9600);

    // initialization
    SPI_MASTER_Init();
    Switch_init();
    initPump();
    initLCD();
    LED_OFF();
    sei();
    LED_OFF();
   
    // local variables
    double average = 0; // average for moisture level readings
    unsigned int moisture_level = 0;
    unsigned int water_level = 0;
    unsigned int num_Water_Servings = 1; // 1/2 cup servings
   
    // look at MAX7219/MAX7221 datasheet
    //initialize the 8x8 LED array 
    write_execute (0x0A, 0x08); // table 7 - brightness control (medium)
    write_execute(0x0B, 0x07);  // table 8 - scaning all rows and columns (display everything)
    
    //when powers up, automatically goes into shutdown
    write_execute(0x0C, 0x01); // set shutdown register to normal operation (0x01)
    write_execute(0x0F, 0x00); // display test register - set to normal operation

    moveCursor(0,0);
    writeString("Welcome Back!");
    delayMs(2000);

    // clear LCD
    eightBitCommandWithDelay(0x01,3000);

    while (1) 
    {   
        switch (Machine_state) // system state machine
        {
        case wait_sixHours: // wait for six hours
            for (int i = 0; i <= 21600; i++){
              delayMs(1000);
            }

            // for (int i = 0; i < 3; i++) // wait for 3 seconds for demo
            // {
            //     delayMs(1000);
            // }
            
            Machine_state = moisture_ON;
            break;
        case moisture_ON: //turn on the moisture sensor
            ADC_init_moisture();
            delayMs(1);
            Machine_state = moisture_Readings;
            break;
        case moisture_Readings: // read the sensor data
            //Delay for 1 minute for 6 readings
            average = 0; // rest average
            for (int i = 0; i < 6; i++){
            // read values
            moisture_level = ADCL;
            moisture_level += ((unsigned int)ADCH) << 8;
            average += moisture_level;
                for (int j = 0; j < 10; j++) // wait for 10 seconds - wait for 1 second for demo
                {
                    delayMs(1000);
                }
            }
            average = (average/6); // average of all six readings
            char temp[10];
            sprintf(temp,"%u",(unsigned int)average);
            moveCursor(0,0);
            writeString("Moisture level:");
            moveCursor(1,0);
            writeString(temp);
            delayMs(5000);
            Machine_state = moisture_OFF;
            break;
        case moisture_OFF: //turn off the moisture sensor
            disable_ADC();
            delayMs(1);
            // clear LCD
            eightBitCommandWithDelay(0x01,3000);
            Machine_state = water_Servings;
            break;
        case water_Servings: // check the number of water servings
            if(num_Water_Servings>1 && average > 400){
                num_Water_Servings--;
                // write the number of servings left to LCD
                char temp[10];
                sprintf(temp,"%u",num_Water_Servings);
                moveCursor(0,0);
                writeString("Servings left:");
                moveCursor(1,0);
                writeString(temp);
                delayMs(2000);
                Machine_state = pump_ON;
            }
            else if(num_Water_Servings==1){
                Machine_state = empty_Tank;
            }
            else{
                Machine_state = wait_sixHours;
            }
            // clear LCD
            eightBitCommandWithDelay(0x01,3000);

            break;
        case pump_ON: // turn on pump
            turnOnPump();
            for (int i = 0; i < 5; i++)
            {
                delayMs(1000);
            }
            Machine_state = pump_OFF;
            break;
        case pump_OFF: // turn off pump
            turnOffPump();
            Machine_state = wait_sixHours;
            break;
        case empty_Tank:
            ADC_init_water();
            moveCursor(0,0);
            writeString("NEED WATER");
            delayMs(2000);
            if(button_press){
                delayMs(1);
                water_level = ADCL;
                water_level += ((unsigned int)ADCH) << 8;
                button_press = false;
                if(water_level>300){
                    num_Water_Servings = 6; // reset servings
                    smile();
                    LED_OFF();
                    Machine_state = water_Servings;
                }
                else{
                    frown();
                    LED_OFF();
                    Machine_state = empty_Tank;
                }
                disable_ADC();
            }
            else{
                Machine_state = empty_Tank;
            }
            // clear LCD
            eightBitCommandWithDelay(0x01,3000);
            break; 
        default: // go back to waiting 6 hours
            Machine_state = wait_sixHours;
            break;
        }

        switch (button_state) // button state machine
        {
        case wp:
            break;

        case dp:
            delayMs(1);
            button_state = wr;
            break;

        case wr:
            break;

        case dr:
            delayMs(1);
            button_state = wp;
            break;
        }  
    } 
    return 0;
}

ISR(INT0_vect){ // interrupt pin
    if(button_state == wp)
    {
        button_press = 1;
        button_state = dp;
    }
  else if (button_state == wr)
    {     
      button_state = dr;
    }
}