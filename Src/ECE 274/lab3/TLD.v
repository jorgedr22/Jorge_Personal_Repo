`timescale 1ns / 1ps

module Top_Design(CLK100MHZ, BTNC, SW, CA, CB, CF, CG);
    input CLK100MHZ, BTNC;
    input [1:0] SW; //SW[1] is L, SW[0] is R
    output CA, CB, CF, CG;
    wire w;
    
   	Lights a1(CA, CF, CG, CB, SW[1], SW[0], w, BTNC);
	ClkDiv a2(CLK100MHZ, 0, w);

    
endmodule