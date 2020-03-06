package algo;

import java.util.Stack;

public class MaxStack {
    /*
    716. Max Stack
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
     */
    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println("-> 5:"+stack.top());
        System.out.println("should be 5: "+stack.popMax());
        System.out.println("-> 1:"+stack.top());
        System.out.println("should be 5: "+stack.peekMax());
        System.out.println("-> 1:"+stack.pop());
        System.out.println("-> 5:"+stack.top());


        // failed test
        // fixed by || max==Integer.MIN_VALUE)
//        MaxStack stack = new MaxStack();
//        stack.push(5);
//        System.out.println("should be 5: " + stack.peekMax());
//        System.out.println("should be 5: " + stack.popMax());

        // failed test
        // changed from pop() with top()
//        MaxStack stack = new MaxStack();
//        stack.push(5);
//        stack.push(1);
//        System.out.println("should be 5: " + stack.popMax());
//        System.out.println("should be 1: " + stack.peekMax());


    }

    /*
    执行结果：通过
显示详情
执行用时 :20 ms
, 在所有 Java 提交中击败了  96.86% 的用户
内存消耗 : 43.9 MB , 在所有 Java 提交中击败了 98.73% 的用户
     */

    int max = Integer.MIN_VALUE;
    Stack<Integer> stack = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MaxStack() {

    }

    public void push(int x) {
        if (x >= max) {
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }

    public int pop() {
        int top = stack.pop();
        if (top == max) {
            max = stack.pop();
        }
        return top;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        Stack<Integer> tmpStack = new Stack<>();
        int val = this.top();

        if (val == max || max==Integer.MIN_VALUE) return this.pop();

        // not max, so buffer all values on top of max
        while (top() != max) {
            tmpStack.push(pop());
        }
        val=this.pop();
        while (!tmpStack.isEmpty()) {
//            stack.push(tmpStack.pop());
            this.push(tmpStack.pop());
        }
        return val;
    }


//    class MaxStack {
//        Stack<Integer> stack;
//        Stack<Integer> maxStack;
//
//        public MaxStack() {
//            stack = new Stack();
//            maxStack = new Stack();
//        }
//
//        public void push(int x) {
//            int max = maxStack.isEmpty() ? x : maxStack.peek();
//            maxStack.push(max > x ? max : x);
//            stack.push(x);
//        }
//
//        public int pop() {
//            maxStack.pop();
//            return stack.pop();
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int peekMax() {
//            return maxStack.peek();
//        }
//
//        public int popMax() {
//            int max = peekMax();
//            Stack<Integer> buffer = new Stack();
//            while (top() != max) buffer.push(pop());
//            pop();
//            while (!buffer.isEmpty()) push(buffer.pop());
//            return max;
//        }
//    }





}
