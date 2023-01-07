package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T>{
	
	boolean add(T element);
	
	boolean remove(T element);
	
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while(it.hasNext()) {
			T obj = it.next();
			if(predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	};
	
	boolean isEmpty();
	
	int size();
	
	boolean contains(T pattern);
	
	/**
	 * 
	 * @param ar 
	 * @return array containing elements of a Collection
	 * if ar refers to memory that enough for all elements of Collection than new array is not created
	 * otherwise there will be created new array
	 * if ar refers to memory that is greater than required for all elements of Collection than all
	 * elements of Collection will be put in the array and rest of memory will be filled by NULL's
	 */
	default T[] toArray(T[] ar) {
		int size = size();
		int i = 0;
		if (size > ar.length) {
			ar = Arrays.copyOf(ar, size);
		}
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			ar[i++] = it.next();
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}
}