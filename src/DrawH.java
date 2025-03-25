package src;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DrawH {

    private static void printCharH(int L) {
        for(int i = 0 ; i < L ; i++) {
            System.out.print("*");
            if(i == L/2)
                for(int k = L/2;  k < L - 1; k++) {
                    System.out.print("*");
                }
            else
                for(int j = L/2 ; j < L - 1; j++ ){
                    System.out.print(' ');
                }
            System.out.println("*");
        }
    }

    private static String getCharH(int L) {
        String tmp = "";
        for(int i = 0 ; i < L ; i++) {
            tmp += '*';
            if(i == L/2)
                for(int k = L/2;  k < L - 1; k++) {
                    tmp += '*';
                }
            else
                for(int j = L/2 ; j < L - 1; j++ ){
                    tmp += ' ';
                }
            tmp += '*';
            tmp += '\n';
        }
        return tmp;
    }

    static void drawHgraphics(int L) {
        Frame f = new Frame("Ζωγραφίζοντας το H") {
            @Override
            public void paint (Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.draw(new Line2D.Double(50, 50, 50, 300));
                g2.draw(new Line2D.Double(50,190,260,190));
                g2.draw(new Line2D.Double(260, 300, 260, 50));
                g2.draw(new QuadCurve2D.Double(300, 300, 260, 180, 260, 300));
            }
        };
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose(); // Close the window
            }
        });
        f.setVisible(true);
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a char that should be of the values : c, w, f, g");
        char M = in.next().charAt(0);
        System.out.println("Please enter a number that the value L that should be between [3,20]");
        int L = in.nextInt();
        while (true) {

            if (L < 3 || L > 20) {
                try {
                    throw new IllegalArgumentException("Error: L value must be between 3 and 20.");
                }
                catch (Exception e) {
                    System.out.println("Caught exception: " + e.getMessage());
                    System.exit(0);
                }
            }

            if (M == 'c') {
                printCharH(L);
            }
            else if (M == 'w') {
                String pattern = getCharH(L);
                UIManager.put("OptionPane.messageFont", new Font("Lucida Console", Font.BOLD, 12));
                JOptionPane.showMessageDialog(null,
                        "Ακολουθεί σε αστεράκια το γράμμα Η " + pattern,
                        "Παράθυρο Εξόδου",
                        JOptionPane.INFORMATION_MESSAGE);
                L = Integer.parseInt(JOptionPane.showInputDialog("Give me a number ",4));
            }
            else if (M == 'f') {
                PrintWriter writer;
                try {
                    writer = new PrintWriter("C:\\temp\\H.html", "UTF-8");
                    writer.println("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/>\n" +
                            "</head>\n" +
                            "<body><font size=\"" + L + "\">H with font size = " + L + "</font></body>\n" +
                            "</html>");
                    writer.close();
                } catch (Exception e) {
                    System.out.println("Problem: " + e);
                }
            }
            else if (M == 'g') {
                drawHgraphics(L);
            }
            if(M != 'w') {
                System.out.println("Please enter a number that the value L that should be between [3,20]");
                L = in.nextInt();
            }
        }
    }
}
