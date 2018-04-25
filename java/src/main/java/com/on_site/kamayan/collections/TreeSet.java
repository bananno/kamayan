package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;

import java.util.function.Consumer;

public class TreeSet<T extends Comparable<T>> {
    private int size = 0;
    private Node root;

    private class Node {
        final T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public TreeSet<T> add(T object) {
        Node tempNode = new Node(object);
        if (size == 0) {
            root = tempNode;
            size += 1;
        } else {
            int comparison = root.value.compareTo(object);
            if (comparison == -1) {
                root.left = tempNode;
                size += 1;
            } else if (comparison == 1) {
                root.right = tempNode;
                size += 1;
            }
        }
        return this;
    }

    public TreeSet<T> remove(T object) {
        return this;
    }

    public boolean contains(T object) {
        if (size == 0) {
            return false;
        }
        if (root.value.compareTo(object) == 0) {
            return true;
        }
        return false;
    }

    public TreeSet<T> each(Consumer<T> block) {
        throw Kamayan.todo(
        );
    }
}
