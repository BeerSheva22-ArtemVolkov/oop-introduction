package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

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
	
	public static <T> int binarySearch (T[] array, T key, Comparator<T> comp) {
		
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2; 
		
		while (left <= right && !array[middle].equals(key)) {
			if(comp.compare(key, array[middle]) < 0) {
				right = middle - 1;
			}
			else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		
		return left > right ? -left - 1 : middle;
	}

	public static<T> T[] filter(T[] array, Predicate<T> predicate) {
		
		int index = 0;
		T[] res = Arrays.copyOf(array, array.length);
		
		for(T element: array) {
			if(predicate.test(element)) {
				res[index++] = element;
			}
		}
		return Arrays.copyOf(res, index);
	}

	private static <T>int getCountPredicate(T[] array, Predicate<T> predicate) {
		
		int res = 0;
		
		for(T element: array) {
			if(predicate.test(element)) {
				res++;
			}
		}
		
		return res;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		return filter(array, predicate.negate());
	}
	
	public static <T> T[] removeRepeated(T[] array) {
		final Object[] helper = new Object[array.length];
		final int index[] = {0};
		return removeIf(array, element -> {
			// Формально, определяет какие элементы будут удалены, путем получения True или False из Лямбда замыкания
			boolean res = true;
			if (!contains(helper, element)) {
				helper[index[0]++] = element;
				res = false;
			}
			return res;
		});
	}
	
	public static <T> boolean contains(T[] array, T pattern) {
		int index = 0;
		while(index < array.length && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < array.length;
	}
	
	static private boolean isEqual(Object element, Object pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}
	
	public static <T> String join(T[] array, String delimiter) {
		String res = "";
		if (array.length > 0) {
			StringBuilder builder = new StringBuilder(array[0].toString());
			for (int i = 1; i < array.length; i++) {
				builder.append(delimiter).append(array[i]);
			}
			res = builder.toString();
		}
		return res;
	}
	
	public static <T> String joinString(T[] array, String delimiter) {
		String res = "";
		if (array.length > 0) {
			res = array[0].toString();
			for (int i = 1; i < array.length; i++) {
				res += delimiter + array[i];
			}
		}
		return res;
	}
	
	public static <T> int myBinarySearch (T[] array, int searchedNumber, Comparator<T> comp) {
		
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

	public static <T> T[] myRemoveRepeated(T[] array) {
		T[] res = Arrays.copyOf(array, array.length);
		Arrays.fill(res, null);
		int i = 0;

		while(array.length != 0) {
			T a0 = array[0];
			if(!contains(res, a0)) {
				array = removeIf(array, x -> x.equals(a0));
				res[i++] = a0;
			}
		}
		
		return Arrays.copyOf(res, i);
	}
	
	public static <T> boolean myContains(T[] array, T pattern) {
		boolean res = false;
		int i = 0;
		int len = array.length;
		while (!res && i < len) {
			res = array[i] == null ? pattern == null ? true : false : array[i].equals(pattern) ? true : false;
			i++;
		}
		return res;
	}
	
}
