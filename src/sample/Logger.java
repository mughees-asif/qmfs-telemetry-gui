package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Logger {

    /*<--------------------> Main layout - BorderPane <--------------------> */
    // TODO: Change file loader to CSV format
    /*<--------------------------------------------------------------------> */
    static void log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
        out.write(message);
        out.close();

    }
}