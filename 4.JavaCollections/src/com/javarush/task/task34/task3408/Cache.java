package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Constructor<V> constructor = clazz.getConstructor(key.getClass());
        V obj = constructor.newInstance(key);
        cache.put(key, obj);
        return obj;
    }

    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K)method.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
