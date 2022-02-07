package algo.array;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> listRtn= new ArrayList<>();
            if(strs==null|| strs.length<1){
                return listRtn;
            }

            Map<String, List<String>> maps=new HashMap<>();
            for(int i=0;i<strs.length;i++){
                String item = strs[i];

                // transform string to unique key
                int[] chars = new int[26];
                for(int j=0;j<item.length();j++){
                    chars[item.charAt(j)-'a']++;
                }

//                String uniqKey = Arrays.stream(chars).mapToObj(it->it+String::valueOf).collect(Collectors.joining());
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < chars.length; j++) {
                    sb.append(chars[j]).append((char)('a'+j));
                }
                String uniqKey =sb.toString();

                List<String> listTmp=maps.getOrDefault(uniqKey, new ArrayList<String>());
                listTmp.add(item);
                maps.put(uniqKey, listTmp);
            }
            return new ArrayList<List<String>> (maps.values());

        }
}
