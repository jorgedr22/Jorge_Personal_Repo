#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void Timer1_init() {
    // Initialized Timer1 for 1 ms 
    TCCR1A &= ~ (1 << WGM10);
    TCCR1A |= (1 << WGM11);
    TCCR1B &= ~(1 << WGM12);
    TIMSK1 = (1 << OCIE1A); // Enable compare match interrupt
}

void delayMs(unsigned int delay){
   unsigned int count = 0;

    OCR1A = 249;
    
    TCCR1B |= (1 << CS11);
    TCCR1B &= ~ ((1 << CS12)|(1 << CS10));
    
    //start the timer count at 0
    TCNT1 = 0;

    // set the flag down by applying logic 1
    TIFR1 |= (1<< OCF1A);

    while (count < delay)
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