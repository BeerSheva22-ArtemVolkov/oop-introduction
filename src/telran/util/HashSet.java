package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T> [] hashTable;
	private float factor;
	
	private class HashSetIterator implements Iterator<T>{

		int htIndexCur = 0;
		int indexCur = 0;
		int htIndexPrev = 0;
		int indexPrev = 0;
		List<T> current; 
		List<T> prev; 		
		Iterator<T> it = null;
		boolean flNext = false;
		
		@Override 
		public boolean hasNext() {
			boolean res = false;
			while(!res && htIndexCur < hashTable.length) {
				if(current == null) {
					current = hashTable[htIndexCur];
				}
				if (current != null) {
					if (it == null) {
						it = current.iterator();
					}
					if (it.hasNext()) {
						res = true;
					}	
				}
				if (!res) {
					htIndexCur++;
					it = null;
				}
			}
			return res;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.get(indexCur);
			prev = current;
			htIndexPrev = htIndexCur;
			indexPrev = indexCur;
			if (indexCur + 1 != current.size()) {
				indexCur++;
			}
			else if (htIndexCur + 1 > hashTable.length){
				flNext = false;
			}
			else {
				indexCur = 0;
				do {
					if (++htIndexCur < hashTable.length) {
						current = hashTable[htIndexCur];
					}
				} while (current == null);
				it = current.iterator();
			}
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			prev.remove(indexPrev);
			size--;
			if (prev.isEmpty()) {
				hashTable[htIndexPrev++] = null;
			}
			else {
				indexCur--;
			}
			flNext = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if(tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of Hash Table");
		}
		if(factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong initial factor");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}
	
	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}
	
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T> list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)){
			res = true;
			list.add(element);
			size++;
		}
		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list : hashTable) {
			if(list != null) {
				for(T obj : list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(T element) {
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {

		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}

	//void clearIndex
	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}
//
//	//FIXME The following method is only for initial test
//	@Override
//	public T[] toArray(T[] ar) {
//		if (ar.length < size) {
//			ar = Arrays.copyOf(ar, size);
//		}
//		int index = 0;
//		for (List<T> list : hashTable) {
//			if(list != null) {
//				for(T obj : list) {
//					ar[index++] = obj;
//				}
//			}
//		}
//		Arrays.fill(ar, size, ar.length, null);
//		return ar;
//	}
	
}
