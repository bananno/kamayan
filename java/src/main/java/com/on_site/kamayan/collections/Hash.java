package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;
import com.on_site.kamayan.Ref;

public class Hash {
    private DoublyLinkedList[] hash;
    private int size;

    private static class Entry {
        private final Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public Hash() {
        this.hash = new DoublyLinkedList[10];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public Hash put(Object key, Object value) {
        //throw Kamayan.todo(
        //);
        if (key == null) {
            throw new NullPointerException("Key cannot be null!");
        }

        int index = hashedIndex(key);
        DoublyLinkedList list = hash[index];

        if (list == null) {
            list = new DoublyLinkedList();
            hash[index] = list;
        }

        Entry entry = find(list, key);

        if (entry == null) {
            list.add(new Entry(key, value));
            size++;
        } else {
            entry.setValue(value);
        }

        return this;
    }

    public Object get(Object key) {
        //throw Kamayan.todo(
        //);
        int index = hashedIndex(key);
        DoublyLinkedList list = hash[index];

        if (list == null) {
            throw new MissingKeyException("Cannot find entry for " + key);
        }

        Entry entry = find(list, key);

        if (entry == null) {
            throw new MissingKeyException("Cannot find entry for " + key);
        }

        return entry.getValue();
    }

    private Entry find(DoublyLinkedList list, Object key) {
        Ref<Entry> entry = new Ref<>();

        list.each((element) -> {
            Entry elementEntry = (Entry) element;

            if (elementEntry.getKey().equals(key)) {
                entry.set(elementEntry);
            }
        });

        return entry.get();
    }

    private int hashedIndex(Object key) {
        return key.hashCode() % hash.length;
    }

    public boolean contains(Object key) {
        throw Kamayan.todo(
        );
    }
}
