package Muhamed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
//deleted extra Dzenis imports

public class BlockWriterUtility {
	
	public static void checkFolder() {
		// checks and creates a folder if there isnt one
		Path path = Path.of("./blockchain1").normalize().toAbsolutePath();
		
		try {
			Files.createDirectories(path); // create folder if it doesn't already exist
		} catch (IOException e2) {}
		
	}
	
	public static void writeInInfo(int num) {
		
		Path file = Path.of("./blockchain1/").resolve("info.txt").normalize().toAbsolutePath();
		String info = "" + num;
		
		try {
			
			Files.writeString(file, info);
			
		} catch (IOException e) {
			
			//System.out.println("Info file has already been created");
			
		}
	}
	
	public static void writeGenesis(Block genesisBlock) {
		
		checkFolder();
		
		
		Path file = Path.of("./blockchain1/").resolve(genesisBlock.getBlockID() + ".txt").normalize().toAbsolutePath();
		
		try {
			Files.writeString(file, genesisBlock.toString(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			writeInInfo(1);
		} catch (IOException e) {
			
			//System.out.println("Genesis block has already been created");
			
		}
		
		
		
	}
	
	public static void writeBlock(Block newBlock) {
		
		checkFolder();
		
		Path file = Path.of("./blockchain1/").resolve(newBlock.getBlockID()  + ".txt").normalize().toAbsolutePath();
		
		try {
			
			Files.writeString(file, newBlock.toString(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			writeInInfo(newBlock.getBlockID()+1);
			
		} catch (IOException e) {
			
			System.out.println("Block: " + newBlock.getBlockID() + " has already been created");
			
		}
		
		
	}
	

}
