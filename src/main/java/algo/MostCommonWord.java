package algo;

import java.util.*;

public class MostCommonWord {

    /*
    4 steps:

remove all punctuations
change to lowercase
words count for each word not in banned set
return the most common word
     */
    public String mostCommonWord(String p, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = p.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String w : words) {
            if (!ban.contains(w)) {
                count.put(w, count.getOrDefault(w, 0) + 1);
            }}
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
