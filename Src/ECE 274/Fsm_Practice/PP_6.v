module PP_6(a,b,y,Rst,Clk);
    input a,b,Rst,Clk;
    output reg y;

    reg [2:0]state;
    localparam [2:0] A=0,B=1,C=2,D=3;

always @(posedge Clk) begin
    if (Rst) begin
        y=0;
        state=A;
    end
    else begin
        case (state)
            A:begin
                if ((~a)&&b) begin
                    y=1;
                    state=B;
                end
                else if (~b) begin
                    y=1;
                    state=C;
                end
                else if (a) begin
                    y=0;
                    state=A;
                end
                else begin
                    state=A;
                end
            end 
            B:begin
                if (a) begin
                    y=1;
                    state=C;
                end
                else if (~a) begin
                    y=1;
                    state=B;
                end
                else begin
                    state=B;
                end
            end
            C:begin
                if (b) begin
                    y=0;
                    state=D;
                end
                else begin
                    state=C;
                end
            end
            D:begin
                y=0;
                state=A;
            end
            default: begin
                y=0;
                state=A;
            end
        endcase
    end
end
endmodule
