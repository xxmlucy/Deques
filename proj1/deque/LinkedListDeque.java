package deque;

public class LinkedListDeque<T> implements Deque<T> {

    private class lldNode {

        public T item;
        public lldNode prev, next;

        public lldNode(lldNode prev, T item, lldNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size = 0;
    private lldNode sentinel;

    /**
     * Creates an empty linked list deque.
     */
    //use circular sentinel
    public LinkedListDeque() {
        sentinel = new lldNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    /** Adds an item of type T to the front of the deque.
     * You can assume that item is never null. */
    public void addFirst(T item) {
        lldNode newNode = new lldNode(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode; //connect the next node first, or the lld will break, order matters
        sentinel.next = newNode;
        size++;
    }

    @Override
    /** Adds an item of type T to the back of the deque.
     * You can assume that item is never null. */
    public void addLast(T item) {
        lldNode newNode = new lldNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }

    @Override
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        lldNode track = sentinel; //track the position

        for (int i = 0; i < size() - 1; i++) {
            System.out.print(track.next.item + " ");
            track = track.next;
        }
        System.out.print(track.next.item + "\n");
        System.out.println();
    }


    @Override
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) { //no item exists
            return null;
        } else {
            T rmvItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return rmvItem;
        }
    }


    @Override
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) { //no item exists
            return null;
        } else {
            T rmvItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return rmvItem;
        }
    }


    @Override
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque! */
    public T get(int index) {
        if (size() <= index || isEmpty() || index < 0) {
            return null;
        } else {
            lldNode track = sentinel;
            for (int i = 0; i <= index; i++) {
                track = track.next;
            }
            return track.item;
        }
    }

    /**
     * Helper function: locate the node with given index
     */
    private T recursiveHelper(lldNode track, int index) {
        if (index == 0) {
            return track.item;
        } else {
            return recursiveHelper(track.next, index - 1);
        }
    }

    /** Same as get, but uses recursion.*/

    public T getRecursive(int index) {
        if (index >= size() || isEmpty() || index < 0) {
            return null;
        } else {
            return recursiveHelper(sentinel.next, index);
        }
    }

    @Override
    /** Returns whether or not the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order. */

    public boolean equals(Object o) {
        //null doesn't match
        if (o == null) {
            return false;
        }

        if (!(o instanceof Deque)) { //from lab 5, check type is Deque
            return false;
        }

        Deque<T> cpo = (Deque<T>) o; //cast， move below since non Deque cannot cast. (c016)

        if (cpo.size() != size) { //size doesn't match
            return false;
        }

        for (int i = 0; i < size ; i++) { //contents doesn't match
            if (!this.get(i).equals(cpo.get(i))) {
                return false; //one doesn't match, return false; if one item match, doesn't return true
            }
        }
        return true;
    }

}



