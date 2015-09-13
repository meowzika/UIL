import java.io.*;
import java.util.*;

public class BlackFriday
{
	public static class Entry {
		public String name = "";
		public int ticketNumber = -1;
		
		public Entry(String n, int t) {
			name = n;
			ticketNumber = t;
		}
	}
	
	
	public static class Drawing implements Comparable<Drawing> {
		public String itemName = "";
		public ArrayList<Entry> list = new ArrayList<Entry>();
		
		public Drawing(String name) {
			itemName = name;
		}

		public int compareTo(Drawing drawing) {
			return itemName.compareToIgnoreCase(drawing.itemName);
		}
	}
		
	
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("blackfriday.dat"));
    	
    	int numDays = Integer.parseInt(kb.nextLine().trim());
     	int curTicket = 100;
    	
    	for (int d = 0; d < numDays; ++d) {
    		Random rand = new Random(Long.parseLong(kb.nextLine().trim()));
    		
    		ArrayList<Drawing> drawings = new ArrayList<Drawing>();
    		int lastDaysMaxTicket = curTicket;
    		
    		int numPeople = Integer.parseInt(kb.nextLine().trim());
    		for (int l = 0; l < numPeople; ++l) {
    			String line = kb.nextLine().trim();
        		String[] tokens = line.split(" ");
        		
        		String name = tokens[0];
        		
        		for (int i = 1; i < tokens.length; ++i) {
        			String drawing = tokens[i];
        			boolean bDrawingFound = false;
        			for (int f = 0; f < drawings.size(); ++f) {
        				if (drawings.get(f).itemName.equalsIgnoreCase(drawing)) {
        					bDrawingFound = true;
        					drawings.get(f).list.add(new Entry(name, curTicket++));
        				}
        			}
        			
        			if (!bDrawingFound) {
        				Drawing dr = new Drawing(drawing);
        				dr.list.add(new Entry(name, curTicket++));
        				drawings.add(dr);
        			}
        		}
    		}
    		
    		Collections.sort(drawings);
    		
    		System.out.printf("Day %d: %d tickets given out\n", d + 1, (curTicket - lastDaysMaxTicket));
    		for (int p = 0; p < drawings.size(); ++p) {
    			Drawing drawing = drawings.get(p);
    			Entry entry = drawing.list.get(rand.nextInt(drawing.list.size()));
    			System.out.printf("   %s: %s with ticket %d\n", drawing.itemName, entry.name, entry.ticketNumber);
    		}
    		
    		lastDaysMaxTicket = curTicket;
    	}
    	
    	kb.close();
    }
}
