package Rania;

import java.util.ArrayList;
import java.util.Scanner;

import Dzenis.MessageUI;
import Muhamed.MessageBlockChain;

public class TextUserInterface {
	Scanner in = new Scanner(System.in);
	boolean log = false;
	User u = new User();
	User u2 = new User();
	public MessageBlockChain bc;
	public TextUserInterface() {
		bc = new MessageBlockChain();
		bc.setGenesisBlock();
	}

	public void start() {
		int input;
		while (true) {
			System.out.println("Choose a number\n1 - register\n2 - log in\n3 - quit");
			input = in.nextInt();
			if (input == 1) {
				ArrayList<String> list = u.register();
				bc.registerUserOnBlockchain(list.get(0), list.get(1));			
			} else if (input == 2) {
				while(true) {
					ArrayList<String> list = u.login();
					if(bc.checkIfValidLogin(list.get(0), list.get(1)) == true) {
						loggedIn(list.get(0));
						break;
					}else {
						System.out.print("try again");
					}
					
				}
				
			} else if (input == 3) {
				break;
			}
		}
	}
	
	public void loggedIn(String username) {
		int input;
		//while(log==true)
		while(true)
		{
			System.out.println(
					"Choose a number\n1 - open conversation\n2 - add friend\n3 - remove friend\n4 - view friends\n5 - quit");
			input = in.nextInt();
			if (input == 1) {
				System.out.println("Enter name of recipient: ");
				String targetUsername = in.next();
				//if (bc.checkIfValidFriend(targetUsername)) {
					MessageUI messageUI = new MessageUI();
					messageUI.start(username, targetUsername);
				//}

			} else if (input == 2) {
				System.out.println("Friend's username: ");//message.sendMessage(String user1, String user2) or smth
				String friendUsername = in.next();
				if (bc.checkIfValidFriend(friendUsername) && (!username.equals(friendUsername))) {
					u.addFriend(friendUsername);
				}
			} else if (input == 3) {
				String friendUsername = in.next();
					u.removeFriend(friendUsername);
			} else if (input == 4) {
				u.printEm();
			} else if (input == 5) {
				break;
			}
		}
	}
}
