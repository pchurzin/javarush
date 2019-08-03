package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        int keyHash = key == null ? 0 : key.hashCode();
        int valueHash = value == null ? 0 : value.hashCode();
        result = 37 * result + keyHash;
        result = 37 * result + valueHash;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (key == null || value == null) {
            return false;
        }
        Entry other = (Entry)obj;
        return key.equals(other.key) && value.equals(other.value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
