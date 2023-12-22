// Description: This file implements the initialization of an external
// switch.
//----------------------------------------------------------------------//

#include "switch.h"
#include <avr/io.h>

void initSwitchPB3(){
      
    DDRB &= ~(1<<DDB3);

    PORTB |= (1<<PORTB3);
    
    //Pin Change Interrupt Control Register
    PCICR |= (1<<PCIE0);
    //Pin Change Mask Register 0
    PCMSK0 |= (1<<PCINT3);
}
