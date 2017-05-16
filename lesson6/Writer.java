/**
 * Created by Зайцев Алексей on 13.04.2017.
 */

package lesson6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Writer implements Runnable {
    final private String EXIT_COMMAND = "EXIT";

    Socket socket;
    Scanner scanner;
    PrintWriter writer;

    Writer(Socket socket) {
        try {
            this.socket = socket;
            writer = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (Exception ex) {}
    }

    public void run() {
        String message_out;
        do {
            message_out = scanner.nextLine();
            writer.println(message_out);
            writer.flush();
        } while (!message_out.equalsIgnoreCase(EXIT_COMMAND));
    }
}
