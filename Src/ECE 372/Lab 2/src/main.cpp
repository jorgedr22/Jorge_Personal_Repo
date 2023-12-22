// Author: Jorge Del Rio      
// Net ID:23642921
// Date: Monday, October 1, 2023       
// Assignment:Lab 2
//
// Description: This file contains a programmatic overall description of the
// program. It should never contain assignments to special function registers
// for the exception key one-line code such as checking the state of the pin.
//
//----------------------------------------------------------------------//
#include <avr/interrupt.h>
#include <avr/io.h>
#include <Arduino.h>
#include "led.h"
#include "switch.h"
#include "timer.h"

#define LONG_DELAY 200
#define SHORT_DELAY 100


// Define a set of states that can be used in the state machine using an enum.

typedef enum I_THINK_I_KNOW_WHAT_IM_DOING{
  // short_wait_press
  short_wp,
  // short_debounce_press
  short_dp,
  // short_wait_release 
  short_wr,
  // short_debounce_release
  short_dr,
  // long_wait_press
  long_wp ,
  // long_debounce_press
  long_dp ,
  // long_wait_release 
  long_wr ,
  // long_debounce_release
  long_dr ,
}I_THINK_I_KNOW_WHAT_IM_DOING;

volatile I_THINK_I_KNOW_WHAT_IM_DOING state = long_wp;

// led speed 1 is fast 
// led speed 2 is slow
int LED_Speed = 2; 


int main(){
  // initialized interupts
  sei();
  // initialized LEDs
  initLED();
  // initialized switch pin
  initSwitchPB3();
  // initialized Timer0
  initTimer0();

  // starting the binary count at 0
  unsigned char bin_num = 0;

  Serial.begin(9600);

  while (1) {

    if (LED_Speed == 1)
    {
      turnOnLEDWithChar(bin_num);
      delayMs(SHORT_DELAY);
      
    }
    else if (LED_Speed == 2)
    {
      turnOnLEDWithChar(bin_num);
      delayMs(LONG_DELAY);
    }

    bin_num++;

    // if overload, reset count to 0
    if(bin_num==16){
      bin_num = 0;
    }

    // State machine for the 2 types of delays
    // short: states for when the speed is fast
    // long: states for when the speed is slow
    switch (state)
    {
      case short_wp:
      break;

      case short_dp:
      state = short_wr;
      delayMs(1);
      break; 

      case short_wr:
      break;

      case short_dr:
      state = long_wp;
      delayMs(1);
      break;
    
      case long_wp:
      break;

      case long_dp:
      state = long_wr;
      delayMs(1);
      break; 

      case long_wr:
      break;

      case long_dr:
      state = short_wp;
      delayMs(1);
      break;
    
    }
    
	}
  return 0;
}

// Implement an Pin Change Interrupt which handles the switch being
// pressed and released. When the switch is pressed and released, the LEDs
// change at twice the original rate. If the LEDs are already changing at twice
// the original rate, it goes back to the original rate.

ISR(PCINT0_vect){
  // if the button is pressed, then the state goes to its
  // debounce state
  if(state == short_wp)
  {
    state = short_dp;
  }
  // when the button is relesead, then the state goes to its
  // debounce state
  else if(state == short_wr)
  {
    // The speed of the counting changes once the button is released
    LED_Speed = 2;
    state = short_dr;
  }
  // if the button is pressed, then the state goes to its
  // debounce state
  if(state == long_wp)
  {
    state = long_dp;
  }
  // when the button is relesead, then the state goes to its
  // debounce state
  else if(state == long_wr)
  {
    // The speed of the counting changes once the button is released
    LED_Speed = 1;
    state = long_dr;
  }
}
