import java.util.ArrayList;

/**
 * Created by rhellgren on 02/04/15.
 */
public class SudokuSolver {
    SudokuSolver() {}

    public SudokuGrid solve(SudokuGrid sudokuGrid) {
        long startTime = System.nanoTime();

        int givens = sudokuGrid.numFinals();
        int score = 0;
        int method = 1; //0 is find singles, 1 is search in squares, 2 search in rows, 3 search in columns
        boolean finished = false;

        ArrayList<int[]> singles;
        ArrayList<Integer> current;

        int safety = 0;
        while(!finished) {
            switch(method) {
                case 1: singles = searchInSquares(sudokuGrid);
                        break;
                case 2: singles = searchInRows(sudokuGrid);
                        break;
                case 3: singles = searchInColumns(sudokuGrid);
                        break;
                default:singles = findSingles(sudokuGrid);
                        break;
            }

            if(singles.size() == 0) {
                safety++;

                score ++;
                if(method >= 0 && method <= 2) {
                    method++;
                } else {
                    method = 0;
                }
            } else {
                safety = 0;
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

            if(safety >= 4) {
                System.out.println("Något är knas");
                break;
            }
        }
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Finished: "+(finished ? "yes" : "no"));
        System.out.println("Number of strategy switches: "+score);
        System.out.println("Time: "+(estimatedTime/1000)+" microseconds");
        System.out.println("Number of empty cells: "+(81-sudokuGrid.numFinals()));
        System.out.println("Cells filled by algorithm: "+(sudokuGrid.numFinals()-givens));

        double doubleScore = (double) score;
        double finalScore = 7.0;

        if(finished) {
            if(score <= 4) {
                finalScore = doubleScore*1.1 + 1.0;
            } else if(score <= 8) {
                finalScore = 4.0 + doubleScore/3.0;
            }
        } else {
            double additive = 81.0;
            additive -= givens;
            additive = 1 - (sudokuGrid.numFinals()-givens)/additive;
            additive *= 3;

            finalScore += additive;
        }

        System.out.println("\nFinal score: "+finalScore+"\n");

        return sudokuGrid;
    }

    private ArrayList searchInSquares(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for(int i=0; i <= 8; i++) {
            singles.addAll(searchInSquare(sudokuGrid, i));
        }

        return singles;
    }

    //square is an int 0-8, where 0 is the top left square and 8 is the bottom right square, going row by row.
    private ArrayList searchInSquare(SudokuGrid sudokuGrid, int square) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        int startColumn = 3*(square%3);
        int startRow    = 3*(square/3);

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
        for (int number = 1; number <= 9; number++) {
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

    private ArrayList searchInRows(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for(int i=0; i <= 8; i++) {
            singles.addAll(searchInRow(sudokuGrid, i));
        }

        return singles;
    }

    private ArrayList searchInRow(SudokuGrid sudokuGrid, int row) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        ArrayList[] numPossibles = new ArrayList[10];
        for(int i=0; i <= 9; i++) {
            numPossibles[i] = new ArrayList<int[]>();
        }

        //Every time a number is possible in a cell in the row, add that cell's location to numPossibles[number]
        for (int column = 0; column <= 8; column++) {
            ArrayList possibilities = sudokuGrid.getValues(row, column);
            for(int i=0; i < possibilities.size(); i++) {
                int[] location = new int[2];
                location[0] = row;
                location[1] = column;
                numPossibles[(int) possibilities.get(i)].add(location);
            }
        }

        //If a number is only possible in one cell, then place it as final in that cell.
        for (int number = 1; number <= 9; number++) {
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

    private ArrayList searchInColumns(SudokuGrid sudokuGrid) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        for(int i=0; i <= 8; i++) {
            singles.addAll(searchInColumn(sudokuGrid, i));
        }

        return singles;
    }

    private ArrayList searchInColumn(SudokuGrid sudokuGrid, int column) {
        ArrayList<int[]> singles = new ArrayList<int[]>();

        ArrayList[] numPossibles = new ArrayList[10];
        for(int i=0; i <= 9; i++) {
            numPossibles[i] = new ArrayList<int[]>();
        }

        //Every time a number is possible in a cell in the row, add that cell's location to numPossibles[number]
        for (int row = 0; row <= 8; row++) {
            ArrayList possibilities = sudokuGrid.getValues(row, column);
            for(int i=0; i < possibilities.size(); i++) {
                int[] location = new int[2];
                location[0] = row;
                location[1] = column;
                numPossibles[(int) possibilities.get(i)].add(location);
            }
        }

        //If a number is only possible in one cell, then place it as final in that cell.
        for (int number = 1; number <= 9; number++) {
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
