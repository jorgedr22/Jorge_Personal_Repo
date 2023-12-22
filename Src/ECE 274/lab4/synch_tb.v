`timescale 1ns / 1ps
module Synch_tb();
    reg Coin_in,Clk,Rst;
    wire Coin_out;
    
    Button_Synchronizer tb(Coin_in,Clk,Rst,Coin_out);
    always begin
        Clk <= 0;
        #100;
        Clk <= 1;
        #100;
    end

initial begin
        Coin_in <= 0;Rst <= 1;
        @(posedge Clk);
        #50 Rst<=0;
        
        @(posedge Clk);
        @(posedge Clk);

        #50 Coin_in <= 1;
        @(posedge Clk);@(posedge Clk);@(posedge Clk);@(posedge Clk);
        #50 Coin_in <= 0;


        @(posedge Clk);@(posedge Clk);
        #50 Coin_in <= 1;
        @(posedge Clk);
        #50 Coin_in <= 0;
        @(posedge Clk);

            
        end
endmodule
