/* Copyright (C) 2018 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of the Java Security project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
	package com.game.src.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

	public class EncryptionRSA {

	    private PrivateKey privateKey;
	    private PublicKey publicKey;

	    public EncryptionRSA() throws NoSuchAlgorithmException {
	        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
	        keyGen.initialize(1024);
	        KeyPair pair = keyGen.generateKeyPair();
	        this.privateKey = pair.getPrivate();
	        this.publicKey = pair.getPublic();
	    }

	    public void writeToFile(String path, byte[] key) throws IOException {
	        File f = new File(path);
	        f.getParentFile().mkdirs();

	        FileOutputStream fos = new FileOutputStream(f);
	        fos.write(key);
	        fos.flush();
	        fos.close();
	    }

	    public PrivateKey getPrivateKey() {
	        return privateKey;
	    }

	    public PublicKey getPublicKey() {
	        return publicKey;
	    }
	    
	    public static void keyGenerator() throws NoSuchAlgorithmException, IOException {
	    	EncryptionRSA keyPairGenerator = new EncryptionRSA();
	        keyPairGenerator.writeToFile("src/RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
	        keyPairGenerator.writeToFile("src/RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
//	        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
//	        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
	    }
	    
	    public static PublicKey getPublicKey(String base64PublicKey){
	        PublicKey publicKey = null;
	        try{
	            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            publicKey = keyFactory.generatePublic(keySpec);
	            return publicKey;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return publicKey;
	    }
	    
	    public static PrivateKey getPrivateKey(String base64PrivateKey){
	        PrivateKey privateKey = null;
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
	        KeyFactory keyFactory = null;
	        try {
	            keyFactory = KeyFactory.getInstance("RSA");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        try {
	            privateKey = keyFactory.generatePrivate(keySpec);
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return privateKey;
	    }
	    
	    public static byte[] encryptOtherPadding(String data, PublicKey publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//("RSA/ECB/PKCS1Padding");//("RSA/ECB/NOPADDING")
	    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    	return cipher.doFinal(data.getBytes());
	    }
	    
	    public static byte[] encryptOtherPadding(byte[] data, PublicKey publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//("RSA/ECB/PKCS1Padding");//("RSA/ECB/NOPADDING")
	    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    	return cipher.doFinal(data);
	    }
	    
	    public static byte[] encryptOtherPadding(String data, PublicKey publicKey, byte[] bytes) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//("RSA/ECB/PKCS1Padding");//("RSA/ECB/NOPADDING")
	        byte[] dataBytes = new byte[bytes.length+data.getBytes().length];
	        System.arraycopy(bytes, 0, dataBytes, 0, bytes.length);
	        System.arraycopy(data.getBytes(), bytes.length, dataBytes, bytes.length, data.getBytes().length);
	    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    	return cipher.doFinal(dataBytes);
	    }
	    
	    public static byte[] encrypt(String data, PublicKey publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");//("RSA/ECB/PKCS1Padding");
	    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    	return cipher.doFinal(data.getBytes());
	    }
	    
	    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");//("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return new String(cipher.doFinal(data));
	    }
	    
	    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	    }
}
