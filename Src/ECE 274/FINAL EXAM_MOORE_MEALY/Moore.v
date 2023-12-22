module Moore (b,x,Rst,Clk);
    input b,Rst,Clk;
    output reg x;

    parameter [2:0] s0=0,s1=1,s2=2,s3=3;
    reg [2:0] state,n_state;

    always @(posedge CLK) 
    begin
        if(Rst) begin
            state <= s0; //start at s0
        end
        else state <= n_state; 
    end
    always @(state, b)
    begin
        x <= 0; // output is 0 
        case(state)
            s0:begin
                if(b) n_state <= s1;
                else n_state <= s0;
            end
            s1:begin
                if (~b) n_state <= s2;
                else n_state <= s1;
            end
            s2:begin
                if (b) n_state <= s3;
                else n_state <= s0;
            end
            s3:begin
                x <= 1; //when state s3 is reached x = 1
                if (b) n_state <= s1;
                else n_state <= s0;
            end
        endcase
    end
endmodule