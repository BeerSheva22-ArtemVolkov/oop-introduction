package telran.memory;

public class MemoryOperations {

	public static int getMaxAvailableMemory() {
		
		byte ar[] = null;
		long left = 0;
		long right = Integer.MAX_VALUE;
		long middle = 0;
		
		while(left <= right) {
			try {
				middle = (left + right) / 2;
				ar = new byte[(int)middle];
				left = middle + 1;
			}
			catch (Throwable e){
				right = middle - 1;
			}
			ar = null;
		}
		
		return (int)right;
	}
}
