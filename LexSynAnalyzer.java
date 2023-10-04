/*  Project 2: Lexical and Syntax Analysis
 *  
 * Write a program language of your choice that takes in a Java program as an input and does the 
 * following tasks:
 * 
 * 1. Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
 * appropriately. If not, fix it. Assume that single statement block is also required to include 
 * curly braces.
 * 
 * 2. Check to make sure all loop structures (while, do-while, for) use curly braces
 * appropriately. If not, fix it. Assume that single statement block is also required to include 
 * curly braces.
 * 
 * 3. Check to make sure all the method structure is syntactically correct. If not, fix it.
 * 
 * 4. Count how many time the keyword “public” is used as keywords in the input program.
 * 
 * 5. Then your program will print to a text file the original input program, the updated input 
 * program, and the number of time keyword “public” is used.
 */

import java.io.BufferedReader;  // Used for reading text files.
import java.io.BufferedWriter;  // Used for writing to text files.
import java.io.FileReader;      // Used to open and read from a text file.
import java.io.FileWriter;      // Used to open and write to a text file.
import java.io.IOException;     // Used for handling input/output errors.
import java.util.Scanner;       // Used for input and file reading.

class LexSynAnalyzer {

    //Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
    //appropriately. If not, fix it.
    public void checkConditionals(){

    }

    //Check to make sure all loop structures (while, do-while, for) use curly braces
    //appropriately. If not, fix it.
    public void checkLoops(){

    }
    //Check to make sure all the method structure is syntactically correct. If not, fix it.
    public void checkSyntax() {

    }

    /**
     * Scans the input file and counts the occurrences of a specified keyword.
     * 
     * @param keyword   The keyword to search for in the input file.
     * @param filePath  The path to the input file to scan.
     * @return          The number of occurrences of the keyword in the input file.
     *                  Returns -1 if an error occurs during scanning.
     */
    public int scanFile(String keyword, String filePath){
        return -1;
    }

    //print the output content after running this program, just used for printing the amount of times public
    //was used, not sure what else we want to use this for yet.
    public void printOutput(){

    }

    public static void main(String []args)
    {
        // === Part 4 ===
        // Count occurrences of "public" within input file

        // === Part 5 ===
        // Print original input file to new file
        // Print updated input file to new file
        // Print number of occurrences of "public" keyword
        System.out.println("This is the end of the program!");
    }
}