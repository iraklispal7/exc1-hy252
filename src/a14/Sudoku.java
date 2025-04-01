package src.a14;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author iraklis
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class Sudoku {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    private static final int NO_VALUE = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private static int[][] board = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 0, 0, 0, 0, 0},
            {3, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter how mane valid puzzles you want to be appeared \nN = ");
        int N = in.nextInt();
        System.out.println(N);
        System.out.println("Please enter how many gaps cells eatch one of them must have\nX = ");
        int X = in.nextInt();
        System.out.println(X);
        Nvalid(N,X);
    }

    private static void printBoard() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static  boolean solve(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static  boolean isValid(int[][] board, int row, int column) {
        return rowConstraint(board, row) &&
                columnConstraint(board, column) &&
                subsectionConstraint(board, row, column);
    }

    private static boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private static boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private static boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private static  boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean  isValidBoard(int[][] brd) {

        for (int row = 0; row < brd.length; row++){

            for (int col = 0; col < brd[row].length; col++) {

                int num = brd[row][col];

                for (int otherCol = col + 1; otherCol < brd.length; otherCol++) {

                    if (num == brd[row][otherCol] && num != 0 && brd[row][otherCol] != 0){
                        return false;
                    }
                }
            }
        }

        for (int col = 0; col < brd.length; col++){

            for (int row = 0; row < brd[col].length; row++) {

                int num = brd[row][col];


                for (int otherRow = row + 1; otherRow < brd.length; otherRow++) {

                    if (num == brd[otherRow][col] && num != 0 && brd[otherRow][col] != 0){
                        return false;
                    }
                }
            }
        }

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                int num = brd[i][j];
                if (set.contains(num) && num != 0) {
                    return false;
                }
                set.add(num);
            }

        }
        set.removeAll(set);

        for (int i = 0; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                int num = brd[i][j];
                if (set.contains(num) && num != 0) {
                    return false;
                }
                set.add(num);
            }

        }
        set.removeAll(set);
        for (int i = 0; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                int num = brd[i][j];
                if (set.contains(num) && num != 0) {
                    return false;
                }
                set.add(num);
            }

        }
        set.removeAll(set);
        return true;
    }

    private static boolean isSolvableBoard(int[][] brd) {

        if((isValidBoard(brd) == true  && solve(brd) == true)  ){
            return true;
        }
        else
            return false;
    }

    private static int[][] createBoard(int X) {

        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int inputNumbers = 81 - X ;

        for(int i = 0 ; i < inputNumbers ; i ++) {

            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(MAX_VALUE-MIN_VALUE+1) + MIN_VALUE;

            int randomi = randomGenerator.nextInt(9);
            int randomj = randomGenerator.nextInt(9);

            if( board [randomi][randomj] == 0)
                board [randomi][randomj] = randomInt;
            else
                i--;
        }
        return board;
    }

    private static void Nvalid(int N,int X) {

        int board [][];
        int boards = 1;
        int invalid = 0;
        int unsolved = 0;

        long start = System.currentTimeMillis();
        for(int i = 0; i < N ; i++ ) {


            board = createBoard(X);

            if(!isValidBoard(board)){
                invalid++;
                i--;
            }
            else{
                System.out.println("board #" + boards);
                for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
                    for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                        System.out.print(board[row][column] + " ");
                    }
                    System.out.println();
                }
                if(!solve(board))
                    unsolved++;
                System.out.println("Solution of the board #" + boards);
                for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
                    for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                        System.out.print(board[row][column] + " ");
                    }
                    System.out.println();
                }
                boards++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Empty cells per board   : " + X);
        System.out.println("Valid boards created    : " + N);
        System.out.println("Invalid boards created   : " + invalid);
        System.out.println("Unsolvable boards created: " + unsolved);
        System.out.println ("Elapsed time in seconds :" + (end - start) / 1000F);
    }
}
