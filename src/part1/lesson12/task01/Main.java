package part1.lesson12.task01;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Object> obj = new ArrayList<>();
        for (int i = 0; i < 1000000000; i++) {
            if (i%3==0)
                obj.add(new Object());
        }
    }
}

