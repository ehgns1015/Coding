import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
/**
  * Simple test cases for a max heap.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author Dohun
  * @version 1.0
  */
public class MinHeapStudentTests {

    private static final int TIMEOUT = 200;
    private MinHeap<Integer> minHeap;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        /*
                25                   10
               /  \                 /  \
              10  30     --->      25  30
             /  \                 /  \
            35  40               35  40
        */
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(35);
        passedIn.add(40);

        Integer[] expected = new Integer[11];
        expected[1] = 10;
        expected[2] = 25;
        expected[3] = 30;
        expected[4] = 35;
        expected[5] = 40;

        minHeap = new MinHeap<>(passedIn);
        assertEquals(5, minHeap.size());
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                15
               /  \
              17  43
             /  \
            64  89
        */
        minHeap.add(43);
        minHeap.add(15);
        minHeap.add(64);
        minHeap.add(17);
        minHeap.add(89);

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = 15;
        expected[2] = 17;
        expected[3] = 43;
        expected[4] = 64;
        expected[5] = 89;
        assertEquals(5, minHeap.size());
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                89
               /  \                 64
              64  43     --->      /
             /  \                 89
            15  17
        */
        minHeap.add(43);
        minHeap.add(15);
        minHeap.add(64);
        minHeap.add(17);
        minHeap.add(89);

        assertEquals((Integer) 15, minHeap.remove());
        assertEquals((Integer) 17, minHeap.remove());
        assertEquals((Integer) 43, minHeap.remove());
        assertEquals(2, minHeap.size());

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = new Integer(64);
        expected[2] = new Integer(89);
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMiscellaneous() {
        /*
                15
               /  \
              17  43
             /  \
            64  89
        */
        assertEquals(true, minHeap.isEmpty());
        minHeap.add(43);
        minHeap.add(15);
        minHeap.add(64);
        minHeap.add(17);
        minHeap.add(89);

        assertEquals(false, minHeap.isEmpty());
        assertEquals((Integer) 15, minHeap.getMin());

        minHeap.clear();
        assertEquals(true, minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[MinHeap.INITIAL_CAPACITY],
            minHeap.getBackingArray());
    }
}