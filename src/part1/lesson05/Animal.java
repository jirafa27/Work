package part1.lesson05;

import java.util.*;

public class Animal {
    static int count = 0;
    int id;
    String name;
    Person owner;
    int weight;
    //TODO отделить Animal от картотеки животных
    static Set<Animal> set = new LinkedHashSet<>();

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
    public static void main (String[] args)
    {
        ArrayList<Person> persons = new ArrayList<>();
        for (int i=0; i<5; i++)
            persons.add(new Person("p"+i, i+20, Sex.Male));
        for (int i=0;i<50; i++)
        {
            Random random = new Random();
            int nameLength = random.nextInt()%10 + 10;
            String name = (char) (random.nextInt(26)+65)+"";
            StringBuilder stringBuilder = new StringBuilder(name);
            for (int j=1;j<nameLength; j++)
            {
                stringBuilder.append((char) (random.nextInt(26)+97));
            }
            set.add(new Animal(stringBuilder.toString(), persons.get(random.nextInt(5)), random.nextInt(10)+10));
        }
        sort();
        for (Animal animal: set)
            System.out.println(animal);

    }

    @Override
    public String toString() {
        return owner+" " + name + " " + weight;
    }

    /**
     * Если имя, хозяин и вес совпадают, то это одно и то же животное
     *
     */
    @Override
    public boolean equals(Object obj) {
        Animal animal = (Animal) obj;
        if (animal.name.equals(name)&&animal.owner.equals(owner)&&animal.weight==weight)
            return true;
         return false;
    }

    /**
     * Добавление нового животного в set.
     * Отлавливается исключение (такое животное есть в сете)
     *
     */
    public void add(Animal animal)
    {
        try {
            set.add(animal);
        }
        //TODO Set.add не выбрасывает исключений при некникальности.
        catch (Exception e)
        {
            System.out.println("Такое животное уже есть в списке");
        }
    }
    //TODO по заданию поиск должен быть эффективным
    public Animal search (String name)
    {
        for (Animal animal: set)
            if (animal.name.equals(name))
            {
                return animal;
            }
            return null;

    }

    /**
     * Меняет имя, хозяина и вес по id в set
     * @param id идентификатор
     * @param name имя
     * @param owner хозяин
     * @param weight вес
     */
    public void change (int id, String name, Person owner, int weight)
    {
        for (Animal animal: set)
            if (animal.id==id)
            {
                animal.name=name;
                animal.owner = owner;
                animal.weight = weight;
            }

    }

    /**
     * Сравнивает двух животных: сначала хэш-коды хозяинов, потом имена, затем, вес
     */
    //TODO почему хэш-коды?
    //TODO лучше определить Comparable интерфейс
    //TODO использовать Person из первого задания
    int compare(Animal animal)
    {
        if (animal.owner.hashCode()>owner.hashCode())
        {
            return 1;
        }
        if (animal.owner.hashCode()<owner.hashCode())
        {
            return -1;
        }
        else
            return animal.name.compareTo(name)>0? 1: animal.name.compareTo(name)<0? -1: Integer.compare(animal.weight, weight);
    }

    /**
     * Сортировка вставками
     */
    //TODO Collections.sort() для Comparable
    public static void sort()
    {
        Animal[] animals = set.toArray(Animal[]::new);
        for (int i=0;i<animals.length-1; i++)
            for (int j=i+1; j<animals.length; j++)
                if (animals[i].compare(animals[j])==1)
                {
                    Animal animal = animals[i];
                    animals[i] = animals[j];
                    animals[j] = animal;
                }
                set = new LinkedHashSet<>();
                set.addAll(Arrays.asList(animals));
    }
    @Override
    public int hashCode() {
        return name.hashCode()+owner.hashCode()+weight;
    }

}
