#ifndef PWM_H
#define PWM_H

void PWM_init();

void changeDutyCycle(unsigned int adc_value);
void buzzer_off();
void buzzer_on();
void changeFrequency(int freq);

#endif