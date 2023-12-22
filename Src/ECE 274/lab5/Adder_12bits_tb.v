`timescale 1ns / 1ps

module Adder_12bits_tb();
    reg [11:0] A, B;
    reg Cin;
    wire [11:0] S;
    wire Co;
    
    Adder_12bits Adder_12bits_1(A, B, Cin, S, Co);
    
    initial begin 
        A <= 12'hc23; B <= 12'h0b4; Cin <= 0;
        #50 A <= 12'hc23; B <= 12'h0b4; Cin <= 1;
        #50 A <= 12'h023; B <= 12'h0b4; Cin <= 1;
        #50 A <= 12'hfff; B <= 12'h000; Cin <= 1;
        #50 A <= 12'hfff; B <= 12'h00f; Cin <= 0;
    end
    
endmodule
