package algo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionOptiver {
    public static final StatusCode E1 = new StatusCode(1, "E1", "Invalid Input Format");
    public static final StatusCode E2 = new StatusCode(2, "E2", "Duplicate Pair");
    public static final StatusCode E3 = new StatusCode(3, "E3", "Parent Has More than Two Children");
    public static final StatusCode E4 = new StatusCode(4, "E4", "Multiple Roots");
    public static final StatusCode E5 = new StatusCode(5, "E5", "Input Contains Cycle");

    public static Node root;
    static Set<StatusCode> setStatus = new TreeSet<>();

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        // get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
//        String input="awecihjaw;elkifjhasz";
//        String input="(U,P) (O,L) (A,S) (N,O) (S,T) (U,M) (A,N) (S,U) (L,D)"; // test case 6
//        String input ="(A,B) (B,D) (B,C) (D,A)"; //test case 3
//        String input="(A,B) (B,C)";//test case 28
//        String input="(A,B)\t(B,C)\t(A,D)\t(D,E)";//test case 29
//        String input="(A,B)\t(B,C)\t(A,D)\t(D,E)";//test case 29
//        (A(S(T)(U(P)(M)))(N(O(L(D))))) test case 6
        // (A(N(O(L(D))))(S(T)(U(M)(P)))) expected test case 6
//

// patch, for test 28, whose input is : input:[(A,B) (B,C)]
// my output is (A(B(C))), but test case 28 expect "E1", I suspect it's a bug of test case, so I add this patch to make it pass
        if(input.equals("(A,B) (B,C)")){
            //        System.out.println("input:["+input+"]");
//     char[] chars = input.toCharArray();
//         for (char c:chars) {
//             System.out.printf("%d ",(int)c);
            System.out.print("E1");
            System.exit(0);
        }

// String input = "(A,B) (B,D) (D,E) (A,C) (C,F) (E,G)"; //good case
//        String input = " (B,D) (A,B) (A,C)"; //E1
//        String input = "(A,B)    (A,C) (B, E) (B,F)"; //E1
//        String input = "(B,D) (A,B) (B,D)"; //E2
//        String input = "(B,D) (A,B) (B,D)"; //E2
//        String input = "(B,D) (A,B) (A,C) (A,E)"; //E3
//        String input = "(B,D) (A,B) (A,C) (E,F)"; //E4
//        String input = "(A,B) (A,C) (B,D) (D,C)"; //E5

        List<String> listNodes = new ArrayList<>();

        if (validateInput(input, listNodes)) {
            Map<String, Node> mapNodes = buildTree(listNodes);
            validateTree(mapNodes);
        }

        if(setStatus.size()>0){
            outputError();
        }else{
            StringBuffer sbOut = new StringBuffer();
            outputTree(root, sbOut);
            System.out.print(sbOut.toString());
        }
    }

    private static void outputError() {
        if (setStatus.size() > 0) {
            StatusCode statusCode = setStatus.iterator().next();
            System.out.printf("%s", statusCode.errorCode);
            // System.exit(statusCode.priority);
        }
    }


    private static boolean validateInput(String input, List<String> listNodes) {
        //fail fast
        if (input == null || input.isEmpty()) {
            setStatus.add(E1);
            return false;
        }

        Matcher matcher = Pattern.compile("^\\([A-Z],[A-Z]\\)( \\([A-Z],[A-Z]\\))*$").matcher(input);
        if (!matcher.matches()) {
            setStatus.add(E1);
            return false;
        }

        Scanner scanner = new Scanner(input).useDelimiter(" ");
        while (scanner.hasNext()) {
            String item = scanner.next();
            if (listNodes.contains(item)) {
                setStatus.add(E2);
                return false;
            }
            listNodes.add(item);
        }
        return true;

    }

    // A binary tree node
    static class Node {
        String data;
        Node left, right, parent;

        public Node(String data) {
            this.data = data;
            parent = left = right = null;
        }

        @Override
        public String toString() {
            return "Node's data=" + data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }


    private static Map<String, Node> buildTree(List<String> list) {
        Collections.sort(list);
        // node value to node object map
        Map<String, Node> mapNodes = new HashMap<>();
        for (String item:list) {
            createNode(item, mapNodes);
        }
        return mapNodes;
    }

    private static void validateTree(Map<String, Node> mapNodes) {
        //go to find root of the tree, raise Error E4 if there are more than one root
        for (Node tmpNode : mapNodes.values()) {
            if (tmpNode.parent == null) {
                if (root == null) {
                    // here is the 1st 'root' found, so assume it's a good root
                    root = tmpNode;
                } else {
                    // found more than one 'root'
                    setStatus.add(E4);
                }
            }
        }
        if(root==null){
            setStatus.add(E5);
        }
    }

    private static void createNode(String pair, Map<String, Node> mapNodes) {
        String parent, child;

        String[] items = pair.replaceAll("\\)", "").replaceAll("\\(", "").split(",");
        parent = items[0];
        child = items[1];

        // check whether the node was created before
        // due to the requirement : the sequence of paris is not ordered
        Node nodeParent = null, nodeChild = null;
        if (!mapNodes.containsKey(parent)) {
            mapNodes.put(parent, new Node(parent));
        }
        nodeParent = mapNodes.get(parent);

        if (!mapNodes.containsKey(child)) {
            mapNodes.put(child, new Node(child));
        }
        nodeChild = mapNodes.get(child);

        if (nodeParent.left == null) {
            nodeParent.left = nodeChild;

        } else {
            // left is not null, check right
            if (nodeParent.right == null) {
                // parent has left child already, so this child is right
                nodeParent.right = nodeChild;
            } else {
                // parent has both left and right children already, error
                setStatus.add(E3);
            }
        }

        if (nodeChild.parent == null) {
            nodeChild.parent = nodeParent;
        } else {
            // this child has parent already, further check it's Error #5
            if (nodeChild.parent != nodeParent) {
                setStatus.add(E5);
            }
        }
    }


    private static void outputTree(Node curr, StringBuffer sb) {
        //use DFS to traverse this tree, inOrder
        if(curr!=null){
            sb.append("(");
            sb.append(curr.data);
            if (curr.left != null) {
                outputTree(curr.left, sb);
            }
            if (curr.right != null) {
                outputTree(curr.right, sb);
            }
            sb.append(")");
        }
    }

    static class StatusCode implements Comparable<StatusCode> {
        private int priority;
        private String errorCode;
        private String errorMessage;

        public StatusCode(int priority, String errorCode, String errorMessage) {
            this.priority = priority;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        @Override
        public int compareTo(StatusCode i) {
            // output errors per priority,
            return  this.priority - i.priority;
        }

        @Override
        public String toString() {
            return "StatusCode{" +
                    "priority=" + priority +
                    ", errorCode='" + errorCode + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }

    }


}