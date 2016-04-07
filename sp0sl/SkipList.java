
/** See  http://en.wikipedia.org/wiki/Skip_list
 */

import java.lang.Comparable;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class SkipList<T extends Comparable<? super T>> {
	// N is 2M which is the initial capacity
	int N = 10000000,currentSize;
	// maxLevel is the maximum level a skip entry can have
	int maxLevel = (int)Math.round(Math.log(N) / Math.log(2));
	SkipListEntry<T> head,tail;

	// find the element in O(logN)
	@SuppressWarnings("unchecked")
	SkipListEntry<T>[] find(T x){
		SkipListEntry<T> p = head;
		SkipListEntry<T>[] prev = new SkipListEntry[maxLevel];
		for (int i=maxLevel-1;i>=0;i-- ){
			// reach the element in the top level and slide on level down
			while(p.next[i].element.compareTo(x) < 0)
				p = p.next[i];
			// copy the previous node reference at each level
			prev[i] = p;
		}
		return prev;
	}

	// add an element in logN steps
    boolean add(T x) {  // Add an element x to the list.  Returns true if x was a new element.
    	SkipListEntry<T>[] prev = find(x);
    	// if the element is equal then return false
    	if( prev[0].next[0].element.compareTo(x) == 0){
    		prev[0].next[0].element = x;
    		return false;
    	}
    	else{
    		// create a new entry and copy all the references back to which the new entry blocks
    		int level = choice (maxLevel);
    		SkipListEntry<T> n = new SkipListEntry<T>(x,level);
    		//System.out.println("lev"+level);
    		for (int i = 0;i<level;i++){
    			n.next[i] = prev[i].next[i];
    			prev[i].next[i] = n;
    		}
    	}
    	// if the current is reaches the N then resize it
    	currentSize++;
    	if (currentSize >= N)
    		rebuild();
	return true;
    }
    // return a random level between 1 and maxLevel
    int choice(int level){
    	int i = 1;
    	Random r = new Random();
    	while (i < maxLevel){
    		boolean b = r.nextBoolean();
    		if (b)
    			i++;
    		else
    			break;
    	}
    	return i;
    }

    T ceiling(T x) { // Least element that is >= x, or null if no such element
	return null;
    }

    boolean contains(T x) {  // Is x in the list?
    	SkipListEntry<T>[] prev = find(x);
	return prev[0].next[0].element.compareTo(x) == 0;
    }

    T findIndex(int index) {  // Return the element at a given position (index) in the list
	return null;
    }

    T first() {  // Return the first element of the list
	return null;
    }

    T floor(T x) {  // Greatest element that is <= x, or null if no such element
	return null;
    }

    boolean isEmpty() {  // Is the list empty?
	return true;
    }

    Iterator<T> iterator() {  // Returns an iterator for the list
	return null;
    }

    T last() {  // Return the last element of the list
	return null;
    }

    @SuppressWarnings("unchecked")
	void rebuild() {  // Rebuild this list into a perfect skip list in O(N) + O(N) time
    	N += N;
    	maxLevel = (int)Math.round(Math.log(N) / Math.log(2));
    	T[] input = (T[])new Comparable[currentSize];
    	SkipListEntry<T> p = head.next[0];
    	int i = 0;
    	// copy all the current elements in the list
    	while(p.element.compareTo(tail.element) != 0){
    		input[i++] = p.element;
    		p = p.next[0];
    	}
    	// initialize the head and tail with the new maxLevel
    	head.next = new SkipListEntry[maxLevel];
    	tail.next = new SkipListEntry[maxLevel];
    	for (int j=0;j<maxLevel;j++)
    		head.next[j] = tail;

    	currentSize = 0;
    	// add the elements back to the list after the re size
    	for (T eachInput : input)
    		add(eachInput);
    }

    T remove(T x) {  // Remove x from list; returns false if x was not in list in O(logN)
    	SkipListEntry<T>[] prev = find(x);
    	SkipListEntry<T> n = prev[0].next[0];
    	// if the element in not present the return null
    	if (n.element.compareTo(x)!=0)
    		return null;
    	else{
    		// copy the references
    		for (int i=0;i<maxLevel;i++){
    			if (prev[i].next[i] == n)
    				prev[i].next[i] = n.next[i];
    			else
    				break;
    		}
    	}
    	currentSize--;
	return n.element;
    }

    int size() {  // Number of elements in the list
	return currentSize;
    }

    public static void main(String[] args){
    	SkipList<Integer> sl = new SkipList<Integer>();
    	SkipListEntry<Integer> head = new SkipListEntry<Integer>(Integer.MIN_VALUE,sl.maxLevel);
    	SkipListEntry<Integer> tail = new SkipListEntry<Integer>(Integer.MAX_VALUE,sl.maxLevel);
    	sl.head = head;
    	sl.tail = tail;
    	for (int j=0;j<sl.maxLevel;j++)
    		head.next[j] = tail;

    	/*
    	Integer[] ops = new Integer[1000000];
    	Random r = new Random();
    	long startTime = System.currentTimeMillis();
    	for (int i=0;i<ops.length;i++){
    		int currentOperation = r.nextInt(3);
    		int randCurrentNumber = r.nextInt(ops.length);
    		if (currentOperation == 0)
    			sl.add(randCurrentNumber);
    		else if (currentOperation == 1)
    			sl.contains(randCurrentNumber);
    		else
    			sl.remove(randCurrentNumber);
    	}
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : "+ (endTime - startTime) / 1000 +" s ");
        */


        TreeSet<Integer> ts = new TreeSet<Integer>();
    	Integer[] ops = new Integer[50000000];
    	Random r = new Random();
    	long startTime = System.currentTimeMillis();
    	for (int i=0;i<ops.length;i++){
    		int currentOperation = r.nextInt(3);
    		int randCurrentNumber = r.nextInt(ops.length);
    		if (currentOperation == 0)
    			ts.add(randCurrentNumber);
    		else if (currentOperation == 1)
    			ts.contains(randCurrentNumber);
    		else
    			ts.remove(randCurrentNumber);
    	}
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : "+ (endTime - startTime) /1000 +" s ");

    }
}

class SkipListEntry<E extends Comparable<? super E>>{
	E element;
	SkipListEntry<E>[] next;

	@SuppressWarnings("unchecked")
	SkipListEntry(E element, int level){
		this.element = element;
		next = new SkipListEntry[level];
	}
}
