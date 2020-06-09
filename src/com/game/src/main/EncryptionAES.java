package com.game.src.main;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * Class used to encrypt/decrypt data from the database.properties file.
 * 
 * @author Lokesh Gupta & Thomas Rader (vil203)
 */
public class EncryptionAES {
	private final static String keyString = "aaah!";
	private static SecretKeySpec secretKeyLocal;
	public static SecretKey secretKeyPublic;
	private static byte[] key;
	 
	/**
	 * Sets the private key to the keyString passcode.
	 * 
	 * @param myKey				Name of the key passcode
	 */
	public static void setKeyLocal(String myKey) {
		MessageDigest sha = null;
		try {
		    key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretKeyLocal = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Sets the private key to a randomly generated AES secret key.
	 * 
	 * @param myKey				Name of the key passcode
	 */
	public static void setKeyPublic() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		    SecureRandom random = new SecureRandom(); // cryptograph. secure random 
		    keyGen.init(random); 
		    SecretKey secretKey = keyGen.generateKey();
		    System.out.println(secretKey.getEncoded());
		    EncryptionAES.secretKeyPublic = (SecretKeySpec) secretKey;
		    System.out.println(EncryptionAES.secretKeyPublic.getEncoded());
		} 
		catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} 
	}
 
    /**
     * Encrypts local data using AES encryption.
	 * 
	 * @param strToEncrypt		String you want to encrypt
	 * @return					Returns an AES encrypted string
	 */
	public static String encryptLocal(String strToEncrypt) {
		try {
			setKeyLocal(keyString);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeyLocal);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} 
		catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
    	}
	    return null;
	}
 
    /**
     * Encrypts data to be sent to server using AES encryption.
	 * 
	 * @param strToEncrypt		String you want to encrypt
	 * @return					Returns an AES encrypted string
	 */
	public static String encryptPublic(String strToEncrypt) {
		try {
	    	if(strToEncrypt == null)
	    		return "";
			System.out.println(strToEncrypt);
			if(EncryptionAES.secretKeyPublic == null)
				setKeyPublic();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeyPublic);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} 
		catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
    	}
	    return null;
	}
	
	/**
	 * Decrypts local data using AES encryption.
	 * 
	 * @param strToDecrypt		String you want to decrypt
	 * @return					Returns a String in UTF-8 format
	 */
	public static String decryptLocal(String strToDecrypt) {
	    try {
	    	if(strToDecrypt == null)
	    		return "";
			setKeyLocal(keyString);
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeyLocal);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    } 
	    catch (Exception e) {
//	    	return null;
	    	System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	
	/**
	 * Decrypts data from server using AES encryption.
	 * 
	 * @param strToDecrypt		String you want to decrypt
	 * @return					Returns a String in UTF-8 format
	 */
	public static String decryptPublic(String strToDecrypt) {
	    try {
			if(EncryptionAES.secretKeyPublic == null)
				setKeyPublic();
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeyPublic);
	        return new String(cipher.doFinal(Base64.getMimeDecoder().decode(strToDecrypt)));
	    } 
	    catch (Exception e) {
	    	System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	public static byte[] EncryptSecretKey (){
	    Cipher cipher = null;
	    byte[] key = null;
	    try{
			CertificateFactory fact = CertificateFactory.getInstance("X.509");
			FileInputStream is = new FileInputStream ("src/serverCRT.crt");
			X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
	        // initialize the cipher with the user's public key
	        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, cer.getPublicKey() );
	        key = cipher.doFinal(secretKeyPublic.getEncoded());
	    }
	    catch(Exception e ){
	        System.out.println ( "exception encoding key: " + e.getMessage() );
	        e.printStackTrace();
	    }
	    return key;
	}
	public static byte[] encryptCBC(String plainText, String key) throws Exception {
        byte[] clean = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        System.out.println("IV pre rand: " + Arrays.toString(iv));
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        System.out.println("IV POST rand: " + Arrays.toString(iv));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(secretKeyPublic.getEncoded());
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
        //System.out.println("Sending = "+);
        return encryptedIVAndText;
    }
	  public static String encryptCBC2(String value, String key, String initVector) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());
	            return Base64.getEncoder().encodeToString(encrypted);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }
	  public static String encryptCBC2(String value, String key, byte[] initVector) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector);
	            SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());
	            return Base64.getEncoder().encodeToString(encrypted);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }
	  public static byte[] encryptCBC2Byte(String value, String key, byte[] initVector) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector);
	            SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());
	            return encrypted;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

	public static String decryptCBC2(String encrypted, String key, byte[] initVector) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector);
	        SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encrypted));

	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	public static String decryptCBC2(byte[] encrypted, String key, byte[] initVector) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector);
	        SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");//("AES/CBC/NoPadding");//("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(encrypted);

	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	public static String decryptCBC2(String encrypted, String key, String initVector) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
	        SecretKeySpec skeySpec = new SecretKeySpec(secretKeyPublic.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encrypted));

	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
    public static String decryptCBC(byte[] encryptedIvTextBytes, String key) throws Exception {
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(secretKeyPublic.getEncoded());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/NoPadding");//("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
    
    public static String decryptCBC(byte[] encryptedIvTextBytes, String key,String IVString) throws Exception {
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        iv = IVString.getBytes();
//        System.out.println(iv);
//        for(int i = 0; i < iv.length; i++)
//        	System.out.println("iv array = " + iv[i]);
        //System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(secretKeyPublic.getEncoded());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/NoPadding");//("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
    
    /**
	 * Sets the private key to a randomly generated AES secret key.
	 * 
	 * @param myKey				Name of the key passcode
	 */
	public static void generateKeyCBC() {
		try {
			Key key;
			SecureRandom rand = new SecureRandom();
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			generator.init(256, rand);
			SecretKey secretKey = generator.generateKey();
		    EncryptionAES.secretKeyPublic = secretKey;
		} 
		catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} 
	}
}
