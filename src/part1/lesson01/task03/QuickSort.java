package part1.lesson01.task03;

public class QuickSort implements Sort {
    public  Person[] sort(Person[] array) {
        Person[] arr = array;
        quickSort(arr, 0, array.length-1);
        return arr;
    }
    public void quickSort(Person arr[], int l, int r)
    {
        if (l>=r)
            return;
        int m = partition(arr, l, r);

        quickSort(arr, l, m-1);
        quickSort(arr, m+1, r);

    }
    public int partition (Person[] arr, int l, int r)
    {
        Person x = arr[l];
        int j = l;
        for (int i=l+1; i<=r; i++) {
            if (Person.compare(arr[i], x)==0)
                try {
                    throw new EqualsNameAndAge();
                } catch (EqualsNameAndAge equalsNameAndAge) {
                    System.out.println("Two equal person");
                }
            if (Person.compare(arr[i], x) < 0) {
                j = j + 1;
                Person buff = arr[i];
                arr[i] = arr[j];
                arr[j] = buff;

            }
        }
        Person buff = arr[l];
            arr[l] = arr[j];
            arr[j]  = buff;
        return j;
    }
}
