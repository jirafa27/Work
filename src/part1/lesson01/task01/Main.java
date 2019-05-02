package part1.lesson01.task01;

public class Main {
    public static void main(String[] args)
    {
        int[] arr = new int [2];
        System.out.println(arr[2]);
        Object a = null;
        System.out.println(a.toString());
        System.out.println("Hello World!");
        throw new ClassCastException();
    }
}
