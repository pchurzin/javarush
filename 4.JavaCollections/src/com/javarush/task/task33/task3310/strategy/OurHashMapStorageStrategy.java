package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;


    @Override
    public boolean containsKey(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        Entry current = table[index];
        if (current == null) {
            return false;
        }
        if (current.key.equals(key)) {
            return true;
        }
        while (current.next != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (Entry entry : table) {
            Entry current = entry;
            if (current == null) {
                continue;
            }
            if (current.value.equals(value)) {
                return true;
            }
            while (current.next != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        Entry current = getEntry(key);
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (current == null) {
            createEntry(hash, key, value, index);
            return;
        }
        while (current.next != null) {
            if (current.hash == hash && current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        addEntry(hash, key, value, index);
    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table) {
            Entry current = entry;
            if (current == null) {
                continue;
            }
            if (current.value.equals(value)) {
                return current.key;
            }
            while (current.next != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry current = getEntry(key);
        if (current == null) {
            return null;
        }

        if (current.key.equals(key)) {
            return current.value;
        }

        while (current.next != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    private int hash(Long k) {
        return k == null ? 0 : k.hashCode();
    }

    private int indexFor(int hash, int length) {
        return hash % length;
    }

    private Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        return table[index];
    }

    private void resize(int newCapacity) {
        if (newCapacity < size) {
            newCapacity = size;
        }
        threshold = (int)(newCapacity * loadFactor);
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
    }

    private void transfer(Entry[] newTable) {
        for (Entry entry : table) {
            Entry current = entry;

            while (current != null) {
                Entry next = current.next;
                current.next = null;
                int newIndex = indexFor(current.hash, newTable.length);
                Entry newTableCurrent = newTable[newIndex];
                if (newTableCurrent == null) {
                    newTable[newIndex] = current;
                    current = next;
                    continue;
                }
                while (newTableCurrent.next != null) {
                    newTableCurrent = newTableCurrent.next;
                }
                newTableCurrent.next = current;
                current = next;
            }
        }
        table = newTable;
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (bucketIndex > table.length - 1) {
            return;
        }

        Entry current = table[bucketIndex];
        if (current == null) {
            createEntry(hash, key, value, bucketIndex);
            return;
        }

        while (current.next != null) {
            current = current.next;
        }
        current.next = new Entry(hash, key, value, null);
        if (++size > threshold) {
            resize(table.length << 1);
        }
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        if (bucketIndex > table.length - 1) {
            return;
        }

        table[bucketIndex] = new Entry(hash, key, value, null);
        if (++size > threshold) {
            resize(table.length << 1);
        }
    }
}
