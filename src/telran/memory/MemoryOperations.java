package telran.memory;

public class MemoryOperations {

	public static int getMaxAvailableMemory() {
		
		int minMemory = 0;
		int maxMemory = Integer.MAX_VALUE;
		int middle = 0;
		
		while(minMemory <= maxMemory) {
			try {
				middle = minMemory + (maxMemory - minMemory) / 2;
				byte[] ar = new byte[middle];
				minMemory = middle + 1;
			}
			catch (Throwable e){
				maxMemory = middle - 1;
			}
		}
		
		return maxMemory;
	}
}
