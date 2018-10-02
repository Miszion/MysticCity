// Mission Marcus
// mmarcu6 PROJ 2

import java.util.Vector;
import java.util.HashMap;
import java.util.Scanner;

public class Place { // we have an ID for the place ID, name for the name, description, and list of possible directions.

	private int ID;
	private String name;
	private String description;
	private Vector<Direction> directions;
    private Vector<Artifact> artifacts;
    private static HashMap<Integer, Place> places = new HashMap<Integer, Place>(); // our list of places.
	
	public Place(Scanner scan)
	{
		artifacts = new Vector<Artifact> ();
		directions = new Vector<Direction> ();
	
		String input = LineParser.findNextLineS(scan); // find next line of text
		
		String [] firstLine = input.split("\t");
		
		ID = Integer.parseInt(firstLine[0].trim());
		

		name = firstLine[1]; // get the name.
		
		name = name.trim();
		
		
		String in = scan.nextLine(); // disregard the number. 
		
		int numberOfLines = (Integer.parseInt(in));
		
	    description = "";
	    
	    for (int x = 0; x < numberOfLines; x++) // description
	    {
			description = description + " ";
			in = scan.nextLine(); 
			description = description + in ;	
			
	    }
	    description = description.trim();
		
		places.put(ID, this);
	}
		

	public void addArtifact(Artifact art)
	{
		artifacts.add(art);
	}
	
	public Vector<Artifact> getA()
	{
		return artifacts;
	}
	
	static Place getPlaceByID(int ID)
	{
		return places.get(ID);
	}
	
	public String name() // return name if needed
	{
		return name;
	}
	
	public void useKey(Artifact art)
	{
		for (Direction dir: directions)
		{
			dir.useKey(art);
		}
	}
	
	public Vector<Direction> directions()
	{
		return directions;
	}
	
	public String description() // return the description
	{
		return description;
	}
	
	public void addDirection(Direction dir) // add a direction to the place
	{
		directions.add(dir);
	}
	
	public Place followDirection(String dir) // return the next place.
	{
		for (Direction dirs : directions)
		{
			if (dirs.match(dir))
			{
				return dirs.follow();
			}
		}
		
		return this;
	}
	
	public int getID() // added to obtain a specific ID for print checking
	{
		return ID;
	}
	
	
	public void display() // display the name and description for look.
	{
		System.out.println("The Name of this place is " + name);
		System.out.println("Description: "  + description + "\n");		
		
	}
	
	public void print() // printing information for debugging.
	{
		System.out.println("The ID of this place is " + ID);
		System.out.println("The possible directions this leads to are: \n");
		
		for (Direction dir : directions)
		{
			System.out.println(dir.getDirectString() + ": " + dir.getTo() + "\n");
		}
		
		System.out.println("Artifacts: \n");
		
		for (Artifact b : artifacts)
		{
			System.out.println(b.name() + ": " + b.description());
		}
	}
	
}
