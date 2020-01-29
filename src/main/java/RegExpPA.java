import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
public class RegExpPA {

//    public final static StatusCode E1= new StatusCode(1,"E1","Error 1");
//    public final static StatusCode E2= new StatusCode(2,"E2","Error 1");
//    public final static StatusCode E3= new StatusCode(3,"E3","Error 1");
//
    public static void main(String[] args) {
//        Set<String> set =new HashSet<>();
//        set.add(null);
////        String str=" (A,B) (B,C) ";
        String str="(A,B) (B,C) (B,C)";
//        String str="(A,B)\t(B,C)\t(B,C)";
////        (?<!\s)
        Matcher matcher= Pattern.compile("^\\([A-Z],[A-Z]\\)( \\([A-Z],[A-Z]\\))*$").matcher(str);
        if(matcher.matches()){
//            System.out.println("yes"+ Status.E1.getStatus());
            System.out.println("yes");
        }
        else{
//            System.out.println("No"+Status.E2.getStatus());
            System.out.println("No");
        }
//
//        Set<StatusCode> set =new TreeSet<>();
//        set.add(E2);
//        set.add(E3);
//        set.add(E1);
//        for (StatusCode code:set
//             ) {
//            System.out.println(code);
//
//        }
//
    }
//
//
//
//    static class StatusCode implements Comparable<StatusCode>{
//        private int priority;
//        private String errorCode;
//        private String errorMessage;
//
//        public StatusCode(int priority, String errorCode, String errorMessage) {
//            this.priority = priority;
//            this.errorCode = errorCode;
//            this.errorMessage = errorMessage;
//        }
//
//        @Override
//        public int compareTo(StatusCode i) {
//            return  this.priority - i.priority;
//        }
//
//        @Override
//        public String toString() {
//            return "StatusCode{" +
//                    "priority=" + priority +
//                    ", errorCode='" + errorCode + '\'' +
//                    ", errorMessage='" + errorMessage + '\'' +
//                    '}';
//        }
//    }
//
//    enum Status{
//        E1("Error1"),
//    E2("Error2");
//        private String status;
//        Status(String s){
//            this.status=s;
//        }
//
//        public String getStatus(){
//            return status;
//        }
//    }
//
//
}
