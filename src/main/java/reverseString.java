package algo;

import java.util.Arrays;

public class reverseString {
    public static void main(String[] args) {
//        char[] ary = new char[]{'h','e','l','l','o'};
        char[] ary = new char[]{'h','o','l','d'};
        reverseString(ary);
    }

    public static void reverseString(char[] s) {
        int len = s.length; //[!] s is array , "length" is a property rather than a method call
        char[] data = s.clone();    //immutable char array
        for(int idx=0;idx<(len/2);idx++){
            int left=idx, right = len-idx-1;
            data[left] ^= data[right];  // in-place swap, save space
            data[right] ^= data[left];
            data[left] ^= data[right];
        }
        System.out.println("data before transform = " + Arrays.toString(s));
        System.out.println("data after transform = " + Arrays.toString(data));
    }
}

//class Solution {
//    public void reverseString(char[] s) {
//        int len = s.length();
//        for(int idx=0;idx<n/2;idx++){
//
//            s[idx] ^= s[idx]
//        }
//    }
//}
