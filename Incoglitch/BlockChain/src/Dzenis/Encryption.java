package Dzenis;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Encryption {

	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public Encryption() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
		} catch (Exception e) {}
	}
	
	public PublicKey getPublic() {
		return this.publicKey;
	}
	
	public PrivateKey getPrivate() {
		return this.privateKey;
	}
	
	public String encrypt(String plainText) throws Exception {
		byte[] textToBytes = plainText.getBytes();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(textToBytes);
		return encode(encryptedBytes);
	}
	
	private String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	public String decrypt(String cipherText) throws Exception{
		byte[] encryptedBytes = decode(cipherText);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage, "UTF8");
	}
	
	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	public static void main(String[] args) {
		Encryption RSA = new Encryption();
		try {
			String encryptedMessage = RSA.encrypt("Hello World");
			String decryptedMessage = RSA.decrypt(encryptedMessage);
			
			//System.out.println("Private: " + RSA.getPrivate());
			//System.out.println("Public: " + RSA.getPublic());
			System.out.println("Encrypted: " + encryptedMessage);
			System.out.println("Decrypted: " + decryptedMessage);
		} catch (Exception e) {}
	}

}
