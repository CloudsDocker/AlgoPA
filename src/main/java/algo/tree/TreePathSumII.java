package algo.tree;

import java.util.ArrayList;
import java.util.List;

public class TreePathSumII {
    public static void main(String[] args) {
        /*
        113. Path Sum II
Medium

1348

44

Add to List

Share
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
         */
        System.out.println("===path sum II:");
        TreePathSumII inst = new TreePathSumII();
        List<List<Integer>> result = new ArrayList<>();
    }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        pathSumII(root, sum, current, results);
        return results;

    }

    public void pathSumII(TreeNode node, int target, List<Integer> current, List<List<Integer>> result){
        if(node==null){
            return ;
        }

        // keep track of current node
        current.add(node.val);

        // meeting point
        if(node.left==null && node.right==null&&node.val==target){
            // met all target, add current path to results
            result.add(current);
            return;
        }

        // for "Tree" problem, it's always *recursively* invoke functions for "left" and "right" children
        pathSumII(node.left, target-node.val, new ArrayList<>(current), result);
        pathSumII(node.right, target-node.val, new ArrayList<>(current), result);
    }
}
