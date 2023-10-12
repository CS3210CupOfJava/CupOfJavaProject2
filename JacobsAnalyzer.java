//*  Project 2: Lexical and Syntax Analysis
// * In this project we are assuming that the input file has correct indentation as well as
// * comments where missing brackets should be as we heavily rely on this
// * for the logic of conditional statements and loops.
// *
// * Write a program language of your choice that takes in a Java program as an input and does the
// * following tasks:
// *
// * 1. Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
// * appropriately. If not, fix it. Assume that single statement block is also required to include
// * curly braces.
// *
// * 2. Check to make sure all loop structures (while, do-while, for) use curly braces
// * appropriately. If not, fix it. Assume that single statement block is also required to include
// * curly braces.
// *
// * 3. Check to make sure all the method structure is syntactically correct. If not, fix it.
// *
// * 4. Count how many times the keyword “public” is used as keywords in the input program.
// *
// * 5. Then your program will print to a text file the original input program, the updated input
// * program, and the number of time keyword “public” is used.
// */
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Stack;
//class JacobsAnalyzer {
//    //Check to make sure all decision structures (if, if-else, if-else-if, switch) use curly braces
//    //appropriately. If not, fix it. Assigned to Jacob.
//    public static ArrayList checkConditional(int i, ArrayList<String> text) {
//        Stack<String> stack = new Stack<String>();
//        Stack<Integer> tabStack = new Stack<Integer>();
//        int tabCount = 0;
//        boolean close = false;
//
//        //begin checks for opening bracket.
//        //we start with i + 1 because we know that's the line that should have an opening bracket.
//        String car = text.get(i + 1);
//        //grab the tab count of the line
//        tabCount = car.length() - car.replaceAll(Character.toString('\t'), "").length();
//        String line = "";
//        //if the line does not have an opening bracket, add a bracket
//        // (assuming it's indented and formatted correctly).
//        stack.push("{");
//         //if the current line does not have an open brace.
//        if (!car.contains("{")) {
//            text.set(i+1, "");
//            //set tabstack - 1 since we should be indented one tab further than the original if statement.
//            tabStack.push(tabCount - 1);
//            //building the string (just the tabs)
//            for (int j = 0; j < tabCount - 1; j++) {
//                line += "\t";
//            }
//            //add brace, add new line character
//            text.add(i+1, line + "{" + "\n");
//            //replace next line if there was code there.
//            text.set(i + 2, car);
//        }
//        else{
//            //push the tab count to tabstack since the bracket is there and indented correctly.
//            tabStack.push(tabCount);
//        }
//        tabCount = 0;
//        //begin iterating through entire array starting at the given i index.
//        for (int j = i + 2; j < text.size(); j++) {
//            //checks for if closing brackets are found.
//            //if the closing bracket is found, stop searching through the ArrayList.
//            if (close) {
//                break;
//            }
//            car = text.get(j);
//            //begin iterating through the individual array elements one character at a time to check
//            //for tabs, brackets, and new lines.
//            for (int k = 0; k < car.length(); k++) {
//                char character = car.charAt(k);
//                //if we find a tab, add it to the tab counter.
//                /** this code can be condensed to where we use the same method
//                 * as the opening bracket.
//                 * this will eliminate extra checks for every iteration of the character loop. */
//                if (character == '\t') {
//                    tabCount++;
//                }
//                //if we find a new line, reset tab counter.
//                else if (character == '\n') {
//                    tabCount = 0;
//                    break;
//                }
//                //check if the current character is a closing bracket and
//                //check if the tab count is equal to the tab count on our opening brace.
//                //if both are true, we have found the closing brace and can stop searching.
//                else if (character == '}' && tabCount == tabStack.peek()){
//                    close = true;
//                    break;
//                }
//                //check if the tab stack has at least one value and
//                //check if the tab count is equal to the next item in the tab stack and
//                //check if the current line has more than one character.
//                if (tabStack.size() > 0 && tabCount == tabStack.peek() && car.length() > 1) {
//                    //check the next character
//                    char temp = car.charAt(k + 1);
//                    //if that character is not another tab and if it's not a bracket (where it should be)
//                    if (temp == '\n' && !car.contains("}")) {
//                        //keep only the tabs
//                        line = text.get(j).replaceAll("^\\t+", "");
//                        //set line2 to empty string to begin building.
//                        String line2 = "";
//                        //remove opening brace from the stack since we don't need it anymore.
//                        stack.pop();
//                        //set the corresponding tab stack count to a variable.
//                        int tabs = tabStack.pop();
//                        //begin building the string to add to the array.
//                        for (int y = 0; y < tabs; y++) {
//                            line2 += "\t";
//                        }
//                        //add string to the array.
//                        text.set(j, line2 + "}" + line);
//                        //set close to true since we have created the closing brace in the correct location.
//                        close = true;
//                    }
//                }
//            }
//        }
//        return text;
//    }
//
//
//    //Check to make sure all loop structures (while, do-while, for) use curly braces
//    //appropriately. If not, fix it. Assigned to Ali
//    public static void checkLoops() {
//
//    }
//
//    //Check to make sure all the method structure is syntactically correct. If not, fix it. Assigned to Aria
//    public static void checkSyntax() {
//
//    }
//
//    //scan through entire input java file.
//    public static void scanFile(ArrayList<String> inputLines) {
//        File output = new File("SyntaxOutput.txt");
//        try {
//            FileWriter writer = new FileWriter(output);
//            for (int i = 0; i < inputLines.size(); i++) {
//                if (inputLines.get(i).contains("if") ||
//                        inputLines.get(i).contains("else if") ||
//                        inputLines.get(i).contains("if else if") ||
//                        inputLines.get(i).contains("else") ||
//                        inputLines.get(i).contains("switch")) {
//                    inputLines = checkConditional(i, inputLines);
//                }
//            }
//            for (int i = 0; i < inputLines.size(); i++) {
//                writer.write(inputLines.get(i));
//                System.out.print(inputLines.get(i));
//            }
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    //print the output content after running this program, just used for printing the amount of times public
//    //was used, not sure what else we want to use this for yet.
//    public void printOutput() {
//
//    }
//
//
//    public static void main(String[] args) throws IOException {
//
//        String inputPath = "Test.txt";
//        String outputPath = "UpdatedCode.txt";
//
//        // ArrayList to track each line of code from input file and to edit with fixes
//        ArrayList<String> inputLines = new ArrayList<String>();
//        // String to hold each line of code from input file
//        String line = "";
//
//        boolean insideMultiLineComment = false;
//        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
//            while ((line = br.readLine()) != null) {
//                // Check if inside a multi-line comment block
//                if(insideMultiLineComment) {
//                    // Check if the line contains an end for multi-line comments
//                    if (line.contains("*/")) {
//                        insideMultiLineComment = false;
//                    }
//                    continue; // Skip this line since it's commented
//                }
//                // Check if the line starts with a multi-line comment
//                if(line.contains("/*")) {
//                    insideMultiLineComment = true;
//                    // Check if the line also ends the multi-line comment on same line
//                    if(line.contains("*/")) {
//                        insideMultiLineComment = false;
//                    }
//                    continue; // Skip this line since it's a comment
//                }
//                if(line.contains("//")){
//                    inputLines.add(line.substring(0, line.indexOf("//")) + "\n");
//                }
//                else {
//                    inputLines.add(line + "\n");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        scanFile(inputLines);
//    }
//}
