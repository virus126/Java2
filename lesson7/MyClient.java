/**
 * Created by Алексей on 17.04.2017.
 */

package lesson7;

import sun.applet.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class MyClient {
    final private String SERVER_ADDR = "localhost"; // or "127.0.0.1"
    final private int SERVER_PORT = 8189;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    AuthWindow aW;
    MainWindow mW;

    MyClient() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            aW = new AuthWindow(this);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public class Listener implements Runnable {
        final private String EXIT_COMMAND = "/exit";

        @Override
        public void run() {
            String message_in;
            try {
                do {
                    message_in = in.readUTF();
                    mW.jTextArea.append(" [" + sdf.format(new Date()) + "] " + message_in + "\n");
                } while (!message_in.equalsIgnoreCase(EXIT_COMMAND));
                socket.close();
                System.exit(0);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e1) {}
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void attemptAuthorization(String login, String password) {
        try {
            sendMsg("/auth " + login + " " + password);
            int answer = in.readInt();
            switch (answer) {
                case 0:
                    try {
                        mW = new MainWindow(this);
                        aW.dispose();
                        Thread listener = new Thread(new Listener());
                        listener.start();
                        break;
                    } catch (Exception ex) {}
                case 1:
                    aW.jTextFieldLogin.setText("Неверный логин");
                    aW.jTextFieldPassword.setText(null);
                    break;
                case 2:
                    aW.jTextFieldPassword.setText("Неверный пароль");
                    break;
                case 3:
                    aW.jTextFieldLogin.setText("Данный логин уже авторизован");
                    aW.jTextFieldPassword.setText(null);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        new MyClient();
    }
}