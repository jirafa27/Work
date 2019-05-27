package part1.lesson12.task02;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        File root = new File("src");
        for (int i=0;i<1000000000; i++)
        {
            String newClass = "/part1/lesson12/task02/NewClass" + i + ".java";
            File file = new File(root, newClass);
            String source = "package part1.lesson12.task02;\npublic class NewClass" + i + "{\n}\n";
            Files.write(file.toPath(), source.getBytes(StandardCharsets.UTF_8));
            int k = jc.run(null, null, System.out, file.getPath());
            file.delete();
            if (k == 0) {
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
                Class<?> cls = Class.forName("part1.lesson12.task02.NewClass" + i, false, classLoader);
                File file2 = new File("src/part1/lesson12/task02/NewClass" + i + ".class");
                file2.delete();
            }
        }
    }
}