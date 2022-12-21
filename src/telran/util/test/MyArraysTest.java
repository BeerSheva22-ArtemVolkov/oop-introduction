package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {

	
	@Test
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		
		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);
		
		//MyArrays.sort(ar, (a, b) -> a - b);
	}


	@Test
	void evenOddTest() {
		Integer numbers[] = {13, 2, -8, 47, 100, 10, 7};
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}
	
	
	@Test
	void binarySearchTest() {
		Integer[] ar = {1, 3, 5, 7, 9, 123124, 444444};
		assertEquals(-6, MyArrays.binarySearch(ar, 100, new BinarySearchComparator()));
		assertEquals(6, MyArrays.binarySearch(ar, 444444, new BinarySearchComparator()));
		assertEquals(-1, MyArrays.binarySearch(ar, -1, new BinarySearchComparator()));
		assertEquals(1, MyArrays.binarySearch(ar, 3, new BinarySearchComparator()));
		assertEquals(-8, MyArrays.binarySearch(ar, 777777, new BinarySearchComparator()));
	}
}