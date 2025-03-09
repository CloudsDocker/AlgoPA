package algo;

public class isPalindromeStr {
    public static void main(String[] args) {
        String str;
//        String str="race a car";
        str="A man, a plan, a canal: Panama";
        str="anana";
        System.out.println(":"+new isPalindromeStr().isPalindrome(str));
    }

    private boolean isPalindrome(String str) {
        if(str==null || str.length()<2){
            return true;
        }

        // aba
        // (1) two pointers,
        // (2) left from 0, right from length-1
        // (3) exit only left>right
        // Dev: (1) For characters, should think about whether case sensitive? (2) should think to skip Non-characters of the str
        int left = 0;
        int right = str.length()-1;
        while(left<right){
            if(str.charAt(left) == str.charAt(right)){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }


    //public boolean isPalindrome(String s) {
    //    if(s==null || s.length()<1) return false;
    //
    //    int i=0, len=s.length(), j=len-1;
    //    while(i<j){
    //
    //        while(!Character.isLetter(s.charAt(i)) && i<j){
    //            i++;
    //        }
    //
    //        while(!Character.isLetter(s.charAt(j)) && j>i){
    //            j--;
    //        }
    //        if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
    //            return false;
    //        }else{
    //            i++;
    //            j--;
    //        }
    //    }
    //    return true;
    //}
}
