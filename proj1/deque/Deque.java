package deque;

/* The Deque interface defines the expected behavior for any
* Deque, whether it is an ArrayDeque or LinkedListDeque. A
* Deque is a doubly-ended queue, that allows you to quickly add
* and remove items from the front and back. */
public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);


    int size();

    /** Default implementation. Returns true if size() is 0 */
    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    void printDeque ();

    T removeFirst ();

    T removeLast ();

    T get ( int index);

    boolean equals (Object o);

}
