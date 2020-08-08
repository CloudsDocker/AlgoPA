package algo;

import java.util.Arrays;

public class SpiralMatrixII {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new SpiralMatrixII().generateMatrix(3)));
    }

    public int[][] generateMatrix(int n) {
        int[][] data=new int[n][n];
        int num=0;
        int row=0;
        int col=0;
        boolean bRowRev=false,bColRev=false;
        while(!(num==col && num==n/2)){
            if(row>=n || col>=n) break;
            if(!bRowRev){
                for(int j=0;j<n;j++){
                    data[row][j]=++num;
                }
            }else{
                for(int j=n-col-2;j>=0;j--){// -2 (not -1)
                    data[n-row-1][j]=++num;
                }
                row++;
            }
            bRowRev=!bRowRev;



            if(!bColRev){
                for(int j=row+1;j<n;j++){
                    data[j][n-col-1]=++num;
                }
            }else{
                for(int j=n-col-2;j>0;j--){
                    data[j][col]=++num;
                }
                col++;
            }
            bColRev=!bColRev;
        }
        return data;

    }
}
