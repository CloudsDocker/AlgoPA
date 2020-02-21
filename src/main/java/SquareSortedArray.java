package algo;

import java.util.Arrays;

public class SquareSortedArray {
    public static void main(String[] args) {
        int[] aryInput=  new int[]{-20,-4,-1,0,3,10};
        int[] aryOutput = new int[]{0,1,9,16,100};
        System.out.println("square minus 3 is: "+Math.pow(-3,2));
        int[] aryOut = squareArray(aryInput);
        System.out.println("output is :" + Arrays.toString(aryOut));
        System.out.println("expected true:"+ Arrays.equals(aryOutput,aryOut));
    }
    static int[] squareArray(int[] input){
        int[] aryOutput =new int[input.length];
        // for sake of runtime performance, to have only ONE linear loop
        // so we keep two pointers to save one more loop
        int left=0, right=input.length-1;
        // as expected output is also increasing, so loop from right to left
        for (int i = input.length-1;i>=0; i--) {
            double leftValue= Math.pow(input[left],2);
            double rightValue= Math.pow(input[right],2);
            if(leftValue<rightValue){
                aryOutput[i]=(int)rightValue;
                right--;
            }else {
                aryOutput[i]=(int)leftValue;
                left++;
            }
        }

        return aryOutput;
    }
}
