#include "ADC.h"
#include "PWM.h"
#include "Timer.h"
#include "SevenSegment.h"
#include "Switch.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

typedef enum button_sm{
  //wait_press
  wp,
  // debounce_press
  dp,
  // wait_release 
  wr,
  // debounce_release
  dr,
  // pause to disable ISR and update display
  pause
}button_sm;

volatile button_sm button_state = wp;

int main() {
    // initialization of modules
    ADC_init(); 
    PWM_init();
    Timer0_init();
    Timer1_init();
    SevenSegment_init();
    Switch_init();
    sei(); // Enable global interrupts
    
    unsigned int adc_value = 0;
    
    while (1) {
        switch (button_state)
        {
        case wp:
            // reading the values stored in the ADCL and ADCH registers
            // adding them together in order to change the duty cycle
            adc_value = ADCL;
            adc_value += ((unsigned int)ADCH)<<8;
            Serial.println(adc_value); 
            changeDutyCycle(adc_value);
            break;
        case dp:
            _delay_ms(100);
            button_state = wr;
            break;
        case wr:
            break;
        case dr:
            _delay_ms(100);
            button_state = pause;
            break;
        case pause:
            _delay_ms(100);
            cli(); // disabling interrupts
            DDRE &= ~(1 << DDE3); // disable the motor
            //display countdown from 9 - 0
            for(int i=9;i>=0;i--){
                displayDigit(i);
                delay_sec(1);
            }
            sei(); // re-enable interrupts
            DDRE |= (1 << DDE3); // re-enable the motor
            button_state = wp;
            break;
        }
    }
    return 0;
}
ISR(INT0_vect) { // debouncing
    if(button_state == wp)
    {
        button_state = dp;
    }
    else if (button_state == wr)
    {
        button_state = dr;
    }
}
