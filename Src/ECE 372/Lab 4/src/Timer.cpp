#include "Timer.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void Timer0_init(){
    TCCR0A &= ~ (1 << WGM00);
    TCCR0A |= (1 << WGM01);
    TCCR0B &= ~(1 << WGM02);
}

void delayMs(unsigned int delay){
   unsigned int count = 0;

    OCR0A = 249;
    
    TCCR0B |= (1 << CS01);
    TCCR0B &= ~ ((1 << CS02)|(1 << CS00));
    
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
void Timer1_init() {
    // Initialized Timer1 for 1 second for 10-second countdown
    TCCR1B = (1 << WGM12) | (1 << CS12) | (1 << CS10); // CTC mode, prescaler set
    OCR1A = 15624; // Set output compare register for 1 second (with a 16 MHz clock)
    TIMSK1 = (1 << OCIE1A); // Enable compare match interrupt
}

// delay is set up to count for one second. 
// line 65 in main.cpp called 10 times for a total of 10 seconds
void delay_sec(unsigned int delay){
    unsigned int count = 0;

    OCR1A = 15624;

    TCCR1B = (1 << CS12) | (1 << CS10);

    // start Timer1 count at 0
    TCNT1 = 0;
    
    // set flag down 
    TIFR1 |= (1<<OCF1A);

    while (count<delay)
    {
        // set the flag down by applying logic 1
        TIFR1 |= (1<< OCF1A);
        
        //start the timer count at 0
        TCNT1 = 0; 

        while (!(TIFR1 & (1<<OCF1A)));
        count++;
    }
    
    TCCR1B &= ~((1<<CS10) | (1<<CS11) | (1<<CS12));
}
