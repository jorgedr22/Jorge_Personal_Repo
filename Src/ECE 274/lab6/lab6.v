`timescale 1ns / 1ps

module Lab6(go, sum, data, done, Clk, Rst);
    input go, Clk , Rst;
    output reg [7:0] sum, data;
    output reg done;
    
    wire [7:0] R_data;
    
    
    parameter [3:0]s0=0,
                   s1=1, 
                   s2=2, 
                   s3=3, 
                   s4=4, 
                   s5=5, 
                   s6=6,
                   s_wait = 7,
                   s_disp = 8,
                   s_wait2 = 9,
                   s_disp2 = 10,
                   s_disp_done = 11;

    
    reg [3:0] state;
    reg [7:0] temp;
    reg R_en, W_en;
    reg [7:0] W_data;
    reg [4:0] i;
    reg [13:0] k;
    
    RegFile16x8 a1(i, i, R_en, W_en, R_data, W_data, Clk, Rst,debug_Reg0,debug_Reg1,debug_Reg2,debug_Reg3,debug_Reg4,debug_Reg5,debug_Reg6,debug_Reg7,debug_Reg8,debug_Reg9,debug_Reg10,debug_Reg11,debug_Reg12,debug_Reg13,debug_Reg14,debug_Reg15);

    always @(posedge Clk) begin
        if(Rst) begin
            state <= s0;
        end
        else begin
            case(state)
            s0: begin
                if(~go)begin
                    state <= s0;
                end
                else if(go) begin
                    i <= 0;
                    done <= 0;
                    sum <= 0;
                    R_en <= 0;
                    W_en <= 0;
                    state <= s1;
                end
            end
            s1: begin
                if(i<16)begin
                    R_en <= 1;
                    state <= s2; 
                end
                else begin
                    R_en <= 0;
                    state <= 6;
                end
            end
            s2: begin
                temp <= R_data;
                state <= s3;
            end            
            s3: begin
                if ((temp>47) && (temp<58))begin
                    W_en <= 1;
                    R_en <= 0;
                    W_data <= (temp - 48);
                    state <= s4;
                end
                else if(~((temp>47) && (temp<58)))begin
                    state <= s5;
                end
            end            
            s4: begin
                sum <= sum + (temp - 48);
                state <= s5;
            end
            s5: begin
                W_en <= 0;
                i <= i + 1;
                state <= s1;
            end            
            s6: begin
                i <= 0;
                k <= 0;
                state <= s_wait;
            end
            s_wait:begin
                if (k < 10000) begin
                    k <= k + 1;
                    state <= s_wait;
                end
                else begin
                    R_en <= 1;
                    done <= 1;
                    state <= s_disp; 
                end
            end
            s_disp:begin
                    k <= 0;
                    data <= R_data;
                    state <= s_wait2;
            end
            s_wait2:begin
                if (k < 10000) begin
                    k <= k + 1;
                    state <= s_wait2;
                end
                else begin
                    state <= s_disp2; 
                end
            end
            s_disp2:begin
                if (i<=15) begin
                    k <= 0;
                    i <= i + 1;
                    data <= R_data;
                    state <= s_wait2;
                end
                else begin
                    state <= s_disp_done;
                end
            end
            s_disp_done:begin
            
            end                                
        endcase
    end
end

endmodule