package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataReader {

    public static void main(String[] args) throws IOException {

        String fileName = "C:/Users/fluxw/OneDrive/Desktop/TelemetrySystem_FS/input.txt";

        try (InputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            br.lines().forEach(System.out::println);

            System.out.println(new String(Files.readAllBytes(Paths.get("C:/Users/fluxw/OneDrive/Desktop/TelemetrySystem_FS/input.txt"))));
        }
    }
}