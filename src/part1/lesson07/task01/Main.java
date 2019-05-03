package part1.lesson07.task01;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
   volatile static Map<Integer, BigInteger> map = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<1000; i++)
        {
            list.add(random.nextInt(1000));
            executorService.submit(new Task(list.get(i)));
        }
        sleep(1000);
        for (int i=0;i<1000; i++)
            System.out.println(list.get(i)+ " " + map.get(list.get(i)));
        executorService.shutdown();

    }
    static class Task implements Callable<BigInteger>
    {
        int n;

        public Task(int n) {
            this.n = n;
        }

        @Override
        public BigInteger call() throws Exception {
               BigInteger result = BigInteger.ONE;
               for (int i = n; i >= 2; i--) {
                   if (map.containsKey(i)) {
                       result = result.multiply(map.get(i));
                       break;
                   }
                   result = result.multiply(BigInteger.valueOf((long) i));
               }
               map.put(n, result);
               return result;

        }
    }

}
