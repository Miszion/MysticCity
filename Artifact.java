// Mission Marcus
// mmarcu6 Project 2

import java.util.Scanner;

public class Artifact {

	private String name;
	private String description;
	private int value;
	private int mobility;
	private int keyPattern;
	
	public Artifact(Scanner scan)
	{
		
		int starting = 0;
		
		String input = LineParser.findNextLineS(scan); // find the next line of text
		
		String [] id = input.split("\\s+"); // split from spaces
		
		int placeID;
		
		
		
		if (id.length == 1) // if the id is on a separate line.. (ie: in gdf 3.1) 
		{
			input = LineParser.findNextLineS(scan);
			starting = 0;
			placeID = Integer.parseInt(id[0]);
			
		}
		else // if its by itself, its the second value. (ie: in gdf 3.0)
		{
			starting = 1;
			placeID = Integer.parseInt(id[starting]);
		}

		
		String [] splitter = input.split("\\s+");
		
		this.value = Integer.parseInt(splitter[starting+1].trim()); // the value is the 1th index
		this.mobility = Integer.parseInt(splitter[starting + 2].trim()); // mobility
		this.keyPattern = Integer.parseInt(splitter[starting + 3].trim()); // key pattern.
		 
		 name = " "; // get the name by adding in parts of the string until we hit //
		
		for (int x = starting + 4; x < splitter.length; x++)
		{
			if (splitter[x].startsWith("//"))
			{
				break;
				
			}
			
			name = name + splitter[x];
			name = name + " ";
		}
		
		name = name.trim(); // trim it.

		input = LineParser.findNextLineS(scan); // find the next line again.
		
		int howManyMore = Integer.parseInt(input); // find how many lines are up in the description
		
		description = "";
		
		for (int x = 0; x < howManyMore; x++) // concatnate description
		{
			description = description + LineParser.findNextLineS(scan);
			description = description + " ";
		}
		
		Place.getPlaceByID(placeID).addArtifact(this); // add the artifact to the specific place.
		
	}

	
	
	public int value() // return value
	{
		return value;
	}
	
	public int size() // return size.
	{
		return mobility;
	}
	
	public String name() // return name
	{
		return name;
	}
	
	public String description() // return the description
	{
		return description;
	}
	
	public void use() // use the key
	{
		Place ha = Game.getCurrentPlace();
		ha.useKey(this);
	}
	
	public int keyPattern() // return the key pattenr
	{
		return keyPattern;
	}
	
	public void print() // print out the artifact info
	{
		System.out.println("Artifact is " + name);
		System.out.println("Description is " + description);
		System.out.println("Value is " + value);
		System.out.println("Mobility is " + mobility);
		System.out.println("KeyPattern is " + keyPattern);
		
	}
	
}
