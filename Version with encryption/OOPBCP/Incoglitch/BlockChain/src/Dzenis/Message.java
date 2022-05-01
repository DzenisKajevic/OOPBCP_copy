package Dzenis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;
import java.util.Scanner;
import java.io.BufferedReader;

public class Message {
	private static boolean deleted;
	
	
public static void sendMessage(String user1, String user2) {
		
		String directoryName;
		String fileName;
		Scanner reader = new Scanner(System.in);
		int messageNumber = 1;
		
		if(user2.compareTo(user1)>0) directoryName = user1 + user2;
		else directoryName = user2 + user1;
		
		String input = user1 + ": " + reader.nextLine();
		try {
			String encryptedInput = Encryption.encrypt(input);

		
		Path path = Path.of("./messages/").resolve(directoryName).normalize().toAbsolutePath();
		
		try {
			Files.createDirectories(path); // create folder if it doesn't already exist
		} catch (IOException e2) {
			
		}	
		
		messageNumber =  checkIfMessagesWereAdded(user1, user2);
		
		fileName = directoryName + messageNumber + ".txt";	// messageNumber is used for file naming / ordering
		// for example, message1, message2, message3...
		
		Path file = Path.of("./messages/").resolve(directoryName).resolve(fileName).normalize().toAbsolutePath();
		
		String messageNumberDirectory = directoryName + "MessageNumber";
		Path messageNumberPath = Path.of("./messages/").resolve(messageNumberDirectory);


		//take input from user
			
		//actually making files here
		try {
			//Path utfFile = Files.createFile(file);
			Files.writeString(file, encryptedInput, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			
			//printConversation();	// clear console and print conversation
		} catch (IOException e) {
			System.out.println("File " + file + " already exists!?!?!?!?!");
		}

		
		
		printConversation(path);
		
		String messageNumberString = Integer.toString(messageNumber);
		try {
			Files.write(messageNumberPath.resolve("messageNumber.txt"), messageNumberString.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		
	}
	
	
	/*
	public static void sendMessage(String user1, String user2) { //ENKRIPCIJA
		
		Encryption RSA = new Encryption();
		String directoryName;
		String fileName;
		Scanner reader = new Scanner(System.in);
		int messageNumber = 1;
		
		if(user2.compareTo(user1)>0) directoryName = user1 + user2;
		else directoryName = user2 + user1;
		
		String input = user1 + ": " + reader.nextLine();
		
		Path path = Path.of("./messages/").resolve(directoryName).normalize().toAbsolutePath();
		
		try {
			Files.createDirectories(path); // create folder if it doesn't already exist
		} catch (IOException e2) {
			
		}	
		
		messageNumber =  checkIfMessagesWereAdded(user1, user2);
		
		fileName = directoryName + messageNumber + ".txt";	// messageNumber is used for file naming / ordering
		// for example, message1, message2, message3...
		
		Path file = Path.of("./messages/").resolve(directoryName).resolve(fileName).normalize().toAbsolutePath();
		
		String messageNumberDirectory = directoryName + "MessageNumber";
		Path messageNumberPath = Path.of("./messages/").resolve(messageNumberDirectory);


		//take input from user
			
		//actually making files here

			try {
				String encryptedInput = RSA.encrypt(input);
				Files.writeString(file, encryptedInput, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			} catch (Exception e) {}
			
			
			//printConversation();	// clear console and print conversation

		printConversation(path);
		
		String messageNumberString = Integer.toString(messageNumber);
		try {
			Files.write(messageNumberPath.resolve("messageNumber.txt"), messageNumberString.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static void receiveMessage(int messageNumber) {
		
		// When other person adds a message to the folder
		//printConversation();	// clear console and print remaining conversation
	}
	
	public static boolean deleteOldMessages(String user1, String user2, int timer) { //timer in seconds
		
		// deletes only certain messages in the folder, not the folder itself
		
		String directoryName;	// deciding folder name based on alphabetical order
		if(user2.compareTo(user1)>0) directoryName = user1 + user2;
		else directoryName = user2 + user1;
		//boolean deleted = false;
		deleted = false;
		Path path = Path.of("./messages/").resolve(directoryName);
		
		try (Stream<Path> walk = Files.walk(path)) {
		    walk.sorted(Comparator.reverseOrder()).forEach(file -> {
		    	long currentTime = new Date().getTime();
		    	FileTime fileCreationTime;
		    	long convertedFileCreationTime;
		    	
		    	try {	//delete file if older than selected threshold (timer)
		    		fileCreationTime = (FileTime) Files.getAttribute(file, "creationTime");
		    		convertedFileCreationTime = new Date(fileCreationTime.toMillis()).getTime();
		    		
		    		if(((currentTime - convertedFileCreationTime)/1000) > timer)
		    			{
		    				Files.delete(file);
		    				printConversation(path);	// clear console and print remaining conversation
									deleted = true;
									// after deletion of file(s)
		    			}
		    		else return;
		    		
		    	} catch (IOException e) {
		    		//e.printStackTrace();
		    	}
		    });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//System.out.println("Can't delete folder");
		}
		if(deleted) return true;
		return false;
	}
		
	
	/*public static void printConversation(Path path) {//DEKRIPCIJA
		try (Stream<Path> walk = Files.list(path)) {
			if(!walk.findAny().isPresent()) return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		ClearScreen.clearConsole();
		try (Stream<Path> walk = Files.list(path)) {
			
			Encryption RSA = new Encryption();
			
		    walk.sorted(Comparator.naturalOrder()).forEach(file -> {
		    	try {
					BufferedReader reader = Files.newBufferedReader(file);
					try {
						System.out.println(RSA.decrypt(reader.readLine()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
		    });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//System.out.println("Can't delete folder");
		}
		
	}*/

	public static void printConversation(Path path) { 
		try (Stream<Path> walk = Files.list(path)) {
			if(!walk.findAny().isPresent()) return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		ClearScreen.clearConsole();
		try (Stream<Path> walk = Files.list(path)) {
			
		    walk.sorted(Comparator.naturalOrder()).forEach(file -> {
		    	try {
					BufferedReader reader = Files.newBufferedReader(file);
					String output = reader.readLine();
					try {
						System.out.println(Encryption.decrypt(output));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
		    });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//System.out.println("Can't delete folder");
		}
		
	}

	
	public static void deleteConversation(String user1, String user2) {
		
		// deletes the folder along with the messages
		
		String directoryName;
		
		if(user2.compareTo(user1)>0) directoryName = user1 + user2;
		else directoryName = user2 + user1;
		
		Path path = Path.of("./messages/").resolve(directoryName);
		
		try (Stream<Path> walk = Files.walk(path)) {
		    walk.sorted(Comparator.reverseOrder()).forEach(file -> {
		    	
		    	try {	//delete file if older than selected threshold (timer)
		    		Files.delete(file);		
		    	} catch (IOException e) {
		    		System.out.println("Can't delete file as it doesn't exist.");
		    	}
		    });
		} catch (IOException e1) {
			//e1.printStackTrace();
		}
	}
	
	private static int checkIfMessagesWereAdded(String user1, String user2) {
		
		int messageNumber = 1;
		String directoryName;
		
		if(user2.compareTo(user1)>0) directoryName = user1 + user2;
		else directoryName = user2 + user1;
		String messageNumberDirectory = directoryName + "MessageNumber";
		Path path = Path.of("./messages/").resolve(directoryName).normalize().toAbsolutePath();
		
		Path messageNumberPath = Path.of("./messages/").resolve(messageNumberDirectory);
		
		try {
			Files.createDirectories(messageNumberPath); // create folder if it doesn't already exist
		} catch (IOException e2) {
			
		}	
		
		
		try {
			Stream<Path> files = (Files.list(path));
			if(!files.findAny().isPresent()) { 
				messageNumber = 1; // set messageNumber to 1 if folder is empty
			}
			else {
				try {
					BufferedReader bufferedReader = Files.newBufferedReader(messageNumberPath.resolve("messageNumber.txt"), Charset.forName("UTF-8"));
					messageNumber = Integer.parseInt(bufferedReader.readLine())+1; //set messageNumber to previous +1
				} catch (IOException e) {
						e.printStackTrace();
						//System.out.println("Can't write messageNumber");
				}
			}
			files.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return messageNumber;
	}
}
