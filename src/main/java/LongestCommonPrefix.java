package algo;

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
//        String[] list = new String[]{"todd","toddad","toddmmrow"};
        String[] list = new String[]{"todd","atad","tddmmrow"};
        System.out.printf("\nCommon prefix is [%s] for strings %s", getLonggestCommonPrefix(list), Arrays.toString(list));
    }

    /*
    logic is pick one string to for loop, increase return string if character is same for all sub strings, 
    otherwise return saved loggest sub string
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
