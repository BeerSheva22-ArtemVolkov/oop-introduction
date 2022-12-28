package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.MyArrays;

class MyArraysTest {

	static final int N_NUMBERS = 100000;
	static final int N_RUNS = 1000;
	
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
	
	@Disabled
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
	
	@Disabled
	@Test
	void removeTest() {
		Integer nums[] = {100, 10, 18, 10, 20, 18};
		Integer nums2[] = {100, 10, 18, null, 10, 20, 18};
		String strs[] = {"a", "ab", "ab", "abc", "a", "abcd", "abc", "abcde"};
		Integer expectedRepeatedNums[] = {100, 10, 18, 20};
		Integer expectedIfNums[] = {100, 18, 20, 18};
		String expectedRepeatedStrs[] = {"a", "ab", "abc", "abcd", "abcde"};

		//assertArrayEquals(MyArrays.removeRepeated(nums), expectedRepeatedNums);
		assertArrayEquals(MyArrays.removeIf(nums, x -> x.equals(10)), expectedIfNums);
		//assertArrayEquals(MyArrays.removeRepeated(strs), expectedRepeatedStrs);
		//assertTrue(MyArrays.contains(nums2, null));
	}
	
	@Disabled
	@Test
	void joinFunctionalTest() {
		String expected = "13,2,-8,47,100,10,-7,7";
		assertEquals(expected, MyArrays.joinString(numbers, ","));
	}
	
	@Disabled
	@Test
	void joinPerfomanceTest() {
		Integer[] largeArray = getLargeNumbersArray();
		for(int i = 0; i < N_RUNS; i++) {
			MyArrays.joinString(largeArray, ",");
		}
	}
	
	private Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}
	

	@Test
	void arrayListTest() {
	
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		al.add(6);
		al.add(3);
		al.add(7);
		
		assertEquals(al.size(), 8);
		assertFalse(al.isEmpty());
		assertEquals(al.indexOf(3), 2);
		assertEquals(al.lastIndexOf(3), 6);
		assertTrue(al.contains(7));
		assertFalse(al.contains(8));
		
		al.add(8, 11);
		// {1,2,3,4,5,6,3,7,11}
		assertEquals(al.indexOf(11), 8);
		assertEquals(al.size(), 9);
		
		al.remove(0);
		// {2,3,4,5,6,3,7,11}
		assertEquals(al.indexOf(1), -1);
		assertEquals(al.size(), 8);
		
		assertEquals((Integer) 2, al.get(0));
		assertEquals((Integer) 6, al.get(4));
		assertEquals((Integer) 11, al.get(7));
		
		al.removeIf(x->{
			return (x != null && x.equals(3));
		});
		// {2,4,5,6,7,11}
		assertEquals(al.size(), 6);
		assertEquals((Integer) 4, al.get(1));
		assertEquals((Integer) 11, al.get(5));
		
		al.remove((Integer)5);
		// {2,4,6,7,11}
		assertEquals(al.size(), 5);
		assertEquals((Integer) 6, al.get(2));
		
		al.set(2, (Integer) 1000);
		// {2,4,1000,7,11}
		assertEquals((Integer) 1000, al.get(2));
		
		assertArrayEquals(al.toArray(new Integer[0]), new Integer[] {2, 4, 1000, 7, 11});
	}
}
