package algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinStringWindow {

    public static void main(String[] args) {
//Solution s = new Solution();
String s ="ABAACBAB";
String t = "ABC";
        System.out.println("args 1 = " + minWindowOp(s,t));
        System.out.println("args 2= " + minWindowBetter(s,t));
    }

//    class Solution {


    public static String minWindowBetter(String s, String t){

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


        public static String minWindowOp(String s, String t) {
            int [] map = new int[128];//map to track number of occurrence of each character of sub string
            for (char c : t.toCharArray()) {
                map[c]++;
            }
            int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
            // counter is number of distinct chars in sub string
            while (end < s.length()) {
                final char c1 = s.charAt(end);// walk through each char in source string
                if (map[c1] > 0) {
                    counter--; // if cached char number greater than 0, decrease counter
                }
                map[c1]--;//decrease cached char number, for chars not in substring, it will be negative
                end++; //move right pointer
                while (counter == 0) {  //counter is zero means all chars found
                    if (minLen > end - start) { //to find and cache minimum sliding window length and minimum start
                        minLen = end - start;
                        minStart = start;
                    }
                    final char c2 = s.charAt(start);
                    map[c2]++;// A is -2， B is 1
                    if (map[c2] > 0) { // greater than zero means c2 is part ot our "t" (rather than other chars)
                        counter++; //if current char exist in cache, increase counter, otherwise keep counter zero
                    }
                    start++;
                }
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
        }



        public String minWindow(String s, String t) {

            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            // Dictionary which keeps a count of all the unique characters in t.
            Map<Character, Integer> dictT = new HashMap<Character, Integer>();
            for (int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }

            // Number of unique characters in t, which need to be present in the desired window.
            int required = dictT.size();

            // Left and Right pointer
            int l = 0, r = 0;

            // formed is used to keep track of how many unique characters in t
            // are present in the current window in its desired frequency.
            // e.g. if t is "AABC" then the window must have two A's, one B and one C.
            // Thus formed would be = 3 when all these conditions are met.
            int formed = 0;

            // Dictionary which keeps a count of all the unique characters in the current window.
            Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

            // ans list of the form (window length, left, right)
            int[] ans = {-1, 0, 0};

            while (r < s.length()) {
                // Add one character from the right to the window
                char c = s.charAt(r);
                int count = windowCounts.getOrDefault(c, 0);
                windowCounts.put(c, count + 1);

                // If the frequency of the current character added equals to the
                // desired count in t then increment the formed count by 1.
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }

                // Try and contract the window till the point where it ceases to be 'desirable'.
                while (l <= r && formed == required) {
                    c = s.charAt(l);
                    // Save the smallest window until now.
                    if (ans[0] == -1 || r - l + 1 < ans[0]) {
                        ans[0] = r - l + 1;
                        ans[1] = l;
                        ans[2] = r;
                    }

                    // The character at the position pointed by the
                    // `Left` pointer is no longer a part of the window.
                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                        formed--;
                    }

                    // Move the left pointer ahead, this would help to look for a new window.
                    l++;
                }

                // Keep expanding the window once we are done contracting.
                r++;
            }

            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
//    }


}
