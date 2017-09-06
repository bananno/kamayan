package com.on_site.kamayan.collections;

import com.on_site.kamayan.Kamayan;

public class Stack {
    private Integer maxSize;
    private DoublyLinkedList list;

    public Stack() {
        this(null);
    }

    public Stack(Integer maxSize) {
        this.maxSize = maxSize;
        // You may use any of the collections you've built so far, though you
        // will need to implement `each` for that collection if you use
        // something other than DoublyLinkedList.
        this.list = new DoublyLinkedList();
    }

    public int size() {
        return list.size();
    }

    public Stack push(Object value) {
        checkIncreaseAllowed();
        list.add(value);
        return this;
    }

    public Object pop() {
        checkNotEmpty();
        return this.list.deleteLast();
    }

    public boolean isEmpty() {
        throw Kamayan.todo(
            "The isEmpty() method should return whether or not the size is 0."
        );
    }

    public Object peek() {
        throw Kamayan.todo(
            "The peek() method should return the last value in the stack,",
            "without removing any elements in the stack."
        );
    }

    private void checkIncreaseAllowed() {
        if (this.maxSize != null && this.size() == this.maxSize) {
            throw new StackOverflowException("Warning");
        }
    }

    private void checkNotEmpty() {
        if (this.size() == 0) {
            throw new IndexOutOfBoundsException("Warning");
        }
    }
}
