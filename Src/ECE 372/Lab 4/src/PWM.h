#ifndef PWM_H
#define PWM_H

#include <avr/io.h>

void PWM_init();
void changeDutyCycle(unsigned int adcValue);

#endif
