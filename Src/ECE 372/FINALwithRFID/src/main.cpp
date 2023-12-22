#include <avr/interrupt.h>
#include <avr/io.h>
#include <util/delay.h>
#include <string.h>
#include <arduino.h>

#include "password.h"
#include "lcd.h"
#include "timer.h"
#include "pwm.h"
#include "keypad.h"
#include "motor.h"
#include "switch.h"
#include "rfid.h"

#define RST_PIN         5          // Configurable, see typical pin layout above
#define SS_PIN          53          // Configurable, see typical pin layout above

#define WORD_SIZE 6

// Define the valid card ID
byte validCardID[] = {0xD7, 0x46, 0x03, 0x53};

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

typedef enum{
  locked, unlocked, alert
}StateType;

typedef enum{
  wait_press, debounce_press, wait_release, debounce_release
}ButtonState;

volatile ButtonState buttonState = wait_press;

volatile StateType lockState = locked; 

int main() {
  Serial.begin(9600);

  // Enable global interrupts
  sei();

  Serial.flush();

  // Initialize all pins and components
  initSwitch();
  initButtons();
  initTimer0();
  initTimer1();
  initPWMTimer3();
  initPWMTimer4();
  initLCD();
	mfrc522.PCD_Init();
  byte* ID;
  unsigned int IDsize;

  Serial.println ("Init of LCD complete");
  
  // Wait for RFID to check to pass

  // Lock the door
  lock();
  
  unsigned int numStrikes = 0;
  char savedPassword[WORD_SIZE + 1];
  char enteredPassword[WORD_SIZE + 1];

  // Setup password when the system first starts
  moveCursor(0,0);
  writeString("Set Password:");

  getInput(savedPassword, WORD_SIZE);
  Serial.println(savedPassword);

  delayMs(500);
  //Serial.println(savedPassword);

  moveCursor(1,0);
  writeString("Password Set!");

  // Delay 1sec before asking for password
  delayMs(1000);

  while (1) {
  
    switch(buttonState){
      case wait_press:
      break;
      
      case debounce_press:
      delayMs(1);
      buttonState = wait_release;
      break;

      case wait_release:
      break;
      
      case debounce_release:
      delayMs(1);
      buttonState = wait_press;
      break;
    }

    switch(lockState){
      case locked:
      displayMessage("Enter Password", 0);
      getInput(enteredPassword, WORD_SIZE);
      if(checkPassword(enteredPassword, savedPassword) == 0){
        // Display
        displayMessage("Door Opened!", 1000);

        // Open lock
        unlock();

        // Clear all strikes
        numStrikes = 0;
        
        // Change State
        lockState = unlocked;

      }
      else{
        // Display
        displayMessage("Incorrect Password", 1000);

        numStrikes++;
        if(numStrikes == 3){
          Serial.println("Switch to alert");
          lockState = alert;
        }
      }
      break;

      case unlocked:
      // Wait for the door to close
      // Door Closes
      // Delay time for simulation
      
      if(buttonState == wait_release){
        lock();
        lockState = locked;
      }
      break;

      case alert:
      displayMessage("Alarm Activated", 0);
      _delay_ms(100);
      chirpingSound(5000);

      bool b = 1;
      bool match = 1;
      if ( ! mfrc522.PICC_IsNewCardPresent()) {
		    b = 0;
	    }

      // Select one of the cards
      if(b == 1){
        if ( ! mfrc522.PICC_ReadCardSerial()) {
          b = 0;
        }
      }

      // Dump debug info about the card; PICC_HaltA() is automatically called
      if(b == 1){
        ID = mfrc522.PICC_GetUid(&(mfrc522.uid));
        IDsize = mfrc522.PICC_GetSize(&(mfrc522.uid));
        Serial.println("ID is:");
        for(byte i = 0; i < IDsize; i++){
          Serial.print(ID[i] < 0x10 ? " 0" : " ");
          Serial.print(ID[i], HEX);               
        }
        Serial.print("\n");

        for(byte i=0;i<4;i++){
          if(validCardID[i] != ID[i]){
            match=0;
            break;
          }
        }

        if(match == 1){
          Serial.println("Valid Card");
          turnOffAlarm();

          unsigned int numStrikes = 0;
          char savedPassword[WORD_SIZE + 1];
          char enteredPassword[WORD_SIZE + 1];

          // Setup password when the system first starts
          moveCursor(0,0);
          writeString("Set Password:    ");

          getInput(savedPassword, WORD_SIZE);
          Serial.println(savedPassword);

          delayMs(500);
          //Serial.println(savedPassword);

          moveCursor(1,0);
          writeString("Password Set!");

          // Delay 1sec before asking for password
          delayMs(1000);

          lockState = locked;
        }
        else{
          Serial.println("Invalid Card");
        }
      }

      _delay_ms(100);      
      break;

    }

  }
  return 0;
}


ISR(INT0_vect){
  if(buttonState == wait_press){
    buttonState = debounce_press;
  }
  else if(buttonState == wait_release){
    buttonState = debounce_release;
  }
}