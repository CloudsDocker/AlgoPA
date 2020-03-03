package algo;

import java.util.Stack;

public class TreeHeight {
    public static void main(String[] args) {
        TreeNode root = TreeNode.buildBSTTree();
        TreeNode node6 = new TreeNode("6", 25);
        root.left.left.right=node6;
        System.out.printf("\n===get height of tree====:"+getHeight_recursive(root));
        System.out.printf("\n===get height of tree iteratively====:"+getHeight_Iteratively(root));


    }
    static int getHeight_recursive(TreeNode root){
        if(root==null){
            return  0;
        }
        return Math.max(getHeight_recursive(root.left),getHeight_recursive(root.right))+1;  //[!!!!!] Here is the key point, it should add "1" at last
    }

    /*
    The basic idea:
    1. traverse layer by layer
    2. For each layer, firslty get number of element,
    3. Then add its left & right child for each element
    4. Increase height once all element of current layer finished
     */
    static int getHeight_Iteratively(TreeNode root) {
        int height=0;
        Stack<TreeNode> stack=new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            int numberOfSibling=stack.size();
            // loop in all element in this layer till none is left
            while(numberOfSibling-- >0){
                root = stack.pop();
                // add current element's children
                if(root.left!=null) stack.push(root.left);
                if(root.right!=null) stack.push(root.right);
            }
            height++;
        }
        return height;
    }
}
