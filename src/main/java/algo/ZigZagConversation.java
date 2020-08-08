package algo;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversation {
    public static void main(String[] args) {
        System.out.println("zigzag:"+new ZigZagConversation().convert("ABCDEFSF",3));

    }
        public String convert(String s, int numRows) {

            if(s==null || numRows<1) return "";

            ArrayList<Character>[] rows=new ArrayList[numRows];

            //init array
            for(int i=0;i<numRows;i++){
                rows[i]=new ArrayList();
            }
            int i=0, l=s.length();
            while(i<l){
                for(int nRow=0;nRow<numRows;nRow++){
                    // move vertically down
                    rows[nRow].add(s.charAt(i++));
                }

                for(int nRow=numRows-2;nRow>=1;nRow--){
                    rows[nRow].add(s.charAt(i++));
                }
            }

            StringBuffer sb=new StringBuffer();
            for(List<Character> list: rows){
                for(Character str:list){
                    sb.append(str);
                }
            }
            return sb.toString();

            //Arrays.stream(rows).map(list-> list.stream().map(Object::toString).collect(Collectors.joining(","))).collect(Collectors.joining(","));
    }
}
