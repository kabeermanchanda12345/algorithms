import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

  //  private ArrayList<Item> mItems = new ArrayList<Item>();
    private ResizingArray<Item> mArray  = new ResizingArray<Item>();
    
    public RandomizedQueue() {
        // construct an empty randomized queue
    }
    public boolean isEmpty() {
        // is the queue empty?
      //  return mItems.size() == 0;
        return mArray.getLength() == 0;
    }
    public int size() {
        // return the number of items on the queue
      //  return mItems.size();
        return mArray.getLength();
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        // add the item
       // mItems.add(item);
        mArray.push(item);
    }
    public Item dequeue() {
        if (mArray.getLength() == 0) throw new NoSuchElementException();
        // delete and return a random item
        double rand = Math.random()*mArray.getLength();
        //Item item = mItems.remove(((int) rand));
        Item item = mArray.pop((int) rand);
        return item;
    }
    public Item sample() {
        if (mArray.getLength() == 0) throw new NoSuchElementException();
        // return (but do not delete) a random item
        double rand = Math.random()*mArray.getLength();
        
       // return mItems.get(((int) rand));
        return mArray.get((int) rand);
    }
    
    public Iterator<Item> iterator() {
        //Iterator<Item> iterator = new RandListIterator((Item[]) mItems.toArray());
        Iterator<Item> iterator = new RandListIterator((Item[]) mArray.getArray());
        return iterator;
    }
    
    private class ResizingArray<T>  {
        private T[] x;
        private int N = 0;
        public ResizingArray() {
            x = (T[]) new Object[1];
        }
        
        public int getLength() {
            return N;
        }
        
        public void push(T item) {
            if (N == x.length) resize(2*x.length);
            x[N++] = item;
        }
        
        public void resize(int size) {
            T[] copy = (T[]) new Object[size];
            for (int i = 0; i < N; i++) {
                copy[i] = x[i];
            }
            x = copy;
        }
        
        public T[] getArray() {
            T[] copy = (T[]) new Object[N];
            for (int i = 0; i < N; i++) {
                copy[i] = x[i];
            } 
            return copy;
        }
        
        public T pop(int i) {
            T item = (T) (new Object());
           // T item = x[i];
           // x[i] = null;
            swap(i, N-1);
            item = x[N-1];
            --N;
            if (N > 0 && N == x.length/4)resize(x.length/2);
            return item;
        }
        
        public T get(int i) {
            return x[i];
        }
        
        private void swap(int i , int j) {
            T temp = x[i];
            x[i] = x[j];
            x[j] = temp;
        }
    }
    
    private class RandListIterator implements Iterator<Item> {

        private Item[] mArray;
        private int mCurrentIndex = -1;
        
        public RandListIterator(Item[] array) {
            mArray = array;
            for (int i = 0; i < array.length; i++) {
                int index1 = (int) (Math.random()*array.length);
                int index2 = (int) (Math.random()*array.length);
                swap(index1, index2);
            }
            if (array.length > 0) {
                mCurrentIndex = 0;
            } else {
                mCurrentIndex = -1;
            }
        }
        public boolean hasNext() {
            return mCurrentIndex >= 0 && mCurrentIndex < mArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            return mArray[mCurrentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
            
        }
        
        private void swap(int i , int j) {
            Item temp = mArray[i];
            mArray[i] = mArray[j];
            mArray[j] = temp;
        }

    }
    
    
    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("Kabeer");
        queue.enqueue("is");
        queue.enqueue("a");
        queue.enqueue("boy");
        queue.enqueue("dog ");
        
        String w = queue.dequeue();
        System.out.println("1: "+w);
        System.out.println(queue.size());
        
         w = queue.dequeue();
        System.out.println("2: "+w);
        System.out.println(queue.size());
        w = queue.dequeue();
       System.out.println("3: "+w);
       System.out.println(queue.size());
       w = queue.dequeue();
      System.out.println("4: "+w);
      System.out.println(queue.size());
      w = queue.dequeue();
     System.out.println("5: "+w);
     System.out.println(queue.size());
//        for(String x:queue) {
//            System.out.print(x+" ");
//        }
//        System.out.println(" ");
//        
//        
//        for(String x:queue) {
//            System.out.print(x+" ");
//        }
//        
//        System.out.println(" ");
//        
//        
//        for(String x:queue) {
//            System.out.print(x+" ");
//        }
    }
    


}
