package algo;

public class TreeIsSameTree {
    public static void main(String[] args) {
        System.out.println("===check is same tree=======");
        TreeNode tree1 = TreeNode.buildBSTTree();
        TreeNode tree2 = TreeNode.buildBSTTree();
        tree2.left.val+=200;
        System.out.printf("checking same tree result is %s, left tree is %s and right tree is %s",isSameTree(tree1, tree2),tree1, tree2);
    }

    static boolean isSameTree(TreeNode tree1, TreeNode tree2) {
        // check base case, null checking
        if(tree1==null || tree2 ==null){
            return tree1 == tree2; // true when both null, false when only one is null
        }

        /* same tress should be :
        1. node data is same
        2. left sub tree is same
        4. right sub tree is same
         */

        return tree1.val==tree2.val && isSameTree(tree1.left,tree2.left) && isSameTree(tree1.right,tree2.right);
    }
}
