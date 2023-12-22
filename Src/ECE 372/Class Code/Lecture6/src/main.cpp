// main program to turn on 3 LEDS in the "Mustang Tail Light sequence"
// Program calls a function to initialize LEDS
// Program then calls a function to do the Mustang sequence

#include <Arduino.h> // for serial print
#include <avr/io.h>
#include "mustang.h"
#define DELAY 300
int main () {

InitLED();
//char i=0;
unsigned int LEDnum = 0;
while (1) {
  if (LEDnum < 4){
    //Mustang_Tail_LED(i);
    LEDon(LEDnum);
    _delay_ms(DELAY);
    LEDnum++;
  }
  else{
    LEDnum=0;
    LEDalloff();
    _delay_ms(DELAY);
  }
} 
return(0);
}