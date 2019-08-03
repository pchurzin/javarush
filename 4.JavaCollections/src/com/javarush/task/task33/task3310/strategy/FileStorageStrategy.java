package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10_000L;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
        for (FileBucket bucket : table) {
            if (bucket == null) {
                continue;
            }
            if (bucket.getFileSize() > bucketSizeLimit) {
                resize(table.length << 1);
                return;
            }
        }
    }

    @Override
    public boolean containsKey(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        FileBucket bucket = table[index];
        if (bucket == null) {
            return false;
        }

        Entry current = bucket.getEntry();

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket bucket : table) {
            if (bucket == null) {
                continue;
            }

            Entry current = bucket.getEntry();

            while (current != null) {
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
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        if (table[index] == null) {
            table[index] = new FileBucket();
        }
        addEntry(table[index], hash, key, value);
    }


    @Override
    public Long getKey(String value) {
        for (FileBucket bucket : table) {
            if (bucket == null) {
                continue;
            }
            Entry current = bucket.getEntry();
            while (current != null) {
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
        FileBucket bucket = getBucket(key);
        if (bucket == null) {
            return null;
        }
        Entry current = bucket.getEntry();

        while (current != null) {
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

    private FileBucket getBucket(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        return table[index];
    }

    private void resize(int newCapacity) {
        /*if (newCapacity < size) {
            newCapacity = size;
        }*/

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
    }

    private void transfer(FileBucket[] newTable) {
        Entry[] entries = new Entry[newTable.length];

        for (FileBucket bucket : table) {
            if (bucket == null) {
                continue;
            }

            Entry current = bucket.getEntry();
            while (current != null) {
                Entry next = current.next;
                current.next = null;
                int newIndex = indexFor(current.hash, newTable.length);
                if (entries[newIndex] == null) {
                    entries[newIndex] = current;
                    current = next;
                    continue;
                }
                Entry newTableCurrent = entries[newIndex];
                while (newTableCurrent.next != null) {
                    newTableCurrent = newTableCurrent.next;
                }
                newTableCurrent.next = current;
                current = next;
            }
        }

        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                continue;
            }
            newTable[i] = new FileBucket();
            newTable[i].putEntry(entries[i]);
        }

        for (FileBucket bucket : table) {
            if (bucket == null) {
                continue;
            }
            bucket.remove();
        }

        table = newTable;
    }

    private void addEntry(FileBucket bucket, int hash, Long key, String value) {
        Entry current = bucket.getEntry();
        if (current == null) {
            current = new Entry(hash, key, value, null);
            bucket.putEntry(current);
            size++;
            if (bucket.getFileSize() > bucketSizeLimit) {
                resize(table.length << 1);
            }
            return;
        }
        Entry firstEntry = current;
        while (current.next != null) {
            if (current.hash == hash && current.key.equals(key)) {
                current.value = value;
                bucket.putEntry(firstEntry);
                if (bucket.getFileSize() > bucketSizeLimit) {
                    resize(table.length << 1);
                }
                return;
            }
            current = current.next;
        }
        current.next = new Entry(hash, key, value, null);
        bucket.putEntry(firstEntry);
        size++;
        if (bucket.getFileSize() > bucketSizeLimit) {
            resize(table.length << 1);
        }
    }
}
