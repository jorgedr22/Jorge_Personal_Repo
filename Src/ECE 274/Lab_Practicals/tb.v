`timescale 1ns / 1ps
module tb();
    reg b,clk,rst;
    wire w;

     Prac_2 tb(b,w,rst,clk);
     
always 
    begin
        clk=0;
        #100;
        clk=1;
        #100; 
    end

initial 
begin
    
    rst=1;b=1;
    @(posedge clk);
    
    #50 rst=0;b=1;
    @(posedge clk);    
    
    #50 b=0;
    @(posedge clk);     
    @(posedge clk);     
    @(posedge clk);     

    #50 b=1;
    @(posedge clk);

    #50 b=0;
    @(posedge clk);     
    @(posedge clk);     

    #50 b=1;
    @(posedge clk);
    @(posedge clk);

    #50 b=0;
    @(posedge clk);

    #50 b=1;
    @(posedge clk);
    
    #50 b=0;
    @(posedge clk);    
    
     #50 b=1;
    @(posedge clk);
    @(posedge clk);   
    
    #50 b=0;
    @(posedge clk); 
end

endmodule
