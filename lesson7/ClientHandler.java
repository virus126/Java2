/**
 * Created by Алексей on 17.04.2017.
 */

package lesson7;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private MyServer owner;
    private Socket s;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;
    private boolean isAuthorized = false;

    public ClientHandler(Socket s, MyServer owner) {
        try {
            this.s = s;
            this.owner = owner;
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            name = "";
        } catch (IOException e) {}
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    @Override
    public void run() {
        String message = null;
        try {
            // Авторизация
            do {
                message = in.readUTF();
                String n[] = message.split(" ");
                int answer = owner.attemptAuthorization(n[1], n[2]);
                if (answer == 0) {
                    name = n[1];
                    isAuthorized = true;
                }
                out.writeInt(answer);
            } while (!isAuthorized);
            // Переписка + команды
            while (true) {
                message = in.readUTF();
                if (message.startsWith("/")) {
                    commandHandler(message);
                } else
                    owner.broadcastMsg(name + ": " + message);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            System.out.println("Output Error");
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        }
        try {
            System.out.println("Клиент " + name + " отключился");
            if (!name.equals("")) {
                owner.broadcastMsg(name + " покинул чат");
                owner.remove(this);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public int commandHandler(String message) {
        try {
            String[] n = message.split(" ");
            switch (n[0]) {
                case "/changenick":
                    if (owner.changeNick(name, n[1]) == 1) {
                        name = n[1];
                        out.writeUTF("Никнейм успешно изменен на " + name);
                    }
                    else
                        out.writeUTF("При изменении никнейма произошла ошибка");
                    break;
                case "/exit":
                    out.writeUTF("/exit");
                    break;
                default:
                    return -1;
            }
        } catch (Exception ex) {}
        return -1;
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {}
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public String getName() {
        return name;
    }
}


