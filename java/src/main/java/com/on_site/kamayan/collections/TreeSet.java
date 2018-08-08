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
        appendNode(newNode);
        return this;
    }

    public TreeSet<T> remove(T object) {
        if (size == 0) {
            return this;
        }

        if (root.value.compareTo(object) == 0) {
            size -= 1;
            if (root.left != null) {
                Node tempRightBranch = root.right;
                root = root.left;
                if (tempRightBranch != null) {
                    appendNode(tempRightBranch);
                }
            } else {
                root = root.right;
            }
            return this;
        }

        Node tempNode = findNode(object);

        if (tempNode != null) {
            Node tempRight = tempNode.right;
            tempNode = null;
            if (tempRight != null) {
                appendNode(tempRight);
            }
            size -= 1;
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

    private Node findNode(T object) {
        Node tempNode = root;

        while (tempNode != null) {
            int comparison = tempNode.value.compareTo(object);
            if (comparison == 0) {
                return tempNode;
            } else if (comparison > 0) {
                tempNode = tempNode.left;
            } else {
                tempNode = tempNode.right;
            }
        }

        return null;
    }

    private void appendNode(Node newNode) {
        if (size == 0) {
            root = newNode;
            size += 1;
            return;
        }

        Node tempNode = root;

        while (true) {

            int comparison = tempNode.value.compareTo(newNode.value);

            if (comparison == 0) {
                return;
            } if (comparison > 0) {
                if (tempNode.left == null) {
                    tempNode.left = newNode;
                    size += 1;
                    return;
                }
                tempNode = tempNode.left;
            } else if (comparison < 0) {
                if (tempNode.right == null) {
                    tempNode.right = newNode;
                    size += 1;
                    return;
                }
                tempNode = tempNode.right;
            }
        }

    }
}
