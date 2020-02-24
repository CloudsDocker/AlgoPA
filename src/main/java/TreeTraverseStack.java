package algo;

import java.util.*;

public class TreeTraverseStack {
    public static void main(String[] args) {
        System.out.println("===start===");
        System.out.println("InOrder Traverse:" + inorderTraversal_better(TreeNode.buildBSTTree()));
        System.out.println("preorderTraversal Traverse:" + preorderTraversal(TreeNode.buildBSTTree()));
        System.out.println("preorderTraversal_better Traverse:" + preorderTraversal_better(TreeNode.buildBSTTree()));
        System.out.println("postOrderTraver_iterative Traverse:" + postOrderTraver_iterative(TreeNode.buildBSTTree()));
        System.out.println("postOrderTraver_linkedList Traverse:" + postOrderTraver_linkedList(TreeNode.buildBSTTree()));
        System.out.println("postOrderTraversal_stack_better Traverse:" + postOrderTraversal_stack_better(TreeNode.buildBSTTree()));



    }

    static public List<Integer> inorderTraversal_better(TreeNode root) {
        List<Integer> listRtn = new ArrayList<>();

        // for inorder trave iteratively we'll push/pop stacks
        Stack<TreeNode> stack = new Stack<>();
        // to determine when to push stack
        // for inorder traverse, to push left first, then pop root, then last right
        while(root!=null || !stack.empty()) {
            while(root!=null){
                stack.push(root);
                // keep on assign left to root for in order traverse
                root=root.left;
            }
            root = stack.pop(); // pop up value of root
            listRtn.add(root.val);
            root=root.right;
        }
        return listRtn;
    }

    /*
    This one is more intuitive
     */
    static public List<Integer> preorderTraversal_better(TreeNode root) {
        List<Integer> listRtn = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop(); // pop up value of root
            if (root != null) {
                listRtn.add(root.val);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
        return listRtn;
    }

    static List<Integer> postOrderTraversal_stack_better(TreeNode node) {
            List<Integer> list = new ArrayList<>();
            if(node==null) {
                return  list;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while(!stack.isEmpty()){
                node= stack.pop();
                list.add(0, node.val); //[!!!!!!!!!!] here is key logic, add item at postion "0" means at the begining
                if(node.left!=null) stack.push(node.left);
                if(node.right!=null) stack.push(node.right);

            }
            return  list;
    }

    static List<Integer> postOrderTraver_iterative(TreeNode node) {
        List<Integer> listRtn = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while(!stack.isEmpty() || node!=null){
            if(node!=null){
                stack.push(node);
                listRtn.add(node.val);
                node=node.right;
            }
            else{
                node=stack.pop().left;
            }
        }
        Collections.reverse(listRtn);
        return listRtn;
    }


    // Key point of this approach is LinkedList's adFirst, which is practically *reverse* the list
        static List<Integer> postOrderTraver_linkedList(TreeNode root){
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return ans;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);      // [!!!!] Here is the key part, "addFirst"
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return ans;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> list, TreeNode root){
        if(root == null)    return;
        helper(list, root.left);
        helper(list, root.right);
        list.add(root.val);
    }

    static public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> listRtn = new ArrayList<>();

        // for preorder trave iteratively we'll push/pop stacks

        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            listRtn.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            root=root.left;
            // left is null, pop up from stack
            if(root==null && !stack.isEmpty()) {
                root=stack.pop();
            }
        }
        return listRtn;

    }



}
