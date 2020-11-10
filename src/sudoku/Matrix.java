package sudoku;

/**
 * Class to make the changes on the Grids
 * @author Bruno Jungberck
 */

public class Matrix {

    private final int[][] controlMatrix;
    private final int rows;
    private final int colls;

    /**
     * Constructor based on some other matrix
     * @param controlMatrix = external matrix
     */
    Matrix(int[][] controlMatrix){
        this.controlMatrix = controlMatrix;
        this.rows = controlMatrix.length;
        this.colls = controlMatrix[0].length;
    }

    /**
     * Constructor based on the lenght of rows and collumns
     * @param rows number of rows
     * @param colls number of collumns
     */
    Matrix(int rows , int colls ){
        this.controlMatrix = new int[rows][colls];
        this.rows = rows;
        this.colls = colls;
    }

    /**
     * Changes the position of two rows
     * @param x row 1
     * @param y row 2
     */
    public void swapRow(int x, int y){
        int[] temp_row = new int[colls];
        x = x - 1;
        y = y - 1;
        for(int i = 0; i < colls; i++){
            temp_row[i] = controlMatrix[y][i];
            controlMatrix[y][i] = controlMatrix[x][i];
            controlMatrix[x][i] = temp_row[i];
        }
    }

    /**
     * Changes the position of two collumns
     * @param x collumn 1
     * @param y collumn 2
     */
    public void swapCollumn(int x, int y){
        int[] temp_coll = new int[rows];
        x = x - 1;
        y = y - 1;
        for(int i = 0; i < rows; i++){
            temp_coll[i] = controlMatrix[i][y];
            controlMatrix[i][y] = controlMatrix[i][x];
            controlMatrix[i][x] = temp_coll[i];
        }
    }

    /**
     * Changes the position of two group of rows
     * @param x row group 1
     * @param y row group 2
     */
    public void swapRowGroup(int x, int y){
        for(int i = 0; i < 3; i++){
            swapRow(x*3-i, y*3-i);
        }
    }

    /**
     * Changes the position of two group of collumns
     * @param x group collumn 1
     * @param y group collumn 2
     */
    public void swapCollumnGroup(int x, int y){
        for(int i = 0; i < 3; i++){
            swapCollumn(x*3-i, y*3-i);
        }
    }

    /**
     *Erase a number in the grid based on the coordinates provided by user
     * @param row row coordinate
     * @param coll collumn coordinate
     */
    public void eraseNumber(int row, int coll){
        this.controlMatrix[row][coll] = 0;
    }

    /**
     *Put a number in the grid based on the coordinates provided by user
     * @param row row coordinate
     * @param coll  collumn coordinate
     * @param value value to put at the coordinate provided
     */
    public void setElement(int row, int coll, int value){
        this.controlMatrix[row][coll] = value;
    }

    /**
     * Get a specified number located by the coordinates provided by user
     * @param row row coordinate
     * @param coll collumn coordinate
     * @return returns the specified number
     */
    public int getElement(int row, int coll){
        return this.controlMatrix[row][coll];
    }

    /**
     * Clone a entire grid
     * @return returns the grid clone
     */
    public Matrix cloneMatrix(){
        Matrix result = new Matrix(this.rows, this.colls);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                result.controlMatrix[i][j] = controlMatrix[i][j];
            }
        }
        return result;
    }

    /**
     * verify if the position specified by coordinates provided by user is filled by a number
     * @param row row coordinate
     * @param coll collumn coordinate
     * @return returns true or false
     */
    public boolean isFilled(int row, int coll){
        if(this.controlMatrix[row][coll] > 0){
            return true;
        }
        return false;
    }

}
