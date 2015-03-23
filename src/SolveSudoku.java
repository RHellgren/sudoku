import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rhellgren on 10/03/15.
 */
public class SolveSudoku {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter file path: ");
        String filePath = in.nextLine();

        ReadSudoku reader = new ReadSudoku(filePath);
        ArrayList<Integer>[][] sudoku = reader.getSudokuGrid();

        System.out.println("Läst in sudoku");
        System.out.println("5: " + sudoku[7][4].get(0));
        System.out.println("3: " + sudoku[0][2]);
        System.out.println("tom: " + sudoku[0][5]);
    }
}
