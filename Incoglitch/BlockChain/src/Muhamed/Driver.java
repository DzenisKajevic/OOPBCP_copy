package Muhamed;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		
		MessageBlockChain bc = new MessageBlockChain();
		
		bc.setGenesisBlock();

		bc.registerUserOnBlockchain("user1","secret3");
		bc.registerUserOnBlockchain("user22","secret");
		bc.registerUserOnBlockchain("user322","secr3et2");
		bc.registerUserOnBlockchain("user4","secre4t3");
		
		System.out.println(bc.checkIfValidLogin("user1", "secret3"));
		System.out.println(bc.checkIfValidLogin("user1", "secret"));
		
		/*ArrayList<Block> blocks = bc.getBlockchain();
		
		System.out.println();
		
		for(Block b : blocks) {
			System.out.println(b);
			System.out.println();
		}*/
	}

}
