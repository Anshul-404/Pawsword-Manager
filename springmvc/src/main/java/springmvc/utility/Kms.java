package springmvc.utility;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Kms {
	public static String getKey() {
        return System.getenv("key");
	}
	
	public static SecretKey decodeB64(String encoded) {
        byte[] decodedKey = Base64.getDecoder().decode(encoded);
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(decodedKey, "AES");
        return secretKey;
	}
	
	public static String getEncryptedPassword(String password) {
		SecretKey secretKey = decodeB64(getKey());
		byte[] ciphertext = null;
		
		try {
			ciphertext = encrypt(password, secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String encryptedPass = Base64.getEncoder().encodeToString(ciphertext);
		return encryptedPass;
	}
	
	public static String getDecryptedPassword(String encryptedPassword) {
		SecretKey secretKey = decodeB64(getKey());
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
        try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
        byte[] decryptedBytes = null;
        
        try {
			decryptedBytes = cipher.doFinal(encryptedBytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        return new String(decryptedBytes);
	}
	
	  private static byte[] encrypt(String plaintext, Key key) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        return cipher.doFinal(plaintext.getBytes());
	    }
	  
	  private static String decrypt(byte[] ciphertext, Key key) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decryptedBytes = cipher.doFinal(ciphertext);
	        return new String(decryptedBytes);
	    }
}


