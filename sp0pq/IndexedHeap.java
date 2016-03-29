// Ver 1.0:  Wec, Feb 3.  Initial description.
package sp0pq;
import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(x.getIndex());
    }
    
    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { 
    	T x = pq[i];
    	while(2*i <= size){
    		if (2*i ==size ){
    			if (c.compare(x,pq[size]) > 0){
    				pq[i] = pq[size];
    				pq[i].putIndex(i);
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
    				pq[i].putIndex(i);
    				i = smallChild;
    			}
    			else
    				break;
    		}
    	}
    	pq[i] = x;
    	pq[i].putIndex(i);
    }
    
    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { 
    	pq[0] = pq[i];
    	while(c.compare(pq[i/2],pq[0]) > 0){
    		pq[i] = pq[i/2];
    		pq[i].putIndex(i);
    		i = i/2;
    	}
    	pq[i] = pq[0];
    	pq[i].putIndex(i);
    }
    
}
