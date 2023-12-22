module Seq_Det(B,Rst,Clk,w);
input B,Rst,Clk;
output reg w;

    reg [3:0]State;
    localparam[3:0] S0=0,S1=1,S2=2,S3=3,S4=4,S5=5;

    always @(posedge Clk) begin
        if(Rst)begin
            State=S0;
            w=0;
            end
        else begin
            case (State)
                S0:begin
                    w=0;
                    if (~B) begin
                        State=S0;
                    end
                    else if (B) begin
                        State=S1;
                    end
                end
                
                S1:begin
                    w=0;
                    if (B) begin
                        State=S2;
                    end
                    else if (~B) begin
                        State=S4;
                    end
                end

                S2:begin
                    w=0;
                    if (B) begin
                        State=S2;
                    end
                    else if (~B) begin
                        w=1;
                        State=S3;
                    end
                end

                S3:begin
                    if (B) begin
                        State=S1;
                    end
                    else if (~B) begin
                        State=S0;
                    end
                end

                S4:begin
                    w=0;
                    if (B) begin
                        w=1;
                        State=S5;
                    end
                    else if (~B) begin
                        State=S0;
                    end
                end

                S5:begin
                    if (B) begin
                        State=S1;
                    end
                    else if (~B) begin
                        State=S0;
                    end
                end

                default: begin
                    State=S0;
                end
            endcase
        end
    end
endmodule
