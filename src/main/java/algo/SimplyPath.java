package algo;

import java.util.Deque;
import java.util.LinkedList;

public class SimplyPath {
    public static void main(String[] args) {
        System.out.println("==:"+new SimplyPath().simplifyPath("/home/foo/"));
    }

    public String simplifyPath(String path) {
        if (path == null) return "";
        Deque<String> queue = new LinkedList();
        String[] ary = path.split("/");
        for (String it : ary) {
            if (it.equals(".") || it.isEmpty()) {//[!!!!] don't use it=="", String should be equals(), better to use it.isEmpty()
                continue;
            } else if (it.equals("..")) {
                queue.poll();
            } else {
                queue.push(it);
            }
        }

        StringBuffer sb = new StringBuffer();
        String strRtn="";
        if(queue.size()==0) {
            sb.append("/");
            // strRtn="/";
        }else{
            while(!queue.isEmpty()){
                sb.append("/").append(queue.pollLast());
            }
            // strRtn="/"+String.join("/", queue);
        }
        return strRtn;
    }
}
