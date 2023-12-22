#include "PWM.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void PWM_init() {
    DDRE |= (1 << DDE3); // pin 5 
    DDRH |= (1 << DDH3); // pin 6

    // Initialize Timer3 for PWM
    TCCR3A |= (1 << COM3A1) | (1 << WGM31); // Fast PWM mode
    TCCR3B |= (1 << WGM32) | (1 << WGM33) | (1 << CS31); // Set prescaler to 8 and start PWM  

    ICR3 = 1023; // Set top value for Timer3
    OCR3A = 0; // where timer3 starts
    
    // Initialize Timer4 for PWM
    TCCR4A |= (1 << COM4A1) | (1 << WGM41); // Fast PWM mode
    TCCR4B |= (1 << WGM42) | (1 << WGM43) | (1 << CS41); // Set prescaler to 8 and start PWM  
    
    ICR4 = 1023; // Set top value for Timer4
    OCR4A = 0; // where timer4 starts
}

void changeDutyCycle(unsigned int adc_value) {
    OCR3A = adc_value; 
    OCR4A = 1023 - adc_value;
}

