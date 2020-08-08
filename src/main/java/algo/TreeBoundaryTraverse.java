package algo;

import java.util.*;

public class TreeBoundaryTraverse {
    public static void main(String[] args) {
        System.out.println(" start traverse tree boundary");
        TreeNode root=TreeNode.buildBSTTree();
        System.out.println(Arrays.toString(boundaryTraverse(root).toArray()));
    }

    static List<Integer> boundaryTraverse(TreeNode node){
        List<Integer> list =new ArrayList<>();
        if(node==null){
            return list;
        }
        Stack<Integer> rigthBoundary= new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur=queue.poll();
                if(i==0){
                    // first element in current level
                    // add to result
                    list.add(cur.val);
                }else if(i==size-1){
                    rigthBoundary.push(cur.val);
                }
                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
            }

        }

        while(!rigthBoundary.isEmpty()){
            list.add(rigthBoundary.pop());
        }
        return list;

    }
}
