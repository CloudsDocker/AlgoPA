package algo;


import com.google.common.collect.Lists;

import java.util.*;

public class WordLadderII {
    /*
    126. Word Ladder II
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

1. Only one letter can be changed at a time
2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

- Return an empty list if there is no such transformation sequence.
- All words have the same length.
- All words contain only lowercase alphabetic characters.
- You may assume no duplicates in the word list.
- You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */



    // explainantion
    /*
    The basic idea is:
1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node, and store node's next level neighbors to HashMap;
2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the distance of the next level node equals the distance of the current node + 1.
     */
//    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//return null;
//    }

    public static void main(String[] args) {
        System.out.println(new WordLadderII().findLadders("hit","cog", Lists.newArrayList("hot","dot","dog","lot","log","cog")));
    }
    /**
     * High level design: BFS + DFS
     *
     * Step 1: use BFS to build graph (adjacency list of each word), as well as calculating distance from beginWord to
     * each node in the graph (should store minimum distance)
     *
     * Step 2: use DFS to traverse and record path from beginWord to endWord with shortest path. We can use distance map
     * to control every next word.
     * */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) {
            return new ArrayList<>();
        }

        /**
         * @param result: result list to store final return list
         * @param graph: adjacency list of key - store all neighbors of each word (neighbor means all words in dictionary
         *             that only has one character difference with key)
         * @param distance: distance between beginWord and current key word, used for tracing path when we do DFS
         * @param dict: word dictionary, efficient for searching purpose
         * */
        List<List<String>> result = new ArrayList<>();
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        Set<String> dict = new HashSet<>(wordList);

        bfs(beginWord, endWord, dict, graph, distance);
        dfs(result, graph, distance, endWord, beginWord, new ArrayList<>(Arrays.asList(beginWord)));
        return result;
    }

    // step 1
    public void bfs(String beginWord, String endWord, Set<String> dict, Map<String, Set<String>> graph, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            boolean reachEnd = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();

            /* try all possible substitution (26 characters) in every position of current word, if newWord exists in dictionary,
               we add it to the adjacency list of curWord */
                for (int j = 0; j < curWord.length(); j++) {
                    char[] curWordArr = curWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        curWordArr[j] = c;
                        String newWord = new String(curWordArr);
                        if (dict.contains(newWord)) {
                            graph.putIfAbsent(curWord, new HashSet<>());    //graph is used for BFS to record its neighbor
                            graph.get(curWord).add(newWord);    //curWord: log, added newWord: cog
                        }
                    }
                }

                // traverse all neighbors of current word, update distance map and queue for next ladder (level)
                // WARNING: DO NOT USE visited set, since it is hard to deal with end word if endWord is visited
                for (String neighbor : graph.get(curWord)) {// curWord: dot, graph:[lot,dot,hot,dog]
                    if (!distance.containsKey(neighbor)) {  //distance: [log:2,hit:0,log:3,dot:1,hot:1]
                        distance.put(neighbor, distance.get(curWord) + 1);// add "dog:3"
                        if (neighbor.equals(endWord)) {
                            reachEnd = true;
                        }
                        else {
                            queue.offer(neighbor);
                        }
                    }
                }
                if (reachEnd) {
                    break;
                }
            }
        }
    }

    // step 2
    // search backtrack
    public void dfs(List<List<String>> result, Map<String, Set<String>> graph, Map<String, Integer> distance,
                    String endWord, String curWord, List<String> tempList) {
        if (curWord.equals(endWord)) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (String nextWord : graph.get(curWord)) {// graph contains all adjcent neighbour of each node of the dict
            // only if next node is on the minimum path to the endWord, we can traverse it
            if (distance.get(nextWord) == distance.get(curWord) + 1) {
                tempList.add(nextWord);
                dfs(result, graph, distance, endWord, nextWord, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }



}
