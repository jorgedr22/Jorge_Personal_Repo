#include "timer.h"
void initTimer1(){
	TCCR1A &= ~((1 << WGM10)|(1 << WGM11));
    TCCR1B |= (1 << WGM12);
    TCCR1B &= ~(1 << WGM13);
}

void delayUs(unsigned int delay){
    unsigned int count = 0;

    OCR1A = 2;

    TCCR1B |= (1 << CS11);
    TCCR1B &= ~((1<< CS12)|(1 << CS10));

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

void initTimer0(){
    TCCR0A &= ~ (1 << WGM00);
    TCCR0A |= (1 << WGM01);
    TCCR0B &= ~(1 << WGM02);
}

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