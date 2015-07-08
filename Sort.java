import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
Sort Algorithm (Added QuickSort)
CS201 Data Structures
by James Lindamood
13 October 2014
*/


public class Sort
{
	static int numOperations = 0;

	public static void main(String[] paramArrayOfString) {
		if (paramArrayOfString.length < 1) {
			usage();
		}

		int i = 115;
		String str1 = "";

		if (paramArrayOfString[0].equals("-m")) {
			i = 109;
			if (paramArrayOfString.length < 2) usage();
			str1 = paramArrayOfString[1];
		} else if (paramArrayOfString[0].equals("-s")) {
			i = 115;
			if (paramArrayOfString.length < 2) usage();
			str1 = paramArrayOfString[1];

			// Addition of quick sort

		} else if (paramArrayOfString[0].equals("-q")) {
			i = 113;
			if (paramArrayOfString.length < 2) usage();
			str1 = paramArrayOfString[1];
		} else {

			// Added default quick sort functionality

			System.err.println("No sort specified, using quick sort");
			str1 = paramArrayOfString[0];
		}

		String[] arrayOfString1 = null;
		File localFile = new File(str1);
		try {
			ArrayList localArrayList = new ArrayList();
			Scanner localScanner = new Scanner(localFile);
			while (localScanner.hasNextLine()) {
				localArrayList.add(localScanner.nextLine());
			}
			arrayOfString1 = new String[localArrayList.size()];
			localArrayList.toArray(arrayOfString1);
		} catch (FileNotFoundException localFileNotFoundException) {
			System.err.println("Error, cannot find file: " + str1);
			System.exit(1);
		}

		if (i == 109) {
			mergeSort(arrayOfString1);
		} else if (i == 115) {
			selectionSort(arrayOfString1);
		} else {
			quickSort(arrayOfString1);
		}

		for (String str2 : arrayOfString1) {
			System.out.println(str2);
		}

		System.out.println("Number of Operations : " + Integer.toString(numOperations));
	}


	public static void usage()
	{
		System.err.println("Usage:\n\tjava Sort <sorttype> filename");
		System.err.println(" Where <sorttype> is:\n -m Merge Sort\n -s Selection Sort");
		System.exit(1);
	}


	public static void mergeSort(String[] paramArrayOfString)
	{
		mergeSortRec(paramArrayOfString, 0, paramArrayOfString.length - 1);
	}


	private static void mergeSortRec(String[] paramArrayOfString, int paramInt1, int paramInt2)
	{
		if (paramInt2 > paramInt1) {
			int i = (paramInt2 - paramInt1 + 1) / 2;
			int j = paramInt1 + i - 1;
			mergeSortRec(paramArrayOfString, paramInt1, paramInt1 + (paramInt2 - paramInt1 + 1) / 2 - 1);
			mergeSortRec(paramArrayOfString, j + 1, paramInt2);
			merge(paramArrayOfString, paramInt1, j, j + 1, paramInt2);
		}
		else {}
	}


private static void merge(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
{
	String[] arrayOfString = new String[paramInt4 - paramInt1 + 1];
	int i = 0;
	int j = paramInt1;
	while ((paramInt1 <= paramInt2) && (paramInt3 <= paramInt4)) {
		numOperations += 1;
		if (paramArrayOfString[paramInt1].compareTo(paramArrayOfString[paramInt3]) < 0) {
			arrayOfString[i] = paramArrayOfString[paramInt1];
			paramInt1++;
		} else {
			arrayOfString[i] = paramArrayOfString[paramInt3];
			paramInt3++;
		}
		i++;
	}

	while (paramInt1 <= paramInt2) {
		arrayOfString[i] = paramArrayOfString[paramInt1];
		paramInt1++;
		i++;
	}

	while (paramInt3 <= paramInt4) {
		arrayOfString[i] = paramArrayOfString[paramInt3];
		paramInt3++;
		i++;
	}

	for (int k = j; k <= paramInt4; k++) {
		paramArrayOfString[k] = arrayOfString[(k - j)];
	}
}


public static void selectionSort(String[] paramArrayOfString)
{
	for (int i = 0; i < paramArrayOfString.length; i++)
	{
		int j = findNextSmallest(paramArrayOfString, i);


		String str = paramArrayOfString[i];
		paramArrayOfString[i] = paramArrayOfString[j];
		paramArrayOfString[j] = str;
	}
}



private static int findNextSmallest(String[] paramArrayOfString, int paramInt)
{
	int i = paramInt;
	for (int j = paramInt + 1; j < paramArrayOfString.length; j++) {
		numOperations += 1;
		if (paramArrayOfString[j].compareTo(paramArrayOfString[i]) < 0) {
			i = j;
		}
	}
	return i;
}



// Quick Sort method call

public static void quickSort(String[] paramArrayOfString)
{
	quickSortRec(paramArrayOfString, 0, paramArrayOfString.length - 1);
}



// Recursive quickSort method
// Code adapted from Data Structures and Abstractions in Java by Frank M. Carrano

public static void quickSortRec(String[] paramArrayOfString, int paramInt1, int paramInt2)
{
	if (paramInt1 < paramInt2)
	{
		int i = partition(paramArrayOfString, paramInt1, paramInt2);

		quickSortRec(paramArrayOfString, paramInt1, i - 1);
		quickSortRec(paramArrayOfString, i + 1, paramInt2);
	}
}


private static void sortFirstMiddleLast(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
{
	order(paramArrayOfString, paramInt1, paramInt2);
	order(paramArrayOfString, paramInt2, paramInt3);
	order(paramArrayOfString, paramInt1, paramInt2);
}


private static void order(String[] paramArrayOfString, int paramInt1, int paramInt2)
{
	numOperations += 1;
	if (paramArrayOfString[paramInt1].compareTo(paramArrayOfString[paramInt2]) > 0) {
		swap(paramArrayOfString, paramInt1, paramInt2);
	}
}

private static void swap(String[] paramArrayOfString, int paramInt1, int paramInt2) {
	String str = paramArrayOfString[paramInt1];
	paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
	paramArrayOfString[paramInt2] = str;
}


private static int partition(String[] paramArrayOfString, int paramInt1, int paramInt2)
{
	int i = (paramInt1 + paramInt2) / 2;
	sortFirstMiddleLast(paramArrayOfString, paramInt1, i, paramInt2);




	swap(paramArrayOfString, i, paramInt2 - 1);
	int j = paramInt2 - 1;
	String str = paramArrayOfString[j];

	int k = paramInt1 + 1;
	int m = paramInt2 - 1;
	int n = 0;
	while (n == 0)
	{


		numOperations += 1;
		while (paramArrayOfString[k].compareTo(str) < 0) {
			k++;



			numOperations += 1;
		}
		while (paramArrayOfString[m].compareTo(str) > 0) {
			m--;
			numOperations += 1;
		}
		assert ((paramArrayOfString[k].compareTo(str) >= 0) && (paramArrayOfString[m].compareTo(str) <= 0));

		if (k < m) {
			swap(paramArrayOfString, k, m);
			k++;
			m--;
		} else {
			n = 1;
		}
	}


	swap(paramArrayOfString, j, k);
	j = k;
	return j;
}
}