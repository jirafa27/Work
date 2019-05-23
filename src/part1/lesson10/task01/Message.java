package part1.lesson10.task01;

import java.io.Serializable;

public class Message implements Serializable {
    String to;
    String from;
    String message;

    public Message(String to, String from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }
}
