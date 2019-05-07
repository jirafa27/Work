package part1.lesson07.task01;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
    static Map<Integer, BigInteger> map = new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Task> l = new ArrayList<>();
        for (int i=0;i<1000; i++) {

            l.add(new Task(random.nextInt(1000)));
        }
        List<Future<String>> m = executorService.invokeAll(l);
        for (Future f: m)
        {
            System.out.println(f.get());
        }
        executorService.shutdown();

    }
    static class Task implements Callable<String>
    {
        int n;

        public Task(int n) {
            this.n = n;
        }

        @Override
        public String call() throws Exception {
               BigInteger result = BigInteger.ONE;
               for (int i = n; i >= 2; i--) {
                   if (map.containsKey(i)) {
                       result = result.multiply(map.get(i));
                       break;
                   }
                   result = result.multiply(BigInteger.valueOf((long) i));
               }
               map.put(n, result);
               return n+" " + result;

        }
    }

}
