package algo;

public class TreeBinaryTreeIsBST {
    public static void main(String[] args) {
        System.out.println("===check a binary tree is binery search tree");
        System.out.println("boundary check result:"+binaryTreeIsBST(buildTree(),Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("boundary check result for BST:"+binaryTreeIsBST(buildBSTTree(),Integer.MIN_VALUE, Integer.MAX_VALUE));

    }

    static  TreeNode buildTree(){
        /*
                50
            20     80
         0  60
         */
        TreeNode tree1=new TreeNode("1",50);
        TreeNode tree2=new TreeNode("2",20);
        TreeNode tree3=new TreeNode("3",80);
        TreeNode tree4=new TreeNode("4",0);
        TreeNode tree5=new TreeNode("5",60);
        tree1.left=tree2;
        tree1.right=tree3;
        tree2.left=tree4;
        tree2.right=tree5;
        return  tree1;
    }

    static  TreeNode buildBSTTree(){
        /*
                50
            20     80
         0  30
         */
        TreeNode tree1=new TreeNode("1",50);
        TreeNode tree2=new TreeNode("2",20);
        TreeNode tree3=new TreeNode("3",80);
        TreeNode tree4=new TreeNode("4",0);
        TreeNode tree5=new TreeNode("5",30);
        tree1.left=tree2;
        tree1.right=tree3;
        tree2.left=tree4;
        tree2.right=tree5;
        return  tree1;
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
