`timescale 1ns / 1ps

//A + B + Cin gives S and Co where Co is Carry-out
module Adder_12bits(A, B, Cin, S, Co);
    
    input [11:0] A, B;
    input Cin;
    output reg [11:0] S;
    output reg Co;
    
    always @ (A or B or Cin) begin
        {Co, S} = A + B + Cin;
    end

endmodule

