package sudoku;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Class to create a new match
 * @author Bruno Jungberck
 */

public class Match {
    private final Console console = new Console();
    private LocalDateTime startTime;
    private Sudoku sudoku;
    private int invalidMoves;
    private int validMoves;

    /**
     * Constructor to create a new game match based on the difficulty
     * @param difficultyLevel param to specify the difficulty level
     */
    Match(int difficultyLevel){
        GenerateGrid gen = new GenerateGrid(difficultyLevel);
        this.sudoku = gen.generateSudoku();
        this.startTime = LocalDateTime.now();
        this.validMoves = 0;
        this.invalidMoves = 0;
    }

    /**
     * method to start the moves of the match
     */
    public void play(){
        int option = 0;
        do{
            this.sudoku.printGrid(2);
            console.playOptions();
            option = console.readInt();
            if(option == 1){
                console.printLn("Row - Coordinate: ");
                int row = console.readInt()-1;
                console.printLn("Collumn - Coordinate: ");
                int coll = console.readInt()-1;
                console.printLn("Value of index: ");
                int value = console.readInt();
                if(this.sudoku.validatePosition(row, coll)){
                    this.sudoku.insertValue(row, coll, value);
                }
                if(this.sudoku.isValidMove(row, coll)){
                    this.validMoves++;
                }else{
                    this.invalidMoves++;
                }
            }
            if(option == 2){
                console.printLn("Row - Coordinate: ");
                int row = console.readInt()-1;
                console.printLn("Collumn - Coordinate: ");
                int coll = console.readInt()-1;
                if(this.sudoku.validatePosition(row, coll)) {
                    this.sudoku.removeValue(row, coll);
                }
            }
            if(this.sudoku.gameWinner()){
                printGameReport();
                return;
            }
        }while(option != 3);
        return;
    }

    /**
     * Method to print the game match report, informing the valid and invalid moves
     * duration of the match, the initial and the solved grid
     */
    public void printGameReport(){
        Duration elapsedTime = Duration.between(startTime, LocalDateTime.now());
        console.printLn("********* VICTORY !!! *********");
        console.printLn("-> Game time: "+elapsedTime.getSeconds()+" seconds");
        console.printLn("-> Valid Moves: "+this.validMoves);
        console.printLn("-> Invalid Moves: "+this.invalidMoves);
        console.printLn("-> Initial Sudoku: ");
        this.sudoku.printGrid(3);
        console.printLn("");
        console.printLn("-> Solved Sudoku:");
        this.sudoku.printGrid(1);
    }

}
