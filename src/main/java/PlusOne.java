import java.util.Arrays;

//plusOneSolutions.java
public class PlusOne {
	public static void main(String[] args) {
		System.out.println("=== Start ====");
		final int[] ary = new int[] { 1, 9 };
		System.out.println("Input is :"+Arrays.toString(ary));
		System.out.println("the result of mime is :"
				+ Arrays.toString(plusOneMyOwnWorkable(Arrays.copyOf(ary,ary.length))));
		System.out.println("the result of better solution is :"
				+ Arrays.toString(plusOneBetter(Arrays.copyOf(ary,ary.length))));
		System.out.println("the result of best solution is :"
				+ Arrays.toString(plusOneBest(Arrays.copyOf(ary,ary.length))));
	}


	private static int[] plusOneBest(int[] ary) {
		for (int i = ary.length - 1; i >= 0; i--) {
			if (ary[i] != 9) {
				ary[i]++; //[!] Here is key step, for two cases: (1) last digit, add one then exit (2) Next digit with carry, add on and exit
				break;
			} else {
				ary[i] = 0;
			}
		}

		if (ary[0] == 0) {
			int[] aryRtn = new int[ary.length + 1];
			System.arraycopy(aryRtn, 1, ary, 0, ary.length);
			aryRtn[0] = 1;
			return aryRtn;
		} else {
			return ary;
		}
	}

	private static int[] plusOneBetter(int[] ary) {
		int one = 1;
		int sum = 0;
		for (int i = ary.length - 1; i > -1; i--) {
			sum = one + ary[i];
			one = sum / 10;
			ary[i] = sum % 10;
		}

		if (one > 0) {
			int[] aryRtn = new int[ary.length + 1];
			System.arraycopy(aryRtn, 1, ary, 0, ary.length);
			aryRtn[0] = 1;
			return aryRtn;
		} else {
			return ary;
		}

		// return j+1;
	}

	private static int[] plusOneMyOwnWorkable(int[] ary) {
		int carry = 0;
		for (int i = ary.length - 1; i > -1; i--) {
			if (ary[i] == 9) {
				ary[i] = 0;
				carry = 1;
			} else {
				// ary[i]+=carry+1;
				ary[i] += 1;
				carry = 0;
				break;
			}
		}
		if (carry == 1) {
			int[] aryRtn = new int[ary.length + 1];
			System.arraycopy(aryRtn, 1, ary, 0, ary.length);
			aryRtn[0] = 1;
			return aryRtn;
		} else {
			return ary;
		}

		// return j+1;
	}

}
