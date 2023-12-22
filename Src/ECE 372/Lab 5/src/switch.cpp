#include "switch.h"
#include <avr/interrupt.h>
#include <avr/delay.h>
#include <avr/io.h>

void Switch_init() 
{
    DDRD &= ~(1 << DDD2); // Set PORTD2 as input
    PORTD |= (1 << PORTD2); // enable PORTD
    EICRA |= (1 << ISC20); // Trigger INT0 on any logical change // fixme 
    EIMSK |= (1 << INT2); // Enable INT0
}


