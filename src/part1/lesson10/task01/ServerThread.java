package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    String name;

    public ServerThread(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            while (true) {
                Message message = (Message) in.readObject();
                if (message.message.equals("quit")) {
                    break;
                }
                if (message.to.equals("all"))
                    for (String n : Server.map.keySet()) {
                        if (Server.map.get(n) != this) {
                            Server.map.get(n).out.writeObject(message);
                        }
                    }
                else {
                    message.from = "Личное сообщение от " + message.from;
                    Server.map.get(message.to).out.writeObject(message);
                }
            }
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
