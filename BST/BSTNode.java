package src.BST;

/**
 * This class represents a node in your BST.
 * DO NOT EDIT THIS FILE.
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public class BSTNode<T extends Comparable<? super T>> {
    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    /**
     * Creates a BST node with the given data.
     *
     * @param data the data to be stored in this node
     */
    public BSTNode(T data) {
        this.data = data;
    }

    /**
     * Gets the data in this node.
     *
     * @return the data in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data in this node.
     *
     * @param data the data to be placed into the node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the left child of this node.
     *
     * @return the node to the left
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the new node to the left
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return the node to the right
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the new node to the right
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}