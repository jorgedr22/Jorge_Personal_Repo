module PP_3(w,z,Rst,Clk);
    input w,Rst,Clk;
    output reg z;

    reg [3:0]State;
    localparam[3:0] s0=0,s1=1,s2=2,s3=3,s4=4,s5=5,s6=6;

always @(posedge Clk) begin
    if (Rst) begin
        State=s0;
        z=0;
    end
    else begin
        case (State)
            s0:begin
                if (~w) begin
                    State=s0;
                end
                else if (w) begin
                    State=s1;
                end
            end

            s1:begin
                if (~w) begin
                    State=s2;
                end
                else if (w) begin
                    State=s5;
                end
            end 

            s2:begin
                if (~w) begin
                    State=s3;
                end
                else if (w) begin
                    State=s5;
                end                
            end

            s3:begin
                if (~w) begin
                    State=s0;
                end
                else if (w) begin
                    z=1;
                    State=s4;
                end                
            end

            s4:begin
                if (~w) begin
                    z=0;
                    State=s0;
                end
                else if (w) begin
                    z=0;
                    State=s1;
                end                
            end

            s5:begin
                if (~w) begin
                    State=s2;
                end
                else if (w) begin
                    State=s6;
                end                
            end

            s6:begin
                if (~w) begin
                    State=s2;
                end
                else if (w) begin
                    z=1;
                    State=s4;
                end                
            end

            default: begin
                State=s0;
            end
        endcase
    end
end












endmodule
