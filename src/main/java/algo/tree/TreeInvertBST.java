package algo.tree;

import static algo.tree.TreePrinter.printTree;

public class TreeInvertBST {
    public static void main(String[] args) {
        System.out.printf("===start===");
        System.out.println("current tree is:");
        final TreeNode tree = TreeNode.buildBSTTree();
        printTree(tree);
        TreeNode root = invertTree(tree);
        System.out.println("invert tree: ");
        printTree(tree);
    }

    static TreeNode invertTree(TreeNode root){
        if(root==null)  return null;
        TreeNode tmpLeft = root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(tmpLeft);
        return root;
    }

}
