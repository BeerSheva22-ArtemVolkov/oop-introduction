package telran.cipher;

public class BaseCipher {

	private String key;
	
	BaseCipher(int length){
		
		if (length < 2) {length = 2;}
		else if (length > 94) {length = 94;}
		
		String res = "";
		long part1 = 0, part2 = 0;
		int max = BitOperations.N_BITS;
		for (int i = 0; i < length; i++) {
			boolean isFound = false;
			while (!isFound) {
				int num = BitOperations.getRandomASCII();
				if (num < max && BitOperations.getBitValue(part1, num) == 0 )  {
					res = res + (char)num;
					isFound = true;
					part1 = BitOperations.invertBitValue(part1, num);
				}
				else if (num > max - 1 && BitOperations.getBitValue(part2, num - max) == 0 ) {
					res = res + (char)num;
					isFound = true;
					part2 = BitOperations.invertBitValue(part2, num - max);
				}
			}
		}
		key = res;
	}

	public String getKey() {
		return key;
	}
	
	private int getPosition(char symbol) {
		boolean res = false;
		char[] keyArray = getKey().toCharArray();
		int i = 0;
		while(!res) {
			if (keyArray[i] == symbol) {
				res = true;
			}
			else {
				i++;
			}
		}
		return i;
	}
	
	public String cipher(int number) {
		String res = "";
		int delimetr = getKey().length();
		do {
			res = res + key.charAt(number % delimetr);
			number = number / delimetr;
		} while (number != 0);
		return res;
	}
	
	public Integer decipher(String cipher) {
		int res = 0;
		int delimetr = getKey().length();
		char[] charArray = cipher.toCharArray();
		for (int i = 0; i < cipher.length(); i++) {
			res = res + getPosition(charArray[i]) * (int)Math.pow(delimetr, i);
		}
		return res;
	}

}
