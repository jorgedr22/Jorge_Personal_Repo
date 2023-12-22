#include "ADC.h"
#include <avr/io.h>
#include<avr/interrupt.h>
#include <Arduino.h>

void ADC_init_water() {
    DDRF &= ~(1 << DDF0); // Set Analog A0 as an input and enable ADC
    ADMUX |= (1 << REFS0); // AVcc with external capacitor at AREF pin and input channel 0
    ADMUX &= ~(1 << ADLAR); //set to right justified
    
    //ADMUX = REFS1 REFS0 ADLAR MUX4 MUX3 MUX2 MUX1 MUX0
    //          0     1     0    0    0    0    0    0
    // Determines the gain on the amplifier (set for 1x gain)
    // lecture 17 slide 102
    ADMUX &= ~((1 << MUX4) | (1 << MUX3) | (1 << MUX2) | (1 << MUX1) | (1 << MUX0));
    ADCSRB &= ~(1 << MUX5);
    
    // set to auto triggering
    // use free running mode ADTS[2:0] = 0b000
    ADCSRB &= ~((1 << ADTS2) | (1 << ADTS1) | (1 << ADTS0)); 

    // Enable ADC and enable auto triggering
    ADCSRA |= (1 << ADEN) | (1 << ADATE);
    
    // set the ADC clock frequency. Use prescaler of 128
    // ADC clock frequency is 16 Mhz divided by pre-scaler = 125 KHz
    // sampling rate is 1 / ((1/125KHz) * (13 clock cycles)) = 9615 KHz
    ADCSRA |= (1<< ADPS2) | (1 << ADPS1) | (1 << ADPS0); 
   
    // disable ADC0 pin digital input - pin A0 on board
    DIDR0 |= (1 << ADC0D);

    // start the first ADC conversion
    ADCSRA |= (1 << ADSC);
}

void ADC_init_moisture() {
    DDRF &= ~(1 << DDF1); // Set Analog A1 as an input and enable ADC
    ADMUX |= (1 << REFS0); // AVcc with external capacitor at AREF pin and input channel 0
    ADMUX &= ~(1 << ADLAR); //set to right justified
    
    //ADMUX = REFS1 REFS0 ADLAR MUX4 MUX3 MUX2 MUX1 MUX0
    //          0     1     0    0    0    0    0    1
    // Determines the gain on the amplifier (set for 1x gain)
    // lecture 17 slide 102
    // Datasheet page 283
    ADMUX &= ~((1 << MUX4) | (1 << MUX3) | (1 << MUX2) | (1 << MUX1));
    ADMUX |= (1 << MUX0);
    ADCSRB &= ~(1 << MUX5);
    
    // set to auto triggering
    // use free running mode ADTS[2:0] = 0b000
    ADCSRB &= ~((1 << ADTS2) | (1 << ADTS1) | (1 << ADTS0)); 

    // Enable ADC and enable auto triggering
    ADCSRA |= (1 << ADEN) | (1 << ADATE);
    
    // set the ADC clock frequency. Use prescaler of 128
    // ADC clock frequency is 16 Mhz divided by pre-scaler = 125 KHz
    // sampling rate is 1 / ((1/125KHz) * (13 clock cycles)) = 9615 KHz
    ADCSRA |= (1<< ADPS2) | (1 << ADPS1) | (1 << ADPS0); 
   
    // disable ADC1 pin digital input - pin A1 on board
    DIDR0 |= (1 << ADC1D);

    // start the first ADC conversion
    ADCSRA |= (1 << ADSC);
}

void disable_ADC(){
    ADCSRA &= ~(1 << ADEN);
}
