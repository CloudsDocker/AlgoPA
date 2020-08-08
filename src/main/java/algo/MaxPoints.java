package algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxPoints {
    public static void main(String[] args) {
        int[][] data= new int[][]{{1,4},{1,1},{2,2},{3,3}};
        System.out.println("max points:"+new MaxPoints().maxPoints(data));
    }

    public int maxPoints(int[][] points) {
        Arrays.sort(points,(a, b)->a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]);
        Set<String> vivisted=new HashSet<String>();

        int n=points.length, incre=0, len =1, maxLen=1;
        for(int i=0;i<n;i++){
            int[] point=points[i];
            String id=point[0]+"_"+point[1];
            if(vivisted.contains(id)) continue;
            vivisted.add(id);

            //check direction by next point
            if(i<n-1){
                int[] next=points[i+1];
                if(next[1]==point[1]+1){
                    // move upwards
                    incre=1;
                    len++;
                }else if(next[1]==point[1]-1){
                    // move downwards
                    incre=-1;
                    len++;
                }else if(next[1]==point[1]){
                    // move horizentally
                    incre=0;
                    len++;
                }else{
                    // not part of a line
                    len=1;
                }
            }

        }
        return 0;
    }
}
