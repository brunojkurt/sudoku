package sudoku;

import java.util.Random;
import java.lang.Math;

/**
 * Class to generate the first grid
 * @author Bruno Jungberck
 */

public class GenerateGrid {
    /**
     * Base grid to generate the grids of the whole game
     */
    private static final int[][] baseGrid = {
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {2,3,4,5,6,7,8,9,1},
            {5,6,7,8,9,1,2,3,4},
            {8,9,1,2,3,4,5,6,7},
            {3,4,5,6,7,8,9,1,2},
            {6,7,8,9,1,2,3,4,5},
            {9,1,2,3,4,5,6,7,8}
    };
    private Matrix newGrid;
    private Matrix solvedGrid;
    private int difficultyLevel;

    /**
     * Constructor to Generate the Grid based on the difficulty level
     * @param difficultyLevel holds the difficulty level of the resulted grid
     */
    GenerateGrid(int difficultyLevel){
        this.newGrid = new Matrix(baseGrid);
        this.difficultyLevel = difficultyLevel;
        shuffleGrid();
        this.solvedGrid = newGrid.cloneMatrix();
        eraseGridFields();
    }

    /**
     * Generate class sudoku with the solved and the playable grid
     * @return return generated class sudoku
     */
    public Sudoku generateSudoku(){
        return new Sudoku(solvedGrid, newGrid);
    }

    /**
     * Shuffles the base_grid to generate the playable grid
     */
    public void shuffleGrid(){
        int x = 0;
        int y = 0;
        int shuffletimes = randomNumber(30, 60);

        for(int i = 0; i < shuffletimes; i++){

            int quadrant = randomNumber(1, 3);
            int secondary_quadrant = 1;

            do{
                x = randomNumber(((quadrant*3) - 2), (quadrant*3));
                y = randomNumber(((quadrant*3) - 2), (quadrant*3));
            }while(x == y);

            do {
                secondary_quadrant = randomNumber(1, 3);
            }while(quadrant == secondary_quadrant);

            this.newGrid.swapRow(x, y);
            this.newGrid.swapCollumn(x, y);
            this.newGrid.swapRowGroup(quadrant, secondary_quadrant);
            this.newGrid.swapCollumnGroup(quadrant, secondary_quadrant);
        }
    }

    /**
     * Erases the fields to generate the playable grid based on the difficulty level
     */
    public void eraseGridFields(){
        double numOfBlanks = verifyNumOfBlanks();
        double erasesPerSector = Math.floor(numOfBlanks / 9);
        int minBlanksSector = getMinOfBlanksPerSector();
        int row_target = 0;
        int coll_target = 0;
        do {
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++) {
                    for(int w = 0; w < erasesPerSector; w++){
                        row_target = ((i + 1)*3) - randomNumber(1,3);
                        coll_target = ((j + 1)*3) - randomNumber(1,3);
                        if(newGrid.isFilled(row_target, coll_target) && getNumOfTipsPerSector(i, j) > minBlanksSector){
                            newGrid.eraseNumber(row_target, coll_target);
                            numOfBlanks--;
                        }
                        if(numOfBlanks < 1){
                            return;
                        }
                    }
                }
            }
        }while(numOfBlanks > 0);
    }

    /**
     * Gets the number of tips in a specified quadrant
     * @param x specify the row group
     * @param y´specify the collumn group
     * @return returns the number of tips
     */
    public int getNumOfTipsPerSector(int x, int y){
        int counter = 0;
        x++;
        y++;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(newGrid.isFilled(((x*3)-i)-1, ((y*3)-j)-1)){
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * @return returns the difficulty level
     */
    public int getDifficultyLevel(){
        return this.difficultyLevel;
    }

    /**
     * @return returns the number of blanks that the grid must to have
     */
    private int verifyNumOfBlanks(){
        if(getDifficultyLevel() == 1){
            return 81 - 36;
        }
        if(getDifficultyLevel() == 2){
            return 81 - 32;
        }
        return 81 - 27;
    }

    /**
     * @return returns the minimum of blanks that the sectors of the grid must to have
     */
    private int getMinOfBlanksPerSector(){
        if(getDifficultyLevel() == 1){
            return 3;
        }
        if(getDifficultyLevel() == 2){
            return 3;
        }
        return 2;
    }

    /**
     * generate a randown number
     * @param min minimum number
     * @param max maximum number
     * @return return the random number based on the params min and max
     */
    private int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
