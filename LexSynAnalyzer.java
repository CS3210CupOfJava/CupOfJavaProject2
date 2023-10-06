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
import java.util.ArrayList;     // ArrayList used for storing lines of code

class LexSynAnalyzer {

    public static void main(String []args)
    {   
        // ===== Main Project Declarations =====
        // Define path to the input file and output file
        String inputPath = "Test.txt";
        String outputPath = "UpdatedCode.txt";
        // ArrayList to track each line of code from input file and to edit with fixes
        ArrayList<String> inputLines = new ArrayList<>();
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
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            while ((line = br.readLine()) != null) {
                // Add the line to the ArrayList without trimming whitespace
                inputLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ===== Part 1 =====
        
        // ===== Part 2 =====
        
        // ===== Part 3 =====

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
    //Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
    //appropriately. If not, fix it.
    public static void checkConditionals(){

    }

    // =============== Part 2 Method(s) ===============
    //Check to make sure all loop structures (while, do-while, for) use curly braces
    //appropriately. If not, fix it.
    public static void checkLoops(){

    }

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
        // Flag to track whether inside a Javamulti-line comment
        boolean insideMultiLineComment = false;
        // String to track line of code
        String line = "";

        // Begin reading input file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while((line = br.readLine()) != null) {
                // Check if inside a multi-line comment block
                if(insideMultiLineComment == true) {
                    // Check if the line contains an end for multi-line comments
                    if(line.contains("*/")) {
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