// Author: Jorge Del Rio        
// Net ID: 23642921 
// Date: August 28,2023      
// Assignment: Lab 1
//----------------------------------------------------------------------//
#include <Arduino.h>
#include <avr/io.h>
#include <util/delay.h>
#include "led.h"
#include "switch.h"

#define Short_Delay 100
#define Long_Delay 1000

int main(){
  //initialized LEDs and switch ---> (button) 
  initLED();
  initSwitch();

  // starting LED cycle at pin 11
  unsigned int led = 11;

  while(1){
    //If button is pressed, delay increases
    if (!(PINA & (1<<PINA0))) {
      //turing on LEDs and when LED reaches the center, starts at center and cycles backwards
      Serial.println("Button was pressed");
      runLED(led);
      _delay_ms(Long_Delay);
      led--;
      if(led < 4){
        led = 11;
      }
    }
    // else if button is released, delay decreases 
    else{
      //turing on LEDs and when LED reaches the center, starts at center and cycles backwards
      runLED(led);
      _delay_ms(Short_Delay);
      led--;
      if(led < 4){
        led = 11;
      }
  }
  }
return 0;
}