package algo;

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
//        String[] list = new String[]{"todd","toddad","toddmmrow"};
        String[] list = new String[]{"todd","atad","tddmmrow"};
        System.out.printf("\nCommon prefix is [%s] for strings %s", getLonggestCommonPrefix(list), Arrays.toString(list));
    }

    /*
    To improve perforamance, better to check if it can sort array first, which will make it more faster
    The magical point of this solution is:
    1. To Array sort first, which is log(n)
    2. Then compare only the 1st and last two string, they should be MOST distinct ones, so only compare those two
     */
    public String longestCommonPrefix_faster(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs!= null && strs.length > 0){

            Arrays.sort(strs);

            char [] a = strs[0].toCharArray();
            char [] b = strs[strs.length-1].toCharArray();

            for (int i = 0; i < a.length; i ++){
                if (b.length > i && b[i] == a[i]){
                    result.append(b[i]);
                }
                else {
                    return result.toString();
                }
            }

        }
        return result.toString();
    }

    /*
    logic is pick one string to for loop, increase return string if character is same for all sub strings, 
    otherwise return saved loggest sub string
    Runtime: 10 ms, faster than 6.58% of Java online submissions for Longest Common Prefix.
     */
    static String getLonggestCommonPrefix(String[] listWords){
        String strCommonPrefix = "";
        if(listWords==null || listWords.length<1) return strCommonPrefix; 
        
        // we can take either word in list to traverse, 
        for (int i = 0; i < listWords[0].toCharArray().length; i++) {
            for (int j = 1; j < listWords.length; j++) {
                if(i>(listWords[j].length()-1) || listWords[0].charAt(i)!=listWords[j].charAt(i)){
                   return strCommonPrefix;
                }
            }
            strCommonPrefix+=listWords[0].charAt(i);
        }
        return strCommonPrefix;
    }

    
    
}
