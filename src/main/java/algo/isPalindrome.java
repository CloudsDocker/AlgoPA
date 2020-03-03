package algo;


class isPalindrome {
    public boolean isPalindrome(int x) {
        //first of all, boundary (or edge case)
        if(x<0 || (x!=0 && x%10==0)) {
            return false;
        }

        int reverse =0;
        while(x> reverse) {
            reverse = reverse * 10 + x%10;
            x /= 10;
        }
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == reverse || x == reverse/10;
    }
}
