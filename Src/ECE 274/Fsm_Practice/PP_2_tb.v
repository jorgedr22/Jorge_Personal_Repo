`timescale 1ns / 1ps
`include "PP_2.v"

module PP_2_tb();
reg B,Rst,Clk;
wire w;

always begin
        Clk = 0;
        #100;
        Clk = 1;
        #100;
    end

Seq_Det tb(B,Rst,Clk,w);

initial begin
    $dumpfile("PP_2_tb.vcd");
    $dumpvars(0,PP_2_tb);

    Rst=1;B=0;
    //$display("%d",B);
    
    @(posedge Clk);
    #50 Rst=0;B=1;
    //$display("%d",B);

    @(posedge Clk);
    #50 Rst=0;B=1;
    //$display("%d",B);
    
    @(posedge Clk);
    #50 Rst=0;B=0;
    //$display("%d",B);
        
    @(posedge Clk);
    //$display("%d",B);
    
    @(posedge Clk);
    #50 Rst=0;B=1;
    //$display("%d",B);
       
    @(posedge Clk);
    #50 Rst=0;B=0;
    //$display("%d",B);
    
    @(posedge Clk);
    #50 Rst=0;B=1;    
    //$display("%d",B);
    
    $display("Tests done.Ready to simulate.");
end
endmodule
