package sp1;

import java.util.Stack;

public class SinglyLinkedListReverse<E> {

	// singly linked list class
	
	Node<E> head = null;
	
	Node<E> insertElement(E element){
		if (head == null){
			head = new Node<E>();
			head.element = element;
			return head;
		}
		else{
			Node<E> temp = head;
			head = head.next;
			Node<E> nextNode = insertElement(element);
			head = temp;
			head.next = nextNode;
			return head;
		}
	}
	
	// reverse the list in the recursively
	
	Node<E> reverseListRecursive(Node<E> temp){
		if (temp.next != null){
			Node<E> oldHead = null;
			if (temp == head) oldHead = temp;
			Node<E> prevNode = reverseListRecursive(temp.next);
			prevNode.next = temp;
			if (temp == oldHead) temp.next = null;
			return temp;
		}
		else{
			head = temp;
			return temp;
		}
	}
	
	// print the list in the reverse order recursively
	
	void printListReverseRecursive(Node<E> temp){
		if (temp != null){
			printListReverseRecursive(temp.next);
			System.out.println(temp.element);
		}
	}
	
	// reverse the list non recursively
	// Loop invariant - variable 'temp' will be non null while inside the loop
	
	void reverseListNonRecursive(Node<E> temp){
		Stack<E> stackList = new Stack<E>();
		while (temp != null){
			stackList.push(temp.element);
			temp = temp.next;
		}
		head = null;
		while (!stackList.empty())
			insertElement(stackList.pop());
	}
	
	// print the list in the reverse order non recursively
	// Loop invariant - variable 'temp' will be non null while inside the loop
	
	void printListReverseNonRecursive(Node<E> temp){
		Stack<E> stackList = new Stack<E>();
		while (temp != null){
			stackList.push(temp.element);
			temp = temp.next;
		}
		while (!stackList.empty())
			System.out.println(stackList.pop());
	}
	
	
	public static void main(String[] args){
		SinglyLinkedListReverse<Integer> newList = new SinglyLinkedListReverse<Integer>();
		int inputElements[] = {10,20,30,40,50,60,70,80,90,100,110};
		
		for (int eachElement : inputElements )
			newList.insertElement(eachElement);
		
		//newList.reverseListNonRecursive(newList.head);
		//newList.printListReverseNonRecursive(newList.head);
		Node<Integer> temp = newList.head;
		while (temp != null){
			System.out.println(temp.element);
			temp = temp.next;
		}
	}
}

class Node<E>{
	E element = null;
	Node<E> next = null;
}
