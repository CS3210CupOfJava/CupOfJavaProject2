/*  Project 2: Lexical and Syntax Analysis
 * In this project we are assuming that the input file has correct indentation as we heavily rely on this
 * for the logic of conditional statements and loops.
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
 * 4. Count how many times the keyword “public” is used as keywords in the input program.
 * 
 * 5. Then your program will print to a text file the original input program, the updated input 
 * program, and the number of time keyword “public” is used.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Stack;
class LexSynAnalyzer {

    //Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
    //appropriately. If not, fix it. Assigned to Jacob.
    public void checkConditionals(){
        File file = new File("jacobTest.txt");
        File output = new File("SyntaxOutput.txt");
        Stack<String> stack = new Stack<String>();
        Stack<Integer> tabStack = new Stack<Integer>();
        int tabCount = 0;
        try {
            Scanner scan = new Scanner(file);
            scan.useDelimiter("");
            FileWriter writer = new FileWriter(output);
            while (scan.hasNext()) {
                String car = scan.next();
                if (car.equals("\t")){
                    tabCount++;
                }
                if (car.equals("{") || car.equals("}")) {
                    stack.push(car);
                    tabStack.push(tabCount);
                }
                if (car.equals("\n")){
                    tabCount = 0;
                }

            }
            int size = stack.size();
            for (int i = 0; i < size; i++){
                writer.write(tabStack.pop() + " ");
                writer.write(stack.pop() + "\n");
            }
            writer.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Check to make sure all loop structures (while, do-while, for) use curly braces
    //appropriately. If not, fix it. Assigned to Ali
    public void checkLoops(){

    }
    //Check to make sure all the method structure is syntactically correct. If not, fix it. Assigned to Aria
    public void checkSyntax() {

    }

    //scan through entire input java file, this method will also be our counting method for the word 'public' as
    //a keyword.
    public void scanFile(){
        File file = new File("test.txt");
        File output = new File("SyntaxOutput.txt");
        try {
            Scanner scan = new Scanner(file);
            scan.useDelimiter("");
            FileWriter write = new FileWriter(output);
            while (scan.hasNext()) {
                write.write(scan.next());
            }
            write.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //print the output content after running this program, just used for printing the amount of times public
    //was used, not sure what else we want to use this for yet.
    public void printOutput(){

    }

    public static void main(String []args)
    {
        LexSynAnalyzer anal = new LexSynAnalyzer();
        anal.scanFile();

    }
}