package algo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoveDupPhotos {
    public static void main(String[] args) throws IOException {
        long n = Files.list(Paths.get("/Users/todzhang/Downloads/00_phone_back/iphoneX")).count();
        System.out.println("===:"+n);
//                .filter(it->it.getFileName().toString().contains(" 2."))
//                .forEach(it2->
//        {
//
//            System.out.println(it2);
//            try {
//                Files.delete(it2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
