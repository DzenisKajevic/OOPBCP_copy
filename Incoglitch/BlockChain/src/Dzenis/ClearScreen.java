package Dzenis;
import java.io.IOException;  

/*public class ClearScreen {
	public static void main(String... arg) throws IOException, InterruptedException   
		{  
		System.out.println("sadasd");
		clearConsole(); 
		}  
	
	public final static void clearConsole()  
	{  
		try  
		{  
			final String os = System.getProperty("os.name");  
			String[] cls = new String[] {"cmd.exe", "/c", "cls"};
			if (os.contains("Windows"))  
			{  
				Runtime.getRuntime().exec(cls);  
			}  
			else  
			{  
				Runtime.getRuntime().exec("clear");  
			}  
		}  
		catch (final Exception e)  
		{  
			e.printStackTrace();  
		}  
		
	}  
}*/

public class ClearScreen
{  
	public final static void clearConsole()  
	{  
			for(int i = 0; i < 50; i++) System.out.println("\n");
	}  
}  
