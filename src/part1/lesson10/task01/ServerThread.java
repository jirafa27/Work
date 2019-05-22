package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    BufferedReader in;//Прочитываем то, что отправляет клиент
    BufferedWriter out;//Отправляем что-то клиенту
    String name;
    public ServerThread (Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        name = in.readLine();
        start();//Запускаем поток прямо в конструкторе
    }
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                if(word.equals("quit")) {
                    break;
                }
                if (!word.contains("$to "))
                for (ServerThread vr : Server.list) {//По идее должно отправляться всем клиентам, кроме того, кто ввел
                    if (vr != this) {
                        vr.out.write(name + ": " + word);
                        vr.out.newLine();
                        vr.out.flush();
                    }
                }
                 else
                     for (ServerThread serverThread: Server.list) {
                         if (word.contains("$to "+ serverThread.name))
                         {
                             serverThread.out.write("Личное сообщение от " + name+": " + word.substring(4+serverThread.name.length()));
                             serverThread.out.newLine();
                             serverThread.out.flush();
                         }
                     }
                }
            } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
