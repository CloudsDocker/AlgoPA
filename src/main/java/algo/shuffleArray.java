package algo;

import java.util.Arrays;
import java.util.Random;

public class shuffleArray {
    int[] ori=new int[0];
    int[] data=new int[0];
    Random random;

    public static void main(String[] args) {
        System.out.println(" shuffle array");
        shuffleArray inst=new shuffleArray(new int[]{1,2,3});
        System.out.println(Arrays.toString(inst.shuffle()));
        System.out.println(Arrays.toString(inst.reset()));
        System.out.println(Arrays.toString(inst.shuffle()));

    }

    public shuffleArray(int[] nums) {
        ori= Arrays.copyOf(nums,nums.length);
        data=nums;
        random=new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return ori;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int len=data.length;
        boolean[] flags=new boolean[len];
        int lef=len;
        for(int i=0;i<len;i++){
            if(flags[i]){
                // this was processed before
                continue;
            }
            if(len==1){
                break;
            }

            int rdm=random.nextInt(len);
            while(data[i]==data[rdm] || flags[rdm]){
                //keep on find random to find one element NOT changed. swap values
                rdm=random.nextInt(len);
            }
            data[i]^=data[rdm];
            data[rdm]^=data[i];
            data[i]^=data[rdm];
            flags[i]=true;
            flags[rdm]=true;
            len-=2;
        }
        return data;
    }

}
