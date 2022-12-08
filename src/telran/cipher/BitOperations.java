package telran.cipher;

public class BitOperations {

	private static final double MIN = 33;
	private static final double MAX = 126;
	public static final int N_BITS = 64;
	
	public static int getRandomASCII() {
		return (int) (MIN + Math.random() * (MAX - MIN + 1));
	}
	
	public static int getBitValue(long number, int nBit) {
		int res = -1;
		if (checkNbit(nBit)) {
			long mask = getMask(nBit);
			res = (number & mask) == 0 ? 0 : 1;
		}
		return res;
	}
	
	private static boolean checkNbit(int nBit) {
		return nBit < N_BITS && nBit > -1;
	}
	
	private static long getMask(int nBit) {
		return 1l << nBit;
	}
	
	public static long setBitValue(long number, int nBit, boolean value) {
		long res = -1;
		
		if (checkNbit(nBit)) {
			long mask = getMask(nBit);
			res = value ? number | mask : number & ~mask;
		}
		return res;
	}
	
	public static long invertBitValue(long number, int nBit) {
		long res = -1;
		if (checkNbit(nBit)) {
			long mask = getMask(nBit);
			res = number ^ mask;
		}
		return res;
	}
}
