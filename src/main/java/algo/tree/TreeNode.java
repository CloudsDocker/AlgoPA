package algo.tree;

public class TreeNode {

	public  int val =Integer.MIN_VALUE;
	private String name;
	public  TreeNode left=null;
	public  TreeNode right=null;
	public TreeNode(String name, int value) {
		super();
		this.name=name;
		this.val = value;
	}
	@Override
	public String toString() {		
		return String.format(" value is %d, and left is [%s], right is [%s]", val,left==null?"":left,right==null?"":right);
	}

	static  TreeNode buildTree(){
        /*
                50
            20     80
         0  60
         */
		TreeNode tree1=new TreeNode("1",50);
		TreeNode tree2=new TreeNode("2",20);
		TreeNode tree3=new TreeNode("3",80);
		TreeNode tree4=new TreeNode("4",0);
		TreeNode tree5=new TreeNode("5",60);
		tree1.left=tree2;
		tree1.right=tree3;
		tree2.left=tree4;
		tree2.right=tree5;
		return  tree1;
	}

	static  TreeNode buildBSTTree(){
        /*
	           50
            20	   80
         0     30
         		   40
         */
		TreeNode tree1=new TreeNode("1",50);
		TreeNode tree2=new TreeNode("2",20);
		TreeNode tree3=new TreeNode("3",80);
		TreeNode tree4=new TreeNode("4",0);
		TreeNode tree5=new TreeNode("5",30);
        TreeNode tree6=new TreeNode("6",40);
        TreeNode tree7=new TreeNode("7",35);
		tree1.left=tree2;
		tree1.right=tree3;
		tree2.left=tree4;
		tree2.right=tree5;
		tree5.right=tree6;
		tree5.left=new TreeNode("",25);
		tree6.left=tree7;
		tree6.right=new TreeNode("",45);
		return  tree1;
	}
	
}
