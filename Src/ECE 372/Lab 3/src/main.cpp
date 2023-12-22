// Author:
// Net ID:
// Date:
// Assignment:     Lab 3
//
// Description: This file contains a programmatic overall description of the
// program. It should never contain assignments to special function registers
// for the exception key one-line code such as checking the state of the pin.
//
// Requirements:
//----------------------------------------------------------------------//




#include <avr/interrupt.h>
#include <avr/io.h>
#include "led.h"
#include "switch.h"
#include "timer.h"
#include "lcd.h"

#define LONG_DELAY 200
#define SHORT_DELAY 100

typedef enum BIG_BRAIN_CODE{
  //wait_press
  wp,
  // debounce_press
  dp,
  // wait_release 
  wr,
  // debounce_release
  dr,
}BIG_BRAIN_CODE;

volatile BIG_BRAIN_CODE sm = wp; //state machine

//led 0 = slow
//led 1 = fast
int led = 1;

int main(){

  initTimer1();
  initLCD();
  initSwitchPB3();
  initLED();
  initTimer0();
  sei(); // Enable global interrupts.

  moveCursor(0, 0); // moves the cursor to 0,0 position
  writeString("Current rate:");
  moveCursor(1, 0);  // moves the cursor to 1,0 position
  writeString("Fast");

  unsigned char num = 0;
  while (1) {

    if (led == 1)
    {
      turnOnLEDWithChar(num);
      delayMs(SHORT_DELAY);
      moveCursor(1, 0);  // moves the cursor to 1,0 position
      writeString("Fast");
    }
    else if (led == 0)
    {
      turnOnLEDWithChar(num);
      delayMs(LONG_DELAY);
      moveCursor(1, 0);  // moves the cursor to 1,0 position
      writeString("Slow");
    }

    num++;

    // if overload, reset count to 0
    if(num==16){
      num = 0;
    }

    // state transitions for binary counting
    switch (sm)
    {
      case wp:
      break;

      case dp:
      sm = wr;
      delayMs(1);
      break;

      case wr:
      break;

      case dr:
      sm = wp;
      delayMs(1);
      break;
    }
	}
  
  return 0;
}

ISR(PCINT0_vect){
  if(sm == wp)
  {
    sm = dp;
  }
  else if ((sm == wr) & (led == 1))
  {
    led = 0;
    sm = dr;
  }
  else if ((sm == wr) & (led == 0))
  {
    led = 1;
    sm = dr;
  }
  
}