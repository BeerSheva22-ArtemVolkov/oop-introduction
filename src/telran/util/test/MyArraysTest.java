package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {

	Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
	String[] ar = {"ab", "abm", "abmb", "abmbc"};
//	Comparator<Integer> evenOddComparator = (o1, o2) -> {
//		int reminder = Math.abs(o1) % 2;
//		int res = reminder - Math.abs(o2) % 2;
//		if (res == 0) {
//			res = reminder != 0? Integer.compare(o2, o1) : Integer.compare(o1, o2);
//		}
//		return res;
//	};
	Comparator<Integer> evenOddComparator = this::evenOddCompare;
	
	@Disabled
	@Test
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		
		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);
		
		//MyArrays.sort(ar, (a, b) -> a - b);
	}

	@Disabled
	@Test
	void evenOddTest() {
		//Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7, -7};
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}
	
	@Disabled
	@Test
	void binarySearchTest() {
		Integer[] ar = {1, 3, 5, 7, 9, 123124, 444444};
		assertEquals(-6, MyArrays.binarySearch(ar, 100, new BinarySearchComparator()));
		assertEquals(6, MyArrays.binarySearch(ar, 444444, new BinarySearchComparator()));
		assertEquals(-1, MyArrays.binarySearch(ar, -1, new BinarySearchComparator()));
		assertEquals(1, MyArrays.binarySearch(ar, 3, new BinarySearchComparator()));
		assertEquals(-8, MyArrays.binarySearch(ar, 777777, new BinarySearchComparator()));
	}
	
	@Disabled
	@Test
	void binarySearchStringTest() {
		//String[] ar = {"ab", "abm", "abmb", "abmbc"};
		Comparator<String> comp = new StringsComparator();
		assertEquals(0, MyArrays.binarySearch(ar, "ab", comp));
		assertEquals(1, MyArrays.binarySearch(ar, "abm", comp));
		assertEquals(2, MyArrays.binarySearch(ar, "abmb", comp));
		assertEquals(3, MyArrays.binarySearch(ar, "abmbc", comp));
		assertEquals(-1, MyArrays.binarySearch(ar, "a", comp));
		assertEquals(-3, MyArrays.binarySearch(ar, "abma", comp));
		assertEquals(-5, MyArrays.binarySearch(ar, "lmn", comp));
	}
	
	
	@Test
	void filterTest() {
		// мы хотим найти четные числа и с помощью метода фильтр найти строки которые содержат "какую то" букву
		int dividor = 2;
		String subStr = "m";
//		Predicate<Integer> predEven = new DividorPredicate(dividor);
//		Predicate<String> predSubstr = new SubstrPredicate(subStr);
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		
		String[] expectedStr = {"abm", "abmb", "abmbc"};
		Integer expectedNumbers[] = {2, -8, 100, 10};
		
		assertArrayEquals(expectedStr, MyArrays.filter(ar, predSubstr));
		assertArrayEquals(expectedNumbers, MyArrays.filter(numbers, predEven));
	}
	
	int evenOddCompare(Integer o1, Integer o2){
		int reminder = Math.abs(o1) % 2;
		int res = reminder - Math.abs(o2) % 2;
		if (res == 0) {
			res = reminder != 0? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;
	}
}
