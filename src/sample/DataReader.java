package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("C:/Users/fluxw/OneDrive/Desktop/TelemetrySystem_FS/input.txt"));
        String line = in.readLine();
        while (line != null) {
            System.out.println(line);
            line = in.readLine();
        }

        in.close();
    }
}