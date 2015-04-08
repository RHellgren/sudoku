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

        SudokuSolver solver = new SudokuSolver();

        SudokuPrinter printer = new SudokuPrinter();
        SudokuToFile toFile = new SudokuToFile();

        solver.solve(sudokuGrid);

        printer.print(sudokuGrid);
        toFile.writeFile(sudokuGrid, filePath);
    }
}
