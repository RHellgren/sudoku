import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by rhellgren on 05/04/15.
 */
public class SudokuToFile {
    PrintWriter writer;

    SudokuToFile() {}

    public void writeFile(SudokuGrid sudokuGrid, String filename) throws FileNotFoundException, UnsupportedEncodingException {
        filename+="_solved";
        writer = new PrintWriter(filename, "UTF-8");

        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++) {
                if(sudokuGrid.isFinal(row, column))
                    writer.print(sudokuGrid.getFirstValue(row, column));
                else
                    writer.print(" ");

                if(((row+1) % 3) == 0 && (column+1) == 9 && (row+1) != 9) {
                    writer.println("");
                    writer.println("---+---+---");
                } else if((column+1) == 9) {
                    writer.println("");
                } else if(((column+1) % 3) == 0) {
                    writer.print("|");
                }
            }

        writer.close();
    }
}
