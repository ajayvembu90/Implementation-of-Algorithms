package sp3;

import java.util.Random;

public class QuickSort<T extends Comparable<T>> {
void quickSort(T[] inputElements,int p,int r){
	if (p<r){
		int q = randomPartition(inputElements,p,r);
		quickSort(inputElements,p,q-1);
		quickSort(inputElements,q+1,r);
	}
}
int partition(T[] inputElements,int p,int r){
	T pivot = inputElements[r];
	int partitionIndex=p;
	
	for (int i=p;i<=r;i++){
		if (inputElements[i].compareTo(pivot) < 0){
	      swap(i,partitionIndex,inputElements);
	      partitionIndex +=1;
		}
	}
	swap(partitionIndex,r,inputElements);
	return partitionIndex;
}

int randomPartition(T[] inputElements,int p,int r){
	
	Random rand = new Random();
	int randIndex = rand.nextInt(r-p+1) + p;
	T pivot = inputElements[randIndex];
	swap(randIndex,r,inputElements);
	
	int partitionIndex=p;
	
	for (int i=p;i<=r;i++){
		if (inputElements[i].compareTo(pivot) < 0){
	      swap(i,partitionIndex,inputElements);
	      partitionIndex +=1;
		}
	}
	swap(partitionIndex,r,inputElements);
	return partitionIndex;
}

private void swap(int i,int partitionIndex, T[] inputElements){
	T temp = inputElements[i];
	inputElements[i] = inputElements[partitionIndex];
	inputElements[partitionIndex] = temp;
}


}

