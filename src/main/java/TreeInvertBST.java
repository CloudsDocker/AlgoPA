package algo;

public class TreeInvertBST {
    public static void main(String[] args) {
        System.out.printf("===start===");

        System.out.printf("invert tree: "+invertTree(TreeNode.buildBSTTree()));
    }

    static TreeNode invertTree(TreeNode root){
        if(root==null) {
            return root;
        }

        TreeNode tmpLeft = invertTree(root.left);
        TreeNode tmpRight = invertTree(root.right);

        root.left=tmpRight;
        root.right=tmpLeft;
        return root;
    }
}
