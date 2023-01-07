package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	private class ArrayListIterator implements Iterator<T> {
		
		int current = 0;
		boolean flNext = false;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[current++];
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(current - 1);
			flNext = false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int tIndex = 0;
		for (int i = 0; i < oldSize; i++) {
			if(predicate.test(array[i])){
				size--;
			}
			else {
				array[tIndex++] = array[i];
			}
		}
		Arrays.fill(array, size, oldSize, null);
		return oldSize > size;
	}

	//Работает
	public boolean myRemoveIf(Predicate<T> predicate) {
		T[] newArr = MyArrays.removeIf(Arrays.copyOf(array, size), predicate);
		int len = newArr.length;
		System.arraycopy(newArr, 0, array, 0, len);
		for (int i = len; i < size; i++) {
			set(i, null);
		}
		size = len;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;

	}
	
	//Работает
	public void myAdd(int index, T element) {
		checkIndex(index, true);
		if(size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index - 1, array, index, size - 1);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while(index < size && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}
	
	//Работает
	public int myIndexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (res == -1 && i < size) {
			if (get(i) == pattern) {
				res = i;
			}
			else {
				i++;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while(index >= 0 && !isEqual(array[index], pattern)) {
			index--;
		}
		return index;
	}

	// Работает
	public int myLastIndexOf(T pattern) {
		int res = -1;
		for(int i = 0; i < size; i++) {
			if (get(i) == pattern) {
				res = i;
			}
		}
		return res;
	}

	@Override
	public T get(int index){
		checkIndex(index, false);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		array[index] = element;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
}
