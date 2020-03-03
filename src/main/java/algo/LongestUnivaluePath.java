package algo;

public class LongestUnivaluePath {

	public static void main(String[] args) {
		LongestUnivaluePath inst = new LongestUnivaluePath();
		TreeNode root=inst.buildTree();
		int len = inst.longestLen(root);
		System.out.println("=== arrow length is : "+len);
	}
	
	private TreeNode buildTree() {
		TreeNode root=new TreeNode("node1",1);
		TreeNode node2=new TreeNode("node2",2);
		TreeNode node3=new TreeNode("node3",1);
		TreeNode node4=new TreeNode("node4",1);
		TreeNode node5=new TreeNode("node5",1);

		root.left=node2;
		root.right=node3;
		node3.left=node4;
//		node4.left=node5;

		return root;
	}
	
	private int longestLen(TreeNode node) {
		rtn=0;
		arrowLen(node);
		return rtn;
	}
	
	private int rtn=0;
	private int arrowLen(TreeNode node) {
		// edge/base case
		if(node==null) {
			return 0;
		}
		
		int left=this.longestLen(node.left);
		int right = this.longestLen(node.right);
		
		int leftTotal=0,rightTotal=0;
		if(node.left!=null && node.left.val==node.val) {
			leftTotal += left +1;
		}
		
		if(node.right!=null && node.right.val==node.val) {
			rightTotal += right+1;
		}
		
		rtn = Math.max(rtn, leftTotal + rightTotal);
		return Math.max(leftTotal, rightTotal);
	}

}
