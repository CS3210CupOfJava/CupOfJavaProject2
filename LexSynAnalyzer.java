/*  Project 2: Lexical and Syntax Analysis
 * In this project we are assuming that the input file has correct indentation as well as
 * comments where missing brackets should be as we heavily rely on this
 * for the logic of conditional statements and loops.
 *
 * Write a program language of your choice that takes in a Java program as an input and does the
 * following tasks:
 *
 * 1. Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
 * appropriately. If not, fix it. Assume that single statement block is also required to include
 * curly braces. Assigned to Jacob.
 *
 * 2. Check to make sure all loop structures (while, do-while, for) use curly braces
 * appropriately. If not, fix it. Assume that single statement block is also required to include
 * curly braces. Assigned to Ali.
 *
 * 3. Check to make sure all the method structure is syntactically correct. If not, fix it.
 *  Assigned to Aria.
 *
 * 4. Count how many times the keyword “public” is used as keywords in the input program.
 *  Assigned to Braden.
 *
 * 5. Then your program will print to a text file the original input program, the updated input
 * program, and the number of time keyword “public” is used.
 *  Assigned to Braden.
 */

import java.util.Stack;
import java.io.BufferedReader;  // Used for reading text files.
import java.io.BufferedWriter;  // Used for writing to text files.
import java.io.FileReader;      // Used to open and read from a text file.
import java.io.FileWriter;      // Used to open and write to a text file.
import java.io.IOException;     // Used for handling input/output errors.
import java.util.ArrayList;     // ArrayList used for storing lines of code

class LexSynAnalyzer {

    public static void main(String []args)
    {
        // ===== Main Project Declarations =====
        // Define path to the input file and output file
        String inputPath = "Test.txt";
        String outputPath = "UpdatedCode.txt";
        // ArrayList to track each line of code from input file and to edit with fixes
        ArrayList<String> inputLines = new ArrayList<String>();
        // String to hold each line of code from input file
        String line = "";

        // ===== Part 1 Declarations =====
        // ===== Part 2 Declarations =====
        // ===== Part 3 Declarations =====
        // ===== Part 4 Declarations =====
        int keywordCount = 0;
        // ===== Part 5 Declarations =====

        // ========== Begin the project ==========
        System.out.println("Let the scanning begin!");

        // Read input file and save lines to ArrayList as individual elements
        boolean insideMultiLineComment = false;
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            // While loop for inserting lines of code into an arraylist without comments
            while ((line = br.readLine()) != null) {
                // Check if inside a multi-line comment block
                if(insideMultiLineComment) {
                    // Check if the line contains an end for multi-line comments
                    if (line.contains("*/")) {
                        insideMultiLineComment = false;
                    }
                    continue; // Skip this line since it's commented
                }
                // Check if the line starts with a multi-line comment
                if(line.contains("/*")) {
                    insideMultiLineComment = true;
                    // Check if the line also ends the multi-line comment on same line
                    if(line.contains("*/")) {
                        insideMultiLineComment = false;
                    }
                    continue; // Skip this line since it's a comment
                }
                // Check if the line has a single line comment.
                if(line.contains("//")){
                    inputLines.add(line.substring(0, line.indexOf("//")) + "\n");
                }
                else {
                    inputLines.add(line + "\n");
                }
            } // end while loop for inserting code into arraylist
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ===== Part 1 =====
        //begin checking arraylist for lines that contain "if", "else if", "if else if", "else", or "switch"
        for (int i = 0; i < inputLines.size(); i++) {
            if (inputLines.get(i).contains("if") ||
                    inputLines.get(i).contains("else if") ||
                    inputLines.get(i).contains("if else if") ||
                    inputLines.get(i).contains("else") ||
                    inputLines.get(i).contains("switch")) {
                inputLines = checkConditional(i, inputLines);
            }
        }

        // ===== Part 2 =====
        //begin checking arraylist for lines that contain "while", "do-while", or "for"

        // ===== Part 3 =====
        //Check to make sure all the method structure is syntactically correct. If not, fix it.

        // ===== Part 4 =====
        // Count occurrences of keyword "public" within input file
        keywordCount = countKeyword(inputPath, "public");
        // ===== Part 5 =====
        // Print original input file to specified text file
        printInputFileToOutputFile(inputPath, outputPath);
        // Print updated input code to specified text file
        printArrayList(inputLines, outputPath);
        // Print number of occurrences of "public" keyword to specified text file
        printCount(outputPath, keywordCount);
        System.out.println("This is the end of the program!");
    } // End main()

    // =============== Part 1 Method(s) ===============

