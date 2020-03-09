package algo;

import java.util.ArrayList;
import java.util.List;

public class partitionLabels {

    /*
    763. Partition Labels
Medium

1646

78

Add to List

Share
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
     */
//    traverse the string record the last index of each char.
//    using pointer to record end of the current sub string.
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }
    
    public List<Integer> partitionLabels(String S) {
        Integer[] positions = new Integer[26];
        char[] chs = S.toCharArray ();
        for (int i = 0; i < chs.length; i++)
            positions[chs[i] - 'a'] = i;
        List<Integer> resLs = new ArrayList<> ();
        int pos = 0, end = 0, anchor = 0;
        while (pos < chs.length) {
            end = Math.max (positions[chs[pos] - 'a'], end);
            if (pos == end) {
                resLs.add (pos - anchor + 1);
                anchor = pos + 1;
            }
            pos++;
        }
        return resLs;
    }
}
