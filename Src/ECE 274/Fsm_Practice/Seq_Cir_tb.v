`timescale 1ps/1ps
`include "Seq_Cir.v"

module Seq_Cir_tb();
    reg a,b,Clk,Rst;
    wire z;

    Seq_Cir tb(a,b,z,Rst,Clk);  

always 
    begin
        Clk = 0;
        #100;
        Clk = 1;
        #100;
    end

initial begin
    $dumpfile("Seq_Cir_tb.vcd");
    $dumpvars(0,Seq_Cir_tb);

    #50 Rst=1;
    @(posedge Clk);

    #50 Rst=0;a=0;b=1;
    @(posedge Clk);

    #50 Rst=0;a=1;b=1;
    @(posedge Clk);
    
    #50 Rst=0;a=1;b=1;
    @(posedge Clk);

    #50 Rst=0;a=0;b=0;
    @(posedge Clk);

    #50 Rst=0;a=1;b=1;
    @(posedge Clk);

    #50 Rst=1;a=1;b=0;
    @(posedge Clk);

    #50 Rst=0;a=1;b=1;
    @(posedge Clk);

    #50 Rst=0;a=0;b=0;
    @(posedge Clk);

    #50 Rst=0;a=0;b=0;
    @(posedge Clk);

    #50 Rst=0;a=0;b=0;
    @(posedge Clk);

    #50 Rst=0;a=1;b=1;
    @(posedge Clk);

    #50 Rst=0;a=1;b=1;
    @(posedge Clk);

    #50 Rst=0;a=0;b=1;
    @(posedge Clk);

    $display("Test Complete!");
end

endmodule
