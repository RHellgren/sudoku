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
        int square = this.getSquare(row, column);
        int upperLim = square + 3;
        for(int squareRow = square; squareRow < upperLim; squareRow++)
            for (int squareColumn = square; squareColumn < upperLim; squareColumn++)
                if(sudoku[squareRow][squareColumn].indexOf(value) != -1)
                    sudoku[squareRow][squareColumn].remove(sudoku[squareRow][squareColumn].indexOf(value));
    }

    private int getSquare(int row, int column) {
        int square = 0;
        if (row > 4 && row < 7)
            square += 1;
        else
            square += 2;

        if(column > 4 && column < 7)
            square += 1;
        else
            square += 2;

        return square;
    }
}
