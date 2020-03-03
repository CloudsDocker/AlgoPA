package algo;

public class MultiplyString {
    public static void main(String[] args) {
        System.out.println("MultiplyString.main");
        String strNum1 = "123";
        String strNum2 = "9876";
        System.out.println("strNum1 = " + strNum1);
        System.out.println("strNum2 = " + strNum2);
        System.out.println("Result = " + multiply(strNum1, strNum2));
    }

    private static String multiply(String num1, String num2) {
        int nLen1 = num1.length(), nLen2=num2.length();
        int[] result = new int[nLen1+nLen2];
        for(int c1=nLen1-1;c1>=0;c1--) {
            for(int c2=nLen2-1;c2>=0;c2--){
                int nMulti= (num1.charAt(c1) - '0') * (num2.charAt(c2) - '0');
                int sum = nMulti + result[c1+c2+1];
                result[c1+c2] += sum / 10;
                result[c1+c2+1] = sum % 10;
            }
        }

        StringBuffer buff = new StringBuffer();
        for(int p:result) {
            if(!(result.length==0 && p==0)) {
                //remove prefix 0
                buff.append(p);
            }
        }
        return buff.toString();
    }
}
