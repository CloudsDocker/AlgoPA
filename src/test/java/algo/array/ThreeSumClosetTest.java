package algo.array;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreeSumClosetTest {

    @Test
    public void test() {
        final val threeSumCloset = new ThreeSumCloset();

        int[] array = {-1, 2, 1, -4};
        array = new int[]{0, 1, 2};
        int target = 3;
        int expect = 3;

        assertEquals(expect, threeSumCloset.threeSumClosestLC(array, target));
    }

    @Test
    public void testLC() {
        final val threeSumCloset = new ThreeSumCloset();

        int[] array = {-1, 2, 1, -4};
        array = new int[]{0, 1, 2};
        int target = 3;
        int expect = 3;

        assertEquals(expect, threeSumCloset.threeSumClosestLC(array, target));
    }

    @Test
    public void testDummy() {
        final val threeSumCloset = new ThreeSumCloset();

        int[] array = {-1, 2, 1, -4};
        array = new int[]{0, 1, 2};
        int target = 3;
        int expect = 3;

        assertEquals(expect, threeSumCloset.threeSumClosestDummy(array, target));
    }
}