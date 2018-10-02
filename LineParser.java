// Mission Marcus
// mmarcu6 Project 2

import java.util.Scanner;

public class LineParser {

	
	
	
	public static String findNextLine(Scanner scan) // get next line that isnt a comment (could be blank)
	{
		String input = scan.nextLine();
		
		while (input.startsWith("//"))
		{
			input = scan.nextLine();
		}
		return input;
		
	}
	
	public static  String findNextLineS(Scanner scan) // get next line that isnt a comment or space.
	{
		String input = scan.nextLine();
		
		while (input.equals("") || input.startsWith("//"))
		{
			input = scan.nextLine();
		}
		
		return input;
		
	}
	
	public static String getCleanLine(Scanner scan) // get a line without comments. 
	{
		String input = scan.nextLine();
		
		if (input.length() == 0)
		{
			return "";
		}
		else
		{
		for (int x = 0; x < input.length()-2; x++)
		{
			if (input.substring(x, x+2).equals("//"))
			{
				input = input.substring(0, x);
				break;
			}
		}
		
		input.trim();
		
		if (input.length() > 0)
		{
			return input;
		}
		else
		{
			return "";
		}
		}
		
	}
	
	
	
	
	
	
}
