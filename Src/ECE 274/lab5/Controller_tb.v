module Controller_tb();

    reg Clk, Rst, go;
    reg temp1_gt_temp2, i_lt_32;
    wire muxsel, R_en, done;
    wire i_clr, i_ld, temp1_clr, temp1_ld, temp2_clr, temp2_ld, sum_clr, sum_ld;
    
    Controller a1(Clk, Rst, go, temp1_gt_temp2, i_lt_32, done, muxsel, R_en,i_clr, i_ld, temp1_clr, temp1_ld, temp2_clr, temp2_ld, sum_clr, sum_ld);
           
    always
    begin
         Clk <= 0;
         #100;
         Clk <= 1;
         #100;
    end
    initial
    begin 
        Rst <= 1; go <= 0; temp1_gt_temp2 <= 0; i_lt_32 <= 0;
        @(posedge Clk);
        #50 Rst <= 0;
        @(posedge Clk);
        #50 go <= 1;
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        #50 go <= 0; i_lt_32 <= 1;
        @(posedge Clk);
        #50 i_lt_32 <= 0;
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        @(posedge Clk);
        #50 i_lt_32 <= 1;
        @(posedge Clk);
        #50 i_lt_32 <= 0;
        @(posedge Clk);
        @(posedge Clk);
        #50 temp1_gt_temp2 <= 1;
        @(posedge Clk);
        #50 temp1_gt_temp2 <= 0;
    end    
    
endmodule
