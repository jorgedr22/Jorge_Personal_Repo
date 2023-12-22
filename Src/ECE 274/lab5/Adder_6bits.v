`timescale 1ns / 1ps

//A + B + Cin gives S and Co where Co is Carry-out
module Adder_6bits(A, B, Cin, S, Co);
    
    input [5:0] A, B;
    input Cin;
    output reg [5:0] S;
    output reg Co;
    
    always @ (A or B or Cin) begin
        {Co, S} = A + B + Cin;
    end

endmodule

