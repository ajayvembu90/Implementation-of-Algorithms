package sp0;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class MergeSortGeneric<E extends Comparable<E>> {

	public void mergeSortStackImplementation(E[] input,int p,int r){
		
		Stack<MergeInstant> miStack = new Stack<MergeInstant>();
		ArrayDeque<MergeInstant> miQueue = new ArrayDeque<MergeInstant>();
		
		int q = (p+r)/2;
		MergeInstant mi = new MergeInstant();
		mi.p = p;
		mi.q = q;
		mi.r = r;
		miQueue.add(mi);
		
		while (!miQueue.isEmpty()){
			MergeInstant currentMI = miQueue.remove();
			miStack.push(currentMI);
			if (currentMI.p < currentMI.q){
				MergeInstant mi1 = new MergeInstant();
				mi1.p = currentMI.p;
				mi1.q = (currentMI.p + currentMI.q) / 2;
				mi1.r = currentMI.q;
				miQueue.add(mi1);
			}
			if ( ( currentMI.q + 1 ) < currentMI.r){
				MergeInstant mi1 = new MergeInstant();
				mi1.p = currentMI.q+1;
				mi1.q = ((currentMI.q + 1) + currentMI.r) / 2;
				mi1.r = currentMI.r;
				miQueue.add(mi1);
			}
		}
	
		while (!miStack.empty()){
			MergeInstant currentMI = miStack.pop();
			System.out.println(currentMI.p + " " + currentMI.q + " " + currentMI.r);
			merge(input,currentMI.p,currentMI.q,currentMI.r);
		}
		
		/*
		merge(input,6,6,7);
		merge(input,4,4,5);
		merge(input,2,2,3);
		merge(input,0,0,1);
		merge(input,4,5,7);
		merge(input,0,1,3);
		merge(input,0,3,7);
		*/	
	}
	
	public void mergeSort(E[] input,int p,int r){
		if (p < r){
			int q = (p + r) / 2;
			mergeSort(input,p,q);
			mergeSort(input,q+1,r);
			merge(input,p,q,r);
		}
	}
	
	public void merge(E[] input,int p,int q,int r){
		ArrayList<E> left = new ArrayList<E>(q-p+1);
		ArrayList<E> right = new ArrayList<E>(r-q);
		
		int index = 0;
		for (int i = p; i <= q; i++)
			left.add(index++, input[i]) ;
		
		index = 0;
		for (int i = q+1; i <= r; i++)
			right.add(index++,input[i]);
		
		int j = 0,k = 0,l = 0 ;
		for (l = p ; l <= r ;l++){
		  if (j < left.size() && k < right.size()){
			  if ( left.get(j).compareTo(right.get(k)) < 0 ){
				   input[l] = left.get(j);
				   j += 1;
			  }
			  else {
				   input[l] = right.get(k);
				   k += 1;
		      }
		  }
		  else
			  break;
	    }
		
		if ( j < left.size() ){
			for (; l <= r ; l++)
				input[l] = left.get(j++); 		
		}
		
		if ( k < right.size() ){
			for (; l <= r ; l++)
				input[l] = right.get(k++); 		
		}
    }
	
	
	public static void main(String[] args){
		MergeSortGeneric<Integer> msg = new MergeSortGeneric<Integer>();
		Integer[] input = {10,11,7,6,13,12,15,4};
		
		msg.mergeSortStackImplementation(input, 0, input.length-1);
		
		
		for (int each : input){
			System.out.println(each);
		}
		
	}
}

class MergeInstant{
	int p = 0;
	int q = 0;
	int r = 0;
}
