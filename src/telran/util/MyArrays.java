package telran.util;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrays {
	
	static public <T> void sort(T[] objects, Comparator<T> comporator) {
		int length = objects.length;
		do {
			length--;
		} while(moveMaxAtEnd(objects, length, comporator));
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for(int i = 0; i < length; i++) {
			if(comp.compare(objects[i], objects[i + 1]) > 0){
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}
	
	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;
	}
	
	
	public static <T> int binarySearch (T[] array, int searchedNumber, Comparator<T> comp) {
		
		boolean isFound = false;
		int low = 0, mid = 0, high = array.length - 1;
		Comparable<Integer> midVal = (Comparable<Integer>)searchedNumber;	
		
		while (low <= high && !isFound) {
			mid = (low + high) >>> 1;
			@SuppressWarnings("unchecked")
			int cmp = comp.compare(array[mid], (T) midVal);
			if (cmp < 0) {
				low = mid + 1;
			}
			else if (cmp > 0) {
				high = mid - 1;
			}
			else {
				isFound = true;
			}
		}
		
		return isFound ? mid : -(low + 1);
	}
}
