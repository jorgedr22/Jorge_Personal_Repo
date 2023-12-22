`timescale 1ns / 1ps

module RTL_insertionsort(Clk, Rst, go, done);
    input Clk, Rst, go;
    output reg done;
    reg [3:0] state, nextstate;

    parameter s0 = 0, s1 = 1, s2 = 2, s3 = 3;
    parameter s4 = 4, s5 = 5, s6 = 6, s7 = 7;
    parameter s8 = 8, s9 = 9, s10 = 10, sdone = 11;

    reg RSel, WSel, WDataSel;
    reg [3:0] i;
    reg [2:0] j;
    reg [7:0] temp;
    reg R_en, W_en;

    wire [7:0] R_Data, W_Data;
    wire [2:0] R_Addr, W_Addr;

    RegisterFile_8_8 Reg(R_Addr, W_Addr, R_en, W_en,R_Data, W_Data, Clk, Rst);

    assign R_Addr = (RSel == 1)? j:j-1;
    assign W_Addr = (WSel == 1)? j:j-1;
    assign W_Data = (WDataSel == 1)? R_Data:temp;

    always @(posedge Clk)begin
        if(Rst == 1)
            state <= s0;
        else
            state <= nextstate;
        case(state)
            s1: begin i <= 1; end
            s3: begin j <= i; end
            s5: begin temp <= R_Data; end
            s9: begin j <= j-1; end
            s10: begin i <= i + 1; end
        endcase
    end

    always @(*) begin
    //make all outputs to 0
        done <= 0;RSel <= 0; WSel <= 0; WDataSel <= 0;
        R_en <= 0;W_en <= 0;
        case(state)
            s0: begin
            if(go) nextstate <= s1;
            else nextstate <= s0;
            end
            s1: begin
                nextstate <= s2;
            end
            s2: begin
                if(i<8) nextstate <= s3;
                else nextstate <= sdone;
            end
            s3: begin
                nextstate <= s4;
            end
            s4: begin
                if(j > 0) nextstate <= s5;
                else nextstate <= s10;
            end
            s5: begin
                nextstate <= s6;
                R_en <= 1;
                RSel <= 1;
            end
            s6: begin
                R_en <= 1;
                RSel <= 0;
                if(temp < R_Data) nextstate <= s7;
                else nextstate <= s9;
            end
            s7: begin
                R_en <= 1;
                RSel <= 0;
                W_en <= 1;
                WSel <= 1;
                WDataSel <= 1;
                nextstate <= s8;
            end
            s8: begin
                W_en <= 1;
                WSel <= 0;
                WDataSel <= 0;
                nextstate <= s9;
            end
            s9: begin
                nextstate <= s4;
            end
            s10: begin
                nextstate <= s2;
            end
            sdone: begin
                done <= 1;
                nextstate <= s0;
            end
            default:nextstate <= s0;
        endcase
    end
endmodule
