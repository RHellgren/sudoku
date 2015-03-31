import java.io.IOException;
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
        System.out.println("3:" + sudoku.getFirstValue(0, 2));
        System.out.println("5:" + sudoku.getFirstValue(7, 4));
        System.out.println("tom:" + sudoku.getFirstValue(5, 8));
    }
}
