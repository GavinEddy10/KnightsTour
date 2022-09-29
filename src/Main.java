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

    public static void main(String[] args) {

        System.out.println("START");
        initExhausted();
        ArrayList<Location> currentPossible;
        obtainStartLoc();
        startLoc = new Location(0,0);
        System.out.println("Start Loc is " + startLoc);

        stack.push(startLoc);
        //visited[startLoc.getRow()][startLoc.getCol()] = true;
        board[startLoc.getRow()][startLoc.getCol()] = 1;

        printBoard();

        while(stack.size() != rowL * colL && stack.size() != 0)
        {


        }

    }

    /*
     * Printed out the exhausted list for a given Location
     */
    public static void printExhausedList(Location loc)
    {

    }

    /*
     * Prints out the possible move locations for a given Location
     */
    public static void printPossibleMoveLocations(Location loc)
    {

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

    }

    /*
     * set up the exhausted list with empty exhuasted lists.
     */
    public static void initExhausted()
    {
        for (int i = 0; i < rowL*colL; i++) {
            exhausted.add(new ArrayList<Location>());
        }
    }

    /*
     * is this dest Location exhausted from the source Location
     */
    public static boolean inExhausted(Location source, Location dest)
    {
        return false;
    }

    /*
     * returns the next valid move for a given Location on a given ArrayList of possible moves
     */
    public static Location getNextMove(Location loc, ArrayList<Location> list)
    {
        return null;
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

    }

    /*
     * is this Location a valid one
     */
    public static boolean isValid(Location loc)
    {
        return false;
    }

    /*
     * returns another Location for the knight to move in.  If no more possible move
     * locations exist from Location loc, then return null
     */
    public static ArrayList<Location> getPossibleMoves(Location loc)
    {
        return null;
    }


    /*
     * prompt for input and read in the start Location
     */
    public static void obtainStartLoc()
    {

    }

}