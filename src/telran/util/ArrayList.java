package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	// нигде нельзя строить новый массив (не использовать New), кроме add
	
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;
	
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
	public boolean remove(T element) {

		boolean res = false;
		int i = indexOf(element);
		if (i != -1) {
			remove(i);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
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
	public boolean isEmpty() {
		return array.length == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return MyArrays.contains(array, pattern);
	}

	@Override
	public T[] toArray(T[] ar) {
		if (size > ar.length) {
			ar = Arrays.copyOf(ar, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public void add(int index, T element) {
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		System.arraycopy(array, index + 1, array, index, size - index);
		size--;
		return get(index);
	}

	@Override
	public int indexOf(T pattern) {
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
		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
	}

}
