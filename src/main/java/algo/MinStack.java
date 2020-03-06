package algo;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        System.out.println("==== Min heap====");
        MinStack heap= new MinStack();
        heap.push(-2);
        heap.push(0);
        heap.push(-3);
        heap.push(5);
        heap.push(9);
        heap.push(-5);
        heap.push(10);
        heap.pop();
        heap.pop();
        heap.pop();
        System.out.println("====min is:"+heap.min);
        System.out.println("====top is:"+heap.top());
        heap.pop();
        System.out.println("====top is:"+heap.top());
        heap.pop();
        System.out.println("====top is:"+heap.top());
        heap.pop();
        System.out.println("====top is:"+heap.top());
        System.out.println("====min is:"+heap.min);
    }

    int min = Integer.MAX_VALUE;

    // the key logic is we always keep smallest and 2nd smallest value together
    // so that when pop() release smallest, we will get to know the 2nd smallest
    // this will save time to search stack again
    Stack<Integer> stack = new Stack<>();
    public void push(int val){
        System.out.println("to push:"+val);
        if(val<=min){
            // here is key step 1: we push previous min which is just next to current min
            stack.push(min);
            System.out.println(stack);
            min=val;
        }
        stack.push(val);
        System.out.println(stack);
    }

    public void pop(){
        System.out.println("to pop:");
        System.out.println(stack);
        if(min==stack.pop()){
            System.out.println(stack);
            min=stack.pop();
        }
        System.out.println(stack);
    }

    public int top(){
        return stack.peek();
    }

    public int min(){
        return min;
    }
}
