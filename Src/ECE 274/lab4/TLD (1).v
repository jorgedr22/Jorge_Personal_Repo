`timescale 1ns / 1ps

module TopLevelDesign(BTNL,BTNC,BTNR,BTNU,CLK100MHZ,LED,CA,CB,CC,CD,CE,CF,CG,AN);
    input BTNU,BTNL,BTNC,BTNR;
    input CLK100MHZ;
    output CA,CB,CC,CD,CE,CF,CG;
    output [7:0]AN;
    output [0:0]LED;
    
    wire Q_out,D_out,N_out;
    wire ClkOut;
    wire [5:0]Change;
    
    ClkDiv m1(CLK100MHZ, 0, ClkOut);
    VendingMachine m2(Q_out,D_out,N_out,ClkOut,BTNU,LED[0],Change);
    Button_Synchronizer m3(BTNL,ClkOut,BTNU,N_out);
    Button_Synchronizer m4(BTNC,ClkOut,BTNU,D_out);
    Button_Synchronizer m5(BTNR,ClkOut,BTNU,Q_out);    
    TwoDigitDisplay m6(CLK100MHZ, {1'b0, Change}, CA,CB,CC,CD,CE,CF,CG,AN);
    

endmodule
