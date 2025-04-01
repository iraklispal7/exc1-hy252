package src.a13;

import src.a12.MagicSquareChecker;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MagicSquareCheckerFile {
    public static void main(String[] args) {
        List<Integer> matrixList = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filepath = file.getAbsolutePath();
            System.out.println("The path of the selected file is: " + filepath);
            try (Scanner myscan = new Scanner(file)) {
                while (myscan.hasNextLine()) {
                    String line = myscan.nextLine().trim();
                    String[] numbers = line.split(",");
                    for (String num : numbers) {
                        try {
                            int value = Integer.parseInt(num.trim());
                            matrixList.add(value);
                        } catch (NumberFormatException e) {
                            System.out.println("The formatting of the file is not appropriate.");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        }
        int size = (int) Math.sqrt(matrixList.size());

        // Check if it's a perfect square, otherwise handle accordingly
        if (size * size != matrixList.size()) {
            System.out.println("The number of elements is not a perfect square.");
            return;
        }

        int[][] matrix2D = new int[size][size];
        for (int i = 0; i < matrixList.size(); i++) {
            int row = i / size;
            int col = i % size;
            matrix2D[row][col] = matrixList.get(i);
        }
        if(MagicSquareChecker.checkIsMagic(matrix2D)) {
            int magicNumber = MagicSquareChecker.getMagicNumber(matrix2D);
            MagicSquareChecker.printSquare(matrix2D);
            System.out.println();
            System.out.println("Square is magic and the magic element is: " + magicNumber);
        }
        else {
            return;
        }
    }
}
