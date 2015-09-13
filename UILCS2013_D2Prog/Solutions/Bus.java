import java.io.*;
import java.util.*;

public class Bus
{
	public static boolean bDebugPrint = false;
	
	
	public static class Node implements Comparable<Node> {
		char ch = '.';
		int index = 0;
		
		public int compareTo(Node n) {
			return index - n.index;
		}
	}
	
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("bus.dat"));
    	
    	ArrayList<Node> bus = new ArrayList<Node>();
    	int numPeople = 0;
    	
    	while (kb.hasNextLine()) {
    		String line = kb.nextLine().trim();
    		String[] tokens = line.split(" ");
    		
    		if (line.equalsIgnoreCase("END ROUTE")) {
    			while (!bus.isEmpty()) {
    				++numPeople;
    				bus.remove(0);
    			}
    			
    			System.out.printf("People Entered/Exited: %d\n", numPeople);
    			
    			numPeople = 0;
    			bus = new ArrayList<Node>();
    		}
    		else if (tokens[0].equalsIgnoreCase("ON")) {
    			for (int x = 1; x < tokens.length; ++x) {
    				Node n = new Node();
    				n.ch = tokens[x].charAt(0);
    				
    				++numPeople;
    				bus.add(n);
    			}
    		}
    		else {  // OFF
    			final int busSize = bus.size();
    			ArrayList<Node> off = new ArrayList<Node>();
    			for (int x = 1; x < tokens.length; ++x) {
    				char ch = tokens[x].charAt(0);
    				
    				int index = 0;
    				Node n = bus.get(index);
    				while (n.ch != ch) {
    					++index;
    					n = bus.get(index);
    				}
    				
    				n.index = index;
    				off.add(n);
    			}
    			
    			// Order people getting off from back to front of bus
				Collections.sort(off);
				
				// Being clever leads to bugs so just brute force to find the best paths
				// for people.  Start at the back and move forward to each person getting
				// off the bus and use them as the split between back and front exiting.
				final int offSize = off.size();
				int lowestNumPeople = Integer.MAX_VALUE;
				
				// Count up from 0 to the number of people getting off and have that number
				// exit out the back of the bus
				for (int i = 0; i <= offSize; ++i) {
					final int indexOutBack = i - 1;
					final int indexOutFront = i;
					
					int numPeopleOutFront = (indexOutFront == offSize) ? 0 : busSize - off.get(indexOutFront).index;
					int numPeopleOutBack = (indexOutBack < 0) ? 0 : off.get(indexOutBack).index + 1;
					int numPeopleOut = numPeopleOutBack + numPeopleOutFront;
					
					// Number of people walking back in
					int numPeopleIn = numPeopleOut - offSize;
					
					if (numPeopleOut + numPeopleIn < lowestNumPeople) {
						lowestNumPeople = numPeopleOut + numPeopleIn;
					}
				}
				
				// Increment by the lowest number and remove all people getting off
				numPeople += lowestNumPeople;
				for (int i = 0; i < offSize; ++i) {
					bus.remove(off.get(i));
				}
    		}
    	}
    }
}
