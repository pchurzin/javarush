package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader(Solution.class.getClassLoader());
        File sourceFolder = new File(packageName);

        for (File file : sourceFolder.listFiles()) {
//            System.out.println(file);
            Class clazz = null;
            try {
                clazz = loader.load(file);
            } catch (IOException ignored) {
                continue;
            }
            if (clazz != null && HiddenClass.class.isAssignableFrom(clazz)) {
                hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for (Class clazz : hiddenClasses) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    Constructor<HiddenClass> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private class MyClassLoader extends ClassLoader {
        public MyClassLoader() {}

        protected MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        public Class<?> load(File path) throws IOException {
            if (!path.isFile() || !path.getName().endsWith(".class")) {
                return null;
            }
            return defineClass(null, Files.readAllBytes(path.toPath()), 0, (int) path.length());
        }

    }
}

