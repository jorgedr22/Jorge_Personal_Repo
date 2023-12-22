#include "lcd.h"
#include "timer.h"
#include <util/delay.h>


void initLCDPins(){
    DDRA |= (1<<DDA0) | (1<<DDA1) | (1<<DDA2) | (1<<DDA3);
    DDRB |= (1<<DDB4) | (1<<DDB6);
}

void fourBitCommandWithDelay(unsigned char data, unsigned int delay){
  
  // bottom 4 bits of data assigned to PortA pins
  PORTA = (PORTA & 0x0F) | (data & 0xF0);
  
  //RS = 0
  PORTB &= ~(1<<PORTB6);

  //E = 1
  PORTB |= (1<<PORTB4);

  delayUs(1);

  //E = 0
  PORTB &= ~(1<<PORTB4);

  // delay based on the datasheet
  delayUs(delay);

}

void eightBitCommandWithDelay(unsigned char command, unsigned int delay){
  // Top 4 bits of command assigned to PORTA pins
  PORTA = (PORTA & 0xF0) | ((command & 0xF0) >> 4);
  
  // RS = 0
  PORTB &= ~(1<<PORTB6);
  
  // E = 1
  PORTB |= (1<<PORTB4); 

  delayUs(1);

  // E = 0
  PORTB &= ~(1<<PORTB4); 

  // Bottom 4 bits of command assigned to PORTA pins
  PORTA = (PORTA & 0xF0) | (command & 0x0F); 

  // E = 1
  PORTB |= (1<<PORTB4); 
  delayUs(1);
  
  // E = 0
  PORTB &= ~(1<<PORTB4); 
  
  // delay based on the datasheet
  delayUs(delay);
}

void writeCharacter(unsigned char character){
  // Top 4 bits of character assigned to PORTA pins
  PORTA = (PORTA & 0xF0) | ((character & 0xF0) >> 4); 

  // RS = 1
  PORTB |= (1<<PORTB6); 

  // E = 1
  PORTB |= (1<<PORTB4); 
  
  delayUs(1);
  
  // E = 0
  PORTB &= ~(1<<PORTB4); 

  // Bottom 4 bits of character assigned to PORTA pins
  PORTA = (PORTA & 0xF0) | (character & 0x0F);  
  
  // E = 1
  PORTB |= (1<<PORTB4); 
  
  delayUs(1);
  
  // E = 0
  PORTB &= ~(1<<PORTB4);
  delayUs(46);
}

void writeString(const char *string){
  while (*string != '\0') {
    writeCharacter(*string);
    string++;
  }
}

void moveCursor(unsigned char x, unsigned char y){
	// Determines row
  if (x == 0) {
    // Moves LCD over by y amount
    eightBitCommandWithDelay(0x80 + y,100);
    eightBitCommandWithDelay(0x0F,100);
  }
   else {
    eightBitCommandWithDelay(0xC0 + y,100);
    eightBitCommandWithDelay(0x0F,100);
  }
}

void initLCDProcedure(){
  
  // Delay 15 milliseconds
  for (int i = 0; i < 1000; i++) {
    delayUs(15);
  }

  // Write 0b0011 to DB[7:4] and delay 4100 microseconds
  fourBitCommandWithDelay(0x3,4100);

  // Write 0b0011 to DB[7:4] and delay 100 microseconds
  fourBitCommandWithDelay(0x3,100);

  // write 0b0011 to DB[7:4] and 100us delay
  fourBitCommandWithDelay(0x3,100);

  // write 0b0010 to DB[7:4] and 100us delay.
  fourBitCommandWithDelay(0x2,100);

  // Function set in the command table with 53us delay
  eightBitCommandWithDelay(0x28,53);

  // Display off in the command table with 53us delay
  eightBitCommandWithDelay(0x08,53);

  // Clear display in the command table. 
  eightBitCommandWithDelay(0x01,3000);

  // Entry Mode Set in the command table.
  eightBitCommandWithDelay(0x06,53);

  // Display ON/OFF Control in the command table.
  eightBitCommandWithDelay(0x0C,53);
  
}

void initLCD(){
  initLCDPins();
  initLCDProcedure();
}