package part1.lesson01.task03;
import java.util.Random;

public class Main {
    public static void main (String[] args)
    {
        Person[] persons = new Person[10000];
        Random random = new Random();
        for (int i=0;i<persons.length; i++)
        {
            Sex sex;
            if (random.nextInt()%2==0)
            {
                sex = Sex.MAN;
            }
            else
            {
                sex = Sex.WOMAN;
            }
            int nameLength = random.nextInt()%10 + 10;
            String name = (char) (random.nextInt(26)+65)+"";
            StringBuilder stringBuilder = new StringBuilder(name);
            for (int j=1;j<nameLength; j++)
            {
                stringBuilder.append((char) (random.nextInt(26)+97));
            }
            persons[i] = new Person(random.nextInt(101), sex,stringBuilder.toString());
        }
        long startTimeQS = System.currentTimeMillis();
        persons =  new QuickSort().sort(persons);
        long endTimeQS = System.currentTimeMillis();
        long startTimeMS = System.currentTimeMillis();
        persons =  new MergeSort().sort(persons);
        long endTimeMS = System.currentTimeMillis();
        for (int i=0;i<persons.length; i++)
        {
            System.out.println(persons[i]);
        }
        System.out.println("QuickSort time: " + (endTimeQS - startTimeQS));
        System.out.println("MergeSort time: "+(endTimeMS-startTimeMS));


    }

}
