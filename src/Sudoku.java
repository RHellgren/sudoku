import java.util.ArrayList;

/**
 * Created by rhellgren on 30/03/15.
 */
public class Sudoku {
    private final ArrayList[][] sudoku;
    private final boolean[][] finalValue;

    Sudoku() {
        sudoku = new ArrayList[9][9];
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                sudoku[i][j] = new ArrayList();

        finalValue = new boolean[9][9];
    }

    public void setValues(int row, int column, ArrayList value) {
        sudoku[row][column] = value;
    }

    public ArrayList getValues(int row, int column) {
        return sudoku[row][column];
    }

    public void setFinal(int row, int column, boolean value) {
        finalValue[row][column] = value;
    }

    public boolean isFinal(int row, int column) {
        return finalValue[row][column];
    }
}
