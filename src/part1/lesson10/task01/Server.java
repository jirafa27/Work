package part1.lesson10.task01;

import java.io.*;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    static List<ServerThread> list = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4004);//Создаем новый serverSocket
        try {
            while (true) {
                list.add(new ServerThread(serverSocket.accept()));//Добавляем все подключающиеся клиенты в список ServerThread-ов
            }
        }
        finally {
            serverSocket.close();
        }
    }
}
