package sp3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortImplement {
public static void main(String args[])throws IOException{
	
		MultiPivotQuickSort<Integer> qs = new MultiPivotQuickSort<Integer>();
		QuickSort<Integer> qs1 = new QuickSort<Integer>();

		Integer[] inputArray = new Integer[10000000];

		// generate the worst case input descending order
		
		//for (int i = inputArray.length,m=0; i>= 1; i--)
		  // inputArray[m++] = i;
		
		for (int i = 0; i< inputArray.length; i++)
			inputArray[i] = ThreadLocalRandom.current().nextInt(1, inputArray.length + 1);
		
		//System.out.println(inputArray[inputArray.length-1]);
		
		System.out.println("Numbers Generated...");
		long startTime = System.currentTimeMillis();
		
		//qs.quickSort(inputArray,0,inputArray.length-1);
		qs.quickSortRandom(inputArray,0,inputArray.length-1);
		//qs1.quickSort(inputArray,0,inputArray.length-1);
		
        long endTime = System.currentTimeMillis();
		
		System.out.println("Numbers Sorted using Quick Sort...");
		System.out.println("Time taken for Quick Sort : "+ (endTime - startTime) +" ms ");
		
		
		
		for (int i=0;i<inputArray.length-1;i++ ){
			if (inputArray[i]>inputArray[i+1]){
			 System.out.println("Not Sorted"+inputArray[i]+" "+inputArray[i+1]);
			 break;
			}
		}
 }
}
