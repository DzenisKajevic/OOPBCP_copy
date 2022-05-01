package Muhamed;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtility {

	public static String hash(String data) {
		
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(data.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String tempString = bigInt.toString(16);
			String hash = "";
			while(hash.length()<32-tempString.length()) {
				hash+='0';
			}
			hash+=tempString;

			
			return hash;
		} catch (NoSuchAlgorithmException e) {
			
			System.out.print("Hashing algorithm was not found! ):");
			return null;
		}
	}

}
