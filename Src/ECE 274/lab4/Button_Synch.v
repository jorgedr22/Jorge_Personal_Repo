`timescale 1ns/1ps

module Button_Synchronizer(Coin_in,Clk,Reset,Coin_out);
  input Coin_in,Clk,Reset;
  output reg Coin_out;

  reg [1:0]Button_State;
  parameter [1:0] A=0,B=1,C=2;
  
  always @(posedge Clk)begin
    if (Reset) begin
      Button_State <= A;
    end
    
    else begin
        case(Button_State)
          A:begin
              Coin_out <= 0;            
            if(Coin_in)begin
              Button_State <= B;
            end
            else begin
              Button_State <= A;
            end
          end
  
          B:begin
              Coin_out <= 1;                      
            if(Coin_in)begin
              Button_State <= C;
              end
            else begin
              Button_State <= A;
            end
          end
          
          C:begin
            Coin_out <= 0;            
            if(Coin_in)begin
              Button_State <= C;
            end
            else begin
              Button_State <= A;
            end
          end
        endcase
      end
    end
endmodule
          