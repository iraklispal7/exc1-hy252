package src.a12;

import java.util.Scanner;
import java.util.HashSet;

public class MagicSquareChecker {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number between 2 and 10 to set the size of an N x N array:");
        int n = in.nextInt();
        int [] [] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Please fill the number for the position: " + i + "," + j + " of the matrix");
                int number = in.nextInt();
                matrix[i][j] = number;
            }
        }
        for (int i = 0; i < n * 5 + n + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("|    " + matrix[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < n * 5 + n + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        if(checkIsMagic(matrix)) {
            int magicNumber = getMagicNumber(matrix);
            System.out.println("Square is magic and the magic element is: " + magicNumber);
        }
    }

    private static int getMagicNumber(int[][] s) {
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            sum += s[0][i];
        }
        return sum;
    }

    private static boolean checkIsMagic(int[][] s) {
        int n = s.length;
        if (n == 0) return false;
        int magicSum = sumOfRow(s, 0);
        for (int i = 1; i < n; i++) {
            if (sumOfRow(s, i) != magicSum) {
                System.out.println("Matrix is not a magic square: Sum of row " + i + " is different than  possibly magic number");
                return false;
            }
        }
        for (int j = 0; j < n; j++) {
            if (sumOfColumn(s, j) != magicSum) {
                System.out.println("Matrix is not a magic square: Sum of column " + j + " is different than  possibly magic number");
                return false;
            }
        }
        if (hasDuplicates(s)) {
            System.out.println("Matrix is not a magic square: Duplicate values found.");
            return false;
        }

        if (sumOfDiagonal1(s) != magicSum) {
            System.out.println("Matrix is not a magic square: Main diagonal sum is incorrect.");
            return false;
        }

        if (sumOfDiagonal2(s) != magicSum) {
            System.out.println("Matrix is not a magic square: Secondary diagonal sum is incorrect.");
            return false;
        }

        return true;
    }

    private static int sumOfRow(int [][] s, int k) {
        int sum = 0;
        for (int i = 0; i < s.length; i++ ) {
            sum += s[k][i];
        }
        return sum;
    }

    private static int sumOfColumn(int [][] s, int k) {
        int sum = 0;
        for (int j = 0; j < s.length; j++ ) {
            sum += s[j][k];
        }
        return sum;
    }
    private static int sumOfDiagonal1(int [][] s) {
        int sum = 0;
        for (int i = 0; i < s.length; i++ ) {
            sum += s[i][i];
        }
        return sum;
    }

    private static int sumOfDiagonal2(int [][] s) {
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            sum += s[i][s.length - 1 - i];
        }
        return sum;
    }

    private static boolean hasDuplicates(int[][] s) {
        HashSet<Integer> seenNumbers = new HashSet<>();
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (seenNumbers.contains(s[i][j])) {
                    return true;
                }
                seenNumbers.add(s[i][j]);
            }
        }
        return false;
    }
}
