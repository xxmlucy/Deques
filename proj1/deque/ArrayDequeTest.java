package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/* Performs some basic array deque tests. */
public class ArrayDequeTest {

    /** You MUST use the variable below for all of your tests. If you test
     * using a local variable, and not this static variable below, the
     * autograder will not grade that test. If you would like to test
     * ArrayDeques with types other than Integer (and you should),
     * you can define a new local variable. However, the autograder will
     * not grade that test. */

    public static Deque<Integer> ad = new ArrayDeque<Integer>();
    public static Deque<String> ads = new ArrayDeque<String>();

    @Test
    //test String type for basic operations (add, remove, get)
    public void operation() {
        ads = new ArrayDeque<String>();
        ads.addLast("a");
        ads.addLast("b");
        ads.addFirst("c");
        ads.addLast("d");
        ads.addLast("e");
        ads.addFirst("f");
        ads.removeFirst();
        ads.addFirst("g");
        assertEquals(6, ads.size());
        assertEquals("g", ads.get(0));
        assertEquals("c", ads.get(1));
        assertEquals("a", ads.get(2));
        assertEquals("b", ads.get(3));
        assertEquals("d", ads.get(4));
        assertEquals("e", ads.get(5));
        ads.addLast("f");
        ads.printDeque();
    }

    //test Integer type for basic operations, removeLast, print, exceed length case
    @Test
    public void intOperation() {
        ad = new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addFirst(6);
        ad.removeFirst();
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9);
        ad.addFirst(10);
        ad.addFirst(11);
        ad.removeLast();
        ad.printDeque(); //11 10 9 8 7 3 1 2 4
    }

    //LinkedListDeque and ArrayDeque with same length and value
    @Test
    public void testEquals(){
        Deque<Integer> lld = new LinkedListDeque<Integer>();
        ad = new ArrayDeque<Integer>();

        //expected: 213
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addLast(3);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addLast(3);
        assertTrue(ad.equals(lld));
    }

    @Test
    public void random1() {
        ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addFirst(1);
        ad.removeLast();
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);
        ad.removeLast();
        ad.printDeque(); //7 6 5 4 3
    }

    @Test
    public void random2() {
        ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        ad.addFirst(1);
        assertEquals(0, (int) ad.get(1));
        assertEquals(0, (int) ad.removeLast());
        assertEquals(1, (int) ad.removeLast());
        ad.addLast(5);
        ad.addFirst(6);
        assertEquals(6, (int) ad.removeFirst());
        assertEquals(5, (int) ad.get(0));
        assertEquals(5, (int) ad.get(0));
        ad.addFirst(10);
        assertEquals(10, (int) ad.removeFirst());
        assertEquals(5, (int) ad.removeLast());
        ad.addFirst(13);
        ad.addFirst(14);
        ad.addFirst(15);
        ad.removeLast();
    }

    @Test
    public void random3() {
        ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        assertEquals(0, (int) ad.removeFirst());
        ad.isEmpty();
        ad.addFirst(3);
        assertEquals(3, (int) ad.removeFirst());
        ad.addFirst(5);
        assertEquals(5, (int) ad.removeFirst());
        ad.addFirst(7);
        ad.addFirst(8);
        assertEquals(8, (int) ad.removeFirst());
        ad.removeFirst();
    }

    @Test
    public void basicGet() {
        ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        assertEquals(0, (int) ad.removeLast());
        ad.addFirst(2);
        ad.addFirst(3);
        assertEquals(3, (int) ad.removeFirst());
        ad.removeFirst();
    }

    @Test
    // (f010) Inserting a bunch of stuff to the front, removing it from the front, and insertFronting it back again
    public void fillEmptyFill() {
        ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9);
        ad.addFirst(10);
        ad.addFirst(11);
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9);
        ad.addFirst(10);
        ad.addFirst(11);
        ad.printDeque();

    }
}

