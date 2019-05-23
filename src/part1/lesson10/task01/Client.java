package part1.lesson10.task01;

import java.io.*;
import java.net.Socket;

public class Client {
    static ObjectInputStream in;
    static BufferedReader reader;
    static ObjectOutputStream out;
    static String name;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 4004);
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите Ваше имя");
        BufferedWriter nameWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        name = reader.readLine();
        nameWriter.write(name);
        nameWriter.newLine();
        nameWriter.flush();
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Форма отправки сообщений: to all/name: text_message");
        new WriteMsg().start();
        new ReadMsg().start();
        Thread.currentThread().join();
        socket.close();
    }

    private static class ReadMsg extends Thread {
        @Override
        public void run() {
            Message message;
            try {
                while (true) {
                    message = (Message) in.readObject();
                    System.out.println(message.from + ": " + message.message);
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String message;
                try {
                    message = reader.readLine();//Считываем с клавиатуры
                    if (message.equals("exit")) {
                        break;
                    }
                    String to = message.substring(message.indexOf("to") + 3, message.indexOf(':'));
                    out.writeObject(new Message(to, name, message.substring(message.indexOf(':') + 1)));

                } catch (IOException e) {
                }

            }
        }
    }
}
