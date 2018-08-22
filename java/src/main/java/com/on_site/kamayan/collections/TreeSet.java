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
        appendNode(newNode, true);
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
                    appendNode(tempRightBranch, false);
                }
            } else {
                root = root.right;
            }
            return this;
        }

        if (root.left != null) {
            if (root.left.value.compareTo(object) == 0) {
                size -= 1;

                Node tempLeftNode = root.left.left;
                Node tempRightNode = root.left.right;

                root.left = null;

                if (tempLeftNode != null) {
                    appendNode(tempLeftNode, false);
                }

                if (tempRightNode != null) {
                    appendNode(tempRightNode, false);
                }

                return this;
            }
        }

        if (root.right != null) {
            if (root.right.value.compareTo(object) == 0) {
                size -= 1;

                Node tempLeftNode = root.right.left;
                Node tempRightNode = root.right.right;

                root.right = null;

                if (tempLeftNode != null) {
                    appendNode(tempLeftNode, false);
                }

                if (tempRightNode != null) {
                    appendNode(tempRightNode, false);
                }

                return this;
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

    private void appendNode(Node newNode, boolean increaseSize) {
        if (size == 0) {
            root = newNode;
            size += increaseSize ? 1 : 0;
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
                    size += increaseSize ? 1 : 0;
                    return;
                }
                tempNode = tempNode.left;
            } else if (comparison < 0) {
                if (tempNode.right == null) {
                    tempNode.right = newNode;
                    size += increaseSize ? 1 : 0;
                    return;
                }
                tempNode = tempNode.right;
            }
        }

    }
}
