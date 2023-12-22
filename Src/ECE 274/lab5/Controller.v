module Controller (Clk, Rst, go, temp1_gt_temp2, i_lt_32, done, muxsel, R_en,
                  i_clr, i_ld, temp1_clr, temp1_ld, temp2_clr, temp2_ld, sum_clr, sum_ld);
    input Clk, Rst, go;
    input temp1_gt_temp2, i_lt_32;
    output reg muxsel, R_en, done;
    output reg i_clr, i_ld, temp1_clr, temp1_ld, temp2_clr, temp2_ld, sum_clr, sum_ld;
    
    //declare state and statenext with correct number of bits
    //encode state here. Use 0,1,2, ... ,10 for SA, SB, ..., SJ  
    reg [3:0] state,statenext;
    parameter[3:0] sA=0,sB=1,sC=2,sD=3,sE=4,sF=5,sG=6,sH=7,sI=8,sJ=9;
            
       always @ (posedge Clk)
       begin
          if(Rst == 1) begin
                state <= sA;
          end
          else begin
                state <= statenext;
          end
       end
            
       always @ (*) begin  //* used for any input changes in this block
           muxsel <= 0; R_en <= 0; done <= 0;
           i_clr <= 0; i_ld <= 0; 
           temp1_clr <= 0; temp1_ld <= 0; 
           temp2_clr <= 0; temp2_ld <= 0;
           sum_clr <= 0; sum_ld <= 0;
           //with the above, you can now write the signal 
           //only in the state(s) that you want to set it to 1
           case(state)
			// write your code here
		sA:
            begin
			if(go)
                  begin 
                        statenext<=sB;
                  end
			else 
                  begin 
                        statenext<=sA;
                  end
            end

            sB:
            begin 
                  i_clr<=1;temp1_clr<=1;temp2_clr<=1;sum_clr<=1;statenext<=sC;
            end
            
            sC:
            begin 
                  if(i_lt_32)
                  begin 
                        statenext<=sD; 
                  end
                  else if(~(i_lt_32))
                  begin 
                        statenext<=sJ; 
                  end
            end
            
            sD:
            begin 
                  R_en<=1;temp1_ld<=1;i_ld<=1;statenext<=sE;
            end
            
            sE:
            begin 
                  R_en<=1;temp2_ld<=1;statenext<=sF;
            end
            
            sF:
            begin 
                  if(temp1_gt_temp2)begin
                        statenext<=sG;
                  end
                  else if(~(temp1_gt_temp2))
                  begin
                        statenext<=sH;
                  end 
            end
            
            sG:
            begin
            muxsel<=1;sum_ld<=1;statenext<=sI;
            end
            
            sH:
            begin
            muxsel<=0;sum_ld<=1;statenext<=sI;
            end
            
            sI:
            begin
            i_ld<=1;statenext<=sC;
            end
            
            sJ:
            begin
            done<=1;statenext<=sA;
            end
            
            default: 
            begin 
                  statenext <= sA;
            end   
                                                                            
         endcase
      end
    
endmodule
