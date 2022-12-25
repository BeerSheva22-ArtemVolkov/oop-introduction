package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer>{
	
	@Override
	public int compare(Integer o1, Integer o2) {
		return ((isEven(o1) && isEven(o2) && (o1 > o2)) || !isEven(o1) && !isEven(o2) && (o1 < o2) || !isEven(o1) && isEven(o2))? 1 : 0;
	}

	private static boolean isEven(Integer num) {
		return num % 2 == 0 ? true : false;
	}

}
