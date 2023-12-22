`timescale 1ns / 1ps

module EX3 (go,done,min,sum,Clk,Rst);
    input go, Clk, Rst;
    output reg done;
    output reg [7:0] min;
    output reg [12:0]sum;

    localparam [2:0] s0=0,s1=1,s2=2,s3=3,s4=4,s5=5,s6=6,s7=7;

    reg [2:0] state;
    reg [7:0] temp;
    reg W_en,R_en;
    reg [4:0] i;
    reg [7:0] W_Data;
    wire [7:0] R_Data;

    Register16_8 A1(i, i, R_en, W_en, R_Data, W_Data, Clk, Rst,debug_Reg0,debug_Reg1,debug_Reg2,debug_Reg3,debug_Reg4,debug_Reg5,debug_Reg6,debug_Reg7,debug_Reg8,debug_Reg9,debug_Reg10,debug_Reg11,debug_Reg12,debug_Reg13,debug_Reg14,debug_Reg15);

    always @(posedge Clk) begin
        if(Rst)begin
            done <= 0;
            state <= s0;
        end
        else begin
            case(state)
                s0:begin
                        done <= 0;
                        if (go) begin
                        sum <= 0;
                        temp <= 0;
                        min <= 255;
                        i <= 0;
                        W_en <= 0;
                        R_en <= 0;
                        state <= s1;
                        end
                    else state <= s0;
                end
                s1:begin
                    if (i<16) begin
                        W_en <= 0;
                        R_en <= 1;
                        state <= s2;
                    end
                    else begin
                        W_en <= 0;
                        R_en <= 0;
                        state <= s7;
                    end
                end    
                s2:begin
                    temp <= R_Data;
                    state <= s3;
                end
                s3:begin
                    if (temp < min) begin
                        min <= temp;
                        state <= s4;
                    end
                    else if (temp >= 128) begin
                        W_en <= 1;
                        R_en <= 0;
                        W_Data <= temp - 128;
                        state <= s5;
                    end
                    else state <= s6;
                end
                s4:begin
                    if (temp >= 128) begin
                        W_en <= 1;
                        R_en <= 0;
                        W_Data <= temp - 128;
                        state <= s5;
                    end
                    else state <= s6;
                end
                s5:begin
                    W_en <= 0;
                    state <= s6;
                end
                s6:begin
                    sum <= sum + temp;
                    i <= i + 1;
                    state <= s1;
                end
                s7:begin
                    done <= 1;
                    state <= s0;
                end
                default:
                    state <= s0;
            endcase
       end
    end

endmodule
