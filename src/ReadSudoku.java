import java.io.*;
import java.util.ArrayList;

/**
 * Created by rhellgren on 10/03/15.
 */
public class ReadSudoku {
    BufferedReader reader;
    private Sudoku sudoku;

    ReadSudoku(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
    }

    public Sudoku getSudokuGrid() throws IOException {
        sudoku = new Sudoku();
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
                        sudoku.setValues(currentRow, currentColumn, current);
                        sudoku.setFinal(currentRow, currentColumn, currentNumber);
                        currentColumn++;
                        break;
                }
            }
            if(currentColumn > 0)
                currentRow++;
        }

        initialCheck(sudoku);

        return sudoku;
    }

    // Removes values not allowed by initial data from user
    private void initialCheck(Sudoku sudoku) {
        for(int row = 0; row < 9; row++)
            for(int column = 0; column < 9; column++)
                if(sudoku.isFinal(row, column)) {
                    sudoku.removeOthers(row, column, sudoku.getFirstValue(row, column));
                }
    }
}