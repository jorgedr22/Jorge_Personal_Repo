`timescale 1ns / 1ps
module VM_tb();
    reg Q_t,D_t,N_t,Clk,Rst;
    wire Candy;
    wire [5:0]Change;
    integer i;
    
    VendingMachine tb(Q_t,D_t,N_t,Clk,Rst,Candy,Change);

    always begin
        Clk <= 0;
        #100;
        Clk <= 1;
        #100;
    end

initial begin
        Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        #50 Rst<=0;
        
        //more than one coin
        #50 N_t<=1;D_t<=1;
        @(posedge Clk);
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        //sum of 25 cetns
        #50 N_t<=1;
        for(i=0;i<5;i=i+1)begin
        @(posedge Clk);
        end
        
        @(posedge Clk);@(posedge Clk);        
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        #50 D_t<=1;
        for(i=0;i<3;i=i+1)begin
        @(posedge Clk);
        end
        
        @(posedge Clk);@(posedge Clk);        
        
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        #50 D_t<=1;
        @(posedge Clk);
        #50 Q_t<=1;
        @(posedge Clk);

        @(posedge Clk);@(posedge Clk);        
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        #50 N_t<=1;
        @(posedge Clk);
        #50 D_t<=1;
        @(posedge Clk);
        #50 Q_t<=1;
        @(posedge Clk);

        @(posedge Clk);@(posedge Clk);        
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        #50 D_t<=1;
        @(posedge Clk);
        @(posedge Clk);
        #50 Q_t<=1;
        @(posedge Clk);

        @(posedge Clk);@(posedge Clk);        
        
        #50 Q_t<=0;D_t<=0;N_t<=0;Rst<=1;
        @(posedge Clk) Rst<=0;
        
        end
endmodule
