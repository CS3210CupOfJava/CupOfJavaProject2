import java.util.*;
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

    //scan through entire input java file, this method will also be our counting method for the word 'public' as
    //a keyword.
    public void scanFile(){

    }
    //print the output content after running this program, just used for printing the amount of times public
    //was used, not sure what else we want to use this for yet.
    public void printOutput(){

    }

    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, World!");
    }
}