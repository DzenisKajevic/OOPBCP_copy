package Dzenis;

public class DriverTest {

	public static void main(String[] args) {
		/*Blockchain bc = new Blockchain();
		bc.setGenesisBlock();
		bc.addUser("musa", "1234");
		bc.addUser("tito", "as234");
		bc.addUser("alija", "test");
		bc.printBlockchain();*/
		
		Message.deleteConversation("Muhamed", "Dzenis");	//deletes folder
		Message.sendMessage("Muhamed", "Dzenis");
		Message.sendMessage("Muhamed", "Dzenis");
		//ClearScreen.clearConsole();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
		Message.deleteOldMessages("Muhamed", "Dzenis", 30);	//deletes files older than 5s
		//Message.deleteConversation("Muhamed", "Dzenis");
	}

}
