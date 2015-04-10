import java.util.ArrayList;

/**
 * Created by rhellgren on 02/04/15.
 */
public class SudokuSolver {
    SudokuSolver() {}

    public SudokuGrid solve(SudokuGrid sudokuGrid) {
        boolean finished = false;

        int safety = 0;

        ArrayList<int[]> singles;
        ArrayList<Integer> current;

        int method = 0; //0 is find singles, 1 is search in squares

        while(!finished) {
            switch(method) {
                case 1: singles = searchInSquares(sudokuGrid);
                        break;
                default:singles = findSingles(sudokuGrid);
                        break;
            }

            //System.out.println("Singles: "+singles.size()+" Finals: "+sudokuGrid.numFinals());
            if(singles.size() == 0) {
                System.out.println("Switching up strategies");
                if(method == 0) {
                    method = 1;
                } else {
                    method = 0;
                }
            }

            for(int[] single : singles) {
                current = new ArrayList<Integer>();
                int row = single[0];
                int column = single[1];
                int value = single[2];
                current.add(value);
                sudokuGrid.setValues(row, column, current);
                sudokuGrid.setFinal(row, column, value);
                sudokuGrid.removeOthers(row, column, value);
            }

            if(checkIfFinished(sudokuGrid))
                finished = true;

            safety++;
            if(safety > 1000) {
                finished = true;
                System.out.println("Något är knas");
            }
        }

        return sudokuGrid;
    }

    private ArrayList searchInSquares(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for(int i=0; i <=8; i++) {
            singles.addAll(searchInSquare(sudokuGrid, i));
        }

        return singles;
    }

    //square is an int 0-8, where 0 is the top left square and 8 is the bottom right square, going row by row.
    private ArrayList searchInSquare(SudokuGrid sudokuGrid, int square) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        int startColumn, startRow;
        switch(square) {
            case 0: startColumn = 0;
                    startRow    = 0;
                    break;
            case 1: startColumn = 3;
                    startRow    = 0;
                    break;
            case 2: startColumn = 6;
                    startRow    = 0;
                    break;
            case 3: startColumn = 0;
                    startRow    = 3;
                    break;
            case 4: startColumn = 3;
                    startRow    = 3;
                    break;
            case 5: startColumn = 6;
                    startRow    = 3;
                    break;
            case 6: startColumn = 0;
                    startRow    = 6;
                    break;
            case 7: startColumn = 3;
                    startRow    = 6;
                    break;
            case 8: startColumn = 6;
                    startRow    = 6;
                    break;
            default:
                    return singles;
        }

        ArrayList[] numPossibles = new ArrayList[10];
        for(int i=0; i <= 9; i++) {
            numPossibles[i] = new ArrayList<int[]>();
        }

        //Every time a number is possible in a cell in the square, add that cell's location to numPossibles[number]
        for (int row = startRow; row < startRow+3; row++) {
            for (int column = startColumn; column < startColumn+3; column++) {
                ArrayList possibilities = sudokuGrid.getValues(row, column);
                for(int i=0; i < possibilities.size(); i++) {
                    int[] location = new int[2];
                    location[0] = row;
                    location[1] = column;
                    numPossibles[(int) possibilities.get(i)].add(location);
                }
            }
        }

        //If a number is only possible in one cell, then place it as final in that cell.
        for (int number = 1; number < 10; number++) {
            if(numPossibles[number].size() == 1) {
                int[] location = (int[]) numPossibles[number].get(0);

                if(!sudokuGrid.isFinal(location[0], location[1])) {
                    int[] single = new int[3];
                    single[0] = location[0];
                    single[1] = location[1];
                    single[2] = number;
                    singles.add(single);
                }
            }
        }

        return singles;
    }

    private ArrayList findSingles(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++)
                if (sudokuGrid.getValues(row, column).size() == 1 && !sudokuGrid.isFinal(row, column)) {
                    int[] single = new int[3];
                    single[0] = row;
                    single[1] = column;
                    single[2] = sudokuGrid.getFirstValue(row, column);
                    singles.add(single);
                }

        return singles;
    }

    private boolean checkIfFinished(SudokuGrid sudokuGrid) {
        boolean finished = true;

        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++)
                if (!sudokuGrid.isFinal(row, column))
                    finished = false;

        return finished;
    }

    private ArrayList checkSquares(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singledOut = new ArrayList<int[]>();


        return singledOut;
    }
}
