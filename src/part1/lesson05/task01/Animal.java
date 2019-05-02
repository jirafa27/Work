package part1.lesson05.task01;

public class Animal implements Comparable<Animal> {
    static int count = 0;
    int id;
    String name;
    Person owner;
    int weight;

    /**
     * Конструктор Animal
     * @param name кличка
     * @param owner объект класса Person
     * @param weight вес живоного
     */
    public Animal(String name, Person owner, int weight) {
        this.id = count++;
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return owner+" Animal: " + name + " " + weight;
    }

    /**
     * Если имя, хозяин и вес совпадают, то это одно и то же животное
     *
     */
    @Override
    public boolean equals(Object obj) {
        Animal animal = (Animal) obj;
        return animal.name.equals(name) && animal.owner.equals(owner) && animal.weight == weight;
    }


    @Override
    public int hashCode() {
        return name.hashCode()+owner.hashCode()+weight;
    }



    @Override
    public int compareTo(Animal animal) {
        if (Person.compare(owner, animal.owner)==-1)
            return -1;
        if (Person.compare(owner, animal.owner)==1)
            return 1;
        return animal.name.compareTo(name)>0? 1: animal.name.compareTo(name)<0? -1: Integer.compare(animal.weight, weight);

    }
}
