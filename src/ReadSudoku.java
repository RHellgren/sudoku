import java.io.*;
import java.util.ArrayList;

/**
 * Created by rhellgren on 10/03/15.
 */
public class ReadSudoku {
    BufferedReader reader;
    private SudokuGrid sudokuGrid;

    ReadSudoku(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
    }

    public SudokuGrid getSudokuGrid() throws IOException {
        sudokuGrid = new SudokuGrid();
        String line;
        int currentRow = 0;
        int currentColumn;
        ArrayList<Integer> current;
        while ((line = reader.readLine()) != null){
            currentColumn = 0;
            for(int i = 0; i < line.length(); i++){
                switch (line.charAt(i)) {
                    case ' ':
                        currentColumn++;
                        break;
                    case '|':
                        break;
                    case '-':
                        break;
                    case '+':
                        break;
                    default:
                        current = new ArrayList<Integer>();
                        int currentNumber = Character.getNumericValue(line.charAt(i));
                        current.add(currentNumber);
                        sudokuGrid.setValues(currentRow, currentColumn, current);
                        sudokuGrid.setFinal(currentRow, currentColumn, currentNumber);
                        currentColumn++;
                        break;
                }
            }
            if(currentColumn > 0)
                currentRow++;
        }

        initialCheck(sudokuGrid);

        return sudokuGrid;
    }

    // Removes values not allowed by initial data from user
    private void initialCheck(SudokuGrid sudokuGrid) {
        for(int row = 0; row < 9; row++)
            for(int column = 0; column < 9; column++)
                if(sudokuGrid.isFinal(row, column)) {
                    sudokuGrid.removeOthers(row, column, sudokuGrid.getFirstValue(row, column));
                }
    }
}