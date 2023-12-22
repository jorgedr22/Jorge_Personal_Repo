`timescale 1ns / 1ps
module Prac_1(A1,A2,B1,B2,F,G);
    input A1,A2,B1,B2;
    output reg F,G;
    
    always @(A1,A2,B1,B2)begin
        if( (A1&B1) | ((~A1)&(~B1)) | (A1&(~A2)&B2) | ((~A1)&A2&(~B2)) )begin
            F<=1;
        end
        else begin F<=0; end
        
        if ((A1&A2&B1) | (A1&B1&B2))begin
            G<=1;
        end
        else begin G<=0; end
    end
endmodule
