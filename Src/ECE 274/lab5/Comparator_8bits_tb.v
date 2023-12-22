`timescale 1ns / 1ps

module Comparator_8bits_tb();
    reg [7:0] A, B;
  //  wire Gt, Lt, Eq;
   
    Comparator_8bits Comparator_8bits_1(A, B, AltB, AeqB, AgtB);
    
    initial begin
      A = 175;
      B = 100;
      #100;
        
      A = 10;
      B = 200;
      #100;
    
      A = 45;
      B = 45;
      #100;
    
     end

endmodule
