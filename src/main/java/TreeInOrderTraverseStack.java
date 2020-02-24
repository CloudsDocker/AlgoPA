package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeInOrderTraverseStack {
    public static void main(String[] args) {
        System.out.println("===start===");
        System.out.println("InOrder Traverse:" + inorderTraversal(TreeNode.buildBSTTree()));
    }

    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }
}
