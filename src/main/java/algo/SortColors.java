package algo;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] colors = new int[]{2,0,1,0,2,1,0};
        sortColors(colors,colors.length);
        System.out.println(Arrays.toString(colors));

    }

    // one pass in place solution
    static void  sortColors(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0)
            {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            }
            else if (A[i] == 1)
            {
                A[++n2] = 2; A[++n1] = 1;
            }
            else if (A[i] == 2)
            {
                A[++n2] = 2;
            }
        }
    }
}
