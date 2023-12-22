// Description: This file implements functions that utilize the timers
//----------------------------------------------------------------------//

#include "timer.h"
#include <Arduino.h>

// Initialize timer 0, you should not turn the timer on here.
// You will need to use CTC mode
void initTimer0(){
    TCCR0A &= ~ (1 << WGM00);
    TCCR0A |= (1 << WGM01);
    TCCR0B &= ~(1 << WGM02);
    
}

// This delays the program an amount specified by unsigned int delay.
// Use timer 0. Keep in mind that you need to choose your prescalar wisely
//such that your timer is precise to 1 millisecond and can be used for
//100-200 milliseconds

void delayMs(unsigned int delay){
    unsigned int count = 0;

    OCR0A = 249;
    
    TCCR0B |= (1 << CS00) | (1 << CS01);
    TCCR0B &= ~ (1 << CS02);
    
    //start the timer count at 0
    TCNT0 = 0;

    // set the flag down by applying logic 1
    TIFR0 |= (1<< OCF0A);

    while (count < delay)
    {
        // set the flag down by applying logic 1
        TIFR0 |= (1<< OCF0A);
        
        //start the timer count at 0
        TCNT0 = 0; 

        while (!(TIFR0 & (1<<OCF0A)));
        count++;
    }

    TCCR0B &= ~((1<<CS00) | (1<<CS01) | (1<<CS02));
}
