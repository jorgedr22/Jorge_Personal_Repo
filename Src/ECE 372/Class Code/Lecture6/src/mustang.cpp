// PROGRAM TO SEQUENCE LIGHTS IN A MUSTANGE TAIL PATTERN

#include <avr/io.h>
#include "mustang.h"

void InitLED(){
    // set Port pins PB7, PB6, PB5 for output
    DDRB |= (1 << DDB5)|(1 << DDB6) | (1 << DDB7);
}

void LEDon (unsigned int LEDnum){
    switch (LEDnum)
    {
    case 1:
        PORTB |= (1<<PORTB5);
        break;
    case 2:
        PORTB |= (1<<PORTB6);
        break;
    case 3:
        PORTB |= (1<<PORTB7);
        break;
    default:
        break;
    }
}

void LEDalloff(){
    PORTB &= ~((1<<PORTB5) | (1<<PORTB6) | (1<<PORTB7));
}

void Mustang_Tail_LED(char i){
// in one line of code the function takes the value of i and turns on the sucessive PORTpin
// for the Mustang LED lighting sequence
// the algorthim is (i) or'd with  [(2 to the power of i)-1 ] 
// note that 2 to the power of i  = (1 << i) 
PORTB = (((i)|((1 << i)-1) << 5) & 0xE0)| (PORTB & 0X1F); 
}

