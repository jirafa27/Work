package part1.lesson01.task03;

public class Person {
   private int age;
   private Sex sex;
   private String name;

   Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }
    static int compare(Person p1, Person p2)//p1<p2 -1 p1>p2: 1
    {
        return p1.sex==Sex.MAN&&p2.sex==Sex.WOMAN? -1: p1.sex==Sex.WOMAN&&p2.sex==Sex.MAN? 1: p1.age>p2.age? -1: p1.age<p2.age? 1: p1.name.compareTo(p2.name);
    }

    @Override
    public String toString() {
        return sex+ " " + age + " " + name;
    }
}
