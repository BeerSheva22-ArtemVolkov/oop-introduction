package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MemoryOperationsTest {

	byte ar[];
	
	
	@Test
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flException = false;
		try {
			ar = new byte[maxMemory + 1];
		} catch (Throwable e){
			flException = true;
		}
		assertTrue(flException);
	}
	
	@Test
	void standardMemoryMethods() {
		System.out.println("Used Memory   : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
	    System.out.println("Free Memory   : " + Runtime.getRuntime().freeMemory() + " bytes");
	    System.out.println("Total Memory  : " + Runtime.getRuntime().totalMemory() + " bytes");
	    System.out.println("Max Memory    : " + Runtime.getRuntime().maxMemory() + " bytes");
	}

}
