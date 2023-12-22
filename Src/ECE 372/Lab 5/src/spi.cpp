#include "spi.h"
#include <Arduino.h>
#include <avr/interrupt.h>
#include <avr/delay.h>
#include <avr/io.h> // input/ output special function registers

#define DDR_SPI DDRB // Data direction register on ATMEGA 2560 for SPI
#define DD_SS DDB0 // SS chip select port pin on ATMEGA 2560
#define DD_SCK DDB1 // Clock pin connection on ATMEGA 2560
#define DD_MOSI DDB2 // MOSI pin on 2560
#define SPI_PORT PORTB // port pin for SPI 
#define SPI_SS_BIT PORTB0 // Port B register bit of chip select

// 2560 datasheet 21.2.2 - polling / wait for flag to be raised
#define wait_for_complete while(!(SPSR & (1 << SPIF))); 

void SPI_MASTER_Init() {
    // set MOSI, SCK, and SS output
    DDR_SPI = (1 << DD_MOSI) | (1 << DD_SCK) | (1 << DD_SS); // all outputs

    // this program does not use MISO line, it only writes to device

    // set SS high initially (chip select off)
    SPI_PORT |= (1 << SPI_SS_BIT);

    // enable SPI, master mode, CPOL, CPHA, default clock and fosc/128
    // data sheet says sample on rising edge. CPOL = 1, SPHA = 1
    // control register = SPCR
    SPCR |= (1 << SPE) | (1 << MSTR) | (1 << CPOL) | (1 << CPHA) | (1 << SPR1) | (1 << SPR0);
 }

 void write_execute(unsigned char CMD, unsigned char data) {
    // call from main
    SPI_PORT &= ~(1 << SPI_SS_BIT); // enable chip select bit to begin SPI frame
    SPDR = CMD; // address for command
    wait_for_complete;
    SPDR = data;
    wait_for_complete;
    SPI_PORT |= (1 << SPI_SS_BIT); // disable chip select bit to end SPI frame

 }