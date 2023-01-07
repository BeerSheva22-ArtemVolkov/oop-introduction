package telran.util.test;

import org.junit.jupiter.api.*;

import telran.util.*;

import static org.junit.Assert.*;

class LinkedListTest extends ListTest{

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}

	@Test
	void isLoopTest() {
		assertFalse(((LinkedList<Integer>)collection).isLoop());
		((LinkedList<Integer>)collection).setNext(5, 1);
		assertTrue(((LinkedList<Integer>)collection).isLoop());
	}
}
