/**
 * Created by Зайцев Алексей on 13.04.2017.
 */

package lesson6;

import java.io.*;
import java.net.*;

public class Listener implements Runnable {
    final private String EXIT_COMMAND = "EXIT";
    final private String DISCONNECTED = " DISCONNECTED";

    BufferedReader reader;
    Socket socket;
    String message_in;
    String hostname;

    Listener(Socket clientSocket, String hostname) {
        try {
            this.hostname = hostname;
            socket = clientSocket;
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            do {
                message_in = reader.readLine();
                System.out.println(hostname + ": " + message_in);
            } while (!message_in.equalsIgnoreCase(EXIT_COMMAND));
            socket.close();
            System.out.println(hostname.toUpperCase() + DISCONNECTED);
            System.exit(0);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
