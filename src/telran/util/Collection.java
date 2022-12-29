package telran.util;

import java.util.function.Predicate;

public interface Collection<T> {

	boolean add(T element);
	
	boolean remove(T element);
	
	boolean removeIf(Predicate<T> predicate);
	
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
	T[] toArray(T[] ar);
}