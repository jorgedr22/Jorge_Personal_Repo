#include <avr/io.h>
#include <util/delay.h>
#include <Arduino.h>
#include "ADC.h"
#include "waterpump.h" 

void initPump() {
    // Set pump pin as input
    DDRB &= ~(1 << DDB7);
}

void turnOnPump() {
    DDRB |= (1 << DDB7);
}

void turnOffPump() {
    DDRB &= ~(1 << DDB7);
}