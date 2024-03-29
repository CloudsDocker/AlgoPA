package algo.tree;


import static algo.tree.TreeNode.buildBSTTree;
import static algo.tree.TreeNode.buildTree;

public class TreeBinaryTreeIsBST {
    public static void main(String[] args) {
        System.out.println("===check a binary tree is binery search tree");
        System.out.println("boundary check result:"+binaryTreeIsBST(buildTree(),Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("boundary check result for BST:"+binaryTreeIsBST(buildBSTTree(),Integer.MIN_VALUE, Integer.MAX_VALUE));

    }


    /*
    The key logic are:
     1. assign two boundaries (lower , upper) for each node,
     2. update upper to current node for its left child and lower for its right child
     3. recursively check each node
     */
    static boolean binaryTreeIsBST(TreeNode node, int lower, int upper){

        // for recursive, base case
        // Number 1: base case is null return true
        if(node==null) return true;

        // Number 2: check data
        if(node.val < lower || node.val>upper) {
            System.out.printf("%s failed in BST check [%d,%d]: ", node, lower,upper);
            return false;
        }

        // for left child node, it's value should between current's node's lower boundary and current node's value
        // for right child node, it's value should between current node's value and current's node's upper boundary
        return binaryTreeIsBST(node.left,lower,node.val) && binaryTreeIsBST(node.right,node.val, upper);

    }
}
