
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Mission Marcus
// mmarcu6 PROJ 2

public class GameTester {
	
	
	
	public static void main(String [] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String input = "";
		Scanner fileScanner; // file scanner
		
		boolean worked = false;
		
		System.out.println("Please enter in a file name");
		
		while (worked == false  && !input.toLowerCase().equals("quit") && !input.toLowerCase().equals("q") && !input.toLowerCase().equals("exit"))
		{
		try
		{
		input = scan.nextLine();
		File fin = new File("maps/" + input);
		
		fileScanner = new Scanner(new FileInputStream(fin), "UTF-8");
		
		Game newGame = new Game(fileScanner);
		worked = true;
		newGame.play();
		scan.close();
		fileScanner.close();
		}
		catch(FileNotFoundException ex)
		{
			if (input.toLowerCase().equals("quit") || input.toLowerCase().equals("q") || input.toLowerCase().equals("exit"))
			{
			worked = true;
			}
			else
			{
				worked = false;
				System.out.println("File was not found, Please enter a new filename");
			}
			
		}
		}
		
		
		

		
	}
	
}
