#include "ADC.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void ADC_init() {
    DDRF &= ~(1 << DDF0); // Set Analog A0 as an input and enable ADC
    ADMUX |= (1 << REFS0); // AVcc with external capacitor at AREF pin and input channel 0
    ADMUX &= ~(1 << ADLAR); //set to right justified
    
    // Determines the gain on the amplifier (set for 1x gain)
    // lecture 17 slide 102
    ADMUX &= ~((1 << MUX4) | (1 << MUX3) | (1 << MUX2) | (1 << MUX1) | (1 << MUX0));
    ADCSRB &= ~(1 << MUX5);
    
    // set to auto triggering
    ADCSRB &= ~((1 << ADTS2) | (1 << ADTS1) | (1 << ADTS0)); 

    // Enable ADC and set prescaler to 128
    ADCSRA |= (1 << ADEN) | (1 << ADATE) | (1 << ADSC) | (1<< ADPS2) | (1 << ADPS1) | (1 << ADPS0);  

    // Disable ADCO
    DIDR0 |= (1 << ADC0D);

}

