package algo;

import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<int[]> queue=new PriorityQueue<int[]>((a, b)->a[0]!=b[0]?b[0]-a[0]:b[1]-a[1]);
    int topK=0;

    public KthLargest(int k, int[] nums) {
        this.topK=k;

    }

    public int add(int val) {
//        queue.stream().findAny(it[0]==val).orElse()
return -1;
    }
}

