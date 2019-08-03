package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        File classDir = new File(pathToAnimals);
        if (!classDir.isDirectory()) {
            return result;
        }

        MyClassLoader loader = new MyClassLoader(Solution.class.getClassLoader());
        for (File file : classDir.listFiles()) {
            Class<?> clazz = null;
            try {
                clazz = loader.load(file);
            } catch (IOException ignore) {
                continue;
            }
            if (clazz == null) {
                continue;
            }
            if (!Animal.class.isAssignableFrom(clazz)) {
                continue;
            }
            try {
                result.add((Animal) clazz.newInstance());
            } catch (Exception ignore) {
                continue;
            }
        }

        return result;
    }

    private static class MyClassLoader extends ClassLoader {
        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        public MyClassLoader() {
        }

        public Class<?> load(File path) throws IOException {
            if (!path.isFile() || !path.getName().endsWith(".class")) {
                return null;
            }
            return defineClass(null, Files.readAllBytes(path.toPath()), 0, (int) path.length());
        }
    }
}
