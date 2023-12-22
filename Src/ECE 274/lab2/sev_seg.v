`timescale 1ns / 1ps

module SevenSegment(SW,CA,CB,CC,CD,CE,CF,CG);
input [3:0] SW;
output reg CA, CB, CC, CD, CE, CF, CG;
    always @(SW)
      begin
        case (SW)
        4'b0000: begin CA <= 0; CB <= 0; CC <= 0; CD <= 0; CE <= 0; CF <= 0; CG <= 1; end
        4'b0001: begin CA <= 1; CB <= 0; CC <= 0; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end
        4'b0010: begin CA <= 0; CB <= 0; CC <= 1; CD <= 0; CE <= 0; CF <= 1; CG <= 0; end
        4'b0011: begin CA <= 0; CB <= 0; CC <= 0; CD <= 0; CE <= 1; CF <= 1; CG <= 0; end
        4'b0100: begin CA <= 1; CB <= 0; CC <= 0; CD <= 1; CE <= 1; CF <= 0; CG <= 0; end
        4'b0101: begin CA <= 0; CB <= 1; CC <= 0; CD <= 0; CE <= 1; CF <= 0; CG <= 0; end
        4'b0110: begin CA <= 0; CB <= 1; CC <= 0; CD <= 0; CE <= 0; CF <= 0; CG <= 0; end
        4'b0111: begin CA <= 0; CB <= 0; CC <= 0; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end
        4'b1000: begin CA <= 0; CB <= 0; CC <= 0; CD <= 0; CE <= 0; CF <= 0; CG <= 0; end
        4'b1001: begin CA <= 0; CB <= 0; CC <= 0; CD <= 0; CE <= 1; CF <= 0; CG <= 0; end 
        4'b1010: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end
        4'b1011: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end
        4'b1100: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end 
        4'b1101: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end 
        4'b1110: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end 
        4'b1111: begin CA <= 1; CB <= 1; CC <= 1; CD <= 1; CE <= 1; CF <= 1; CG <= 1; end 
        endcase
      end
endmodule
