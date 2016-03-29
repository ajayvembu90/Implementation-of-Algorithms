package sp0;

import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.PriorityQueue;
//import java.util.concurrent.ThreadLocalRandom;

public class ImplementSorts {
	
	
	public static void main(String[] args)throws IOException{
		MergeSortGeneric<String> ms = new MergeSortGeneric<String>();
		//Integer[] inputArray = new Integer[3000000];
		
		String[] inputArray = {"zach","yang","kumar","balaji","ajay"};
		
		//for (int i = 0; i< 3000000; i++)
			//inputArray[i] = ThreadLocalRandom.current().nextDouble(1.0, 3000000.0 + 1.0);
		
		//for (int i = 3000000,m=0; i>= 1; i--)
		  //inputArray[m++] = i;
		
		System.out.println("Numbers Generated...");
		
		FileWriter writerObject1 = new FileWriter("inputElements.txt");
		
		for (String element : inputArray){
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
		
		for (String element : inputArray){
			//writerObject2.write(Integer.toString(element));
			writerObject2.write(element);
			writerObject2.write("\n");
		}
		
		System.out.println("Output file generated for Merge Sort...");
		writerObject2.close();
		/*
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
		*/
		
	}

}
