// Mission Marcus
// mmarcu6 Project 2


import java.util.Vector;
import java.util.Scanner;

public class Game { // remember the name and the current place and list of places of the game

	private String name;
	private static Place currentPlace;
	private Vector<Place> places;
	private Vector<Artifact> artifacts; // possessions of the player.
	

	
	@SuppressWarnings("unused") // this is to suppress the fact that artifact and direction are both unused. 
	public Game(Scanner scan)
	{
		places = new Vector<Place>(); // places and artifact lists
		artifacts = new Vector<Artifact>();
		
		String [] line;
		
		String environment = LineParser.getCleanLine(scan); // get the clean line without comments
		
		
		
		this.name = environment;
		
		System.out.println(name);
		
		int numberOfPlaces = 0; // find the number of places, directions, and artifacts.
		int numberOfDirections = 0;
		int numberOfArtifacts  = 0;

		
		
		while (scan.hasNextLine()) // while we still have lines.
		{
			String txt = LineParser.findNextLine(scan); // find the next line
			
			
			line = txt.split("\\s+"); // split at space

			if (line[0].equals("PLACES")) // if we are reading places
			{
				
				line = line[1].split("\\s+"); // split at the space
			
				numberOfPlaces = Integer.parseInt(line[0]); // find the integer value
				
				LineParser.findNextLine(scan);
				
				for (int x = 0; x < numberOfPlaces; x++) // for the number of spaces..
					
				{
					Place newPlace = new Place(scan);
					places.add(newPlace);
				

					
				}
				
				currentPlace = places.get(0); // set the current place to the first one read.
					
			}
			
			else if (line[0].equals("DIRECTIONS")) // if we are reading directions
			{
				line = line[1].split("\\s+");
				numberOfDirections = Integer.parseInt(line[0]);
				
				LineParser.findNextLine(scan);
				
				for (int x = 0; x < numberOfDirections; x++)
				{
					Direction newDirection = new Direction(scan);
				}
				
				
			}
			else if (line[0].equals("ARTIFACTS")) // if its artifacts..
			{
				line = line[1].split("\\s+");
				numberOfArtifacts = Integer.parseInt(line[0]);
				
				LineParser.findNextLine(scan);
				
				for (int x = 0; x < numberOfArtifacts; x++)
				{
					Artifact art = new Artifact(scan);
				}
			}
			
		}
		
		
	}
	
	
	

	
	public void print() // printing the current place and the name of the game.
	{
		System.out.println("The name of the game is " + name);
		System.out.println("The Current Place is " + currentPlace.name());
		System.out.println("Player artifacts are: ");
		
		for (Artifact b: artifacts)
		{
			System.out.println(b.name() + ": " + b.description());
		}
	}
	
	
	public static Place getCurrentPlace() // return the current place
	{
		return currentPlace;
	}
	
	public boolean get(String aName) // get the item
	{
		for (Artifact a : getCurrentPlace().getA()) // for all the artifacts in the place
		{
			if (a.name().toLowerCase().trim().equals(aName.toLowerCase().trim())) // if the name matches what is typed
			{
				artifacts.add(a); // add the artifacts
				getCurrentPlace().getA().remove(a); // remove it from the place artifacts
				System.out.println("You obtained the " + aName + "!");
				return true;
			}
		}
		System.out.print("That artifact doesn't exist!"); // if its not found, 
		return false;
	}
	
	public boolean drop(String aName) // drop the item
	{
		for (Artifact a : artifacts) // for all the artifacts in the place
		{
			if (a.name().toLowerCase().equals(aName.toLowerCase())) // if it equals the name
			{
				getCurrentPlace().getA().addElement(a); // add the inventory element back to the place
				artifacts.remove(a); // remove it from player artifacts
				System.out.println("You dropped the " + aName + "!"); 
				return true;
			}
		}
		System.out.print("That artifact doesn't exist!"); // if it doesnt exist.
		return false;
	}
	
	public boolean use(String aName) // using a key
	{
		for (Artifact a: artifacts)
		{
			
			if (a.name().toLowerCase().equals(aName.toLowerCase()) && a.keyPattern() != 0) // if the item exists and is a key. 
			{
				a.use();
				return true;
			}
			

			
		}
		
		System.out.print("You cant use that!"); // if its not a key
		return false;
		
	}
	
	public void look()
	{
		System.out.println("Artifacts: \n");
		
		for (Artifact b : currentPlace.getA())
		{
			System.out.println(b.name() + ": " + b.description());
		}
	}
	
	public void inventory() // 
	{
		System.out.println("Inventory: \n");
		
		for (Artifact a : artifacts)
		{
			System.out.println("Name: " + a.name() + "\nValue: " + a.value() + "\nMobility: " + a.size() + " lb(s)\n");
		}
		
	}
	
	public boolean addPlace(Place p1) // add a place to the places..
	{
		places.add(p1);
		return true;
	}
	
	public boolean go(Place pl, String dir) // go to a place
	{
		for (Direction direct: pl.directions())
		{
			if (direct.match(dir.toUpperCase()) && !direct.isLocked()) // if the strings match and its not locked..
			{
				currentPlace = pl.followDirection(dir); // follow the direction
				return true;
			}
			else if (direct.match(dir.toUpperCase()) && direct.isLocked()) // if they match but is locked..
			{
				System.out.println("That door is locked!");
			}
		}
		return false;
	}
	
	public void play()
	{
		Scanner scanny = new Scanner(System.in);
		String input = "";
		
		
		while ((currentPlace.getID() != 1) && (!input.toLowerCase().equals("quit")) && (!input.toLowerCase().equals("exit")))
		{
			System.out.println("\n"); // while we arent at id 1 or quit or exit
			
			currentPlace.display(); // display info
			
			input = scanny.nextLine();
			
			//checkFormat(input, currentPlace);
			
			String []arr = input.toLowerCase().split("\\s+");
			
			switch(arr[0])
			{
			case "get": // run the get command on the item typed
				if (arr.length > 1)
				{
				get(input.substring(4, input.length()));
				}
				else
				{
					System.out.println("Invalid Command.");
				}
				break;
			case "inve": // call inventory
				inventory();
				break;
			case "inventory": // call inverntory
				inventory();
				break;
			case "use": // use an item
				if (arr.length > 1)
				{
				use(input.substring(4, input.length()));
				}
				else
				{
					System.out.println("Invalid Command.");
				}
				break;
			case "drop": // drop signalled item
				if (arr.length > 1)
				{
				drop(input.substring(5, input.length()));
				}
				else
				{
					System.out.println("Invalid Command.");
				}
				break;
			case "go": // go to the direction noted.
				if (arr.length > 1)
				{
				go(currentPlace, arr[1]);
				}
				else
				{
					System.out.println("Invalid Command.");
				}
				break;
			case "exit": // exit
				input = "exit";
				break;
			case "quit":
				input = "quit";
				break;
			case "q":
				input = "quit";
				break;
			case "look": // edit this one later?
				look();
				break;
			default:
				System.out.println("Invalid Command.");
				break;
			
			}
			
			
			if (currentPlace == null) // if we are at a null area,
			{
				break; // break it and end.
			}
			
		}
	
			

		scanny.close(); // closing out the scanner.
		System.out.println("Thanks for playing!");
		
	}
	
	
}
