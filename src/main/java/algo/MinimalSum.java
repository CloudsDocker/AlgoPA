package algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MinimalSum {

    public static void main(String[] args) {
        System.out.println(minimalSum(Arrays.asList(5,20,7),4));
    }

    public static long minimalSum(List<Integer> data, int ktests) {
        if (data.size() == 0) {
            return 0;
        }

        int index = 0;
        int max = Integer.MAX_VALUE;
        while (ktests > 0) {
            int currentValue = data.get(index);
            if (currentValue <= max) {
                data.sort(Comparator.reverseOrder());
                index = 0;
                max = data.get(0) / 2;
                continue;
            }
            ktests--;

            int roundUp = currentValue & 1;// 1 is 0001, so & 1 means get 1 for odd number (end with 1) and return 0 for even number
            currentValue /= 2;
            currentValue += roundUp;

            data.set(index, currentValue);
            if (index + 1 < data.size()) {
                index++;
            }
        }
//        return data.stream().reduce(0,Integer::sum);
//        return data.stream().reduce(0,(a,b)->a+b);
//        return data.stream().collect(Collectors.summingLong(Integer::intValue));
        return (long) data.stream().mapToInt(Integer::intValue).sum();

//        return data.stream().mapToLong(a->a).sum();
    }
}
