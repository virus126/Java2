/**
 * Created by Зайцев Алексей on 13.04.2017.
 */

package lesson6;

import java.net.Socket;

class MyClient {

    final private String SERVER_ADDR = "localhost"; // or "127.0.0.1"
    final private int SERVER_PORT = 2048;
    final private String CONNECTION_START = "CONNECTION TO SERVER ESTABLISHED";
    final private String CONNECTION_CLOSED = "CONNECTION CLOSED";

    Socket socket;
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void go() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println(CONNECTION_START);
            Thread listener = new Thread(new Listener(socket, "Server"));
            Thread writer = new Thread(new Writer(socket));
            listener.start();
            writer.start();
            writer.join();
            socket.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECTION_CLOSED);
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        new MyClient().go();
    }
}