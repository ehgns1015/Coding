import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * These tests are not exhaustive.
 * @author dyeo8
 * @version 1.0
 */
public class OptionTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testDeepestBranches() {
        /*
                                10
                            /        \
                           5          15
                         /   \      /    \
                        2     7    13    20
                       / \   / \     \  / \
                      1   4 6   8   14 17  25
                     /           \          \
                    0             9         30
         */

        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(2);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(8);
        avlTree.add(14);
        avlTree.add(17);
        avlTree.add(25);
        avlTree.add(0);
        avlTree.add(9);
        avlTree.add(30);

        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(5);
        expected.add(2);
        expected.add(1);
        expected.add(0);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        expected.add(15);
        expected.add(20);
        expected.add(25);
        expected.add(30);

        assertEquals(expected, avlTree.deepestBranches());
    }

    @Test(timeout = TIMEOUT)
    public void testSortedInBetween() {
        /*
                                10
                            /        \
                           5          15
                         /   \      /    \
                        2     7    13    20
                       / \   / \     \  / \
                      1   4 6   8   14 17  25
                     /           \          \
                    0             9         30
         */

        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(2);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(8);
        avlTree.add(14);
        avlTree.add(17);
        avlTree.add(25);
        avlTree.add(0);
        avlTree.add(9);
        avlTree.add(30);

        List<Integer> expected = new ArrayList<>();
        expected.add(8);
        expected.add(9);
        expected.add(10);
        expected.add(13);

        assertEquals(expected, avlTree.sortedInBetween(
            new Integer(7), new Integer(14)));
    }
}