package algo;

public class TreeInvertBST {
    public static void main(String[] args) {
        System.out.printf("===start===");
        TreeNode root = invertTree(TreeNode.buildBSTTree());
        System.out.printf("invert tree: "+ root);
    }

    static TreeNode invertTree(TreeNode root){
        if(root==null)  return null;
        TreeNode tmpLeft = root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(tmpLeft);
        return root;
    }
}
