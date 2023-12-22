#ifndef TIMER_H
#define TIMER_H

#include <avr/io.h>

void Timer0_init();
void delayMs(unsigned int delay);
void Timer1_init();
void delay_sec(unsigned int delay);

#endif
