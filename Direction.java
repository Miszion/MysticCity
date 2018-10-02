// Mission Marcus
// mmarcu6 PROJ 1
import java.util.Scanner;

public class Direction {
	
	private Place from; // remember a from, to location and if a direction is locked or not. and ID and name of direction ex ("E or W")
	private Place to;
	private boolean locked;
	private int ID;
	private int lockPattern;
	private dirType dir;
	
	
	
	public Direction(Scanner scan)
	{
		
		String input = LineParser.findNextLineS(scan); // find the next clean line
		
		String [] firstLine = input.split("\\s+"); // split on spaces.
		
		ID = Integer.parseInt(firstLine[0].trim()); // get the ID integer.
		
		
	
		int fromID = Integer.parseInt(firstLine[1].trim()); // get the integer for the FROM place
		
		int toID = Integer.parseInt(firstLine[3].trim()); // get the intege for the TO place
		
		if (fromID < 0) // if either are negative in the file. (which indicates locked or not).
		{
			locked=  true;
			fromID = fromID * -1;
		}
		
		else if (toID < 0)
		{
			locked = true;
			toID = toID * -1;
		}
		else
		{
			locked = false;
		}
		
		
		from = Place.getPlaceByID(fromID); // get the place iDs
		
		to = Place.getPlaceByID(toID);
		
		String enumm = firstLine[2].trim(); // get the directional string
		
		
		 dir = dirType.fromString(enumm); // find the enum equivalent
		

	  lockPattern = Integer.parseInt(firstLine[4]); // get the lock pattern
	
		from.addDirection(this); // add the direction to the from place.
		
		
	}
		
	
	
private enum dirType {
	
	NONE("NONE", ""),
	NORTH("NORTH", "N"),
	SOUTH("SOUTH", "S"),
	EAST("EAST", "E"),
	WEST("WEST", "W"),
	UP("UP", "U"),
	DOWN("DOWN", "D"),
	NORTHEAST("NORTHEAST", "NE"),
	NORTHWEST("NORTHWEST", "NW"),
	SOUTHEAST("SOUTHEAST", "SE"),
	SOUTHWEST("SOUTHWEST", "SW"),
	NORTHNORTHEAST("NORTH-NORTHEAST", "NNE"),
	NORTHNORTHWEST("NORTH-NORTHWEST", "NNW"),
	EASTNORTHEAST("EAST-NORTHEAST", "ENE"),
	WESTNORTHWEST("WEST-NORTHWEST", "WNW"),
	EASTSOUTHEAST("EAST-SOUTHEAST", "ESE"),
	WESTSOUTHWEST("WEST-SOUTHWEST", "WSW"),
	SOUTHSOUTHEAST("SOUTH-SOUTHEAST", "SSE"),
	SOUTHSOUTHWEST("SOUTH-SOUTHWEST", "SSW");
	
	
	private final String name; // name and abbrev for the enum
	private final String abbrev;
	
	private dirType(String name, String abbrev)
	{
		this.name = name;
		this.abbrev = abbrev;
	}
	
	public String toString() // cast it to the string
	{
		return name;
	}
	
	public boolean match(String userS) // type matching strings to match
	{
		if (userS.toUpperCase().equals(name) || userS.toUpperCase().equals(abbrev))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static dirType fromString(String userS) // this will turn a string into an enum
	{
		for (dirType d: values())
		{
			if (d.match(userS))
			{
				return d;
			}
		}
		return null;
	}
	
	
}

	


	
	public void useKey(Artifact art) // using keys
	{
		if ((art.keyPattern() >= 0) && (art.keyPattern() == lockPattern) && locked == true) // if keypattern isnt negative and it matches and its locked.
		{
			unlock();
			System.out.println("You used the " + art.name()+ ". " + to.name() + " " + " is now unlocked!");
		}
		else if ((art.keyPattern() >= 0) && (art.keyPattern() == lockPattern) && locked == false) // if they match, but its already unlocked
		{
			System.out.println("Room is already unlocked!");
		}
		
	}
	
	
	
	public boolean match(String n) // check if the strings are matching
	{
		
		return dir.match(n);
				
	}
	
	public String getTo() // get the name of the next location.
	{
		return to.name();
	}
	
	public void lock() // lock or unlock the direction
	{
		locked = true;
	}
	
	public void unlock()
	{
		locked = false;
	}
	
	public boolean isLocked()
	{
		return locked;
	}
	
	public Place follow() // follow the next direction.
	{
		if (isLocked())
		{
			System.out.println("That place is locked!");
			return from;
		}
		else 
		{
			return to;
		}
		
	}
	
	public void print() // print the direction info out.
	{
		System.out.println("ID is " + ID);
		System.out.println("Locked? " + locked);
		System.out.println("The direction is " + dir);
		System.out.println("The lock pattern is " + lockPattern);
	}
	
	public String getDirectString() // get the direction name of the direction.
	{
		return dir.name();
	}
}
