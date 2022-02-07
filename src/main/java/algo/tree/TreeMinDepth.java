package algo.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeMinDepth {
    public static void main(String[] args) {
        System.out.println("min depth is = " + findMinDepth(buildTree()));
        System.out.println("min depth v2 is = " + findMinDepth_v2(buildTree()));
        System.out.println("min depth iterative is = " + findMinDepth_iterative(buildTree()));
    }
    static TreeNode buildTree(){
        /*
                        1
                    2       3
                4
            5

         */
        TreeNode rtn=new TreeNode("1",1);

        TreeNode node2=new TreeNode("2",2);
        TreeNode node3=new TreeNode("3",3);
        TreeNode node4=new TreeNode("4",4);
        TreeNode node5=new TreeNode("5",5);
        rtn.left=node2;
        rtn.right=node3;
        node2.left=node4;
        node4.left=node5;
        return rtn;
    }
    static int findMinDepth(TreeNode treeNode){

        //edge case
        if(treeNode==null){
            return 0;
        }

        int nLeft = findMinDepth(treeNode.left);
        int nRight = findMinDepth(treeNode.right);

        // find depth of right
        if(treeNode.left==null){
            return nRight+1;
        }

        // find depth of left
        if(treeNode.right==null){
            return nLeft+1;
        }

        // contains both left and right leaves
        return Math.min(nLeft,nRight)+1;      // [!!!!] here should "+1"
    }


//         * Key point:
//     * if a node only has one child -> MUST return the depth of the side with child, i.e. MAX(left, right) + 1
//     * if a node has two children on both side -> return min depth of two sides, i.e. MIN(left, right) + 1
//     * */
    public static int findMinDepth_v2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = findMinDepth_v2(root.left);
        int right = findMinDepth_v2(root.right);
        if (left == 0 || right == 0) {
            //     * if a node only has one child -> MUST return the depth of the side with child, i.e. MAX(left, right) + 1
            return Math.max(left, right) + 1;
        }
        else {
            //     * if a node has two children on both side -> return min depth of two sides, i.e. MIN(left, right) + 1
            return Math.min(left, right) + 1;
        }
    }

    //    /** Solution 2: BFS level order traversal */
    public static int findMinDepth_iterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left == null && curNode.right == null) {
                    return level;
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            level++;
        }
        return level;
    }
}
