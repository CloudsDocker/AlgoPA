package algo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {
    public static void main(String[] args) {

        System.out.println(frequencySort("tree"));
    }

    public static String frequencySort(String s) {
        if(s==null || s.length()<1) return s;
        char[] chars=s.toCharArray();
        Map<Character,Integer> map=new HashMap<>();
        for(char c: chars){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> q=new PriorityQueue<>((a, b)->b.getValue()-a.getValue());
        for(Map.Entry entry:map.entrySet()){
            q.add(entry);
        }

        StringBuffer sb=new StringBuffer();
        while(!q.isEmpty()){
            Map.Entry<Character,Integer> entry=q.poll();
            int size=entry.getValue();
            while(size-->0){
                sb.append(entry.getKey());
            }
        }

        return sb.toString();
    }
}
