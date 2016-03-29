package sp0;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ImplementSorts {
	
	
	public static void main(String[] args)throws IOException{
		MergeSortGeneric<Integer> ms = new MergeSortGeneric<Integer>();
		Integer[] inputArray = new Integer[50000000];
		
		
		for (int i = inputArray.length,m=0; i>= 1; i--)
		   inputArray[m++] = i;
		
		System.out.println("Numbers Generated...");
		
		FileWriter writerObject1 = new FileWriter("inputElements.txt");
		
		
		for (Integer element : inputArray){
			//writerObject1.write(Integer.toString(element));
			writerObject1.write(element);
			writerObject1.write("\n");
		}
		System.out.println("Input file generated...");
		writerObject1.close();
		
		// Merge Sort 
		
		long startTime = System.currentTimeMillis();
		
				
		ms.mergeSort(inputArray,0,inputArray.length-1);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Numbers Sorted using Merge Sort...");
		System.out.println("Time taken for Merge Sort : "+ (endTime - startTime) +" ms ");
		
		
		FileWriter writerObject2 = new FileWriter("sortedElementsMergeSort.txt");
		
		for (Integer element : inputArray){
			writerObject2.write(element);
			writerObject2.write("\n");
		}
		
		System.out.println("Output file generated for Merge Sort...");
		writerObject2.close();
		
       // Priority Queue Sort
		
		ArrayList<Integer> inputForPQ = new ArrayList<Integer>();
		
		for (int element: inputArray)
			inputForPQ.add(element);
		
		System.out.println("Array List created for Priority Queue sort...");
		
		PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(inputForPQ);
		
		Integer[] outputArray = new Integer[pQ.size()];
		
		startTime = System.currentTimeMillis();
		
		for (int i = 0; i < outputArray.length; i++)
			outputArray[i] = pQ.poll();
		
		endTime = System.currentTimeMillis();
		
		System.out.println("Numbers Sorted using Priority Queue Sort...");
		System.out.println("Time taken for Priority Queue Sort : "+ (endTime - startTime) +" ms ");
		
		FileWriter writerObject3 = new FileWriter("sortedElementsPriorityQueueSort.txt");

		for (int element : outputArray ){
			writerObject3.write(Integer.toString(element));
			writerObject3.write("\n");
		}
		
		System.out.println("Output file generated for Priority Queue Sort...");
		writerObject3.close();
		
	}

}
