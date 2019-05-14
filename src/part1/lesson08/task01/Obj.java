package part1.lesson08.task01;

import java.io.Serializable;

public class Obj implements Serializable {
    String s;
    int a;
    double[] b;
    Obj2 obj2;
    public Obj(String s, int a, double[] b, Obj2 obj2) {
        this.s = s;
        this.a = a;
        this.b = b;
        this.obj2 = obj2;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append(s);
        answer.append(" ").append(a).append(" ");

        for (int i=0;i<b.length; i++)
        {
            answer.append(b[i]).append(" ");
        }
        answer.append(obj2);
        return answer.toString();

    }
}
class Obj2 implements Serializable {
    String s = "Object2";

    @Override
    public String toString() {
        return s;
    }
}
