package sp4;
import java.util.*;

public class BST<T extends Comparable<? super T>> {
    class Entry<E extends Comparable<? super E>> {
        E element;
        Entry<E> left, right, parent;
        int height = 0;

        Entry(E x, Entry<E> l, Entry<E> r, Entry<E> p) {
        element = x;
	    left = l;
	    right = r;
	    parent = p;
        }
    }
    
    Entry<T> root;
    int size;
    // variable added to alternate between MinRight and MaxLeft
    boolean removeTwoInd;

    BST() {
	root = null;
	size = 0;
    }

    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
	Entry<T> pre = t;
	while(t != null) {
	    pre = t;
	    int cmp = x.compareTo(t.element);
	    if(cmp == 0) {
		return t;
	    } else if(cmp < 0) {
		t = t.left;
	    } else {
		t = t.right;
	    }
	}
	return pre;
    }

    // Is x contained in tree?
    public boolean contains(T x) {
	Entry<T> node = find(root, x);
	return node == null ? false : x.equals(node.element);
    }

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new Entry<>(x, null, null, null);
	} else {
	    Entry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    Entry<T> newNode = new Entry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++;
	//adjustHeightValueForEachNode(root);
	return true;
    }

    // Remove x from tree.  Return x if found, otherwise return null
    public T remove(T x) {
	T rv = null;
	if(size > 0) {
	    Entry<T> node = find(root, x);
	    if(x.equals(node.element)) {
		rv = node.element;
		remove(node);
		size--;
	    }
	}
	//adjustHeightValueForEachNode(root);
	return rv;
    }

    // Called when node has at most one child.  Returns that child.
    Entry<T> oneChild(Entry<T> node) {
	return node.left == null? node.right : node.left;
    }

    // Remove a node from tree
    void remove(Entry<T> node) {
	if(node.left != null && node.right != null) {
	    removeTwo(node);
	} else {
	    removeOne(node);
	}
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
	if(node == root) {
	    Entry<T> nc = oneChild(root);
	    root = nc;
	    root.parent = null;
	} else {
	    Entry<T> p = node.parent;
	    Entry<T> nc = oneChild(node);
	    if(p.left == node) {
		p.left = nc;
	    } else {
		p.right = nc;
	    }
	    if(nc != null) nc.parent = p;
	}
    }

    // remove node that has two children
    void removeTwo(Entry<T> node) {
    
    if(!removeTwoInd){	
    	Entry<T> minRight = node.right;
    	
    	while(minRight.left != null) {
    	    minRight = minRight.left;
    	}
    	node.element = minRight.element;
    	removeOne(minRight);
    	removeTwoInd = true;
    }
    else{
    	// MaxLeft added for part - c
        Entry<T> maxLeft = node.left;
    	
        while(maxLeft.right != null) {
        	maxLeft = maxLeft.right;
        }
        node.element = maxLeft.element;
        removeOne(maxLeft);
        removeTwoInd = false;
    } 
    }

	

    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	inOrder(root, arr, 0);
	return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
	if(node != null) {
	    index = inOrder(node.left, arr, index);
	    arr[index++] = node.element;
	    index = inOrder(node.right, arr, index);
	}
	return index;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print("Element Value : " + node.element+"Element Height : "+node.height);
	    printTree(node.right);
	}
    }

    // Preorder traversal of tree
    void preTree(Entry<T> node) {
	if(node != null) {
	    System.out.print(" " + node.element);
	    preTree(node.left);
	    preTree(node.right);
	}
    }
    
	//Method to return Level Order Array for a BST - part-a
	public Comparable[] levelOrderArray() {
		Comparable[] levelOrderArray = new Comparable[size];
		Queue<Entry<T>> queue = new LinkedList<>();
		int i = 0;
		Entry<T> elem = root;
		if (elem != null)
			queue.add(elem);
		/**
		 * Iterates as long as the queue is empty
		 * Adds Children Nodes of the currently removed node into the queue if they are not null
		 */
		while (!queue.isEmpty()) {
			elem = queue.remove();
			levelOrderArray[i] = elem.element;
			i++;
			if (elem.left != null) {
				queue.add(elem.left);
			}
			if (elem.right != null) {
				queue.add(elem.right);
			}
		}
		return levelOrderArray;
	}
		
	//Constructor part for part-b
	BST(T[] arr){
		root = sortedArrayToBalancedBST(arr, 0, arr.length-1, null);
		size = arr.length;
		System.out.println("Root is " + root.element);
	}

	/**
	 * @param elements - the sorted array 
	 * @param start - start value of the array to look at 
	 * @param end - end value denotes the portion which will choose the next child 
	 * @param parent - the parent of the current node in progress
	 * @return The root of the Balanced Binary Search Tree
	 * 
	 * Method uses recursion to add the left and right child for the given node by choosing the 
	 * middle element on both sides of the given node
	 */
	public Entry<T> sortedArrayToBalancedBST(T[] elements, int start, int end, Entry<T> parent){
        if(start<=end){
            int mid = start + (end - start)/2;
            Entry<T> node = new Entry<T>(elements[mid],null, null, parent);
            node.left = sortedArrayToBalancedBST(elements, start, mid-1, node);
            node.right = sortedArrayToBalancedBST(elements, mid + 1 , end, node);
            return node;
        }
        return null;
    }
	
	
	
	int adjustHeightValueForEachNode(Entry<T> node){
		if (node.left == null && node.right == null){
			node.height = 0;
			return 1;
		}
		else{
			int getLeftChildNodeHeight = node.left != null ? adjustHeightValueForEachNode(node.left):0;
			int getRightChildNodeHeight = node.right != null ? adjustHeightValueForEachNode(node.right):0;
			node.height = getLeftChildNodeHeight > getRightChildNodeHeight ? getLeftChildNodeHeight : getRightChildNodeHeight;
			return node.height + 1;
		}	
	}
	
	class DiameterResult{
		int prevHeight,prevDiameter;
		
		DiameterResult(int prevHeight,int prevDiameter){
			this.prevDiameter = prevDiameter;
			this.prevHeight = prevHeight;
		}
	}
	
	DiameterResult getDiameterOfTree(Entry<T> node){
		if (node.left == null && node.right == null)
			return new DiameterResult(1,1);
		else{
			DiameterResult getLeftChildDiameterResult = node.left != null ? getDiameterOfTree(node.left):new DiameterResult(0,0);
			DiameterResult getRightChildDiameterResult = node.right != null ? getDiameterOfTree(node.right):new DiameterResult(0,0);
			int nodeDiameter = getLeftChildDiameterResult.prevHeight + getRightChildDiameterResult.prevHeight + 1;
			int bigDiameter = getLeftChildDiameterResult.prevDiameter > getRightChildDiameterResult.prevDiameter ? getLeftChildDiameterResult.prevDiameter : getRightChildDiameterResult.prevDiameter;
			bigDiameter = bigDiameter > nodeDiameter ? bigDiameter : nodeDiameter;
			int nodeHeight = getLeftChildDiameterResult.prevHeight > getRightChildDiameterResult.prevHeight ? getLeftChildDiameterResult.prevHeight : getRightChildDiameterResult.prevHeight;
			return new DiameterResult(nodeHeight+1,bigDiameter);
		}	
	}
	
	// to check if a tree complies ordering property
	boolean checkIfOrderingPropertySatisfies(Entry<T> node){
		if (node.left == null && node.right == null)
			return true;
		else if (node.left != null && node.right == null)
			return node.left.element.compareTo(node.element) <= 0 ? true:false;  
		else if (node.left == null && node.right != null)
			return node.right.element.compareTo(node.element) > 0 ? true:false;
		else
			return node.left.element.compareTo(node.element) <= 0 && node.right.element.compareTo(node.element) > 0 
			? checkIfOrderingPropertySatisfies(node.left) & checkIfOrderingPropertySatisfies(node.right):false;
	}
	
	// get the maximum height
	int getMaxHeight(){
		adjustHeightValueForEachNode(root);
		return root.height;
	}
	
	// to check the balance condition for AVL tree 
	boolean checkBalanceCondition(Entry<T> node){
		if (node.left == null && node.right == null)
			return true;
		else if (node.left != null && node.right == null)
			return node.left.height <= 1 ? true:false; 
		else if (node.left == null && node.right != null)
			return node.right.height <= 1 ? true:false;
		else
			return Math.abs(node.left.height - node.right.height) <= 1 ? checkBalanceCondition(node.left) & checkBalanceCondition(node.right):false;
	}
	
	// to check the balance condition for AVL tree 
	boolean checkBalanceCondition(){
		adjustHeightValueForEachNode(root);
		return checkBalanceCondition(root);
	}
	
	boolean checkForNullEntries(Entry<T> node){
		if (node != null){
			if (node.element == null)
				return false;
			else
				return checkForNullEntries(node.left) & checkForNullEntries(node.right);
		}
		return true;
	}
	
	int getMaxDepth(Entry<T> node){
		return getMaxDepth(node,0);
	}
	
	int getMaxDepth(Entry<T> node, int depth){
		int getLeftMaxDepth = node.left != null ? getMaxDepth(node.left,depth+1):depth;
		int getRightMaxDepth = node.right != null ? getMaxDepth(node.right,depth+1):depth;
		return getLeftMaxDepth > getRightMaxDepth ? getLeftMaxDepth : getRightMaxDepth;
	}
	
	int getDiameter(Entry<T> node){
		int maxLeftDepth = getMaxDepth(node.left);
		int maxRightDepth = getMaxDepth(node.right);
		return maxLeftDepth + maxRightDepth + 3;
	}
	
	
	
	boolean verifyAVLTree(){
		if (!checkForNullEntries(root))
			return false;
		if (!checkIfOrderingPropertySatisfies(root))
			return false;
		if (!checkBalanceCondition())
			return false;
		return true;
	}
	
    public static void main(String[] args) {
    BST<Integer> t = new BST<Integer>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()) {
	    int x = in.nextInt();
	    if(x > 0) {
		System.out.print("Add " + x + " : ");
		t.add(x);
		t.printTree();
	    } else if(x < 0) {
		System.out.print("Remove " + x + " : ");
		t.remove(-x);
		t.printTree();
	    } else {
		Comparable[] arr = t.toArray();
		System.out.print("Final: ");
		for(int i=0; i<t.size; i++) {
		    System.out.print(arr[i] + " ");
		}
		System.out.println();
		break;
	    }		
	}
	//t.root.right.left.element = 100;
	//System.out.println("Is Balance = "+t.checkBalanceCondition());
	//System.out.println("Height : "+(t.root.left.height - t.root.right.height));
	//System.out.println("Max Depth : "+t.getMaxDepth(t.root));
	System.out.println("Diameter : "+t.getDiameterOfTree(t.root).prevDiameter);
    }
    
}