import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * We shall implement the Deque operator as a linked list
 * 
 * @author kabeer
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    
    private Node mFirst;
    private Node mLast;
    private int mCount;
    
    private class Node {
        private Item data;
        private Node next;
        private Node prev;
    }

    public Deque() {
        // construct an empty deque
        mFirst = null;
        mLast = null;
        mCount = 0;
    }
    public boolean isEmpty() {
        // is the deque empty?
        return mCount == 0;
    }
    public int size() {
        // return the number of items on the deque
        return mCount;
    }
    public void addFirst(Item item) {
        // insert the item at the front
        if (item == null) throw new NullPointerException();
        
        Node n = new Node();
        n.data = item;
        n.next = mFirst;
        n.prev = null;
        
        
        if (mFirst != null) mFirst.prev = n;
        
        mFirst = n;
        
        if (mLast == null) mLast = mFirst;
        mCount++;
    }
    public void addLast(Item item) {
        // insert the item at the end
        if (item == null) throw new NullPointerException();
        Node n = new Node();
        n.data = item;
        n.next = null;
        n.prev = mLast;
        
        if (mLast != null) mLast.next = n;
        
        mLast = n;
        
        if (mFirst == null) mFirst = mLast;
        
        mCount++;
    }
    public Item removeFirst() {
        // delete and return the item at the front
        if (mCount == 0) throw new NoSuchElementException();
        
        if (mFirst != null) {
            Item first = mFirst.data;
            mFirst = mFirst.next;
            if (mFirst != null) {
                mFirst.prev = null;
            } else {
                mLast = null;
            }
            mCount--;
            return first;
        } else {
            return null;
        }
        

    }
    public Item removeLast() {
        // delete and return the item at the end
        if (mCount == 0) throw new NoSuchElementException();
        
        if (mLast != null) {
            Item last = mLast.data;
            mLast = mLast.prev;
            if (mLast != null) {
                mLast.next = null;
            } else {
                mFirst = null;
            }
            mCount--;
            return last;
        } else {
            return null;
        }
    }
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        Iterator<Item> iterator = new Iterator<Item>() {
            private Node mCurrent = mFirst;
            
            public boolean hasNext() {
                return mCurrent != null;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = mCurrent.data;
                mCurrent = mCurrent.next;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }

    public static void main(String[] args) {
        // unit testing
        Deque<String> deques = new Deque<String>();
        deques.addFirst("Kabeer");
        deques.addFirst("is");
        deques.addLast("a");
        deques.addFirst("brother");
        deques.addLast("wow");
        
        for (String d : deques) {
            System.out.println(d);
        }
        
        System.out.println("Size: "+deques.size());
        String last = deques.removeLast();
        System.out.println("Last: "+last);
        System.out.println("Size: "+deques.size());
        
        for (String d : deques) {
            System.out.println(d);
        }
        
        last = deques.removeFirst();
        
        System.out.println("First: "+last);
        System.out.println("Size: "+deques.size());
        
        for (String d : deques) {
            System.out.println(d);
        }
        
    }


}
