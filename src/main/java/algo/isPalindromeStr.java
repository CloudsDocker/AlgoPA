package algo;

public class isPalindromeStr {
    public static void main(String[] args) {
        String str;
//        String str="race a car";
        str="A man, a plan, a canal: Panama";
        System.out.println(":"+new isPalindromeStr().isPalindrome(str));
    }

    public boolean isPalindrome(String s) {
        if(s==null || s.length()<1) return false;

        int i=0, len=s.length(), j=len-1;
        while(i<j){

            while(!Character.isLetter(s.charAt(i)) && i<j){
                i++;
            }

            while(!Character.isLetter(s.charAt(j)) && j>i){
                j--;
            }
            if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }
}
