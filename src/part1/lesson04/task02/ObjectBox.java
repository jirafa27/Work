package part1.lesson04.task02;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Метод для работы с объектами
 */
public class ObjectBox {
    public Set<Object> set = new LinkedHashSet<>();

    /**
     *
     * @param o - объект для добавления
     */
    public void addObject(Object o)
    {
        set.add(o);
    }

    /**
     *
     * @param o - объект для удаления
     */
    public void deleteObject(Object o)
    {
        set.remove(o);
    }

    /**
     *
     * @return строку из объектов
     */
    public String dump()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object number: set)
        {
            stringBuilder.append(number.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
