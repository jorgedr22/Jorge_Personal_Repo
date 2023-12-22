`timescale 1ns / 1ps
module tb();
reg go,Clk,Rst;
wire done;
wire [7:0] min;
wire [12:0]sum;

    EX3 A1(go,done,min,sum,Clk,Rst);

    always 
        begin
            Clk<=0;
            #200;
            Clk<=1;
            #200; 
        end

    initial
        begin
            Rst<=1;go<=0;
            @(posedge Clk);
            
            #50 Rst<=0;
            @(posedge Clk);
            @(posedge Clk);
            
            #50 go<=1;
            @(posedge Clk);
            #50 go<=0;

           
        end
    
endmodule