    /**
     * Scans the input file, and Checks to make sure all decision structures
     * (if, if-else, if-else-if, switch) use curly braces appropriately.
     * If not, fix it.
     *
     * @param i  The current iteration that the main loop was on.
     * @param text   The arrayList containing the altered code. (removed comments)
     * @return ArrayList    The updated ArrayList after we've added necessary brackets.
     */
    public static ArrayList<String> checkConditional(int i, ArrayList<String> text) {
        Stack<String> stack = new Stack<String>();
        Stack<Integer> tabStack = new Stack<Integer>();
        int tabCount = 0;
        boolean close = false;

        //begin checks for opening bracket.
        //we start with i + 1 because we know that's the line that should have an opening bracket.
        String car = text.get(i + 1);
        //grab the tab count of the line
        tabCount = car.length() - car.replaceAll(Character.toString('\t'), "").length();
        String line = "";
        //if the line does not have an opening bracket, add a bracket
        // (assuming it's indented and formatted correctly).
        stack.push("{");
        //if the current line does not have an open brace.
        if (!car.contains("{")) {
            text.set(i+1, "");
            //set tab stack - 1 since we should be indented one tab further than the original if statement.
            tabStack.push(tabCount - 1);
            //building the string (just the tabs)
            for (int j = 0; j < tabCount - 1; j++) {
                line += "\t";
            }
            //add brace, add new line character
            text.add(i+1, line + "{" + "\n");
            //replace next line if there was code there.
            text.set(i + 2, car);
        }
        else{
            //push the tab count to tab stack since the bracket is there and indented correctly.
            tabStack.push(tabCount);
        }
        //begin iterating through entire array starting at the given i index.
        for (int j = i + 2; j < text.size(); j++) {
            //checks for if closing brackets are found.
            //if the closing bracket is found, stop searching through the ArrayList.
            if (close) {
                break;
            }
            //get current line.
            car = text.get(j);
            //get tab count for the current line.
            tabCount = car.length() - car.replaceAll(Character.toString('\t'), "").length();
            //begin iterating through the individual array elements one character at a time to check
            //for tabs, brackets, and new lines.
            for (int k = 0; k < car.length(); k++) {
                char character = car.charAt(k);

                //check if the current character is a closing bracket and
                //check if the tab count is equal to the tab count on our opening brace.
                //if both are true, we have found the closing brace and can stop searching.
                if (character == '}' && tabCount == tabStack.peek()){
                    close = true;
                    break;
                }
                //check if the tab stack has at least one value and
                //check if the tab count is equal to the next item in the tab stack and
                //check if the current line has more than one character.
                if (tabStack.size() > 0 && tabCount == tabStack.peek() && car.length() > 1) {
                    //check the next character
                    char temp = car.charAt(k + 1);
                    //if that character is not another tab and if it's not a bracket (where it should be)
                    if (temp == '\n' && !car.contains("}")) {
                        //keep only the tabs
                        line = text.get(j).replaceAll("^\\t+", "");
                        //set line2 to empty string to begin building.
                        String line2 = "";
                        //remove opening brace from the stack since we don't need it anymore.
                        stack.pop();
                        //set the corresponding tab stack count to a variable.
                        int tabs = tabStack.pop();
                        //begin building the string to add to the array.
                        for (int y = 0; y < tabs; y++) {
                            line2 += "\t";
                        }
                        //add string to the array.
                        text.set(j, line2 + "}" + line);
                        //set close to true since we have created the closing brace in the correct location.
                        close = true;
                    }
                }
            }
        }
        return text;
    }

    // =============== Part 2 Method(s) ===============
    //Check to make sure all loop structures (while, do-while, for) use curly braces
    //appropriately. If not, fix it.


    // =============== Part 3 Method(s) ===============
    //Check to make sure all the method structure is syntactically correct. If not, fix it.
    public static void checkSyntax() {

    }

    // =============== Part 4 Method(s) ===============
    /**
     * Scans the input file, counts and returns the occurrences of the
     * specified keyword. Ignores Java comment lines and Java multi-line
     * comments while searching.
     *
     * @param filePath  The path to the input file to scan.
     * @param keyword   The keyword to search for.
     * @return          The number of occurrences of the keyword in the input
     *                  file. Returns -1 if an error occurs during scanning.
     */
    public static int countKeyword(String filePath, String keyword) {
        // Counter to track the number of occurrences of a keyword
        int keywordCounter = 0;
        // String to track line of code
        String line = "";
        // Begin reading input file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while((line = br.readLine()) != null) {
                // Check if the line contains the specified keyword
                if(line.contains(keyword)) {
                    keywordCounter++;
                }
            } // End while
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // An error occured during scanning the file
        }
        return keywordCounter;
    } // End countKeyword
    // =============== Part 5 Method(s) ===============
    /**
     * This method prints the input file's content to the specified output
     * file along with a message signifying where it begins.
     *
     * @param inputPath    The file to read from
     * @param outputPath   The file to write to
     */
    public static void printInputFileToOutputFile(String inputPath, String outputPath){
        String line = "";
        String message = "========== HERE IS WHERE THE INPUT FILE'S CODE BEGINS ==========";

        try(BufferedReader br = new BufferedReader(new FileReader(inputPath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            // Print message signifying where the input file's code begins
            bw.write(message);

            while((line = br.readLine()) != null) {
                // Read through input file and copy it all to output file
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // End printInputFileToOutputFile

    /**
     * This method appends the content of an ArrayList to a specified text file
     * along with a message signifying where it begins.
     *
     * @param arrayList  The ArrayList containing the content to be printed.
     * @param outputPath The path to the text file where the content will write to.
     */
    public static void printArrayList(ArrayList<String> arrayList, String outputPath) {
        String line = "";
        String message = "========== HERE IS WHERE THE UPDATED CODE BEGINS ==========";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
            // Print message signifying where the ArrayList code begins
            bw.write(message);
            bw.newLine();

            // Print the contents of the ArrayList to the specified file
            for(int i = 0; i < arrayList.size(); i++) {
                line = arrayList.get(i);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // End printArrayList

    /**
     * This method prints the specified count to a specified text file.
     *
     * @param outputPath The path to the text file where count will be printed to.
     * @param count      The count to be printed.
     */
    public static void printCount(String outputPath, int count) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
            // Append the count to the specified output file
            bw.write("========== The keyword 'public' appears " + count + " times in the input file. ==========");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // End LexSynAnalyzer class