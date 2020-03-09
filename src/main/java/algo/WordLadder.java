package algo;

import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class WordLadder {
/*
127. Word Ladder
Medium

2478

1010

Add to List

Share
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
        public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
            Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

            int len = 1;
            int strLen = beginWord.length();
            HashSet<String> visited = new HashSet<String>();

            beginSet.add(beginWord);
            endSet.add(endWord);
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {// make sure beginSet is smaller, so we only loop small numbers of elements below
                    Set<String> set = beginSet;
                    beginSet = endSet;
                    endSet = set;
                }

                Set<String> temp = new HashSet<String>();
                for (String word : beginSet) {
                    char[] chs = word.toCharArray();

                    for (int i = 0; i < chs.length; i++) {
                        for (char c = 'a'; c <= 'z'; c++) {
                            char old = chs[i];
                            chs[i] = c;
                            String target = String.valueOf(chs);

                            if (endSet.contains(target)) {
                                return len + 1;
                            }

                            if (!visited.contains(target) && wordList.contains(target)) {
                                temp.add(target);
                                visited.add(target);
                            }
                            chs[i] = old;
                        }
                    }
                }

                beginSet = temp;
                len++;
            }

            return 0;
        }

    public static void main(String[] args) {
        WordLadder inst = new WordLadder();
        System.out.println("===:"+inst.ladderLength("hit","cog", Sets.newHashSet("hot","dot","dog","lot","log")));
    }
}
