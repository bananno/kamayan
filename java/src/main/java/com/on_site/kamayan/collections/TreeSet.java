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
        Node newNode = new Node(object);

        if (size == 0) {
            root = newNode;
            size += 1;
        } else if (!contains(object)) {

            Node tempNode = root;

            while (true) {
                int comparison = tempNode.value.compareTo(object);

                if (comparison > 0) {
                    if (tempNode.left == null) {
                        tempNode.left = newNode;
                        break;
                    }
                    tempNode = tempNode.left;
                } else if (comparison < 0) {
                    if (tempNode.right == null) {
                        tempNode.right = newNode;
                        break;
                    }
                    tempNode = tempNode.right;
                }
            }

            size += 1;
        }

        return this;
    }

    public TreeSet<T> remove(T object) {
        if (contains(object)) {
            size -= 1;
            if (size == 0) {
                root = null;
            } else {
                if (root.left != null && root.right == null) {
                    root = root.left;
                }
            }
        }
        return this;
    }

    public boolean contains(T object) {
        Node tempNode = root;

        while (tempNode != null) {
            int comparison = tempNode.value.compareTo(object);
            if (comparison == 0) {
                return true;
            } else if (comparison > 0) {
                tempNode = tempNode.left;
            } else {
                tempNode = tempNode.right;
            }
        }

        return false;
    }

    public TreeSet<T> each(Consumer<T> block) {
        throw Kamayan.todo(
        );
    }
}
