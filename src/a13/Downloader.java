package src.a13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JOptionPane;

public class Downloader {
    static int download(String address) {
        try {
            URL url = new URL(address);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8"));
            int c = in.read();
            while (c != -1) {
                System.out.print((char) c);
                c = in.read();
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        return 0;
    }
    public static void main(String[] a) {
        String toDownload =
                JOptionPane.showInputDialog("Δώστε την διεύθυνση ","");
        System.out.println(toDownload);
        download(toDownload);
    }
}