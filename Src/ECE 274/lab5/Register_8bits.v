`timescale 1ns / 1ps

//at positive edge of Clk, 
//if Clr = 1, Q = 0;
//else if Ld = 1, Q = I;
//otherwise, Q stays the same

module Register_8bits(Clk, Clr, Ld, I, Q);
    input Clk, Clr, Ld;
    input [7:0] I;
    output reg [7:0] Q;
    
    always @ (posedge Clk)begin
        if (Clr) begin
            Q <= 0;
        end
        else if(Ld) begin
            Q <= I;
        end
        else begin
            Q <= Q;
        end
    end
endmodule
