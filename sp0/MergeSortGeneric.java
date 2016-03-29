package sp0;

import java.util.ArrayList;

public class MergeSortGeneric<E extends Comparable<E>> {	
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
	
}
