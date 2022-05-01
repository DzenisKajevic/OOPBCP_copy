package Muhamed;

public class Block {
	
	private int blockID;
	private String blockHash;
	private String hashOfPreviousBlock;
	
	private String data;
	private String username;
	
	public Block(int blockID, String data) {
		this.blockID = blockID;
		this.data = data;
		this.username = "";
	}
	
	public Block(int blockID, String data, String username) {
		this(blockID,data);
		this.username = username;
	}
	
	public void setBlockHash() {
		this.blockHash = HashingUtility.hash(this.blockID + this.hashOfPreviousBlock + this.data + this.username);
	}
	
	public void setPreviousHash(String previousBlockHash) {
		this.hashOfPreviousBlock = previousBlockHash;
	}
	
	public int getBlockID() {
		return this.blockID;
	}
	
	public String getBlockHash() {
		return this.blockHash;
	}
	
	public String getPreviousHash() {
		return this.hashOfPreviousBlock;
	}
	
	public String toString() {
		return ("blockID: " + this.blockID + "\nBlockHash: " + this.blockHash + "\ndata: " + this.data + "\npassword:" + this.username + "\npBlockHash: " + this.hashOfPreviousBlock);
	}

}
