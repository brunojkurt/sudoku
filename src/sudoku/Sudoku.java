package sudoku;

/**
 *Class to store the grids of the match and call the methods to manipulation
 * @author Bruno Jungberck
 */
public class Sudoku {
    private Matrix solvedGrid;
    private Matrix playableGrid;
    private Matrix fixedGrid;
    private final Console console = new Console();

    /**
     * Constructor to call the creation of the grids
     * that will be used to manipulation based on grid provided by class GenerateGrid
     * @param solvedGrid  base to create the solved grid
     * @param playableGrid base to create the playable grid
     */
    Sudoku(Matrix solvedGrid, Matrix playableGrid){
        this.solvedGrid = solvedGrid;
        this.playableGrid = playableGrid;
        this.fixedGrid = this.playableGrid.cloneMatrix();
    }

    /**
     * Method to print a entire grid
     * @param select parameter to select what grid will be printed
     */
    public void printGrid(int select){
        Matrix temp_matrix = new Matrix(9, 9);
        if(select == 1){
            temp_matrix = this.solvedGrid.cloneMatrix();
        }
        if(select == 2){
            temp_matrix = this.playableGrid.cloneMatrix();
        }
        if(select == 3){
            temp_matrix = this.fixedGrid.cloneMatrix();
        }
        console.printLn("   1 2 3   4 5 6   7 8 9");
        console.printLn("   - - -   - - -   - - -");
        for(int i = 0; i < 9; i++){
            console.print((Integer.toString(i+1))+" |");
            for(int j = 0; j < 9; j++){
                if(temp_matrix.getElement(i, j) == 0){
                    console.print("  ");
                }else{
                    console.print(Integer.toString(temp_matrix.getElement(i, j))+" ");
                }
                if(j == 2 || j == 5 || j == 8){
                    console.print("| ");
                }
            }
            if(i == 2 || i == 5 || i == 8){
                console.printLn("");
                console.printLn("   - - -   - - -   - - -");
            }else{
                console.printLn("");
            }
        }
    }

    /**
     * method to call insertion of number in grid
     * @param row row coordinate
     * @param coll collumn coordinate
     * @param value value to be inserted
     */
    public void insertValue(int row, int coll, int value){
        this.playableGrid.setElement(row, coll, value);
    }

    /**
     * method to call the remove of some number in the grid
     * @param row row coordinate
     * @param coll collumn coordinate
     */
    public void removeValue(int row, int coll){
        this.playableGrid.eraseNumber(row, coll);
    }

    /**
     * method to verify if a move in the game is a valid one
     * @param row row coordinate
     * @param coll coll coordinate
     * @return
     */
    public boolean isValidMove(int row, int coll){
        if(row < 0 || row > 8 || coll < 0 || coll > 8){
            return false;
        }
        if(this.playableGrid.getElement(row, coll) != this.solvedGrid.getElement(row, coll)){
            return false;
        }
        return true;
    }

    /**
     * method to validate if some position required is a valid position and can be used
     * @param row row coordinate
     * @param coll coll coordinate
     * @return returns true or false to verification
     */
    public boolean validatePosition(int row, int coll){
        if(row < 0 || row > 8 || coll < 0 || coll > 8){
            return false;
        }
        if(this.fixedGrid.getElement(row, coll) == 0){
            return true;
        }
        return false;
    }

    /**
     * verify if the game is over and have a winner
     * @return true or false to verification
     */
    public boolean gameWinner(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(playableGrid.getElement(i, j) != solvedGrid.getElement(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
}
