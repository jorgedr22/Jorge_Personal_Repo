`timescale 1ns/1ps

module VendingMachine(Q,D,N,Clk,Reset,Candy,Change);
  input Q,D,N,Clk,Reset;
  output reg Candy;
  output reg[5:0]Change;
  
  reg [3:0] State;
  localparam [3:0]cent00=0,cent05=1,cent10=2,cent15=3,cent20=4,cent25=5,cent30=6,cent35=7,cent40=8,cent45=9;

  always @(posedge Clk)begin
      if(Reset) begin
          State <= cent00;
          Candy <= 0;
          Change <= 0;
      end
      else begin
        case(State)
            cent00: begin
              Candy <= 0;
              Change <= 0;
              if((Q)&(~D)&(~N))begin
                State <= cent25;
              end
              else if((~Q)&(D)&(~N)) begin
                State <= cent10;
              end
              else if((~Q)&(~D)&(N)) begin
                State <= cent05;
              end
              else begin
              State <= cent00;
              end
            end
            
            cent05: begin
              Candy <= 0;
              Change <= 5;
              if((Q)&(~D)&(~N))begin
                State <= cent30;
              end
              else if((~Q)&(D)&(~N))begin
                State <= cent15;
              end
              else if((~Q)&(~D)&(N))begin
                State <= cent10;
              end
              else begin
                State <= cent05; 
               end
            end
            
            cent10: begin
              Candy <= 0;
              Change <= 10;
              if((Q)&(~D)&(~N))begin
                State <= cent35;
              end
              else if((~Q)&(D)&(~N))begin
                State <= cent20;
              end
              else if((~Q)&(~D)&(N))begin
                State <= cent15;
              end
              else begin
                State <= cent10;
              end
            end
            
            cent15: begin
              Candy <= 0;
              Change <= 15; 
              if((Q)&(~D)&(~N))begin
                State <= cent40;
              end
              else if((~Q)&(D)&(~N))begin
                State <= cent25;
              end
              else if((~Q)&(~D)&(N))begin
                State <= cent20;
              end
              else begin
                State <= cent15;
              end
            end
            
            cent20: begin
              Candy <= 0;
              Change <= 20;
              if((Q)&(~D)&(~N))begin
                State <= cent45;
              end
              else if((~Q)&(D)&(~N))begin
                State <= cent30;
              end
              else if((~Q)&(~D)&(N))begin
                State <= cent25;
              end
              else begin
                State <= cent20;
               end
            end
            
            cent25: begin
              Candy <= 1;
              Change <= 0;
              State <= cent25;
            end
            
            cent30:begin
              Candy <= 1;
              Change <= 5;            
              State <= cent30;
            end
            
            cent35:begin
              Candy <= 1;
              Change <= 10;            
              State <= cent35;          
            end
            
            cent40:begin
              Candy <= 1;
              Change <= 15;
              State <= cent40;  
            end 
             
            cent45:begin
              Candy <= 1;
              Change <= 20;            
              State <= cent45;           
            end
                      
          endcase
        end
     end
endmodule 