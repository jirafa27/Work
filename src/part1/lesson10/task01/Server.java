package part1.lesson10.task01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    static Map<String, ServerThread> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4004);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = reader.readLine();
                System.out.println(name);
                ServerThread serverThread = new ServerThread(socket);
                map.put(name, serverThread);
                serverThread.start();
            }
        } finally {
            serverSocket.close();
        }
    }
}
