import java.util.Arrays;
import java.util.Scanner;

public class StdinReader {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("args = [" + input+"]");
    }
}
