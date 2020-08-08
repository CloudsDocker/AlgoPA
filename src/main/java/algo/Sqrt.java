package algo;

public class Sqrt {
    public static void main(String[] args) {


        int x=8;

        // Newton algorithm to find Integer square root
        long r=x;
        while(r*r>x){
            r= (r+ x/r)/2;
        }
        System.out.println("sqrt:"+r);

    }
}
