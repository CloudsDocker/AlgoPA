package algo;

import java.io.*;

public class RestfulIOOnly {
    public static void main(String[] args) throws IOException {
        System.out.println("==");
        BufferedReader reader=new BufferedReader(new FileReader("http://www.baidu.com"));
        String str;
        while((str=reader.readLine())!=null){
            System.out.println(str);
        }
    }
}
