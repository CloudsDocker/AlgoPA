package algo.tree;

import java.util.LinkedList;
import java.util.List;

public class RecoverTree {
    public static void main(String[] args) {
        TreeNode root=TreeNode.buildBSTTree();
        TreeNode tmp=root.left.left;
        root.left.left=root.left.right.right.left;
        root.left.right.right.left=tmp;
        TreePrinter.printNode(root);
        System.out.println("recover");
        recoverTree(root);
//        System.out.println(root);
        TreePrinter.printNode(root);


    }
    public static void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode cur = root;
        TreeNode pre_new = null;
        while (cur != null) {
            // 情况 1
            if (cur.left == null) {
                /*******************************************************/
                if (pre_new != null && cur.val < pre_new.val) {
                    if (first == null) {
                        first = pre_new;
                        second = cur;
                    } else {
                        second = cur;
                    }
                }
                pre_new = cur;
                /*******************************************************/
                cur = cur.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 情况 2.1
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // 情况 2.2
                if (pre.right == cur) {
                    pre.right = null; // 这里可以恢复为 null
                    /*******************************************************/
                    if (pre_new != null && cur.val < pre_new.val) {
                        if (first == null) {
                            first = pre_new;
                            second = cur;
                        } else {
                            second = cur;
                        }
                    }
                    pre_new = cur;
                    /*******************************************************/
                    cur = cur.right;
                }
            }
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
