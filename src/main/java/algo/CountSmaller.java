package algo;

import java.util.*;

public class CountSmaller {
    public static void main(String[] args) {

        int[] data=new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
//   output     [10,14,10,1,12,22,29,6,3,2,13,2,10,6,12,5,19,4,22,14,17,6,14,5,14,3,0,12,0,9,10,4,0,0,5,3,2,0,1,0]
  // expect     [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
        System.out.println(new CountSmaller().countSmaller(data));
    }

        public List<Integer> countSmaller(int[] nums) {

            if(nums==null || nums.length<1) return new ArrayList<Integer>();
            //[1,2,5,6]
            Map<Integer,Integer> mapPos=new HashMap();
            Map<Integer,List<Integer>> mapPosDup=new HashMap();
            int len=nums.length;
            int[] ori=nums.clone();
            // int[] rtn=new int[len]; // should NOT int[], the expected return is List<Integer>
            List<Integer> rtn=new ArrayList();
            for(int i=0;i<len;i++){
                if(mapPos.containsKey(nums[i])){
                    //duplicate
                    mapPosDup.getOrDefault(nums[i], new ArrayList<>()).add(i);
                    mapPos.remove(nums[i]);
                }else {
                    mapPos.put(nums[i], i);//[!!!!!!] bug: here is a bug that array may contains duplicate
                }
            }
            Map<Integer,Integer> mapCnt=new HashMap();
            // Map<Integer,List<Integer>> mapSmallers=new HashMap();
            Arrays.sort(nums);
            for(int i=0;i<len;i++){
                int cnt=0;
                for(int j=0;j<i;j++){
                    if(mapPos.containsKey(nums[j])) {
                        if (mapPos.get(nums[j]) > mapPos.get(nums[i])) {
                            cnt++;
                        }
                    }else if(mapPosDup.containsKey(nums[j])){
//                        mapPosDup.get(nums[j]).stream().filter(it->it>mapPos.get(nums[i]))
                    }
                }
                mapCnt.put(nums[i],cnt);
            }

            for(int i=0;i<len;i++){
                int num=ori[i];
                rtn.add(mapCnt.get(num));
            }
            return rtn;
        }

    public List<Integer> countSmaller2(int[] nums) {

        if(nums==null || nums.length<1) return null;
        //[1,2,5,6]
        Map<Integer,Integer> map=new HashMap();
        int len=nums.length;
        int[] ori=nums.clone();
        // int[] rtn=new int[len]; // should NOT int[], the expected return is List<Integer>
        List<Integer> rtn=new ArrayList();
        Arrays.sort(nums);
        for(int i=0;i<len;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<len;i++){
            int num=ori[i];
            rtn.add(map.get(num));
        }
        return rtn;
    }
}
