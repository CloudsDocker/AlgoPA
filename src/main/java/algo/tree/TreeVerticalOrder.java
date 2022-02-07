package algo.tree;


import algo.Pair;

import java.util.*;

public class TreeVerticalOrder {
    public static void main(String[] args) {
        TreeNode root=TreeNode.buildBSTTree();
        /*
        0
        20
        50,30
        80,40
         */
        verticalOrder(root);
    }

    static void verticalOrder(TreeNode root){

        if(root==null) return;
        // level order
        Map<Integer, List<Integer>> map= new TreeMap<>();
        Stack<Pair<TreeNode, Integer>> stack=new Stack<>();
        int order=0;
        stack.push(new Pair<>(root, order));

        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> pair=stack.pop();
            TreeNode node= pair.getKey();
            order=pair.getValue();
            List<Integer> list = map.getOrDefault(order, new ArrayList<>());
            list.add(node.val);
            map.put(order, list);
            if(node.left!=null) stack.push(new Pair<>(node.left, order-1));
            if(node.right!=null) stack.push(new Pair<>(node.right, order+1));
        }

        map.values().forEach(System.out::println);
    }
}
