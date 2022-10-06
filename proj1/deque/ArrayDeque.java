package deque;

public class ArrayDeque<T> implements Deque<T> {

    private int size; //how many value in the array
    private T[] arr;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        arr = (T[]) new Object[8]; //the length of the skeleton
        size = 0;
        nextFirst = 0;
        nextLast = 1; //give space to nextFirst
    }

    /**
     * Helper function: resize the array
     * Create a new array with new size, copy the value from original array to the new one
     *
     * @return
     */
    private T[] resize(int capacity) { //from tips slides
        /* [3] [1] [2] [4] [5] [9] [8] [7]
           0   1    2   3   4   5   6   7
           NF = 4, NL = 5, size = 8, arr.length = 8
           expected [9] [8] [7] [3] [1] [2] [4] [5] [] [] [] [] [] [] [] []
                     0   1   2   3   4   5   6   7  8  9  10 11 12 13 14 15
                     NF = 15, NL = 8, size = 8, arr.length = 16
         */
        T[] resized = (T[]) new Object[capacity];
        for (int i = nextFirst + 1, j = 0; i <= nextFirst + size; i++) {
            if (j >= size) {
                break;
            }
            int index = i;
            if (index >= arr.length) {
                index = index - arr.length;
            }
            resized[j] = arr[index];
            j++;
        }
        nextFirst = capacity - 1;
        nextLast = size;
        return resized;
    }



    @Override
    /** Adds an item of type T to the front of the deque.
     * You can assume that item is never null. */
    public void addFirst(T item) {
        if (size == arr.length) { //no space in the array
            arr = resize(size * 2); //multiply factor
        }

        if (nextFirst < 0) {
            nextFirst += arr.length;  //go to the back
        }

        arr[nextFirst] = item;
        size++;
        nextFirst--;
    }



    @Override
    /** Adds an item of type T to the back of the deque.
     * You can assume that item is never null. */
    public void addLast(T item) {
        if (size == arr.length) { //no space in the array
            arr = resize(size * 2);
        }

        if (nextLast > arr.length - 1) { // go to the front
            nextLast = nextLast - arr.length;
        }

        arr[nextLast] = item;
        size++;
        nextLast++;
    }


    @Override
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }


    @Override
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        /* [e] [f] [] [g] [c] [a] [b] [d]
           0   1   2   3   4   5   6   7
           NF = 2; NL = 1; size = 7; arr.length = 8
           expected: g c a b d e f
         */

        int lastIndex = nextFirst + size; //9
        String result = "";
        for (int i = nextFirst + 1; i < lastIndex; i++) {
            int index = i;
            if (index >= arr.length) {
                index -= arr.length;
            }
            result = result + arr[index] + " ";
        }
        if (lastIndex >= arr.length) {
            lastIndex -= arr.length;
        }
        result = result + arr[lastIndex] + "\n"; //print last item
        System.out.print(result);
        System.out.println();
    }


    @Override
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        //resize down
        if (isEmpty()) { //deque cannot be empty (f010, fillEmptyFill test)
            arr = resize(8);
        }

        //tips slides: half array size when size/item.length < 0.25
        if ((arr.length > 8) && (size < arr.length / 2)) { //has already been resize larger but not efficient in space
            arr = resize((int) arr.length / 2); //efficient space
        }

        if (isEmpty()) {
            return null; //do nothing
        }

        //-1 % 8, java get negative, use Math.floorMod, from lab03
        int removeIndex = Math.floorMod((nextFirst + 1), arr.length);
        T value = arr[removeIndex];
        arr[removeIndex] = null;
        size--;
        nextFirst = removeIndex;
        return value;
    }


    @Override
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) { //deque cannot be empty
            arr = resize(8);
        }

        //tips slides: half array size when size/item.length < 0.25
        if ((arr.length > 8) && (size < arr.length / 2)) {//has already been resize larger but not efficient in space
            arr = resize((int) arr.length / 2); //efficient space
        }

        if (isEmpty()) {
            return null;
        }

        int removeIndex = Math.floorMod((nextLast - 1), arr.length);
        T value = arr[removeIndex];
        arr[removeIndex] = null;
        size--;
        nextLast = removeIndex;
        return value;
    }


    @Override
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque! */

    public T get(int index) { //constant time
        if (index >= arr.length || isEmpty()) {
            return null;
        }
        /* [e] [f] [] [g] [c] [a] [b] [d]
           0   1   2   3   4   5   6   7
           NF = 2; NL = 1; size = 7; arr.length = 8
           expected: arr[0] = g (index = 3)
        */
        return arr[(nextFirst + 1 + index) % arr.length]; //mod allows to apply to arr with proportional length
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
            /* use equals to solve DeepEqual test
               from classmates: == doesn't work on int > 128
             */
            if (!this.get(i).equals(cpo.get(i))) {
                return false; //one doesn't match, return false; if one item match, doesn't return true
            }
        }
        return true;
    }


}