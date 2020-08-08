package algo;

import java.math.BigDecimal;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("===factorial start===");
//        System.out.println(fac(BigDecimal.valueOf(20_000)));
        System.out.println(facTail(4, BigDecimal.valueOf(1)));
    }

    static BigDecimal fac(BigDecimal n){
        if(n==BigDecimal.ZERO || n==BigDecimal.ONE) return n;
        else return n.multiply(fac(n.subtract(BigDecimal.ONE)));
    }

    static BigDecimal facTail(int n, BigDecimal a){
        if(n==1) return a;
        return facTail(n-1, a.multiply(BigDecimal.valueOf(n)));
    }

}


