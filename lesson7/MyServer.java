/**
 * Created by Алексей on 17.04.2017.
 */

package lesson7;

import java.io.IOException;
import java.net.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MyServer {
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    public MyServer() {
        ServerSocket server = null;
        Socket s = null;
        try {
            SQLHandler.connect();
            SQLHandler.fillBase();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидание клиентов...");
            while (true) {
                s = server.accept();
                System.out.println("Клиент подключился");
                ClientHandler h = new ClientHandler(s, this);
                clients.add(h);
                new Thread(h).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                System.out.println("Сервер остановлен");
                s.close();
                SQLHandler.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public void remove(ClientHandler o) {
        clients.remove(o);
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients)
            o.sendMsg(msg);
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public int attemptAuthorization(String login, String password) {
        ResultSet rs = null;
        try {
            rs = SQLHandler.getPasswordByLogin(login);
            if (!rs.next())
                //Неверный логин
                return 1;
            else if (!rs.getString("PASSWORD").equals(password))
                //Неверный пароль
                return 2;
            else
                for (ClientHandler ch : clients)
                    if (ch.getName().equals(login))
                        return 3;
                return 0;
        } catch (Exception ex) {}
        //Неопознанная ошибка
        return -1;
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public int changeNick(String oldNick, String newNick) {
        return SQLHandler.changeNick(oldNick, newNick);
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        new MyServer();
    }
}