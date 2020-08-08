package algo;

import java.util.Stack;

public class Calculator2 {
    public static void main(String[] args) {
        System.out.println("calculator II: 3+2*4");
        Calculator2 inst = new Calculator2();
//        System.out.println("output of 3+2*4:"+inst.calculate("3+2*4"));
//        System.out.println("output of 2*4:"+inst.calculate("2*4"));
//        System.out.println("output of 2*4+9:"+inst.calculate("2*4+9"));
//        System.out.println("output of 3+5 / 2:"+inst.calculate("3+5 / 2")); // there are spaces in middle
//        System.out.println("output of 42:"+inst.calculate("42")); // multiple digits
//        System.out.println("output of 2*3*4:"+inst.calculate("2*3*4")); // multiple multiplies
//        System.out.println("output of 14/3-2:"+inst.calculate("14/3-2")); // multiple multiplies
//        System.out.println("output of 14/3*2:"+inst.calculate("14/3*2"));
//        System.out.println("output of 14-3/2:"+inst.calculate("14-3/2"));
        System.out.println("output of 14-13/2:"+inst.calculate("14-13/2"));
    }

    public int calculate(String s) {
        int res=0;

        if(s==null || s.length()<1) return res;
        s=s.replaceAll("\\s",""); //[!!!!] Edge case is there are space in middle of formula

        char[] data=s.toCharArray();
        int num=0, sign=1,result=0, higher=0;
        Stack<String> stack=new Stack<>();
        boolean prevDigit=false;
        for(int i=0;i<data.length;i++){
            char c=data[i];
            if(Character.isDigit(c)){
//                num=Character.getNumericValue(c);
                // [!!!!ERROR] for char to int, Integer.valueOf will get ASCII.
                // to get value: Character.getNumericValue(c) OR c-'0'
                num=c-'0';
                // check operator right side
                if(i<data.length-1 && (data[i+1]=='*' || data[i+1]=='/')){
                    //case 1.1, right side is higher, so calculate right side and stack left side
                    if(prevDigit)
                        result=result*10+sign*num;
                    else {
                        if (higher == 0) {
                        if (result != 0) {
                            stack.push(String.valueOf(result));
                            stack.push(String.valueOf(sign));
                            stack.push(String.valueOf(higher));
                        }

                        result = num;
                    }else{
                            // previous are * or /, calculate directly
                            if(sign==1){
                                result *=num;
                            }else{
                                result/=num;
                            }

                        }
                    }

                }else{
                    //calculate left formula, 2+3=> data[i]=3, num="2", sign="1", result="0",
                    if(higher==0){
                        if(prevDigit)
                            result=result*10+sign*num;
                        else
                            result+=sign*num;
                    }
                    else{
                        // means * or /
                        if(sign==1){
                            result=result*num;
                        }else{
                            result=result/num;
                        }

                        // process stack
                        if(!stack.isEmpty()){
                            higher=Integer.parseInt(stack.pop());
                            sign=Integer.parseInt(stack.pop());
                            num=Integer.parseInt(stack.pop());
                            if(higher==0)
                                result=num+sign*result; //[!!!!] Should be Num in front and sign multiply "result"
                            else
                            {
                                if(sign==1)
                                    result*=sign*num;
                                else
                                    result/=sign*num;
                            }
                        }
                    }

                }
                prevDigit=true;
            }else if(c=='+' || c=='-'){
                sign=c=='+'?1:-1;
                higher=0;
                prevDigit=false;
            }else if(c=='*' || c=='/'){
                sign=c=='*'?1:-1;
                higher=1;
                prevDigit=false;
            }
        }

        return result;

    }
}
