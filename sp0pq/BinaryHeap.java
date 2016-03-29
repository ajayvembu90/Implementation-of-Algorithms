// Ver 1.0:  Wec, Feb 3.  Initial description.
package sp0pq;
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    Comparator<T> c;
    int size;
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
	size = pq.length-1;
	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    @SuppressWarnings("unchecked")
	BinaryHeap(int n, Comparator<T> comp) { 
		pq = (T[])new Object[n];
		c = comp;
    }

    public boolean isEmpty(){
       if (size == 0)
    	return true;
     return false;
    }
    
    public void insert(T x) {
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x) { 
    	if (size == 0){
    		pq[++size] = x;
    		return;
    	}
    	pq[++size] = x;
    	buildHeap();
    }

    public T remove() { 
    	if (size != 0){
        	T min = pq[1];
        	pq[1] = pq[size--];
        	percolateDown(1);
        	return min;
    	}
	return null;
    }

    public T peek() { 
    	if (size != 0)
    		return pq[1];
	return null;
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { 
    	pq[0] = pq[i];
    	while(c.compare(pq[i/2],pq[0]) > 0){
    		pq[i] = pq[i/2];
    		i = i/2;
    	}
    	pq[i] = pq[0];
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { 
    	T x = pq[i];
    	while(2*i <= size){
    		if (2*i ==size ){
    			if (c.compare(x,pq[size]) > 0){
    				pq[i] = pq[size];
    				i = size;
    			}
    			else
    				break;
    		}
    		else{
    			int smallChild = 0;
    			if (c.compare(pq[2*i],pq[2*i+1]) <= 0)
    				smallChild = 2*i;
    			else
    				smallChild = 2*i+1;
    			if (c.compare(x,pq[smallChild]) > 0){
    				pq[i] = pq[smallChild];
    				i = smallChild;
    			}
    			else
    				break;
    		}
    	}
    	pq[i] = x;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	for (int i =size/2; i>0;i--)
    		percolateDown(i);
    }

    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    public static<T> void heapSort(T[] A, Comparator<T> comp) { /* to be implemented */
    }
    
    public static class IntegerMaxComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return y - x;
        }
    }
    
    public static class IntegerMinComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }
    
    public static class VertexMinComparator implements Comparator<Vertex>{
        public int compare(Vertex u, Vertex v) {
            return u.distance  - v.distance;
        }
    }
    
    public static class VertexMaxComparator implements Comparator<Vertex>{
        public int compare(Vertex u, Vertex v) {
            return v.distance  - u.distance;
        }
    }
    
    public static class EdgeMinComparator implements Comparator<Edge>{
        public int compare(Edge e1, Edge e2) {
            return e1.Weight  -  e2.Weight;
        }
    }
    
    public static class EdgeMaxComparator implements Comparator<Edge>{
        public int compare(Edge e1, Edge e2) {
            return e2.Weight  -  e1.Weight;
        }
    }
}
