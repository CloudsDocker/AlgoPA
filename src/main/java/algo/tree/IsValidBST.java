package algo.tree;

import java.util.Stack;

import static algo.tree.TreePrinter.printTree;

public class IsValidBST {
    public static void main(String[] args) {
        System.out.println("===check is it a valid BST====");
        String test="test 123";
        System.out.println(test);

        TreeNode root = TreeNode.buildBSTTree();
        printTree(root);
        System.out.println("==check isValidBST_Recursive==:"+new IsValidBST().isValidBST_Recursive(root, null, null));
        System.out.println("==check isValidBST_Iterative==:"+new IsValidBST().isValidBST_Iterative(root));
    }

    private boolean isValidBST_Recursive(TreeNode root, TreeNode min, TreeNode max){
        if(root==null)
            return true;
        if(min!=null && min.val>=root.val)
            return false;
        if(max!=null && max.val<=root.val)
            return false;
        return isValidBST_Recursive(root.left,min, root)
                && isValidBST_Recursive(root.right,root,max);

    }

    private boolean isValidBST_Iterative(TreeNode root){
        // in-order search should be sorted

        Stack<TreeNode> stack=new Stack<>();
        TreeNode pre=null;
        while(root!=null || !stack.isEmpty()){

            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            System.out.println("--:"+root.val);
            if(pre!=null && pre.val>=root.val){
                return false;
            }
pre=root;

            root=root.right;
        }
        return true;
    }
}
