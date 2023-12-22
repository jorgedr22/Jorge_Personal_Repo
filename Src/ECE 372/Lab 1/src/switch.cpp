// Author: Jorge Del Rio        
// Net ID: 23642921 
// Date: August 28,2023      
// Assignment: Lab 1
//----------------------------------------------------------------------//
#include <avr/io.h>

// initSwitch returns void and takes no parameters
void initSwitch(){
    // initialized Data Directional Register on PortA for button
    DDRA &= ~(1 << DDA0);
    PORTA |= (1 << PORTA0);
}
