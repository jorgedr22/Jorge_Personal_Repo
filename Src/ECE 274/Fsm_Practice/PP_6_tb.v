`timescale 1ps/1ps
`include "PP_6.v"

module testbench();
    reg a,b,Rst,Clk;
    wire y;

    PP_6 tb(a,b,y,Rst,Clk);


always 
    begin
        Clk = 0;
        #100;
        Clk = 1;
        #100;
    end

initial begin
    $dumpfile("PP_6_tb.vcd");
    $dumpvars(0,PP_6_tb);

    Rst=1;a=0;b=0;
    @(posedge Clk);

    #50 Rst=0;a=0;b=1;
    @(posedge Clk);

    #50 a=1;
    @(posedge Clk);

    #50 b=1;
    @(posedge Clk);

    @(posedge Clk);
    
    $display("Test Complete!");
end


endmodule
