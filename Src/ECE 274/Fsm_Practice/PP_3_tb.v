`timescale 1ps/1ps 
`include "PP_3.v"

module PP_3_tb();
    reg w,Rst,Clk;
    wire z;

PP_3 tb(w,z,Rst,Clk);    

always 
    begin
        Clk = 0;
        #100;
        Clk = 1;
        #100;
    end

initial begin
    $dumpfile("PP_3_tb.vcd");
    $dumpvars(0,PP_3_tb);

    Rst=1;w=0;
    @(posedge Clk);

    #50 Rst=0;w=1;
    @(posedge Clk);
    @(posedge Clk);
    @(posedge Clk);
    @(posedge Clk);

    #50 w=0;
    @(posedge Clk);
    @(posedge Clk);
    #50 w=1;
    @(posedge Clk);

    $display("Tests done.Ready to simulate.");
end
endmodule
