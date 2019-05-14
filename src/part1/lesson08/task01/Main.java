package part1.lesson08.task01;

import java.io.*;

public class Main {
    public static void main (String[]args) throws IOException, ClassNotFoundException {
        Obj o = new Obj("dsfv", 5, new double[]{1,5, 4}, new Obj2());
        serialize(o, "file.txt");
        Obj o2 = (Obj) deSerialize("file.txt");
        System.out.println(o2);

    }
    static void serialize (Object object, String file) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(object);
    }

    static Object deSerialize(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        return objectInputStream.readObject();
    }
}
