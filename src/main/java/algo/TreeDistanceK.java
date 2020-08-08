package algo;

import java.util.*;

public class TreeDistanceK {
    public static void main(String[] args) {
        System.out.println(" to find distance K to a tree element");
        TreeNode root = TreeNode.buildBSTTree();
        List<Integer> res=distanceK(root, root.left.right.right, 2);
        System.out.println("output:"+res);
    }

    ///======= solution A=======
    static Map<TreeNode, Integer> map = new HashMap<>();

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs(root, target, K, map.get(root), res);
        return res;
    }

    // find target node first and store the distance in that path that we could use it later directly
    private static int find(TreeNode root, TreeNode target) {
        if (root == null)
            return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {// not found and reached left leaf
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }

    private static void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
        if (root == null)
            return;
        if (map.containsKey(root))
            length = map.get(root);
        if (length == K)
            res.add(root.val);
        dfs(root.left, target, K, length + 1, res);
        dfs(root.right, target, K, length + 1, res);
    }


    ///======= solution B=======
//    static Map<TreeNode, List<TreeNode>> map = new HashMap();
////here can also use Map<TreeNode, TreeNode> to only store the child - parent mapping, since parent-child mapping is inherent in the tree structure
//
//    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//        List<Integer> res = new ArrayList<Integer> ();
//        if (root == null || K < 0) return res;
//        buildMap(root, null); // build graph of the Tree, i.e. neighbours of each element
//        if (!map.containsKey(target))
//            return res; // The target element is NOT in current tree, so return empty List
//        Set<TreeNode> visited = new HashSet<TreeNode>(); // cache to avoid duplicate access
//        Queue<TreeNode> q = new LinkedList<TreeNode>(); // queue of the elements to be visisted
//        q.add(target);
//        visited.add(target);
//        while (!q.isEmpty()) {
//            int size = q.size();
//            if (K == 0) {// K is zero , so add current target element to queue directly, means hit the expected Length
//                for (int i = 0; i < size ; i++)
//                    res.add(q.poll().val);
//                return res;
//            }
//            for (int i = 0; i < size; i++) {
//                TreeNode node = q.poll();
//                for (TreeNode next : map.get(node)) {// access it's neighbours and put them into queue
//                    if (visited.contains(next))
//                        continue;
//                    visited.add(next);
//                    q.add(next);
//                }
//            }
//            K--; // added all adjacent element to 'visited' and queue, so decrease K 'distance'
//        }
//        return res;
//    }
//
//    private static void buildMap(TreeNode node, TreeNode parent) {
//        if (node == null) return;
//        if (!map.containsKey(node)) {
//            map.put(node, new ArrayList<TreeNode>());
//            if (parent != null)  {
//                map.get(node).add(parent);
//                map.get(parent).add(node) ;
//            }
//            buildMap(node.left, node);
//            buildMap(node.right, node);
//        }
//    }


}
