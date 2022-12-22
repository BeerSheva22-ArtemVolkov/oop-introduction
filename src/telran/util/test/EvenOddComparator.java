package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer>{

	public int myCompare(Integer o1, Integer o2) {
		return ((isEven(o1) && isEven(o2) && (o1 > o2)) || isOdd(o1) && isOdd(o2) && (o1 < o2) || isOdd(o1) && isEven(o2))? 1 : 0;
	}

	private static boolean isEven(Integer num) {
		return num % 2 == 0 ? true : false;
	}
	
	private static boolean isOdd(Integer num) {
		return num % 2 == 1 ? true : false;
	}
	
	@Override
	public int compare(Integer o1, Integer o2) {
		int reminder = Math.abs(o1) % 2;
		int res = reminder - Math.abs(o2) % 2;
		if (res == 0) {
			res = reminder != 0? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;
	}

}
