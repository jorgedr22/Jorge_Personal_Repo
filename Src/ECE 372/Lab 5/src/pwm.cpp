#include "pwm.h"
#include <avr/interrupt.h>
#include <avr/delay.h>
#include <avr/io.h>
#include <Arduino.h>

void PWM_init() {
    DDRE |= (1 << DDE4); // pin 2

    // Initialize Timer3 for PWM
    TCCR3A |= (1 << WGM31) | (1 << WGM30); // Fast PWM mode, mode 15
    TCCR3B |= (1 << WGM32) | (1 << WGM33) | (1 << CS30); // Set prescaler to 1 and start PWM  

    TCCR3A &= ~(1 << COM3B0);
    TCCR3A |= (1 << COM3B1);

    ICR3 = 1023; // Set top value for Timer3
}

void changeDutyCycle(unsigned int adc_value) {
    OCR3A = adc_value; 
}

void buzzer_off(){
    //Serial.print("buzzer off");
    TCCR3B &= ~(1 << CS30);
}

void buzzer_on(){
    TCCR3B |= (1 << CS30);
}

void changeFrequency(int freq){
    //Serial.print("in change frequency");
    buzzer_on();
    
    //chirp in debounce press
    // Joy not sure why you split it up, not sure
    OCR3AH = ((16000000 / freq) >> 8); // high
    OCR3AL = (16000000 / freq); // low
    // divide by 2 to maintain 50% duty cycle - but also in mode 15 so should already be in 50%
    OCR3BH = OCR3AH >> 1; // divide by 2
    OCR3BL = OCR3AL >> 1; // divide by 2
}
// outputing to OC3B (OCR3B)- half of OCR3A
