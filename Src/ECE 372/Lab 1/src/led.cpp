// Author: Jorge Del Rio        
// Net ID: 23642921 
// Date: August 28,2023      
// Assignment: Lab 1
//----------------------------------------------------------------------//
#include <avr/io.h>
#include <Arduino.h>
#include <util/delay.h>
#include "led.h"


// initLED which returns void and takes no parameters
void initLED(){
    //initialized DDRs for the pin and ports being used
    DDRB |= ((1 << DDB5) | (1 << DDB4));
    DDRH |= ((1 << DDH6) | (1 << DDH5) | (1 << DDH4) | (1 << DDH3));
    DDRE |= (1 << DDE3);
    DDRG |= (1 << DDG5);
    
}

// turnOnLED which returns void and takes in an unsigned int parameter called led
void turnOnLED(unsigned int led){
    // switch case that turns on opposite LEDs going from outer to inner and backwards
    switch (led)
    {
    case 11:
        PORTB |= (1 << PORTB5);
        PORTG |= (1 << PORTG5);
        Serial.println("Pin 11 and Pin 4 are on");      
        break;
    case 10:
        PORTB |= (1 << PORTB4);
        PORTE |= (1 << PORTE3);
        Serial.println("Pin 10 and Pin 5 are on");
        break;
    case 9:
        PORTH |= (1 << PORTH6) | (1 << PORTH3);
        Serial.println("Pin 9 and Pin 6 are on");
        break;
    case 8:
        PORTH |= (1 << PORTH5) | (1 << PORTH4);
        Serial.println("Pin 8 and Pin 7 are on");
        break;
    case 7:
        PORTH |= (1 << PORTH5) | (1 << PORTH4);  
        Serial.println("Pin 8 and Pin 7 are on");    
        break;
    case 6:
        PORTH |= (1 << PORTH6) | (1 << PORTH3);
        Serial.println("Pin 9 and Pin 6 are on");
        break;
    case 5:
        PORTB |= (1 << PORTB4);
        PORTE |= (1 << PORTE3);
        Serial.println("Pin 10 and Pin 5 are on");
        break;
    case 4:
        PORTB |= (1 << PORTB5);
        PORTG |= (1 << PORTG5);
        Serial.println("Pin 11 and Pin 4 are on");
        break;
    default:
        break;
    }
    
}

// turnOffLED which returns void and takes in an unsigned int parameter called led
void turnOffLED(unsigned int led){
    // inverse switch case statemetn that turns off LEDs
    switch (led)
    {
    case 11:
        PORTB &= ~(1 << PORTB5);
        PORTG &= ~(1 << PORTG5); 
        Serial.println("Pin 11 and 4 are off");     
        break;
    case 10:
        PORTB &= ~(1 << PORTB4);
        PORTE &= ~(1 << PORTE3); 
        Serial.println("Pin 10 and 5 are off");
        break;
    case 9:
        PORTH &= ~((1 << PORTH6) | (1 << PORTH3));
        Serial.println("Pin 9 and 6 are off");
        break;
    case 8:
        PORTH &= ~((1 << PORTH5) | (1 << PORTH4));
        Serial.println("Pin 8 and 7 are off");
        break;
    case 7:
        PORTH &= ~((1 << PORTH5) | (1 << PORTH4)); 
        Serial.println("Pin 8 and 7 are off"); 
        break;
    case 6:
        PORTH &= ~((1 << PORTH6) | (1 << PORTH3));
        Serial.println("Pin 9 and 6 are off");
        break;
    case 5:
        PORTB &= ~(1 << PORTB4);
        PORTE &= ~(1 << PORTE3); 
        Serial.println("Pin 10 and 5 are off");
        break;
    case 4:
       PORTB &= ~(1 << PORTB5);
       PORTG &= ~(1 << PORTG5); 
       Serial.println("Pin 11 and 4 are off");
        break;
    default:
        PORTB &= ~(1 << PORTB5);
        PORTG &= ~(1 << PORTG5);
        PORTB &= ~(1 << PORTB4);
        PORTE &= ~(1 << PORTE3);
        PORTH &= ~((1 << PORTH6) | (1 << PORTH3));
        PORTH &= ~((1 << PORTH5) | (1 << PORTH4));
        break;
    }
}

// runLED which returns void and takes in an unsigned int parameter called led
void runLED(unsigned int led){
    turnOnLED(led);
    if (led == 11)
    {
        turnOffLED(8);
    }
    else if (led == 7)
    {
        turnOnLED(7);
    }
    else
    {
        turnOffLED(led + 1);
    }
    
        
}
