package Muhamed;

import java.util.ArrayList;

public interface BlockChain {
	
	public boolean setGenesisBlock();
	
	public boolean addBlock(String data);
	
	public Block getBlockByID(int ID);
	
	public ArrayList<Block> getBlockchain();
	
}
