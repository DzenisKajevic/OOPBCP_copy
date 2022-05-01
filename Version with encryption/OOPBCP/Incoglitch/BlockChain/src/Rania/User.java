package Rania;

import Muhamed.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class User {
	Scanner in = new Scanner(System.in);
	public String username;
	private String password;
	private ArrayList<String> friends = new ArrayList<String>();
	public User friend;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		
		
	}

	public User(String username) {
		this.username = username;
	}

	public User() {
	}

	public ArrayList<String> register() {
		while (true) {
			System.out.println("Choose username: ");
			String temp1 = in.next();
			System.out.println("Choose password: ");
			String temp2 = in.next();
			ArrayList<String> list = new ArrayList<>();
			list.add(temp1);
			list.add(temp2);
			return list;
		}
	}

	public ArrayList<String> login() {
		ArrayList<String> list = new ArrayList<String>();
		
		System.out.println("Enter username: ");
		String enteredUsername = in.next();
		System.out.println("Enter password: ");
		String enteredPassword = in.next();
		list.add(enteredUsername);
		list.add(enteredPassword);
		return list;
		
		
	}

	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	String tempUsername = getUsername();

	public void addFriend(String username) {// imam problem s time da ne znam koji parametar može ovdje biti dobar da se
										// naðe neko preko username
		if(!this.friends.contains(username)) this.friends.add(username);
	}

	public void removeFriend(String username) {
		if (friends.contains(username)) {
			friends.remove(username);
		} else {
			System.out.println("LMAOOOO");
			//throw new IllegalArgumentException("Error.");
		}
	}

	public void printEm() {
		for (String i : friends) {
			System.out.println(i);
		}
	}

}
