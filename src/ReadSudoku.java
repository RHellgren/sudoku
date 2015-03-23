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

    public ArrayList<Integer>[][] getSudokuGrid() throws IOException {
        ArrayList<Integer>[][] sudoku = new ArrayList[9][9];
        String line;
        int currentRow = 0;
        int currentColumn;
        ArrayList<Integer> current;
        while ((line = reader.readLine()) != null){
            currentColumn = 0;
            for(int i = 0; i < line.length(); i++){
                switch (line.charAt(i)) {
                    case ' ':
                        current = new ArrayList<Integer>();
                        sudoku[currentRow][currentColumn] = current;
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
                        sudoku[currentRow][currentColumn] = current;
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