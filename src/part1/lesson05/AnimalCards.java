package part1.lesson05;

import java.util.*;

public class AnimalCards {
    private static Set<Animal> set = new LinkedHashSet<>();
    private static Map<String, HashSet<Animal>> map = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            persons.add(new Person("p" + i, i + 20, Sex.MAN));
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int nameLength = random.nextInt() % 10 + 10;
            String name = (char) (random.nextInt(26) + 65) + "";
            StringBuilder stringBuilder = new StringBuilder(name);
            for (int j = 1; j < nameLength; j++) {
                stringBuilder.append((char) (random.nextInt(26) + 97));
            }
            Animal animal = new Animal(stringBuilder.toString(), persons.get(random.nextInt(5)), random.nextInt(10) + 10);
            add(animal);
            if (map.containsKey(name))
                map.get(name).add(animal);
            else {
                map.put(name, new HashSet<Animal>());
                map.get(name).add(animal);
            }
        }
        LinkedList<Animal> list = new LinkedList<Animal>(set);
        Collections.sort(list);
        for (Animal animal : list)
            System.out.println(animal);

    }

    public static void add(Animal animal) {

        if (!set.add(animal)) {
            System.out.println("Такое животное уже есть в списке");
        }

    }

    public HashSet<Animal> search(String name) {
        return map.get(name);
    }

    /**
     * Меняет имя, хозяина и вес по id в set
     *
     * @param id     идентификатор
     * @param name   имя
     * @param owner  хозяин
     * @param weight вес
     */
    public void change(int id, String name, Person owner, int weight) {
        for (Animal animal : set)
            if (animal.id == id) {
                animal.name = name;
                animal.owner = owner;
                animal.weight = weight;
            }

    }
}
