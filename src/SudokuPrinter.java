/**
 * Created by rhellgren on 02/04/15.
 */
public class SudokuPrinter {
    SudokuPrinter() {}

    public void print(SudokuGrid sudokuGrid) {
        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++) {
                if(sudokuGrid.isFinal(row, column))
                    System.out.print(sudokuGrid.getFirstValue(row, column));
                else
                    System.out.print(" ");

                if(((row+1) % 3) == 0 && (column+1) == 9 && (row+1) != 9) {
                    System.out.println("");
                    System.out.println("---+---+---");
                } else if((column+1) == 9) {
                    System.out.println("");
                } else if(((column+1) % 3) == 0) {
                    System.out.print("|");
                }
            }
    }
}
