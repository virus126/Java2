//  Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
//  как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать "Привет", нажать Enter,
//  то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то же самое на серверной стороне,
//  сообщение, соответственно, передаётся клиенту и печатается у него в консоли. Есть одна особенность, которую нужно учитывать:
//  клиент или сервер может написать несколько сообщений подряд, такую ситуацию необходимо корректно обработать.
//
//  ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать следующего клиентов.

/**
 * Created by Зайцев Алексей on 13.04.2017.
 */

package lesson6;

import java.net.*;

class MyServer {

    final private int SERVER_PORT = 2048;
    final private String SERVER_START = "SERVER IS STARTED";
    final private String SERVER_STOP = "SERVER STOPPED";
    final private String CLIENT_JOINED = "CLIENT JOINED";

    ServerSocket server;
    Socket socket;
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void go() {
        try {
            server = new ServerSocket(SERVER_PORT);
            System.out.println(SERVER_START);
            socket = server.accept();
            System.out.println(CLIENT_JOINED);
            Thread listener = new Thread(new Listener(socket, "Client"));
            Thread writer = new Thread(new Writer(socket));
            listener.start();
            writer.start();
            writer.join();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                server.close();
                System.out.println(SERVER_STOP);
                socket.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        new MyServer().go();
    }
}