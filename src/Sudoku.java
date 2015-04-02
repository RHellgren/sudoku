import java.io.IOException;
import java.util.Scanner;

/**
 * Created by rhellgren on 10/03/15.
 */
public class Sudoku {
    private static SudokuGrid sudokuGrid;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter file path: ");
        String filePath = in.nextLine();

        ReadSudoku reader = new ReadSudoku(filePath);
        sudokuGrid = reader.getSudokuGrid();

        System.out.println("Läst in sudoku");
        System.out.println("3:" + sudokuGrid.getFirstValue(0, 2));
        System.out.println("5:" + sudokuGrid.getFirstValue(7, 4));
        System.out.println("tom:" + sudokuGrid.getFirstValue(5, 8));
    }
}
