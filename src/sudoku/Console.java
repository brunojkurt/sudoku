package sudoku;

/**
 * Class to print options and read values
 * @author Bruno Jungberck
 * */

import java.util.Scanner;

public class Console {
    private Scanner input_in = new Scanner(System.in);

    /**
     * method to read string data
     * @return return the read data
     */
    private String read(){
        String userInput;
        userInput = input_in.next();
        return userInput;
    }

    /**
     * method to read int data
     * @return return the read data
     */
    public int readInt(){
        return Integer.parseInt(read());
    }

    /**
     * print string data provided
     * @param text returns the data provided
     */
    public void print(String text){
        System.out.print(text);
    }

    /**
     * print string data provided with row break
     * @param text returns the data provided
     */
    public void printLn(String text){
        System.out.println(text);
    }

    /**
     * print options to play
     */
    public void playOptions(){
        printLn("##### Sudoku Game #####");
        printLn("1 - Insert value");
        printLn("2 - Remove value");
        printLn("3 - Exit");
    }

    /**
     * print difficulty options
     */
    public void difficultyOptions(){
        printLn("1 - Easy (36 tips)");
        printLn("2 - Medium (32 tips)");
        printLn("3 - Hard (27 tips)");
    }

}
