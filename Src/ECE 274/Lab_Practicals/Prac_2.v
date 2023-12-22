`timescale 1ns / 1ps
module Prac_2(b,w,rst,clk);
    input b,rst,clk;
    output reg w;
    
    reg [4:0]state;
    localparam[4:0] s0=0, s1=1, s2=2, s3=3, s4=4, s5=5;
    
    
    always@(posedge clk)begin
        if(rst)
        begin
            w=0;
            state=s0;
        end
        else 
        begin
            case (state)
            s0:
            begin
                if(~b)
                begin
                    state=s1;
                end
                else if(b)
                begin
                    state=s0;
                end
            end
            s1:
            begin
                if(b)
                begin
                    state=s2;
                end
                else if(~b)
                begin
                    state=s4;
                end
            end        
            s2:
            begin
                if(b)
                begin
                    state=s0;
                end
                else if(~b)
                begin
                    w=1;
                    state=s3;
                end
            end
            s3:
            begin
                if(b)
                begin
                    w=0;
                    state=s0;
                end
                else if(~b)
                begin
                    w=0;
                    state=s4;
                end
            end        
            s4:begin
                if(b)
                begin
                    w=1;
                    state=s5;
                end
                else if(~b)
                begin
                    w=0;
                    state=s4;
                end
                    
            end
            s5:
            begin
                if(b)
                begin
                    w=0;
                    state=s0;
                end
                else if(~b)
                begin
                    w=1;
                    state=s3;
                end
            end        
            default:
            begin
                w=0;
                state=0;
            end            
            endcase
        end
    end
endmodule
