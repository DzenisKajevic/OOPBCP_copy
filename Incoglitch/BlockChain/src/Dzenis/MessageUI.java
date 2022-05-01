package Dzenis;

import java.nio.file.Path;
import java.util.Scanner;

public class MessageUI {
	private String user1;
	private String user2;
	private boolean exitedConversation = false;
	
	public void refreshConversation() {
		String directoryName;
		if(this.user2.compareTo(this.user1)>0) directoryName = this.user1 + this.user2;
		else directoryName = this.user2 + this.user1;

		Path path = Path.of("./messages/").resolve(directoryName).normalize().toAbsolutePath();
		Message.printConversation(path);
	}
	
	public void start(String user1, String user2) {
		this.user1 = user1;
		this.user2 = user2;
		
		this.refreshConversation();
		
		/*Runnable messageRunnable = new Runnable() {
			public void run() {
				Message.sendMessage(user1, user2);
			}
		};
		*/
		//Thread messageRunnableThread = new Thread(messageRunnable);
		
		Runnable sendingMessages = new Runnable() {
			public void run() {
				Scanner in = new Scanner(System.in);
				int input;
				while(true) {
					System.out.println("Choose 1 to send message, 2 to exit conversation, 3 to delete all messages, 4 update conversation");
					input = in.nextInt();
					if (input == 1) {
						//Thread messageRunnableThread = new Thread(messageRunnable);
						//messageRunnableThread.start();

						Message.sendMessage(user1, user2);
						
						/*try {
							while(messageRunnableThread.isAlive()) Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					} else if (input == 2) {
						Message.deleteConversation(user1, user2);
						ClearScreen.clearConsole();
						exitedConversation = true;
						return;
					} else if (input == 3) {
						Message.deleteConversation(user1, user2);
						ClearScreen.clearConsole();
					}
					else if (input == 4) {
						refreshConversation();
					}
				}
			}
		};
		Runnable deletingOldMessages = new Runnable() {
			public void run() {
				boolean deleted = false;
				while(!exitedConversation) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					deleted = Message.deleteOldMessages(user1, user2, 30);
					if(exitedConversation) return;
					if(deleted) {
						ClearScreen.clearConsole();
						System.out.println("Choose 1 to send message, 2 to exit conversation, 3 to delete all messages, 4 update conversation");
					}
					//else if(!messageRunnableThread.isAlive()) {
					else {
						refreshConversation();
						System.out.println("Choose 1 to send message, 2 to exit conversation, 3 to delete all messages, 4 update conversation");
					}
					//}
				}
			}
		};
		/*
		Runnable updatingConversationEvery5Seconds = new Runnable() {

			public void run() {
						while(!exitedConversation) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ClearScreen.clearConsole();
							
							refreshConversation();
						}
		}
		};*/
		
		Thread t1 = new Thread(sendingMessages);
		Thread t2 = new Thread(deletingOldMessages);
		//Thread t3 = new Thread(updatingConversationEvery5Seconds);
		t1.start();
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}*/
		while(t1.isAlive() && !t2.isAlive() || (!t1.isAlive() && !t2.isAlive())) t2.start();
		//t3.start();
		while(t1.isAlive())
		try {
			Thread.sleep(200);
			//t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		//t3.start();
		//t3.start();
		//t3.start();
	}
}
