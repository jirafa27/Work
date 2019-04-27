package lesson06.task01;

import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        ArrayList<Word> words = new ArrayList<>();
        String s;
        while ((s=reader.readLine())!=null)
        {
            String[] str = s.split(" ");
            for (int i=0;i<str.length;i++) {
                String word = str[i].toLowerCase().replaceAll("[^а-я]", "");
                if (word.equals(""))
                    continue;
                int j;
                for (j = 0; j < words.size(); j++)
                    if (word.equals(words.get(j).name))
                    {
                        words.get(j).count++;
                        break;
                    }
                    if (j==words.size())
                        words.add(new Word(word));
            }

        }
        reader.close();
        Word[] arrWords = new Word[words.size()];
        quickSort(words.toArray(arrWords), 0, words.size()-1);
        FileWriter out = new FileWriter("output.txt");
        for (int i=0;i<arrWords.length; i++)
        {
            out.write(arrWords[i].toString()+"\n");
        }
        out.close();
    }


    public static void quickSort(Word arr[], int l, int r)
    {
        if (l>=r)
            return;
        int m = partition(arr, l, r);

        quickSort(arr, l, m-1);
        quickSort(arr, m+1, r);

    }
    public static int partition (Word[] arr, int l, int r)
    {
        Word x = arr[l];
        int j = l;
        for (int i=l+1; i<=r; i++) {

            if (arr[i].compare(x) < 0) {
                j = j + 1;
                Word buff = arr[i];
                arr[i] = arr[j];
                arr[j] = buff;

            }
        }
        Word buff = arr[l];
        arr[l] = arr[j];
        arr[j]  = buff;
        return j;
    }
}
class Word{
    String name;
    int count;

    public Word(String name) {
        this.name = name;
        this.count = 1;
    }
    public int compare(Word w)
    {
        return -w.name.compareTo(name);
    }

    @Override
    public String toString() {
        return name+" " + count;
    }
}
