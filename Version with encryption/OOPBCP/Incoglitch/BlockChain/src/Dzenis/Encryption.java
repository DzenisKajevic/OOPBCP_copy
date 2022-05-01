package Dzenis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


import javax.crypto.Cipher;

public class Encryption {

	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public Encryption() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch (Exception e) {}
	}
	
	public void saveKeys() {
		Path path = Path.of("./EncryptionKeys/").normalize().toAbsolutePath();

		String stringPath = path.toString();
		try {
			Files.createDirectories(path); // create folder if it doesn't already exist
		} catch (IOException e2) {
			
		}	
		// Store Public Key.
		File f = new File(stringPath+"/public.key");
		if(!f.exists()) { 
		    // do something
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
			
			try {
				FileOutputStream fos = new FileOutputStream(stringPath + "/public.key");
				fos.write(x509EncodedKeySpec.getEncoded());
				
				fos.close();
			}catch(Exception e) {}
		}
	/*			try {
					Files.writeString(path.resolve("/public.key"), new String(x509EncodedKeySpec.getEncoded()), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
				} catch (IOException e) {}
	*/
				// Store Private Key.
		File f2 = new File(stringPath+"/private.key");
		if(!f2.exists()) { 
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
			try {
				FileOutputStream fos = new FileOutputStream(path + "/private.key");
				fos.write(pkcs8EncodedKeySpec.getEncoded());
					
				fos.close();
			}catch(Exception e) {}
		}
	/*			try {
					Files.writeString(path.resolve("/private.key"), new String(pkcs8EncodedKeySpec.getEncoded()), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
				} catch (IOException e) {}
		*/		/*fos = new FileOutputStream(path + "/private.key");
				fos.write(pkcs8EncodedKeySpec.getEncoded());
	
				fos.close();*/
			/*}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	}
	
	/*public PublicKey loadPublicKey() throws Exception
	{
		String directoryName = "EncryptionKeys";
		
		
		Path path = Path.of(directoryName).resolve("/public.key").normalize().toAbsolutePath();
		
		String path2 = path.toString();
		byte[] publicKeyBytes = Files.readString(path).getBytes();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

		return publicKey;
	}
	
	public PrivateKey loadPrivateKey() throws Exception
	{
		String directoryName = "EncryptionKeys";
		Path path = Path.of(directoryName).resolve("/private.key").normalize().toAbsolutePath();
		
		byte[] privateKeyBytes = Files.readString(path).getBytes();

		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec (privateKeyBytes);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		return privateKey;
	}*/
	/*
	public String encrypt(String message) throws Exception{
		String directoryName = "EncryptionKeys";
		 
		Path path = Path.of(directoryName).normalize().toAbsolutePath();
		String stringPath = path.toString();
		
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, this.LoadKeyPair(stringPath,"RSA").getPublic());
		
		byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedMessageBytes = encryptCipher.doFinal(messageBytes);

		String encryptedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
		return encryptedMessage;
	}*/
	/*
	public String decrypt(String message) throws Exception{
		String directoryName = "EncryptionKeys";
		 
		Path path = Path.of(directoryName).normalize().toAbsolutePath();
		String stringPath = path.toString();
		
		Cipher decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, this.LoadKeyPair(stringPath,"RSA").getPrivate());
		
		byte[] decryptedMessageBytes = decryptCipher.doFinal(message.getBytes());
		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
		
		return decryptedMessage;
	}*/
	/*
	public KeyPair LoadKeyPair(String algorithm) {
		// Read Public Key.

		String directoryName = "EncryptionKeys";
		Path path = Path.of(directoryName).normalize().toAbsolutePath();
		
		File filePublicKey = new File(path + "/public.key");
		FileInputStream fis = new FileInputStream(path + "/public.key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
 
		// Read Private Key.
		File filePrivateKey = new File(path + "/private.key");
		fis = new FileInputStream(path + "/private.key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
 
		return new KeyPair(publicKey, privateKey);
	}
	*//*
	public PublicKey getPublic() {
		return this.publicKey;
	}
	
	public PrivateKey getPrivate() {
		return this.privateKey;
	}
	*/
	public static String encrypt(String plainText) throws Exception {
		byte[] textToBytes = plainText.getBytes();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		Path path = Path.of("./EncryptionKeys/").normalize().toAbsolutePath();
		String directoryName = path.toString();
		KeyPair kp = loadKeyPair(directoryName, "RSA");
		//System.out.println(kp.getPrivate());
		cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
		byte[] encryptedBytes = cipher.doFinal(textToBytes);
		return encode(encryptedBytes);
	}
	
	private static String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	public static String decrypt(String cipherText) throws Exception{
		byte[] encryptedBytes = decode(cipherText);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		Path path = Path.of("./EncryptionKeys/").normalize().toAbsolutePath();
		String directoryName = path.toString();
		KeyPair kp = loadKeyPair(directoryName, "RSA");
		//System.out.println(kp.getPrivate());
		cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage, "UTF8");
	}
	
	private static byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	public static KeyPair loadKeyPair(String path, String algorithm)
			throws Exception{
		// Read Public Key.
		File filePublicKey = new File(path + "/public.key");
		FileInputStream fis = new FileInputStream(path + "/public.key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
 
		// Read Private Key.
		File filePrivateKey = new File(path + "/private.key");
		fis = new FileInputStream(path + "/private.key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		return new KeyPair(publicKey, privateKey);
	}
	
	
	public static void main(String[] args) {
		Encryption RSA = new Encryption();
		try {
			
			String directoryName = "EncryptionKeys";
			 
			Path path = Path.of(directoryName).normalize().toAbsolutePath();
			String stringPath = path.toString();
			//System.out.println("Encrypted: " + encryptedMessage);
			RSA.saveKeys();
			KeyPair nw = loadKeyPair(stringPath, "RSA");
			System.out.println(nw.getPrivate());
			try {
			String encryptedMessage = encrypt("Hello World");
			System.out.println("Encrypted: " + encryptedMessage);
			String decryptedMessage = decrypt(encryptedMessage);
			System.out.println("Decrypted: " + decryptedMessage);
			} catch (Exception e) {e.printStackTrace();}
			

			//System.out.println("Private: " + RSA.getPrivate());
			//System.out.println("Public: " + RSA.getPublic());
			
			
		} catch (Exception e) {}
	}

}
