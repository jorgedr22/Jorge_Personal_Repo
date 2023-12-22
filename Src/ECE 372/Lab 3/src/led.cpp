// Description: This file implements turnOnLED and initLED to control
// the LEDs.
//----------------------------------------------------------------------//


#include <avr/io.h>
#include <util/delay.h>
#include "led.h"

/* Initialize PD0, PD1, PD2, and PD3 to outputs*/
void initLED(){
  DDRD |= (1<<DDD0) | (1<<DDD1) | (1<<DDD2) | (1<<DDD3);
}

void turnOnLEDWithChar(unsigned char num){
  PORTD = (PORTD & 0xF0) | (num & 0x0F);
}
