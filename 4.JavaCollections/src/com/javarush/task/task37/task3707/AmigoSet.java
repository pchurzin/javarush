package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class AmigoSet<E> extends AbstractSet<E>
        implements Set<E>, Cloneable, Serializable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int)Math.ceil(collection.size() / .75f));
        map = new HashMap<>(capacity);
        addAll(collection);
    }


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean add(E e) {
        boolean result = !map.containsKey(e);
        map.put(e, PRESENT);
        return result;
    }



    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet<E> clonedSet = null;
        try {
            clonedSet = new AmigoSet<>(this);
            clonedSet.map = (HashMap<E, Object>)map.clone();
        } catch (Exception e) {
            throw new InternalError(e);
        }
        return clonedSet;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = map.keySet().contains(o);
        map.remove(o);
        return result;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        stream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        stream.defaultWriteObject();
        stream.writeInt(map.size());
        for (E el : map.keySet()) {
            stream.writeObject(el);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int capacity = stream.readInt();
        float loadFactor = stream.readFloat();
        stream.defaultReadObject();
        map = new HashMap<>(capacity, loadFactor);
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            add((E)stream.readObject());
        }
    }
}


