`timescale 1ns / 1ps

module Lights_tb();
    reg L_t, R_t,Clk_t,Rst_t;
    wire Up_t,Left_t,Down_t,Right_t ;
    integer i;
    
  Lights Lights_1(Up_t,Left_t,Down_t,Right_t, L_t, R_t, Clk_t, Rst_t);

    always begin
    Clk_t <= 0;
    #100;
    Clk_t <= 1;
    #100;
    end
      
    initial begin
    L_t<=0;R_t<=0;Rst_t<=1;
    
    #50 L_t<=1;R_t<=0;Rst_t<=0;
    for(i=0;i<8;i=i+1)begin
    @(posedge Clk_t);
    end
     
    #50 L_t<=0;R_t<=1;Rst_t<=0;
    for(i=0;i<8;i=i+1)begin
    @(posedge Clk_t);
    end    
    
    #50 L_t<=0;R_t<=0;Rst_t<=1;
    @(posedge Clk_t);
    @(posedge Clk_t);
    @(posedge Clk_t);
    
    
    #50 L_t<=1;R_t<=1;Rst_t<=0;
    for(i=0;i<8;i=i+1)begin
    @(posedge Clk_t);
    

    end
        
    end
endmodule