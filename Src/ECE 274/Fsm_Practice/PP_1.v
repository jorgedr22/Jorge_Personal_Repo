module PP_1(x,y,Rst,Clk,q);
input x,y,Rst,Clk;
output reg q;

    reg [3:0]State;
    localparam[3:0] SA=0,SB=1,SC=2,SD=3;

always@(posedge Clk)
begin
    if(Rst)begin
        State=SA;
        q=1;
    end
    
    else begin
        case(State)
            SA:begin
                q=1;
                if(x||y)begin
                    State=SB;
                end
                else if((~x)&(~y))begin
                    State=SC;
                end            
            end
            
            SB:begin
                q=0;
                if(~y)begin
                    State=SB;
                end
                else if(y)begin
                    State=SC;
                end
            end
            
            SC:begin
                q=0;
                if((~x)&y)begin
                    State=SC;
                end
                else if(x&y)begin
                    State=SA;
                end
                else if(~y)begin
                    State=SD;
                end
            end
            
            SD:begin
                q=1;
                State=SA;
            end
            
            default:begin
                State=SA;
            end
            
            endcase
        end
end
endmodule
