package algo;

import java.util.Stack;

public class RPN {
    public static void main(String[] args) {

    }
    public int evalRPN(String[] tokens) {
        if(tokens==null || tokens.length<1) return 0;

        Stack<String> stack =new Stack<>();
        for(int i=0;i<tokens.length;i++){
            String str = tokens[i];
            int op1,op2,result;
            switch(str){
                case "+":
                    op1=Integer.parseInt(stack.pop());
                    op2=Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(op1+op2));
                    break;
                case "-":
                    op1=Integer.parseInt(stack.pop());
                    op2=Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(op2-op1));
                    break;
                case "*":
                    op1=Integer.parseInt(stack.pop());
                    op2=Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(op1*op2));
                    break;
                case "/":
                    op1=Integer.parseInt(stack.pop());
                    op2=Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(op1/op2));
                    break;
                default:
                    stack.push(str);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
