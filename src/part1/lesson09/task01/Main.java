package part1.lesson09.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main (String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path someClassPath = Paths.get("src/part1/lesson09/task01/SomeClass.java");
        try (BufferedWriter writer = Files.newBufferedWriter(someClassPath)) {
            writer.write("package part1.lesson09.task01;\n");
            writer.write("public class SomeClass implements Worker {\n");
            writer.write("public void doWork() {\n");
            String line;
            while (!(line=reader.readLine()).equals("")) {
                writer.write(line);
            }
            writer.write("}\n");
            writer.write("}");

        }
        Path javaFilePath = Paths.get("src/part1/lesson09/task01/SomeClass.java");
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, javaFilePath.toAbsolutePath().toString());
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {javaFilePath.getParent().toUri().toURL() });
        Class<?> cls = Class.forName("part1.lesson09.task01.SomeClass", true, classLoader);
        Worker instance = (Worker) cls.newInstance();
        instance.doWork();
    }
}
