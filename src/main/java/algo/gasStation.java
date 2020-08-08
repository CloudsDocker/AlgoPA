package algo;

public class gasStation {
    public static void main(String[] args) {
        int[] gas={5,1,2,3,4};
        int[] cost ={4,4,1,5,1};
//        int[] gas={3,1,1};
//        int[] cost ={1,2,2};
        System.out.println(new gasStation().canCompleteCircuit(gas,cost));
    }

    //LC1 first solution
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }
    }


    //Runtime: 15 ms, faster than 24.77% of Java online submissions for Gas Station.
    // my solution
    public int canCompleteCircuit_todd(int[] gas, int[] cost) {

        if(gas==null || cost ==null || gas.length<1 || cost.length<1) return -1;
        int len=gas.length, total=0;
        int[] diff=new int[len];
        int nPositive=0;
        for(int i=0;i<len;i++){
            int nDiff=gas[i]-cost[i];
            if(nDiff>0) nPositive++;
            diff[i]=nDiff;
            total+=nDiff;
        }
        // fast path
        if(total<0) return -1;


        //check positive numbers only
        for(int i=0;i<len;i++){
            // only possible if current 'diff' is positive
            if(diff[i]>=0){
                if(nPositive==1) return i;
                // traverse whole array to prove
                int tmpTank=diff[i];
                int j=i;
                while(true){
                    j++;
                    if(j==len){
                        j=0;
                    }

                    if(j==i){  //[!!!!] NOT " else if", it should separated from above if (j==len),otherwise 1st position is 0
                        // come back of while trip
                        return i;
                    }

                    tmpTank+=diff[j];
                    if(tmpTank<0){
                        break;
                    }
                }
            }
        }
        return -1;
    }
}
