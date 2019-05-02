package part1.lesson04.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = Arrays.stream(reader.readLine().split("\\s")).collect(Collectors.toList());
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            String s = (String) iterator.next();
            System.out.println(s);
            if (s.equals("Hip"))
            System.out.println("Hop");
        }
    }
}
