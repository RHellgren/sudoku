import java.io.*;
import java.util.ArrayList;

/**
 * Created by rhellgren on 10/03/15.
 */
public class ReadSudoku {
    BufferedReader reader;

    ReadSudoku(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
    }

    public Sudoku getSudokuGrid() throws IOException {
        Sudoku sudoku = new Sudoku();
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
                        current.add(Character.getNumericValue(line.charAt(i)));
                        sudoku.setValues(currentRow, currentColumn, current);
                        sudoku.setFinal(currentRow, currentColumn, true);
                        currentColumn++;
                        break;
                }
            }
            if(currentColumn > 0)
                currentRow++;
        }
        return sudoku;
    }
}