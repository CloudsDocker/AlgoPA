package algo;

import java.util.Stack;

public class TreeDeepestNode {
    int deepest=0;
    TreeNode deepestNode=null;
    public static void main(String[] args) {
        System.out.println("==find deepest node===");
        TreeNode root=TreeNode.buildBSTTree();
        TreeDeepestNode inst= new TreeDeepestNode();
        inst.findDeepest(root);
        System.out.println("output 1:"+inst.deepest);
        System.out.println("output 2:"+inst.deepestNode);
    }

    private void findDeepest(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        int height=0;
        TreeNode node=null;

        while(!stack.isEmpty()){
            ++height;

            int size=stack.size();

            while(size-- >0){
                 node=stack.pop();
                if(height>deepest){
                    deepest=height;
                    deepestNode=node;
                }

                if(node.left!=null) stack.push(node.left);
                if(node.right!=null) stack.push(node.right);
            }


        }
    }
}
