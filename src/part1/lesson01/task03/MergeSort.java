package part1.lesson01.task03;

import java.util.ArrayDeque;

public class MergeSort implements Sort {
    @Override
    public  Person[] sort(Person[] a) {

            ArrayDeque<Person[]> queue = new ArrayDeque<>();
            for (int i=0;i<a.length; i++) {
                Person[] arr = new Person[1];
                arr[0] = a[i];
                queue.addFirst(arr);
            }
            while (queue.size()>1)
            {
                Person[] a1 = queue.pop();
                Person[] a2 = queue.pop();
                Person[] arr = new Person [a1.length+a2.length];
                int i=0;
                int j=0;
                int k=0;

                while (i<a1.length&&j<a2.length)
                {
                    if (Person.compare(a1[i], a2[j])==0)
                        try {
                            throw new EqualsNameAndAge();
                        } catch (EqualsNameAndAge equalsNameAndAge) {
                            System.out.println("Two equal person");
                        }
                    if (Person.compare(a1[i],a2[j])<0)
                    {
                        arr[k] = a1[i];
                        i++;
                        k++;
                    }
                    else
                    {
                        arr[k] = a2[j];
                        j++;
                        k++;

                    }
                }
                while (i<a1.length)
                {
                    arr[k] = a1[i];
                    i++;
                    k++;
                }
                while (j<a2.length)
                {
                    arr[k] = a2[j];
                    j++;
                    k++;
                }
                queue.addLast(arr);
            }
            return queue.getFirst();

        }

}
