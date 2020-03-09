package algo;

public class NumberToWord {
    /*
    273. Integer to English Words
Hard

806

2279

Add to List

Share
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     */

//    public String numberToWords(int num) {
//
//    }
public static void main(String[] args) {
    System.out.println("numer to English:"+new NumberToWord().numberToWords(7654));
}


//    class Solution {
        int[] store = new int[] {1000000000, 1000000, 1000, 100, 90,80,70,60,50,40,30,20, 19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        String[] storeRep = new String[] {"Billion", "Million", "Thousand", "Hundred", "Ninety","Eighty","Seventy","Sixty","Fifty","Forty","Thirty","Twenty", "Nineteen","Eighteen","Seventeen","Sixteen","Fifteen","Fourteen","Thirteen","Twelve","Eleven","Ten","Nine","Eight","Seven","Six","Five","Four","Three","Two","One"};
        public String numberToWords(int num) {
            if(num == 0){
                return "Zero";
            }
            int idx = getIndex(num); // current biggest radix
            int nextNum = num%store[idx]; // get reminding number
            String prefix = "";
            if(idx<=3){ // means need to comibnation, while >3 means one-one mapping among number and English word
                prefix = numberToWords(num/store[idx]); //recursively call numberToWords
                prefix+= " ";
            }
            if(nextNum == 0){
                return prefix+storeRep[idx];
            } else {
                return prefix+storeRep[idx] +" "+ numberToWords(nextNum);
            }

        }

        private int getIndex(int num){
            for(int i=0; i<store.length-1; i++){
                if(num/store[i] != 0){// to find the largest radix
                    return i;
                }
            }
            return store.length-1;
        }
//    }
}
