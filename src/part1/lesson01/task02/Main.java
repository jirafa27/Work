package part1.lesson01.task02;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=0;i<n; i++) {
            int k = random.nextInt(100);
            try {
                if (k < 0) {
                    throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException e)
            {
                continue;
            }
            double q = Math.sqrt(k);
            if( ((int) q )*((int) q)==k)
            {
                System.out.println(k);
            }
        }

    }
}
