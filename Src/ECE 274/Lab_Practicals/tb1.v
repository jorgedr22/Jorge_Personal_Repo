`timescale 1ns / 1ps
module tb();
    reg A1_s,A2_s,B1_s,B2_s;
    wire F,G;
    
    Prac_1 test(A1_s,A2_s,B1_s,B2_s,F,G);

    initial 
        begin
            A1_s<=0;A2_s<=0;B1_s<=0;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=0;B1_s<=0;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=0;B1_s<=1;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=0;B1_s<=1;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=1;B1_s<=0;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=1;B1_s<=0;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=1;B1_s<=1;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=0;A2_s<=1;B1_s<=1;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=0;B1_s<=0;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=0;B1_s<=0;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=0;B1_s<=1;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=0;B1_s<=1;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=1;B1_s<=0;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=1;B1_s<=0;B2_s<=1;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=1;B1_s<=1;B2_s<=0;
//                        $display("F=%b   G=%b",F,G); 
            #10  A1_s<=1;A2_s<=1;B1_s<=1;B2_s<=1;
//                        $display("F=%b   G=%b",F,G);       
        end
endmodule
