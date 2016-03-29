package sp4;

import java.util.Scanner;

import sp4.BST.Entry;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {


    class AVLEntry<E extends Comparable<? super E>>{
    E element;
    int height;
    AVLEntry<E> left, right, parent;
	AVLEntry(E x, AVLEntry<E> l, AVLEntry<E> r, AVLEntry<E> p) {
        element = x;
        left = l;
        right = r;
        parent = p;
	    height = 0;
	}
    }
    
    AVLEntry<T> root;
    
    AVLEntry<T> find(AVLEntry<T> t, T x) {
    AVLEntry<T> pre = t;
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
    
    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new AVLEntry<>(x, null, null, null);
	} else {
		AVLEntry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    AVLEntry<T> newNode = new AVLEntry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++;
	return true;
    }
    
    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(AVLEntry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }


 
    
	
	
	int adjustHeightForEachNode(AVLEntry<T> node){
		if (node.left == null && node.right == null){
			node.height = 0;
			return 1;
		}
		else{
			int getLeftChildNodeHeight = node.left != null ? adjustHeightForEachNode(node.left):0;
			int getRightChildNodeHeight = node.right != null ? adjustHeightForEachNode(node):0;
			node.height = getLeftChildNodeHeight > getRightChildNodeHeight ? getLeftChildNodeHeight : getRightChildNodeHeight;
			return node.height + 1;
		}	
	}

    public static void main(String[] args) {
    AVLTree<Integer> t = new AVLTree<Integer>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()) {
	    int x = in.nextInt();
	    if(x > 0) {
		System.out.print("Add " + x + " : ");
		t.add(x);
		t.printTree();
	    }else {
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
	//System.out.println("g");
	//System.out.println("To check if BST : "+t.checkIfOrderingPropertySatisfies(t.root));
    }

   
}
