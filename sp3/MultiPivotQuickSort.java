package sp3;

import java.util.Random;

public class MultiPivotQuickSort<T extends Comparable<T>> {

	void quickSort(T[] inputElements, int p, int r) {
		if (p < r) {
			PartitionResult pr = multiPivotPartition(inputElements, p, r);
			quickSort(inputElements, p, pr.l - 1);
			if (pr.x1.compareTo(pr.x2) != 0)
				quickSort(inputElements, pr.l + 1, pr.j - 1);
			quickSort(inputElements, pr.j + 1, r);
		}
	}

	PartitionResult multiPivotPartition(T[] inputElements, int p, int r) {
		if (inputElements[p].compareTo(inputElements[r]) <= 0)
			;
		else
			swap(p, r, inputElements);

		T x1 = inputElements[p];
		T x2 = inputElements[r];

		int l = p + 1, i = l, j = r - 1;
		while (i <= j && l < r) {
			if (x1.compareTo(inputElements[i]) <= 0
					&& x2.compareTo(inputElements[i]) >= 0)
				i++;
			else if (inputElements[i].compareTo(x1) < 0) {
				swap(i, l, inputElements);
				l++;
				i++;
			} else if (inputElements[j].compareTo(x2) > 0)
				j--;
			else if (inputElements[i].compareTo(x2) > 0
					&& inputElements[j].compareTo(x1) < 0) {
				circularSwap(i, j, l, inputElements);
				l++;
				i++;
				j--;
			} else if (inputElements[i].compareTo(x2) > 0
					&& (x1.compareTo(inputElements[j]) <= 0 && x2
							.compareTo(inputElements[j]) >= 0)) {
				swap(i, j, inputElements);
				i++;
				j--;
			}
		}

		swap(p, l - 1, inputElements);
		swap(j + 1, r, inputElements);
		PartitionResult pr = new PartitionResult();
		pr.l = l - 1;
		pr.x1 = x1;
		pr.x2 = x2;
		pr.j = j + 1;

		return pr;
	}

	void swap(int p, int r, int index1, int index2, T[] inputElements,
			boolean lessInd) {
		if (lessInd) {

		}
	}

	/*
	 * Loop invariant is that always p < r
	 */
	void quickSortRandom(T[] inputElements, int p, int r) {
		if (p < r) {
			PartitionResult pr = multiPivotPartitionByRandomPivot(
					inputElements, p, r);
			// to sort the elements between p and left partition index
			quickSortRandom(inputElements, p, pr.l - 1);
			// to sort the elements between left partition index and right
			// partition index
			if (pr.x1.compareTo(pr.x2) != 0)
				quickSortRandom(inputElements, pr.l + 1, pr.j - 1);
			// to sort the elements between right partition index and r
			quickSortRandom(inputElements, pr.j + 1, r);
		}
	}

	/*
	 * Loop invariant is that there is always an unprocessed set of elements
	 * when inside the loop The average running time is O(N) index l to track
	 * the set < inputElements[p] index i to track the set inputElements[p] <
	 * set < inputElements[r] index j to track the set > inputElements[r] always
	 * inputElements[p] <= inputElements[r]
	 */

	PartitionResult multiPivotPartitionByRandomPivot(T[] inputElements, int p,
			int r) {

		// to get the two random pivots such that inputElements[p] <
		// inputElements[r]

		Random rand = new Random();
		int index1 = rand.nextInt(r - p + 1) + p, index2 = rand.nextInt(r - p+ 1)+ p;

		if (inputElements[index1].compareTo(inputElements[index2]) <= 0) {
			swap(p, index1, inputElements);
			swap(r, index2, inputElements);
			if (inputElements[p].compareTo(inputElements[r]) > 0)
				swap(p, r, inputElements);
		} else {
			swap(r, index1, inputElements);
			swap(p, index2, inputElements);
			if (inputElements[p].compareTo(inputElements[r]) > 0)
				swap(p, r, inputElements);
		}

		T x1 = inputElements[p];
		T x2 = inputElements[r];

		int l = p + 1, i = l, j = r - 1;

		while (i <= j && l < r) {
			// elements that satisfy the criteria to be in set2
			if (x1.compareTo(inputElements[i]) <= 0
					&& x2.compareTo(inputElements[i]) >= 0)
				i++;
			// elements that satisfy the criteria to be in set1
			else if (inputElements[i].compareTo(x1) < 0) {
				swap(i, l, inputElements);
				l++;
				i++;
			}
			// elements that satisfy the criteria to be in set3
			else if (inputElements[j].compareTo(x2) > 0)
				j--;
			// elements that jumble between set1, set2 and set3
			else if (inputElements[i].compareTo(x2) > 0
					&& inputElements[j].compareTo(x1) < 0) {
				circularSwap(i, j, l, inputElements);
				l++;
				i++;
				j--;
			}
			// elements that jumble between set2 set3
			else if (inputElements[i].compareTo(x2) > 0
					&& (x1.compareTo(inputElements[j]) <= 0 && x2
							.compareTo(inputElements[j]) >= 0)) {
				swap(i, j, inputElements);
				i++;
				j--;
			}
		}
		// actual left pivot partition index
		swap(p, l - 1, inputElements);
		// actual right pivot partition index
		swap(j + 1, r, inputElements);
		// result
		PartitionResult pr = new PartitionResult();
		pr.l = l - 1;
		pr.x1 = x1;
		pr.x2 = x2;
		pr.j = j + 1;

		return pr;
	}

	class PartitionResult {
		int l, j;
		T x1, x2;
	}

	private void circularSwap(int i, int j, int l, T[] inputElements) {
		if (i == l) {
			swap(i, j, inputElements);
			return;
		}
		T temp1 = inputElements[j];
		inputElements[j] = inputElements[i];
		T temp2 = inputElements[l];
		inputElements[l] = temp1;
		inputElements[i] = temp2;
	}

	private void swap(int x, int y, T[] inputElements) {
		T temp = inputElements[x];
		inputElements[x] = inputElements[y];
		inputElements[y] = temp;
	}
}
