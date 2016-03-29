package sp0;

public class MergeSort {

	public void mergeSort(int[] input,int p,int r){
		if (p < r){
			int q = (p + r) / 2;
			mergeSort(input,p,q);
			mergeSort(input,q+1,r);
			merge(input,p,q,r);
		}
	}
	
	public void merge(int[] input,int p,int q,int r){
		int[] left = new int[q-p+1];
		int[] right = new int[r-q];
		
		int index = 0;
		for (int i = p; i <= q; i++)
			left[index++] = input[i];
		
		index = 0;
		for (int i = q+1; i <= r; i++)
			right[index++] = input[i];
		
		int j = 0,k = 0,l = 0 ;
		for (l = p ; l <= r ;l++){
		  if (j < left.length && k < right.length){
			  if ( left[j] <= right[k] ){
				   input[l] = left[j];
				   j += 1;
			  }
			  else {
				   input[l] = right[k];
				   k += 1;
		      }
		  }
		  else
			  break;
	    }
		
		if ( j < left.length ){
			for (; l <= r ; l++)
				input[l] = left[j++]; 		
		}
		
		if ( k < right.length ){
			for (; l <= r ; l++)
				input[l] = right[k++]; 		
		}
    }
	
	public static void main(String[] args){
		MergeSort ms = new MergeSort();
		int[] inputArray = {20,15,9,13,6,7,40};
		
		ms.mergeSort(inputArray,0,inputArray.length-1);
		for (int element : inputArray)
		System.out.println(element);
	}
}
