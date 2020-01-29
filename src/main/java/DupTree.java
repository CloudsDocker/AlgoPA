import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DupTree {
    public static void main(String[] args) {
        String str = "(A,B) (B,C) (B,C)";
        Set<String> dupSet =new HashSet<>();
        Scanner scanner=new Scanner(str).useDelimiter(" ");
        while(scanner.hasNext()){
            String item = scanner.next();
            if(dupSet.contains(item)){
                System.out.printf("test %s\n","error");
                throw new RuntimeException("dup");
            }
            dupSet.add(item);
        }
    }
}
