package algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{7,9}};
        int[] newInterval = new int[] {2,5};

        System.out.println("==:"+new InsertInterval().insert(intervals,newInterval));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ni=new LinkedList();
        int i=0;
        //existing interval starts and ends before candidate starts
        while(i<intervals.length &&  intervals[i][0]<newInterval[0] && intervals[i][1]<newInterval[0]) {
            ni.add(intervals[i]);
            i++;
        }
        //add the candidate interval
        ni.add(newInterval);

        //start merging for anything where candidate DOES NOT start and end after the existing interval
        while(i<intervals.length &&
                ! (  intervals[i][0]> ni.get(ni.size()-1)[1] && intervals[i][1]>ni.get(ni.size()-1)[1])) {
            int s=Math.min(intervals[i][0],ni.get(ni.size()-1)[0]) ;
            int e=Math.max(intervals[i][1],ni.get(ni.size()-1)[1]) ;
            ni.get(ni.size()-1)[0]=s;
            ni.get(ni.size()-1)[1]=e;
            i++;
        }
        //add all the remaining ones where candidate starts and ends after the existing interval ended
        while(i<intervals.length) {
            ni.add(intervals[i]);
            i++;
        }
        System.out.println(Arrays.deepToString(ni.toArray()));
        return ni.toArray(new int[ni.size()-1][]);
    }

}
