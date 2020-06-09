package com.game.src.main;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ServerVerify {
	public boolean verified = false;
	private String generateSafeToken() {
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[16];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    String token = encoder.encodeToString(bytes);
	    return token;
	}
	private byte[] makeIV() {
		SecureRandom random = new SecureRandom();
		byte iv[] = new byte[16];//generate random 16 byte IV AES is always 16bytes
        random.nextBytes(iv);
        return iv;
	}
	/**
	 * reads a public key from a file
	 * @param filename name of the file to read
	 * @param algorithm is usually RSA
	 * @return the read public key
	 * @throws Exception
	 */
	public  PublicKey getPemPublicKey(String filename, String algorithm) throws Exception {
	      File f = new File(filename);
	      FileInputStream fis = new FileInputStream(f);
	      DataInputStream dis = new DataInputStream(fis);
	      byte[] keyBytes = new byte[(int) f.length()];
	      dis.readFully(keyBytes);
	      dis.close();

	      String temp = new String(keyBytes);
	      String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----\n", "");
	      publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");

	      byte[] decoded = Base64.getMimeDecoder().decode(publicKeyPEM);
	      X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
	      KeyFactory kf = KeyFactory.getInstance(algorithm);
	      return kf.generatePublic(spec);
	}
	public int serverVerify() {
		try {
			CertificateFactory fact = CertificateFactory.getInstance("X.509");
			FileInputStream is = new FileInputStream ("src/serverCRT.crt");
			X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
			PublicKey key = cer.getPublicKey();
			PublicKey pemKey = getPemPublicKey("src/publicPEM.pem","RSA");
			//message = message.replace("\0", "");
			byte[] bytes = EncryptionRSA.encryptOtherPadding("hi", pemKey);
			String message = Base64.getEncoder().encodeToString(bytes);
			Game.out.println(message);
			//System.out.println(EncryptionRSA.decrypt(EncryptionRSA.encrypt("gay", clientRSA.getPublicKey()),clientRSA.getPrivateKey()));
			//System.out.println(message);
			String gay = "";
			if((gay = Game.in.readLine()) == null) {
    			throw new IOException("Server is down.");
    		}
			if(gay.equals("hey")) {
//				System.out.println("woohoo");
			}
			else
				return -1;
			/*
			//Create RSA
			EncryptionRSA clientRSA = new EncryptionRSA();
//			String publicKeyString = "-----BEGIN PUBLIC KEY-----\n";
//			publicKeyString = publicKeyString+Base64.getEncoder().encodeToString(clientRSA.getPublicKey().getEncoded());
//			publicKeyString = publicKeyString+"\n-----END PUBLIC KEY-----";
			String publicKeyString = Base64.getEncoder().encodeToString(clientRSA.getPublicKey().getEncoded());
			System.out.println(publicKeyString);
			System.out.println(EncryptionRSA.decrypt(EncryptionRSA.encryptOtherPadding(publicKeyString, clientRSA.getPublicKey()),clientRSA.getPrivateKey()));
			bytes = EncryptionRSA.encryptOtherPadding(publicKeyString,pemKey);
			message = Base64.getEncoder().encodeToString(bytes);
			System.out.println(message);
			Game.out.println(message);
			if((gay = Game.in.readLine()) == null) {
    			throw new IOException("Server is down.");
    		}
			//System.out.println(cer.getPublicKey());
			 
			//Create AES
			if(EncryptionAES.secretKeyPublic != null)
				EncryptionAES.secretKeyPublic.destroy();
			EncryptionAES.secretKeyPublic = null;
			EncryptionAES.setKeyPublic();
			message = Base64.getEncoder().encodeToString(EncryptionAES.secretKeyPublic.getEncoded());
			System.out.println("without 64 message = "+EncryptionAES.secretKeyPublic.getEncoded());
			String decoded = new String(EncryptionAES.secretKeyPublic.getEncoded());
			System.out.println("decoded 64 message = "+decoded);
			System.out.println(message);
			bytes = EncryptionRSA.encryptOtherPadding(message, pemKey);
			message = Base64.getEncoder().encodeToString(bytes);
			System.out.println(message);
			System.out.println("boogawooga = "+EncryptionAES.encryptPublic("boogawooga"));
			Game.out.println(message);
			gay = "";
			if((gay = Game.in.readLine()) == null) {
    			throw new IOException("Server is down.");
    		}
			System.out.println("gay = "+gay);
			String gays = new String(Base64.getDecoder().decode(gay.getBytes()));
			System.out.println("gay64 = "+gays);
			if(EncryptionAES.decryptPublic(gay).equals("hey")) {
				System.out.println("woohoo");
			}
			else
				return -1;
			
				*/
			//Create AES-CBC
//			if(EncryptionAES.secretKeyPublic != null)
//				EncryptionAES.secretKeyPublic.destroy();
			EncryptionAES.secretKeyPublic = null;
			EncryptionAES.generateKeyCBC();
			message = Base64.getEncoder().encodeToString(EncryptionAES.secretKeyPublic.getEncoded());
			//byte[] message2 = EncryptionAES.EncryptSecretKey();
			//System.out.println("without 64 message = "+EncryptionAES.secretKeyPublic.getEncoded());
			//System.out.println("with 64 message2 = "+message2);
			String encodedKey = Base64.getEncoder().encodeToString(EncryptionAES.secretKeyPublic.getEncoded());
			//System.out.println("decoded 64 message = "+encodedKey+" lenght = "+encodedKey.length());
			//System.out.println("decoded 64 message2 = "+Base64.getDecoder().decode(encodedKey));
			//System.out.println(message);
			//String example = Base64.getEncoder().encodeToString(EncryptionAES.encryptCBC("faggot", encodedKey));
			//System.out.println("unencrypted ivs = "+example);
			//System.out.println("ivs = "+EncryptionAES.decryptCBC(Base64.getDecoder().decode(example),encodedKey));
			bytes = EncryptionRSA.encryptOtherPadding(message, pemKey);
			message = Base64.getEncoder().encodeToString(bytes);
			Game.out.println(message);
			gay = "";
			if((gay = Game.in.readLine()) == null) {
    			throw new IOException("Server is down.");
    		}
//			System.out.println("gay = "+gay);
			String ivs = gay.substring(0,16);
			gay = gay.substring(16, gay.length());
			if(gay.length() % 8 != 0) {
				if(gay.length()+1 % 8 == 0)
					gay = gay.substring(0, gay.length()+1);
				else
					gay = gay.substring(0, gay.length()-1);
			}
//			System.out.println("gay = "+gay);
//    		byte[] decoded = Base64.getDecoder().decode(gay);
//    		String decodedString = new String(decoded);
//			System.out.println("decodedString = "+decodedString);
//			String ivs = decodedString.substring(0,16);
//			gay = decodedString.substring(16, decodedString.length()-1);
//			System.out.println("gay = "+gay+" size = "+gay.length());
//			System.out.println("iv = "+ivs);
			//String ivs = gay.substring(0,16);
			//gay= gay.substring(16, gay.length());
//			System.out.println("gay = "+gay+" size = "+gay.length());
			//System.out.println("iv = "+ivs);
			//System.out.println(EncryptionAES.decryptCBC(Base64.getDecoder().decode(gay),encodedKey));
//			System.out.println("gay decrypted = "+EncryptionAES.decryptCBC2(gay,encodedKey,ivs));
			if(EncryptionAES.decryptCBC2(gay,encodedKey,ivs) != null &&
					EncryptionAES.decryptCBC2(gay,encodedKey,ivs).equals("hey")) {
//				System.out.println("herewoohoo");
			}
			else
				return -1;
			byte[] ivBytes = makeIV();
			byte[] hi = EncryptionAES.encryptCBC2Byte("hi", encodedKey, ivBytes);
			byte[] concatBytes = new byte[ivBytes.length+hi.length];
			System.arraycopy(ivBytes, 0, concatBytes, 0, ivBytes.length);
			System.arraycopy(hi, 0, concatBytes, ivBytes.length, hi.length);
			//System.arraycopy(gay.getBytes(), 0, bytess, 0, 16);
			//System.arraycopy(gay.getBytes(), 16, bytess2, 0, gay.getBytes().length-16);
			message = Base64.getEncoder().encodeToString(concatBytes);
//			System.out.println("iv = "+ new String(ivBytes));
//			System.out.println("iv from base64 = "+ ivs);
//			System.out.println("cipher = "+ new String(hi));
//			System.out.println("message = "+ message);
//			System.out.println("final bytes = "+ new String(concatBytes) + " size = "+concatBytes.length);
//			System.out.println("gay decrypted = "+EncryptionAES.decryptCBC2(Base64.getEncoder().encodeToString(hi),encodedKey,ivBytes));
			Game.out.println(message);
			gay = "";
			if((gay = Game.in.readLine()) == null) {
    			throw new IOException("Server is down.");
    		}
			ivs = gay.substring(0,16);
			gay = gay.substring(16, gay.length());
			if(gay.length() % 8 != 0) {
				if(gay.length()+1 % 8 == 0)
					gay = gay.substring(0, gay.length()+1);
				else
					gay = gay.substring(0, gay.length()-1);
			}
			if(EncryptionAES.decryptCBC2(gay,encodedKey,ivs) != null &&
					EncryptionAES.decryptCBC2(gay,encodedKey,ivs).equals("start")) {
				Game.serverVerified = true;
//				System.out.println("herewoohooDONE");
			}
			else
				return -1;
			Game.serverVerified = true;
			//message = EncryptionAES.encryptCBC2("hi", encodedKey,ivBytes);
//			bytes = EncryptionAES.encryptCBC2(message, encodedKey, initVector)(message, pemKey);
//			message = Base64.getEncoder().encodeToString(bytes);
//			Game.out.println(message);
			
			is.close();
			return 0;//System.exit(1);
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}
/*
byte[] bytess = new byte[16];
//byte[] bytess2 = new byte[gay.getBytes().length-16];
////gay = gay.substring(16, gay.length());
//System.arraycopy(gay.getBytes(), 0, bytess, 0, 16);
//System.arraycopy(gay.getBytes(), 16, bytess2, 0, gay.getBytes().length-16);
//String gay2 = new String(bytess2);
//if(gay.length() % 8 != 0) {
//	if(gay.length()+1 % 8 == 0)
//		gay = gay.substring(0, gay.length()+1);
//	else
//		gay = gay.substring(0, gay.length()-1);
//}
//System.out.println("bytes string = "+ new String(bytess) +"size = " + bytess.length +"\nbytes2 string = "+ gay2);
//System.out.println("gay = "+gay);
//byte[] decoded = Base64.getDecoder().decode(gay);
//String decodedString = new String(decoded);
//System.out.println("decodedString = "+decodedString);
//String ivs = decodedString.substring(0,16);
//gay = decodedString.substring(16, decodedString.length()-1);
//System.out.println("gay = "+gay+" size = "+gay.length());
//System.out.println("iv = "+ivs);
//String ivs = gay.substring(0,16);
//gay= gay.substring(16, gay.length());
System.out.println("gay = "+gay+" size = "+gay.length());
//System.out.println("iv = "+ivs);
//System.out.println(EncryptionAES.decryptCBC(Base64.getDecoder().decode(gay),encodedKey));
System.out.println("gay decrypted = "+EncryptionAES.decryptCBC2(gay2,encodedKey,new String(bytess)));
if(EncryptionAES.decryptCBC2(gay2,encodedKey,new String(bytess)) != null &&
		EncryptionAES.decryptCBC2(gay2,encodedKey,new String(bytess)).equals("hey")) {
	System.out.println("herewoohoo");
}
else
	return -1;
*/