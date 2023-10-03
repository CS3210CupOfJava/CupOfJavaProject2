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
    public boolean checkConditional(){
        File file = new File("bottomHalf.txt");
        File output = new File("SyntaxOutput.txt");
        Stack<String> stack = new Stack<String>();
        Stack<Integer> tabStack = new Stack<Integer>();
        boolean good = false;
        int tabCount = 0;
        try {
            Scanner scan = new Scanner(file);
            scan.useDelimiter("");
            FileWriter writer = new FileWriter(output);


            //this code will complete a missing closing bracket one call at a time and only for if statements.

            while (scan.hasNext()) {
                String car = scan.next();
                if (car.equals("\t")) {
                    tabCount++;
                }
                else if (car.equals("\n")) {
                    tabCount = 0;
                }
                else if (car.equals("{") && stack.size() == 0) {
                    stack.push(car);
                    tabStack.push(tabCount);
                }
                else if (stack.size() > 0 && tabCount == tabStack.peek()) {
                    String temp = scan.next();
                    if (temp != "\t") {
                        stack.pop();
                        tabStack.pop();
                        writer.write("}");
                        writer.write(temp);
                    }
                    else {
                        writer.write(temp);
                    }
                }
                writer.write(car);
            }






            //this code will add missing closing braces regardless of the type of line (if, for, method)
            /*
            while (scan.hasNext()) {
                String car = scan.next();
                if (car.equals("\t")) {
                    tabCount++;
                }
                else if (car.equals("\n")) {
                    tabCount = 0;
                }
                else if (car.equals("{")) {
                    stack.push(car);
                    tabStack.push(tabCount);
                }
                else if (car.equals("}")) {
                    if (stack.size() > 0){
                        if (tabStack.peek() == tabCount){
                            System.out.println(stack.pop() + " " +  tabStack.pop());
                        }
                    }
                }
                else if (stack.size() > 0 && tabCount == tabStack.peek()) {
                    String temp = scan.next();
                    if (temp != "\t") {
                        stack.pop();
                        tabStack.pop();
                        writer.write("}");
                        writer.write(temp);
                    }
                    else {
                        writer.write(temp);
                    }

                }
                writer.write(car);
            }
            */

            writer.close();
            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(good);
        int size = stack.size();
        for (int i = 0; i < size;i++){
            System.out.println(stack.pop() + " " + tabStack.pop());
        }
        return good;
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
        File file = new File("Test.txt");
        File topHalf = new File("topHalf.txt");
        File bottomHalf = new File("bottomHalf.txt");
        String currentLine;
        try {
            Scanner scan = new Scanner(file);
            scan.useDelimiter("");
            FileWriter topWriter = new FileWriter(topHalf);
            FileWriter botWriter = new FileWriter(bottomHalf);
            while (scan.hasNextLine()) {
                currentLine = scan.nextLine();
                if (currentLine.contains("if") && !currentLine.contains("//")) {
                    botWriter.write(currentLine + "\n");
                    while (scan.hasNext()) {
                        botWriter.write(scan.next());
                    }
                    botWriter.close();
                    topWriter.close();
                    checkConditional();
                    concatenate();
                }
                else {
                    topWriter.write(currentLine + "\n");
                }
            }
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

    public void concatenate(){
        try {
            File topHalf = new File("topHalf.txt");
            File output = new File("SyntaxOutput.txt");
            File concatenated = new File("Concatenated.txt");
            Scanner top = new Scanner(topHalf);
            Scanner bottom = new Scanner(output);
            top.useDelimiter("");
            bottom.useDelimiter("");
            FileWriter topWriter = new FileWriter(concatenated);
            FileWriter botWriter = new FileWriter(concatenated, true);
            while (top.hasNext()) {
                topWriter.write(top.next());
            }
            topWriter.close();
            top.close();
            while (bottom.hasNext()) {
                botWriter.write(bottom.next());
            }
            botWriter.close();
            bottom.close();
        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }





    public static void main(String []args)
    {
        LexSynAnalyzer anal = new LexSynAnalyzer();
        anal.scanFile();

    }
}