package Muhamed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class BlockReaderUtility {

	public static int readInfoNumberOfBlocks() {
		String numOfBlocks = "";
		Path file = Path.of("./blockchain1/").resolve("info.txt").normalize().toAbsolutePath();
		
		try
        {
            numOfBlocks = Files.readString(file);
 
            //System.out.println(numOfBlocks);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		
		int result = Integer.parseInt(numOfBlocks);
		return result;
	}

	public static String readHash(int blockID) { // possible bug what if hash is not fixed size of 32????
		
		String tempBlockData = "";
		String hash = "";
		int index = 0;
		
		Path file = Path.of("./blockchain1/").resolve(blockID + ".txt").normalize().toAbsolutePath();
		
		try
        {
            tempBlockData = Files.readString(file);
            index = tempBlockData.indexOf("BlockHash: ");
            hash = tempBlockData.substring(index+11,index+11+32);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		
		
		return hash;
	}
	
	public static String readPasswordHash(int blockID) {
		String tempBlockData = "";
		String hash = "";
		int index = 0;
		
		Path file = Path.of("./blockchain1/").resolve(blockID + ".txt").normalize().toAbsolutePath();
		
		try
        {
            tempBlockData = Files.readString(file);
            
            index = tempBlockData.indexOf("password:");
            hash = tempBlockData.substring(index + 9, index + 9 + 32);
            //System.out.print(hash); //debugging with login check
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		
		
		return hash;
	}
	
	public static String readUsername(int blockID) {
		String tempBlockData = "";
		String username = "";
		int indexUser = 0;
		int indexPassword = 0;
		
		//System.out.println("RADI OVDJE");
		Path file = Path.of("./blockchain1/").resolve(blockID + ".txt").normalize().toAbsolutePath();
		
		try
        {
			//System.out.println("RADI OVDJE");
            tempBlockData = Files.readString(file);
            //System.out.print(tempBlockData);
            
            
            indexUser = tempBlockData.indexOf("data: ");
            
            indexPassword = tempBlockData.indexOf("password:");
           
            username = tempBlockData.substring(indexUser + 6, indexUser + (indexPassword - indexUser) - 1);
            //username = tempBlockData.substring(indexUser + 6, indexPassword - 1);
            //System.out.println(username + "iz fajla");
            
        } 
        catch (Exception e) 
        {
            //e.printStackTrace();
        }
		//System.out.println("bem ti mater");
	
		return username;
	}
	
}
