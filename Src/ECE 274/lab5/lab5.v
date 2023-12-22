`timescale 1ns / 1ps

module lab5(Clk, Rst, go, sum, done);
    input Clk, Rst, go;
    output [11:0] sum;
    output done;

////////////////////////////////////
    //declare all internal signals used in this code here
    //make sure that you use correct number of bits
    wire [7:0]temp1,temp2,R_data,D1,D2,S;
    wire [11:0] S2;
    wire [5:0] i, sumout;
    wire Co, AltB;

    //Controller is already instantiated below with all control signals that you can use in your code.     
    wire muxsel, R_en, i_clr, i_ld;
    wire temp2_clr, temp2_ld;
    wire temp1_clr, temp1_ld;
    wire sum_clr, sum_ld;
    wire temp1_gt_temp2, i_lt_32;
    
    Controller c1(Clk, Rst, go, temp1_gt_temp2, i_lt_32, done, muxsel, R_en,
                  i_clr, i_ld, temp1_clr, temp1_ld, temp2_clr, temp2_ld, sum_clr, sum_ld);
    
    RegisterFile_32_8 c2(i[4:0], 5'b0, R_en, 1'b0, R_data, 8'b0, Clk, Rst);
    
    Comparator_6bits c3(i, 32, i_lt_32, AeqB, AgtB); //eq and gt
    Comparator_8bits c4(temp1, temp2, AltB, AeqB, temp1_gt_temp2); //lt and eq
    
    Register_6bits c5(Clk, i_clr, i_ld, sumout, i);
    Register_8bits c6(Clk, temp1_clr, temp1_ld, R_data, temp1);
    Register_8bits c7(Clk, temp2_clr, temp2_ld, R_data, temp2);
    Register_12bits c8(Clk, sum_clr, sum_ld, S2, sum);
    
    mux8bits_2x1 c9(muxsel, temp1, temp2, D1);
    mux8bits_2x1 c10(muxsel, temp2, temp1, D2);
    
    Adder_6bits c11(i, 1, 1'b0, sumout, Co); //Co
    Adder_12bits c12(sum, {4'b0,S}, 1'b0, S2, Co); //Co
    
    Subtractor_8bits c13(D1,D2,S);
    
    /*
    For waveform, display: (show up to 21 us for done = 1)
    -CLK
    -Rst
    -go
    -done
    -temp1[7:0]
    -temp2[7:0]
    -temp1_gt_temp2
    -S2[11:0]
    -sum[11:0]
    -i[5:0]
    -R_Addr[4:0]
    -R_en
    -R_Data[7:0]
    */

endmodule
