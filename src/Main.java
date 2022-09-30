import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;
import javax.swing.JOptionPane;

public class Main {

    final static int rowL = 5;//number of rows for chess board
    final static int colL = 5;//number of cols for chess board
    static Stack<Location> stack = new Stack<Location>(); //store moves in order (backtrack capability)

    //list of exhausted locations for each location.  Must use method convertLocToIndex to find a Locations proper index
    static ArrayList<ArrayList<Location>> exhausted = new ArrayList<ArrayList<Location>>(rowL*colL);
    static int board[][] = new int[rowL][colL];//2d array used to store the order of moves
    static boolean visited[][] = new boolean[rowL][colL];//2d array used to store what locations have been used //NOT REQUIRED
    static Location startLoc;

    public static <Arraylist> void main(String[] args) {

        System.out.println("START");
        initExhausted();
        ArrayList<Location> currentPossible;
        //obtainStartLoc();
        startLoc = new Location(1,1);
        System.out.println("Start Loc is " + startLoc);

        stack.push(startLoc);
        //visited[startLoc.getRow()][startLoc.getCol()] = true;
        board[startLoc.getRow()][startLoc.getCol()] = 1;

        initExhausted();
        printExhausedList(startLoc);
        printPossibleMoveLocations(startLoc);
        //System.out.println("Stack size: " + stack.size());
        printBoard();
        System.out.println();

        int count = 0;
        //num is stack.size
        while(stack.size() != rowL * colL && stack.size() != 0)
        {
            ArrayList<Location> moves = getPossibleMoves(stack.get(stack.size()-1));//current top location on stack
            Location nextMove = getNextMove(stack.get(stack.size()-1), moves);//current top location on stack valid next move
            //if (nextMove == null) {//no loc to move too, backtrack
                //nextMove = getNextMove(stack.get(stack.size()-2),)
            //}

            if (nextMove ==null) {
                int stackBackCount = 2;
                while (nextMove != null) {
                    moves = getPossibleMoves(stack.get(stack.size()-stackBackCount));
                    nextMove = getNextMove(stack.get(stack.size()-stackBackCount),moves);
                }
                System.out.println();
                System.out.println("REACHEDDD");
                break;
            }


            stack.add(nextMove);
            Location topOfStack = stack.get(stack.size()-1);//top of stack
            addToExhausted(stack.get(stack.size()-2),nextMove);//one down from top of stack, top of stack
            board[nextMove.getRow()][nextMove.getCol()] = stack.size();
            printExhausedList(stack.get(stack.size()-2));
            printPossibleMoveLocations(topOfStack);
            printBoard();
            System.out.println();
        }

    }

    /*
     * Printed out the exhausted list for a given Location
     */
    public static void printExhausedList(Location loc)
    {
        //get current arraylist
        ArrayList<Location> current = exhausted.get(convertLocToIndex(loc));
        System.out.print("Exhausted List at index " + convertLocToIndex(loc) + ": ");
        for (int i = 0; i < current.size(); i++) {
            //System.out.print(i + " "); runs thru every value well, check
            System.out.print(current.get(i));
        }
        System.out.println();
    }

    /*
     * Prints out the possible move locations for a given Location
     */
    public static void printPossibleMoveLocations(Location loc)
    {
        ArrayList<Location> possibleMoves = getPossibleMoves(loc);
        System.out.print("Possible moves at Location, " + loc + ": ");
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.print(possibleMoves.get(i) + " ");
        }
        System.out.println();
    }

    /*
     * prints out the board (numbers correspond to moves)
     */
    public static void printBoard()
    {
        for (int i = 0; i < rowL; i++) {
            for (int j = 0; j < colL; j++) {
                System.out.print("[" + board[i][j] + "] ");
            }
            System.out.println();
        }
    }

    /*
     * prints out true/false for what spaces have been visited
     */
    public static void printVisited()
    {
//DON"T WORRY ABT
    }

    /*
     * clear out the exhausted list for a given Location
     * This needs to be done everytime a Location is removed from the Stack
     */
    public static void clearExhausted(Location loc)
    {
        int index = convertLocToIndex(loc);
        exhausted.get(index).clear();
    }

    /*
     * set up the exhausted list with empty exhuasted lists.
     */
    public static void initExhausted()
    {
        for (int i = 0; i < rowL*colL; i++) {
            ArrayList<Location> current = new ArrayList<Location>();
            exhausted.add(current);
        }
    }

    /*
     * is this dest Location exhausted from the source Location
     * exhausted return true
     */
    public static boolean inExhausted(Location source, Location dest)
    {
        int index = convertLocToIndex(source);

        if (exhausted.get(index) == null || exhausted.get(index).size() == 0)
            return false;

        for (int i = 0; i < exhausted.get(index).size(); i++) {
            if (exhausted.get(index).get(i) == dest)
                return true;
        }
        return false;
    }

    /*
     * returns the next valid move for a given Location on a given ArrayList of possible moves
     * returns null if no next move possible
     */
    public static Location getNextMove(Location loc, ArrayList<Location> list)
    {
        //list is possible moves
        //loc is current loc
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /*
     * converts a (row,col) to an array index for use in the exhausted list
     */
    public static int convertLocToIndex(Location loc) {
        return (loc.getRow()*rowL) + loc.getCol();
    }

    /*
     * adds a dest Location in the exhausted list for the source Location
     */
    public static void addToExhausted(Location source, Location dest)
    {
        int index = convertLocToIndex(source);
        exhausted.get(index).add(dest);
        System.out.println("Exhausted List>>> exhausted index " + index + ", added loc destination" + dest);
    }

    /*
     * is this Location a valid one
     */
    public static boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0 && loc.getRow() <= rowL - 1 && loc.getCol() <= colL - 1
                && board[loc.getRow()][loc.getCol()] < 1;
    }

    /*
     * returns another Location for the knight to move in.  If no more possible move
     * locations exist from Location loc, then return null
     */
    public static ArrayList<Location> getPossibleMoves(Location loc)
    {
        int[] xMoves = {-2,-1,1,2,2,1,-1,-2};
        int[] yMoves = {1,2,2,1,-1,-2,-2,-1};

        ArrayList<Location> allPossibleMoves = new ArrayList<>();
        //System.out.print("All possible moves at location " + loc + ": ");
        for (int i = 0; i < xMoves.length; i++) {
            Location current = new Location(loc.getRow() + xMoves[i],loc.getCol() + yMoves[i]);
            //System.out.print(current + " ");
            if (isValid(current))
                allPossibleMoves.add(current);
        }
        return allPossibleMoves;
    }


    /*
     * prompt for input and read in the start Location
     */
    public static void obtainStartLoc()
    {

    }

}

