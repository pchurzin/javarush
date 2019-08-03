package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;
//    private int size;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            V recent = list.get(list.size() - 1);
            if (list.size() >= repeatCount) {
                list.remove(0);
            }
            list.add(value);
            map.replace(key, list);
            return recent;
        } else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list.isEmpty()) {
                map.remove(key);
                return null;
            } else {
                V removed = list.remove(0);
                map.replace((K) key, list);
                if (list.isEmpty()) {
                    map.remove(key);
                }
                return removed;
            }
        } else {
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        Collection<V> collection = new ArrayList<>();
        for (List<V> list : map.values()) {
            collection.addAll(list);
        }
        return collection;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}