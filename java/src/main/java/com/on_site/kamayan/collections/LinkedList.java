package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;
import java.util.function.Consumer;

public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Use this nested class for storing the values of the LinkedList. Each
    // LinkedList.Node contains the value at its index, and a link to the
    // LinkedList.Node at the next index (called the "child" here). If the child
    // is null, that denotes the last element of the LinkedList.
    class Node {
        public Object value;
        public Node child;

        public Node(Object value) {
            this(value, null);
        }

        public Node(Object value, Node child) {
            this.value = value;
            this.child = child;
        }
    }

    public int size() {
        return size;
    }

    public LinkedList prepend(Object value) {
        head = new Node(value, head);
        size++;
        return this;
    }

    public LinkedList add(Object value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node tempNode = head;
            while (tempNode.child != null) {
                tempNode = tempNode.child;
            }
            tempNode.child = newNode;
        }

        size++;

        return this;
    }

    public Object delete(int index) {
        checkBounds(index);

        if (index == 0) {
            Node deletedNode = head;
            if (head.child == null) {
                head = null;
            } else {
                head = head.child;
            }
            size--;
            return deletedNode.value;
        }

        Node parentNode = goToNodeNumber(index - 1);

        Object deletedValue = parentNode.child.value;

        if (parentNode.child.child == null) {
            parentNode.child = null;
        } else {
            parentNode.child = parentNode.child.child;
        }

        size--;

        return deletedValue;
    }

    public Object get(int index) {
        checkBounds(index);
        return goToNodeNumber(index).value;
    }

    public Object set(int index, Object value) {
        checkLowerBound(index);

        if (size == 0) {
            head = new Node(null);
            size++;
        }

        Node tempNode = head;

        for (int count = 0; count < index; count++) {
            if (tempNode.child == null) {
                tempNode.child = new Node(null);
                size++;
            }
            tempNode = tempNode.child;
        }

        Object oldValue = tempNode.value;
        tempNode.value = value;

        return oldValue;
    }

    private Node goToNodeNumber(int index) {
        Node tempNode = head;

        for (int count = 0; count < index; count++) {
            tempNode = tempNode.child;
        }

        return tempNode;
    }

    private void checkBounds(int index) {
        checkLowerBound(index);
        checkUpperBound(index);
    }

    private void checkLowerBound(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkUpperBound(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
