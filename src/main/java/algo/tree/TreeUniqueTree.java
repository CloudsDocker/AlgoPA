package algo.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUniqueTree {
    public static void main(String[] args) {
        System.out.println(" input tree");
        TreeNode root=TreeNode.buildBSTTree();
        TreePrinter.printNode(root);
    }

//    private static List<TreeNode> buildTree(int[] nodes, int i, int j, List<TreeNode> res){
//
//        if(i==j){
//            return new ArrayList<TreeNode>(){{add(new TreeNode("",nodes[0]));}};
//        }
//
//        // binary search, middle node as root and smaller elements to left while bigger elements to right
////        int mid=i + nodes.length/2;
//        for(int k=i;k<=j;k++){
//
//        }
//        TreeNode root=new TreeNode("",mid);
//        root.left=buildTree();
//    }
}
