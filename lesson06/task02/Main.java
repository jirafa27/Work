package lesson06.task02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] strings = new String[]{"111", "222"};
        getFiles("C:/dev/projects/getJavaJob/HomeTasks/src/lesson06/task02/", 10, 400, strings, 2);
    }
    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        for (int i=0;i<n; i++)
        {
            createFile(path, (i+1)+".txt", size, words, probability);
        }
    }
    public static void createFile(String path, String fileName, int size, String[] words, int probability) throws IOException {
        int fSize = size;
        Random random = new Random();
        FileOutputStream out = new FileOutputStream(new File(path+fileName));
        int textLength = random.nextInt(20) + 1;
        while (fSize>0) {
            int sentenceLength = random.nextInt(15) + 1;
            String sentence = "";
            StringBuilder sent = new StringBuilder("");
            int placeToPaste = -1;
            if (random.nextInt(probability) + 1 == probability) {
                placeToPaste = random.nextInt(sentenceLength);
            }
            for (int i = 0; i < sentenceLength; i++) {
                String word;
                if (i == placeToPaste) {
                    word = words[random.nextInt(words.length)];
                } else {
                    int wordLength = random.nextInt(15) + 1;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < wordLength; j++) {
                        stringBuilder.append((char) (random.nextInt(26) + 97));
                    }
                    word = stringBuilder.toString();
                    if (i == 0)
                        word = word.substring(0, 1).toUpperCase() + word.substring(1);
                }
                sent.append(" " + word);
            }
            int r = random.nextInt(4);
            if (r == 0)
                sent.append('.');
            if (r == 1)
                sent.append('!');
            if (r == 2)
                sent.append('?');
            sent.append(" ");
            sentence = sent.toString();
            fSize-=sentence.length();
            out.write(sentence.getBytes());
            out.write("\n".getBytes());

        }
        System.out.println(fSize);

    }
}
