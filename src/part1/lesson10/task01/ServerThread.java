package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    BufferedReader in;//Прочитываем то, что отправляет клиент
    BufferedWriter out;//Отправляем что-то клиенту
    public ServerThread (Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.start();//Запускаем поток прямо в конструкторе
    }
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();//По идее должно считываться то, что вводит клиент на стороне клиента
                                     // с клавиатуры, но там сплошные null(((
                if(word.equals("stop")) {
                    break;
                }
                for (ServerThread vr : Server.list) {//По идее должно отправляться всем клиентам, кроме того, кто ввел
                    if (vr!=this) {
                        vr.out.write(": " + word);
                        vr.out.newLine();
                        vr.out.flush();
                    }

                }
            }

        } catch (IOException e) {
        }
    }

}
