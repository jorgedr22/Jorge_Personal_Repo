`timescale 1ns / 1ps

module Register_12bits_tb();
    reg Clk, Rst, Ld;
    reg [11:0] I;
    wire [11:0] Q;
    
    Register_12bits Register_12bits_1(Clk, Rst, Ld, I, Q);
    
    always begin
        Clk <= 0;
        #100;
        Clk <=1;
        #100;
    end
    
    initial begin 
        I <= 12'h000; Ld<=0; Rst<=1;
        #150;
        I <= 12'habc;Ld<=0;Rst<=0;
        @(posedge Clk);
        #50 Ld<=1;
        @(posedge Clk);
        #50;
        I <= 12'hfff;Ld<=1;Rst<=0;
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
    end
    
endmodule
