package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


/** Performs some basic linked list deque tests. */
public class LinkedListDequeTest {

    /**
     * You MUST use the variable below for all of your tests. If you test
     * using a local variable, and not this static variable below, the
     * autograder will not grade that test. If you would like to test
     * LinkedListDeques with types other than Integer (and you should),
     * you can define a new local variable. However, the autograder will
     * not grade that test. Please do not import java.util.Deque here!
     */

    public static Deque<Integer> lld = new LinkedListDeque<Integer>();

    public static Deque<String> llds = new LinkedListDeque<String>();


    @Test
    /** Adds a few things to the list, checks that isEmpty() is correct.
     * This is one simple test to remind you how junit tests work. You
     * should write more tests of your own.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        assertTrue("A newly initialized LLDeque should be empty", lld.isEmpty());
        lld.addFirst(0);

        assertFalse("lld1 should now contain 1 item", lld.isEmpty());

        lld = new LinkedListDeque<Integer>(); //Assigns lld equal to a new, clean LinkedListDeque!
    }

    /**
     * Adds an item, removes an item, and ensures that dll is empty afterwards.
     */
    @Test
    public void addRemoveTest() {
        lld = new LinkedListDeque<Integer>();

        lld.addFirst(1);
        assertEquals(1, lld.size());

        lld.removeFirst();
        assertTrue(lld.isEmpty());
    }


    /**
     * Make sure that removing from an empty LinkedListDeque does nothing
     */
    @Test
    public void removeEmptyTest() {
        lld = new LinkedListDeque<Integer>();

        lld.removeLast();
        assertTrue(lld.isEmpty());
    }


    /**
     * Test add, remove and print behavior
     */
    @Test
    public void addTest() {
        lld = new LinkedListDeque<Integer>();

        lld.addFirst(1);
        lld.addFirst(2);
        lld.addLast(3);
        assertEquals(2, (int) lld.get(0));
        assertEquals(3, (int) lld.get(2));

        lld.removeLast();
        assertEquals(2, (int) lld.removeFirst()); //test return value
        assertEquals(1, (int) lld.get(0));
        lld.printDeque();

    }
    @Test
    public void randomAdd() {
        lld = new LinkedListDeque<Integer>();
        lld.addFirst(0);
        lld.removeLast();
        lld.addFirst(2);
        assertEquals(2, (int) lld.removeLast());
    }

    /**
     * Make sure your LinkedListDeque also works on non-Integer types
     */
    @Test
    public void multipleParamsTest() {
        llds = new LinkedListDeque<String>();

        llds.addFirst("a");
        llds.addFirst("b");
        llds.addLast("c");
        assertEquals("c", llds.get(2));

        llds.removeLast();
        llds.removeFirst();
        assertEquals("a", llds.get(0));
    }


    /**
     * Make sure that removing from an empty LinkedListDeque returns null
     */
    @Test
    public void emptyNullReturn() {
        lld = new LinkedListDeque<Integer>();
        assertTrue(lld.removeLast() == null);
    }
    /** TODO: Write tests to ensure that your implementation works for really large
     * numbers of elements, and test any other methods you haven't yet tested!
     */
    //test equal, print, edge case, emptynullreturn number? string (null)

    //LinkedListDeque and ArrayDeque with same length and value, test print
    @Test
    public void testEquals(){
        lld = new LinkedListDeque<Integer>();
        Deque<Integer> ad = new ArrayDeque<Integer>();

        //expected: 213
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addLast(3);
        lld.printDeque();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addLast(3);
        ad.printDeque();
        assertTrue(lld.equals(ad));
    }


}