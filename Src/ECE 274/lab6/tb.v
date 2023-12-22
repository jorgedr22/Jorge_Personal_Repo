`timescale 1ns / 1ps

module tb();
    reg go, Clk, Rst;
    wire [7:0] sum_tb,data_tb; 
    wire done;
    
    Lab6 Lab6_1(go, sum_tb, data_tb, done, Clk, Rst);
    
    
    always begin
    Clk <= 0;
    #200;
    Clk <= 1;
    #200;
    end
    
    initial begin
    go=0;Rst=1;
    @(posedge Clk);
    #50; Rst=0;
    #50;
    go=1;
    @(posedge Clk);
    #50;
    go=0;
    end

    
endmodule
