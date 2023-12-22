`timescale 1ns / 1ps

module lab1_tb();
    reg A_t, B_t, C_t, D_t;
    wire I0_t, I1_t, I2_t;
    
    lab1 lab1_1(A_t, B_t, C_t, D_t, I0_t, I1_t, I2_t);
        initial
        begin
        
        //case default
        A_t <= 0; B_t <= 0; C_t <= 0; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 1
        A_t <= 0; B_t <= 1; C_t <= 0; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 2
        A_t <= 0; B_t <= 0; C_t <= 1; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 3
        A_t <= 0; B_t <= 0; C_t <= 0; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);
          
        //case 0
        A_t <= 1; B_t <= 0; C_t <= 0; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 1
        A_t <= 0; B_t <= 1; C_t <= 0; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 2
        A_t <= 0; B_t <= 0; C_t <= 1; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 3
        A_t <= 0; B_t <= 0; C_t <= 0; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 4
        A_t <= 1; B_t <= 1; C_t <= 0; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 5
        A_t <= 1; B_t <= 0; C_t <= 1; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 6
        A_t <= 1; B_t <= 0; C_t <= 0; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 7
        A_t <= 0; B_t <= 1; C_t <= 1; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 8
        A_t <= 0; B_t <= 1; C_t <= 0; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 9
        A_t <= 0; B_t <= 0; C_t <= 1; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 10
        A_t <= 1; B_t <= 1; C_t <= 1; D_t <= 0;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 11
        A_t <= 1; B_t <= 1; C_t <= 0; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 12
        A_t <= 1; B_t <= 0; C_t <= 1; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 13
        A_t <= 0; B_t <= 1; C_t <= 1; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        //case 14
        A_t <= 1; B_t <= 1; C_t <= 1; D_t <= 1;
        #1 $display("I2 = %b, I1 = %b, I0 = %b",I2_t,I1_t,I0_t);

        end 
        
endmodule
