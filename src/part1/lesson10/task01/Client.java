package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class Client {
    static BufferedReader in;
    static BufferedReader reader;//переменная для считывания с клавиатуры
    static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4004);
             reader = new BufferedReader(new InputStreamReader(System.in));
             out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             new WriteMsg().start();//Запускаем поток ввода с клавиатуры
             new ReadMsg().start();//Запускаем поток считывание того, что приходит из ServerThread

        socket.close();
    }
    private static class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();//Считываем сообщение с ServerThread
                    if (str.equals("stop")) {
                        break;
                    }
                }
            } catch (IOException e) {

            }
        }
    }
    public static class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = reader.readLine();//Считываем с клавиатуры
                    if (userWord.equals("stop"))
                    {
                        break;
                    }
                    out.write(userWord);//Отправляем ServerThread
                    out.newLine();
                    out.flush();
                } catch (IOException e) {
                }

            }
        }
    }
}
