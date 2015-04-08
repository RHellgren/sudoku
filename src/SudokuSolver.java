import java.util.ArrayList;

/**
 * Created by rhellgren on 02/04/15.
 */
public class SudokuSolver {
    SudokuSolver() {}

    public SudokuGrid solve(SudokuGrid sudokuGrid) {
        boolean finished = false;

        int safety = 0;

        ArrayList<int[]> singles;
        ArrayList<Integer> current;

        while(!finished) {
            singles = findSingles(sudokuGrid);

            for(int[] single : singles) {
                current = new ArrayList<Integer>();
                int row = single[0];
                int column = single[1];
                int value = single[2];
                current.add(value);
                sudokuGrid.setValues(row, column, current);
                sudokuGrid.setFinal(row, column, value);
                sudokuGrid.removeOthers(row, column, value);
            }

            if(checkIfFinished(sudokuGrid))
                finished = true;

            safety++;
            if(safety > 1000) {
                finished = true;
                System.out.println("Något är knas");
            }
        }

        return sudokuGrid;
    }

    private ArrayList findSingles(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++)
                if (sudokuGrid.getValues(row, column).size() == 1) {
                    int[] single = new int[3];
                    single[0] = row;
                    single[1] = column;
                    single[2] = sudokuGrid.getFirstValue(row, column);
                    singles.add(single);
                }

        return singles;
    }

    private boolean checkIfFinished(SudokuGrid sudokuGrid) {
        boolean finished = true;

        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++)
                if (!sudokuGrid.isFinal(row, column))
                    finished = false;

        return finished;
    }
}
