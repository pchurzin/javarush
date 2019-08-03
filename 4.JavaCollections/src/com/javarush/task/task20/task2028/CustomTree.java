package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String>
        implements Cloneable, Serializable {

    Entry<String> root = new Entry<>("0");
    private int size = 0;

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(((CustomTree) list).size());
        ((CustomTree) list).remove("15");
        System.out.println(((CustomTree) list).size());

        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public boolean add(String s) {
        if (root == null) {
            root = new Entry<>(s);
            size = 1;
            return true;
        }

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
//            entry.checkChildren();
            if (entry.leftChild == null) {
                entry.leftChild = new Entry<>(s);
                size++;
                return true;
            } else {
                queue.add(entry.leftChild);
            }
            if (entry.rightChild == null) {
                entry.rightChild = new Entry<>(s);
                size++;
                return true;
            } else {
                queue.add(entry.rightChild);
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (root == null) {
            return false;
        }
        if (root.elementName.equals(o)) {
            root = null;
            size = 0;
            return  true;
        }

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild != null) {
                if (entry.leftChild.elementName.equals(o)) {
                    entry.leftChild = null;
                    recalculateSize();
                    return true;
                } else {
                    queue.add(entry.leftChild);
                }
            }

            if (entry.rightChild != null) {
                if (entry.rightChild.elementName.equals(o)) {
                    entry.rightChild = null;
                    recalculateSize();
                    return true;
                } else {
                    queue.add(entry.rightChild);
                }
            }
        }
        return false;
    }

    public String getParent(String s) {
        if (root == null) {
            return null;
        }

        if (root.elementName.equals(s)) {
            return null;
        }

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild != null) {
                if (entry.leftChild.elementName.equals(s)) {
                    return entry.elementName;
                } else {
                    queue.add(entry.leftChild);
                }
            }

            if (entry.rightChild != null) {
                if (entry.rightChild.elementName.equals(s)) {
                    return entry.elementName;
                } else {
                    queue.add(entry.rightChild);
                }
            }
        }
        return null;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    private void recalculateSize() {
        int counter = -1;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            counter++;
            if (entry.rightChild != null) {
                queue.add(entry.rightChild);
            }
            if (entry.leftChild != null) {
                queue.add(entry.leftChild);
            }
        }
        size = counter;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            availableToAddRightChildren = rightChild == null;
            availableToAddLeftChildren = leftChild == null;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
