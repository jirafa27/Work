package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class Client {
    static BufferedReader in;
    static BufferedReader reader;//переменная для считывания с клавиатуры
    static BufferedWriter out;
    static String name;
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 4004);
             reader = new BufferedReader(new InputStreamReader(System.in));
             out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Введите Ваше имя");
        name = reader.readLine();
        out.write(name);
        out.newLine();
        out.flush();
        System.out.println("Чтобы отправить сообщение конкретному пользователю введите '$to'+ имя_пользователя");
             new WriteMsg().start();//Запускаем поток ввода с клавиатуры
             new ReadMsg().start();//Запускаем поток считывание того, что приходит из ServerThread
        Thread.currentThread().join();
        socket.close();
    }
    private static class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();//Считываем сообщение с ServerThread
                    System.out.println(str);
                    if (str.equals("quit")) {
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
                    if (userWord.equals("quit"))
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
