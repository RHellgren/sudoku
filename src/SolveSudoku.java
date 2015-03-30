import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rhellgren on 10/03/15.
 */
public class SolveSudoku {
    private static Sudoku sudoku;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter file path: ");
        String filePath = in.nextLine();

        ReadSudoku reader = new ReadSudoku(filePath);
        sudoku = reader.getSudokuGrid();

        System.out.println("Läst in sudoku");
    }
}
