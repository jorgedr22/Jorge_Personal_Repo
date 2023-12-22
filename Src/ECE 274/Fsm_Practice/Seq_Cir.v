module Seq_Cir(a,b,z,Rst,Clk);
    input a,b,Rst,Clk;
    output reg z;

    reg [3:0]state;
    localparam[3:0] s0=0,s1=1,s2=2,s3=3,s4=4;

    always @(posedge Clk) begin
        if (Rst) begin
            z=0;
            state=s0;
        end
        else begin
            case (state)
                s0:begin
                    if ((a&&b)||(~a&&~b)) begin
                        z=0;
                        state=s1;
                    end
                    else begin
                        z=0;
                        state=s0;
                    end
                end 
                s1:begin
                    if ((a&&b)||(~a&&~b)) begin
                        z=0;
                        state=s2;
                    end
                    else begin
                        z=0;
                        state=s0;
                    end
                end
                s2:begin
                    if ((a&&b)||(~a&&~b)) begin
                        z=0;
                        state=s3;
                    end
                    else begin
                        z=0;
                        state=s0;
                    end
                end
                s3:begin
                    if ((a&&b)||(~a&&~b)) begin
                        z=1;
                        state=s4;
                    end
                    else begin
                        z=0;
                        state=s0;
                    end
                end
                s4:begin
                    if ((a&&b)||(~a&&~b)) begin
                        z=1;
                        state=s4;
                    end
                    else begin
                        z=0;
                        state=s0;
                    end                    
                end
                default:begin
                    z=0;
                    state=s0;
                end 
            endcase
        end
    end
endmodule
