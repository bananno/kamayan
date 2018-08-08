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
        if (!contains(object)) {
            return this;
        }

        size -= 1;

        if (size == 0) {
            root = null;
            return this;
        } else if (root.left != null && root.right == null) {
            root = root.left;
            return this;
        } else if (root.left == null && root.right != null) {
            root = root.right;
            return this;
        }

        if (size == 0) {
            root = null;
            return this;
        }

        Node tempParentNode = removeOneNode(object);

        if (tempParentNode == null) {
            if (root.left != null) {
                Node tempBranch = root.right;
                root = root.left;
                if (tempBranch != null) {
                    appendNode(tempBranch);
                }
            } else if (root.right != null) {
                root = root.right;
            }
            return this;
        }

        // if (tempParentNode != null) {
        //     if (tempParentNode.left != null) {
        //         // appendNode(tempParentNode.left);
        //     }

        //     if (tempParentNode.right != null) {
        //         // appendNode(tempParentNode.right);
        //     }
        // }

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

    private Node removeOneNode(T object) {
        Node parentNode = null;
        Node tempNode = root;
        boolean left = false;

        while (true) {

            int comparison = tempNode.value.compareTo(object);

            if (comparison == 0) {
                if (parentNode != null) {
                    if (left) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                }
                return parentNode;
            } else if (comparison > 0) {
                parentNode = tempNode;
                tempNode = tempNode.left;
            } else {
                parentNode = tempNode;
                tempNode = tempNode.right;
            }
        }
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
