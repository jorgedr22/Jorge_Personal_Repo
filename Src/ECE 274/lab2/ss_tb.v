`timescale 1ns / 1ps
module SevenSegment_tb();

reg [3:0] SW_tb; 
wire CA_tb, CB_tb, CC_tb, CD_tb, CE_tb, CF_tb, CG_tb;

SevenSegment SevenSegment_1(SW_tb, CA_tb, CB_tb, CC_tb, CD_tb, CE_tb, CF_tb, CG_tb);

  initial
  begin
      SW_tb <= 4'b0000;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0001;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0010; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0011;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0100; 
      #100; $display("SW_tb <= %b", SW_tb);                        
      SW_tb <= 4'b0101;             
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0110; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b0111; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1000; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1001; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1010; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1011; 
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1100;;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1101;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1110;
      #100; $display("SW_tb <= %b", SW_tb);
      SW_tb <= 4'b1111; 
      #100; $display("SW_tb <= %b", SW_tb);
      end
  endmodule