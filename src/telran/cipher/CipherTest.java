package telran.cipher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class CipherTest {
	
	
	@Test
	void cipherTest() {
		BaseCipher cp1 = new BaseCipher(95);
		int testNum = 93;
		System.out.println("Key of cp1 = " + cp1.getKey());
		String testStr = cp1.cipher(testNum);
		System.out.println("Cipher of cp1 = " + testStr);
		assertEquals(testNum, cp1.decipher(testStr));
		
		BaseCipher cp2 = new BaseCipher(1);
		testNum = 1245;
		System.out.println("Key of cp2 = " + cp2.getKey());
		testStr = cp2.cipher(testNum);
		System.out.println("Cipher of cp2 = " + testStr);
		assertEquals(testNum, cp2.decipher(testStr));
		
		BaseCipher cp3 = new BaseCipher(33);
		testNum = 5683563;
		System.out.println("Key of cp3 = " + cp3.getKey());
		testStr = cp3.cipher(testNum);
		System.out.println("Cipher of cp3 = " + testStr);
		assertEquals(testNum, cp3.decipher(testStr));
		
		BaseCipher cp4 = new BaseCipher(10);
		testNum = 0;
		System.out.println("Key of cp4 = " + cp4.getKey());
		testStr = cp4.cipher(testNum);
		System.out.println("Cipher of cp4 = " + testStr);
		assertEquals(testNum, cp4.decipher(testStr));
	}
	
	
}
