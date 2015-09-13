import java.io.*;
import java.util.*;

public class Escape {
    
    public static final char kCellEmpty     = '.';
    public static final char kCellNoPass	= '#';
    public static final char kCellTree  	= '@';
    public static final char kCellFriend    = 'P';
    public static final char kCellSquirrel  = 'S';
    public static final char kCellEscape    = 'E';
    
    public static final char kCellVisited	= ',';
    
    public static char[][] grid = null;
    public static int gridRows = 0;
    public static int gridCols = 0;
    
    public static char getGridValSafe(int r, int c) {
        if (r < 0 || r >= gridRows) {
            return kCellNoPass;
        }
                
        if (c < 0 || c >= gridCols) {
            return kCellNoPass;
        }
        
        return grid[r][c];
    }
    
    public static boolean recurse(int youCurR, 
                               int youCurC, 
                               int youNextR, 
                               int youNextC,
                               int squirrelCurR,
                               int squirrelCurC,
                               char lastSquirrelCell) {

        char chDst = getGridValSafe(youNextR, youNextC);
        if (kCellEscape == chDst) {
            return true;
        }
        else if (kCellEmpty != chDst) {
            return false;
        }
        
        // Perform the move
        grid[youCurR][youCurC] = kCellVisited;
        grid[youNextR][youNextC] = kCellFriend;
        
        // Squirrel move time
        int squirrelNextR = squirrelCurR;
        int squirrelNextC = squirrelCurC;
        
        char ul = getGridValSafe(squirrelCurR - 1, squirrelCurC - 1);
        char cl = getGridValSafe(squirrelCurR + 0, squirrelCurC - 1);
        char dl = getGridValSafe(squirrelCurR + 1, squirrelCurC - 1);
        char uc = getGridValSafe(squirrelCurR - 1, squirrelCurC + 0);
        char dc = getGridValSafe(squirrelCurR + 1, squirrelCurC + 0);
        char ur = getGridValSafe(squirrelCurR - 1, squirrelCurC + 1);
        char cr = getGridValSafe(squirrelCurR + 0, squirrelCurC + 1);
        char dr = getGridValSafe(squirrelCurR + 1, squirrelCurC + 1);
        
        // Adjacent You means death!
        if (    kCellFriend == ul || kCellFriend == cl || kCellFriend == dl || kCellFriend == uc
             || kCellFriend == dc || kCellFriend == ur || kCellFriend == cr || kCellFriend == dr) {
            // Set back values
            grid[youCurR][youCurC] = kCellFriend;
            grid[youNextR][youNextC] = chDst;
            
            return false;
        }
        
        int diffR = youNextR - squirrelCurR;
        if (diffR < 0) {
            --squirrelNextR;
        }
        else if (diffR > 0) {
            ++squirrelNextR;
        }
        
        int diffC = youNextC - squirrelCurC;
        if (diffC < 0) {
            --squirrelNextC;
        }
        else if (diffC > 0) {
            ++squirrelNextC;
        }
        
        char chSquirrelDst = getGridValSafe(squirrelNextR, squirrelNextC);
        boolean bSquirrelMoved = false;
        if (kCellNoPass != chSquirrelDst) {
            grid[squirrelCurR][squirrelCurC] = lastSquirrelCell;
            grid[squirrelNextR][squirrelNextC] = kCellSquirrel;
            bSquirrelMoved = true;
        }
        else {
            squirrelNextR = squirrelCurR;
            squirrelNextC = squirrelCurC;
            chSquirrelDst = lastSquirrelCell;
        }
        
        // Recurse!
        boolean bEscaped = false;
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR - 1, youNextC - 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR + 0, youNextC - 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR + 1, youNextC - 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR - 1, youNextC + 0, squirrelNextR, squirrelNextC, chSquirrelDst);
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR + 1, youNextC + 0, squirrelNextR, squirrelNextC, chSquirrelDst);
        
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR - 1, youNextC + 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR + 0, youNextC + 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        bEscaped = bEscaped || recurse(youNextR, youNextC, youNextR + 1, youNextC + 1, squirrelNextR, squirrelNextC, chSquirrelDst);
        
        // Set back the values
        if (bSquirrelMoved) {
            grid[squirrelCurR][squirrelCurC] = kCellSquirrel;
            grid[squirrelNextR][squirrelNextC] = chSquirrelDst;
        }
        
        grid[youCurR][youCurC] = kCellFriend;
        grid[youNextR][youNextC] = chDst;
        
        return bEscaped;
    }
    
    public static void main(String args[]) throws IOException {
        // Parse each line in the file
        Scanner kb = new Scanner(new File("escape.dat"));
        
        int numGrids = Integer.parseInt(kb.nextLine().trim());
        for (int g = 0; g < numGrids; ++g) {
            String strLine = kb.nextLine().trim();
            String[] strArr = strLine.split(" ");
            
            gridRows = Integer.parseInt(strArr[0]);
            gridCols = Integer.parseInt(strArr[1]);
            
            grid = new char[gridRows][gridCols];
            
            int squirrelR = 0;
            int squirrelC = 0;
            int youR = 0;
            int youC = 0;
            
            for (int r = 0; r < gridRows; ++r) {
                strLine = kb.nextLine().trim();
                
                for (int c = 0; c < gridCols; ++c) {
                    char ch = strLine.charAt(c);
                    if (kCellFriend == ch) {
                        youR = r;
                        youC = c;
                    }
                    else if (kCellSquirrel == ch) {
                        squirrelR = r;
                        squirrelC = c;
                    }
                    
                    grid[r][c] = ch;
                }
            }
            
            boolean bEscaped = false;
            bEscaped = bEscaped || recurse(youR, youC, youR - 1, youC - 1, squirrelR, squirrelC, kCellEmpty);
            bEscaped = bEscaped || recurse(youR, youC, youR + 0, youC - 1, squirrelR, squirrelC, kCellEmpty);
            bEscaped = bEscaped || recurse(youR, youC, youR + 1, youC - 1, squirrelR, squirrelC, kCellEmpty);
            
            bEscaped = bEscaped || recurse(youR, youC, youR - 1, youC + 0, squirrelR, squirrelC, kCellEmpty);
            bEscaped = bEscaped || recurse(youR, youC, youR + 1, youC + 0, squirrelR, squirrelC, kCellEmpty);
            
            bEscaped = bEscaped || recurse(youR, youC, youR - 1, youC + 1, squirrelR, squirrelC, kCellEmpty);
            bEscaped = bEscaped || recurse(youR, youC, youR + 0, youC + 1, squirrelR, squirrelC, kCellEmpty);
            bEscaped = bEscaped || recurse(youR, youC, youR + 1, youC + 1, squirrelR, squirrelC, kCellEmpty);
            
            if (bEscaped)
            	System.out.println("YES");
            else
            	System.out.println("NO");
        }
       
    }  
}
