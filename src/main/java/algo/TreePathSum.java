package algo;



public class TreePathSum {
    public static void main(String[] args) {
    /*
    112. Path Sum
Easy

1434

425

Add to List

Share
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Accepted
     */
    TreePathSum inst = new TreePathSum();
        System.out.println("path sum I, output is:"+inst.hasPathSum(TreeNode.buildBSTTree(),130));
    }

    public boolean hasPathSum(TreeNode root, int sum){
        //base case first
        if(root==null){
            return false;
        } else if(root.left==null && root.right==null && root.val==sum){
            //found it !!!
            return true;
        }else{
            return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right, sum-root.val);
        }
    }

}
