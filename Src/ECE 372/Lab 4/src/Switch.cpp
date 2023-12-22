#include "Switch.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void Switch_init() {
    DDRD &= ~(1 << DDD0); // Set PORTD0 as input
    PORTD |= (1 << PORTD0); // enable PORTD
    EICRA |= (1 << ISC00); // Trigger INT0 on any logical change
    EIMSK |= (1 << INT0); // Enable INT0
}

