package Muhamed;

import java.util.ArrayList;

public class MessageBlockChain implements BlockChain {
	
	ArrayList <Block> tempBlocks; // we use the array to store blocks we are currently working with
	int numberOfBlocks;
	
	public MessageBlockChain() {
		tempBlocks = new ArrayList<Block>();
		


	}
	
	@Override
	public boolean setGenesisBlock() {
		
		Block genesisBlock = new Block(0,"Genesis Block");
		genesisBlock.setPreviousHash("0");
		genesisBlock.setBlockHash();
		tempBlocks.add(genesisBlock);
		BlockWriterUtility.writeGenesis(genesisBlock);
		numberOfBlocks = BlockReaderUtility.readInfoNumberOfBlocks();
		return true;
	}

	@Override
	public boolean addBlock(String data) {
		
		numberOfBlocks = BlockReaderUtility.readInfoNumberOfBlocks();
		Block newBlock = new Block(numberOfBlocks,data);
		newBlock.setPreviousHash(BlockReaderUtility.readHash(newBlock.getBlockID()-1));
		newBlock.setBlockHash();
		tempBlocks.add(newBlock);
		BlockWriterUtility.writeBlock(newBlock);
		numberOfBlocks++;
		// TODO Auto-generated method stub
		return false;
	}
	public boolean registerUserOnBlockchain(String username, String password) {
		String hashedPassword = HashingUtility.hash(password);
		addUserBlock(username,hashedPassword);
		
		return false;
	}
	
	public void addUserBlock(String data, String username) {
		numberOfBlocks = BlockReaderUtility.readInfoNumberOfBlocks();
		Block newBlock = new Block(numberOfBlocks,data,username);
		newBlock.setPreviousHash(BlockReaderUtility.readHash(newBlock.getBlockID()-1));
		newBlock.setBlockHash();
		tempBlocks.add(newBlock);
		BlockWriterUtility.writeBlock(newBlock);
		numberOfBlocks++;
	}
	
	public boolean checkIfValidLogin(String username, String password) {
		
		int numOfBlocks = BlockReaderUtility.readInfoNumberOfBlocks();
		String currentTry = HashingUtility.hash(password);
		
		for( int i = 1;i < numOfBlocks;i++) {
			String hashedPassword = BlockReaderUtility.readPasswordHash(i);
			String fileUsername = BlockReaderUtility.readUsername(i);
			//System.out.print(currentTry + " == " + hashedPassword + "\n"); //debugging
			//System.out.print(username + " == " + fileUsername + "\n");
			//if(currentTry.equals(hashedPassword) && username.equals(BlockReaderUtility.readUsername(i))) { // add a check for username.equals(BlockReaderUtility.readUsername) !!!
			if(currentTry.equals(hashedPassword) && username.equals(fileUsername)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean checkIfValidFriend(String friendUsername) {
		int numOfBlocks = BlockReaderUtility.readInfoNumberOfBlocks();
		
		for( int i = 1;i <= numOfBlocks;i++) {
			if(friendUsername.equals(BlockReaderUtility.readUsername(i))) { // add a check for username.equals(BlockReaderUtility.readUsername) !!!
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Block getBlockByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Block> getBlockchain() {
		return tempBlocks;
	}

}
