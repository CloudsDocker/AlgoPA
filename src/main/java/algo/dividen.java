package algo;

public class dividen {
    public static void main(String[] args) {
        //System.out.println("===:"+new dividen().divide(-2147483648,-1));
//        System.out.println("===:"+new dividen().divide(-2147483648,1));//-2147483648
        //System.out.println("===:"+new dividen().divide(-2147483648,-1));//2147483647
        System.out.println("1<<31:"+ (1<<31));
        System.out.println("1<<1:"+ (1<<1));
        System.out.println("1<<2:"+ (1<<2));
        System.out.println("1<<31-1:"+ ((1<<31)-1));
        System.out.println("1>>31:"+ (1>>2));
        System.out.println("===:"+new dividen().divide(7,2));//2147483647


    }


    public int divide(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }



    public int divide_lc(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        boolean sign = (dividend < 0) == (divisor < 0);
        int res = div(-Math.abs(dividend), -Math.abs(divisor));
        return sign? res: -res;
    }

    public int div(int dividend, int divisor){
        if(dividend > divisor) return 0;
        int sum = divisor, q = 1;
        while(dividend <= sum + sum && sum + sum < sum){
            sum += sum;
            q += q;
        }
        return q + div(dividend - sum, divisor);
    }


        public int divide2(int dividend, int divisor) {
            if(dividend==0) return 0;
            int sign1=1,sign2=1;
            //if(dividend<0) sign1=-1;
            //if(divisor<0) sign2=-1;
            sign1=sign1*sign2;
            System.out.println("===:"+sign1);
            System.out.println("===MIN_VALUE:"+Integer.MIN_VALUE);
            System.out.println("===MAX_VALUE:"+Integer.MAX_VALUE);


            int n=0;
            int dividend2=Math.abs(dividend);
            int divisor2=Math.abs(divisor);
            if(dividend2!=dividend||dividend==Integer.MIN_VALUE)
                sign1=-1;
            if(divisor2!=divisor||divisor==Integer.MIN_VALUE)
                sign2=-1;

            System.out.println("===dividend:"+dividend);

            if(Math.abs(divisor2)==1){
                System.out.println("=sign1==:"+sign1+",sign2="+sign2+",dividend:"+dividend);
                System.out.println("dividend==Integer.MIN_VALUE====:"+ (dividend==Integer.MIN_VALUE));
                long rtn= (dividend==Integer.MIN_VALUE?(Integer.MAX_VALUE+1):dividend2);
                if(rtn>Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }else{
                    return (int)(rtn*sign1*sign2);
                }
            }

            while(dividend2>=divisor2){
                dividend2-=divisor2;
                n++;
            }
            return sign1*sign2*n;
        }
}
