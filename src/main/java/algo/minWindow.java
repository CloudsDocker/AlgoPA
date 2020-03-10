package algo;

public class MinWindow {
    /*
76. Minimum Window Substring
Hard

3604

253

Add to List

Share
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     */

        public String minWindow(String s, String t) {
            if(s==null||t==null|s.length()==0||t.length()==0){
                return "";
            }
            int left=0,right=0,count=0,min=Integer.MAX_VALUE;
            int pool[] = new int[256];
            String rtn="";
            for(int i =0;i<t.length();i++){
                pool[t.charAt(i)]++;
            }

            while(right<s.length()){
                if(pool[s.charAt(right++)]-->0){//[!]
                    // (a) if(pool[s.charAt(right++)]-->=0), rather than if(pool[right++]-->=0)
                    // (b) this is ">0", but not ">=0"
                    count++;
                }

                while(count==t.length()){
                    if((right-left)<min){
                        min=right-left;
                        rtn=s.substring(left,right);
                    }

                    //shrink window
                    if(++pool[s.charAt(left++)]>0){
                        count--;
                    }
                }
            }

            return rtn;
        }
    /*
    ---previous vesion
    public String minWindow(String s, String t) {
        // for 'substring' problem, use sliding window
        // key concepts for sliding window are
        // (1) hashmap
        // (2) two points of start and end
        // (3) one counter and one len
        // 'end' is used to make a valid window
        // 'start' is used to find a smallest window

        //Map<Character, Integer> map = new HashMap<>();
        // if possible replace Map with Array, which will increase performance
        char[] map=new char[128];
        int start=0, end=0,minLen=Integer.MAX_VALUE;
        int minStart=0;
        int  counter=t.length();// init counter is length of substring
        for(char c: t.toCharArray()){
            map[c]++;
        }

        // S = "ADOBECODEBANC", T = "ABC"
        while(end<s.length()){
            final char c1=s.charAt(end);
            if(map[c1]>0){
                counter--; // found char in source string, decrease counter
            }
            // change variables
            end++; // move end point to right
            map[c1]--; // reduce count of char, if not exist, it will be negative

            while(counter==0){
                // counter is zero means current window is still valid
                if(minLen>(end-start)){
                    minLen=(end-start);
                    minStart=start;
                }

                final char c2=s.charAt(start);
                map[c2]++;
                if(map[c2]>0){
                    counter++; // found valid char again
                }
                start++;
            }

        }

        if(minLen==Integer.MAX_VALUE){
            return "";
        }else{
            return s.substring(minStart,minStart+minLen);
        }
    }
    */
}

