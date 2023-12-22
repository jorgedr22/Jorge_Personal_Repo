#ifndef LED_H
#define LED_H

void SPI_MASTER_Init();
void write_execute(unsigned char CMD, unsigned char data);
void LED_OFF();
void smile();
void frown();
#endif