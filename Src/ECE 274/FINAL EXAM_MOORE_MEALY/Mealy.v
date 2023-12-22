module Moore (b,x,Rst,Clk);
    input b,Rst,Clk;
    output reg x;

    parameter [2:0] s0=0,s1=1,s2=2;
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
            s2:begin // up to this point sequence 10 has been detected
                if (b) // if b is 1, sequence is now 101, output is 1 and goes back to s0
                begin
                    n_state <= s0;
                    x <= 1;
                end
                else n_state <= s0; // else output is 0 and goes back to s0
            end
        endcase
    end
endmodule