import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rhellgren on 30/03/15.
 */
public class SudokuGrid {
    private final ArrayList[][] sudoku;
    private final boolean[][] finalValue;

    SudokuGrid() {
        sudoku = new ArrayList[9][9];

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                ArrayList initial = new ArrayList();
                for(int k = 1; k <= 9; k++)
                    initial.add(k);
                sudoku[i][j] = initial;
            }

        finalValue = new boolean[9][9];
        for(int i = 0; i < 9; i++)
            Arrays.fill(finalValue[i], false);
    }

    public void setValues(int row, int column, ArrayList value) {
        sudoku[row][column] = value;
    }

    public ArrayList getValues(int row, int column) {
        return sudoku[row][column];
    }

    public int getFirstValue(int row, int column) {
        return (Integer) sudoku[row][column].get(0);
    }

    public void setFinal(int row, int column, int value) {
        finalValue[row][column] = true;
        ArrayList finalArray = new ArrayList();
        finalArray.add(value);
        this.setValues(row, column, finalArray);
    }

    public int numFinals() {
        int numFinals = 0;
        for(int row=0; row < 9; row++) {
            for(int column=0; column < 9; column++) {
                if(isFinal(row, column))
                    numFinals++;
            }
        }
        return numFinals;
    }

    public boolean isFinal(int row, int column) {
        return finalValue[row][column];
    }

    public void removeOthers(int row, int column, int value) {
        // Remove from row/column
        for(int i = 0; i < 9; i++) {
            if(i != column && sudoku[row][i].indexOf(value) != -1) {
                sudoku[row][i].remove(sudoku[row][i].indexOf(value));
            }
            if(i != row && sudoku[i][column].indexOf(value) != -1) {
                sudoku[i][column].remove(sudoku[i][column].indexOf(value));
            }
        }

        // Remove from square
        int[] limits = this.getSquareLimits(row, column);
        int lowerRowLim = limits[0];
        int upperRowLim = limits[1];
        int lowerColLim = limits[2];
        int upperColLim = limits[3];

        for(int squareRow = lowerRowLim; squareRow < upperRowLim; squareRow++)
            for (int squareColumn = lowerColLim; squareColumn < upperColLim; squareColumn++)
                if(sudoku[squareRow][squareColumn].indexOf(value) != -1 && row != squareRow && column != squareColumn) {
                    // if it has not yet been removed, and it's not the place itself, then remove
                    sudoku[squareRow][squareColumn].remove(sudoku[squareRow][squareColumn].indexOf(value));
                }
    }

    private int[] getSquareLimits(int row, int column) {
        int[] limits = new int[4];

        if(row <= 2) {
            limits[0] = 0;
            limits[1] = 3;
        } else if(row >= 3 && row <= 5) {
            limits[0] = 3;
            limits[1] = 6;
        } else {
            limits[0] = 6;
            limits[1] = 9;
        }

        if(column <= 2) {
            limits[2] = 0;
            limits[3] = 3;
        } else if(column >= 3 && column <= 5) {
            limits[2] = 3;
            limits[3] = 6;
        } else {
            limits[2] = 6;
            limits[3] = 9;
        }

        return limits;
    }
}
