package algo;

public class TreeSearchBST {
    public static void main(String[] args) {
        System.out.println("====start======");
        System.out.println("====searchBST recursively:"+searchBST(TreeNode.buildBSTTree(),20));
        System.out.println("====searchBST_Iterative:"+searchBST_Iterative(TreeNode.buildBSTTree(),20));
    }

    public static TreeNode searchBST(TreeNode root, int val) {

        if(root==null){
            return null;
        }

        if(root.val==val){
            return root;
        }else if(val > root.val){
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left,val);
        }
    }


    public static TreeNode searchBST_Iterative(TreeNode root, int val) {
        // recursive approach means recursively assgin/update variables
        while(root != null && root.val != val){
            root = val<root.val? root.left:root.right;
        }
        return root;
    }
}
