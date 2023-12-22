`timescale 1ns / 1ps

module Lights(Up,Left,Down,Right, L, R, Clk, Rst);
    input L, R;
    input Clk, Rst;
    output reg Up,Left,Down,Right;

  parameter [2:0] s0=0,s1=1,s2=2,s3=3,s4=4,s5=5,s6=6;
  reg [2:0] State;
  
   always @(posedge Clk) begin
   
         if (Rst) begin
           State = s0;
         end
          else begin
    
            case (State) 
            s0: begin
             if(L&R)begin
                State = s4;
             end
             else if (L&~R) begin
                 State = s1;
             end
             else if (~L&R) begin
                 State = s1;
             end
             else begin
             State = s0;
             end
            end
          
            s1: begin
              if(L & ~R) begin
                State = s2;
                end
              else if (~L & R)begin
                State = s5;
                end
              else begin
                State = s0;
                end
              end
              
            s2: begin
              if(L & ~R) begin
                State = s3;
                end
              else begin
                State = s0;
                end
              end
              
            s3: begin
              if(L & ~R) begin
                State = s4;
                end
               else begin
                State = s0;
                end
                end
              
            s4: begin
               if(L & ~R) begin
                State = s1;
                end
               else begin
                State = s0;
                end
              end
              
             s5: begin
               if(~L & R) begin
                State = s6;
                end
                else begin
                State = s0;
                end
              end  
              
              s6: begin
               if(~L & R) begin
                State = s4;
                end
                end
                 
            default: begin
              State = s0;
            end
        endcase
    
  end
  
                case (State)
                  s0: begin
                    Up=1;Left=1;Down=1;Right=1;
                    end
                  s1: begin
                    Up=0;Left=1;Down=1;Right=1;
                  end
                  s2: begin
                   Up=0;Left=0;Down=1;Right=1;
                  end 
                  s3: begin
                    Up=0;Left=0;Down=0;Right=1;
                  end 
                  s4: begin
                    Up=0;Left=0;Down=0;Right=0;
                  end
                  s5: begin
                    Up=0;Left=1;Down=1;Right=0;
                    end
                  s6: begin
                    Up=0;Left=1;Down=0;Right=0;
                  end
                 default: begin 
                    Up=1;Left=1;Down=1;Right=1;
                 end
           endcase
        end
endmodule