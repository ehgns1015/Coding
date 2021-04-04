package src.BST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Dohun Yeo
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there is no
     * need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data should be added
     * in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type of
     * loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data==null");
        }
        for (T element : data) {
            if (element == null) {
                throw new IllegalArgumentException("element==null");
            }
            add(element);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing should
     * be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst case
     * of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data==null");
        }
        root = add(root, data);
    }

    /**
     * Helper method for add
     * 
     * @param curr to traverse
     * @param data to add
     * @return new node with data
     */
    private BSTNode<T> add(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        }
        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(add(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(add(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf (no children). In this case, simply remove it. 2: the
     * data has one child. In this case, simply replace it with its child. 3: the
     * data has 2 children. Use the predecessor to replace the data. You MUST use
     * recursion to find and remove the predecessor (you will likely need an
     * additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst case
     * of O(n).
     *
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data that was
     *         passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data==null");
        }
        BSTNode<T> removed = new BSTNode<>(null);
        root = remove(root, removed, data);
        return removed.getData();
    }

    /**
     * Helper method for remove
     * 
     * @param curr    to traverse
     * @param removed to contain removed data
     * @param data    data to remove
     * @return new node tree
     */
    private BSTNode<T> remove(BSTNode<T> curr, BSTNode<T> removed, T data) {
        if (curr == null) {
            throw new NoSuchElementException("curr==null");
        }
        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(remove(curr.getLeft(), removed, data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(remove(curr.getRight(), removed, data));
        } else {
            size--;
            removed.setData(curr.getData());
            if (curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> temp = new BSTNode<>(null);
                curr.setLeft(predecessor(curr.getLeft(), temp));
                curr.setData(temp.getData());
            }
        }
        return curr;
    }

    /**
     * Helper method for predecessor
     * 
     * @param curr
     * @param temp
     * @return
     */
    private BSTNode<T> predecessor(BSTNode<T> curr, BSTNode<T> temp) {
        if (curr.getRight() == null) {
            temp.setData(curr.getData());
            return curr.getLeft();
        }
        curr.setRight(predecessor(curr.getRight(), temp));
        return curr;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst case
     * of O(n).
     *
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the same
     *         data that was passed in. Return the data that was stored in the tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data==null");
        }
        return get(root, data);
    }

    /**
     * Helper method for get
     * 
     * @param curr to traverse
     * @param data to find
     * @return data found
     */
    private T get(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("no data");
        }
        if (curr.getData().compareTo(data) > 0) {
            return get(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return get(curr.getRight(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is contained
     * within the tree. The same type of equality should be used as in the get
     * method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst case
     * of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        preorder(list, root);
        return list;
    }

    /**
     * Helper method for preorder
     * 
     * @param list
     * @param curr
     */
    private void preorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            list.add(curr.getData());
            preorder(list, curr.getLeft());
            preorder(list, curr.getRight());
        }
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        inorder(list, root);
        return list;
    }

    /**
     * Helper method for inorder
     * 
     * @param list
     * @param curr
     */
    private void inorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            inorder(list, curr.getLeft());
            list.add(curr.getData());
            inorder(list, curr.getRight());
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        postorder(list, root);
        return list;
    }

    /**
     * Helper method for postorder
     * 
     * @param list
     * @param curr
     */
    private void postorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            postorder(list, curr.getLeft());
            postorder(list, curr.getRight());
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't empty,
     * remove one node, add its data to the list being returned, and add its left
     * and right child nodes to the queue. If what you just removed is {@code null},
     * ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n). This does not need to be done recursively.
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<>();
        List<T> list = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> temp = q.poll();
            list.add(temp.getData());
            if (temp.getLeft() != null) {
                q.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                q.add(temp.getRight());
            }
        }
        return list;
    }

    /**
     * Option!!!
     * 
     * This method checks whether a binary tree meets the criteria for being a
     * binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is correct,
     * that there are no duplicates, and that every parent has at most 2 children.
     * So, what you will have to check is that the order property of a BST is still
     * satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you find
     * evidence that the tree is not a BST rather than checking the rest of the
     * tree.
     *
     * @param <T>      the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(BSTNode<T> treeRoot) {
        if (treeRoot.getLeft() != null) {
            if (treeRoot.getData().compareTo(treeRoot.getLeft().getData()) < 0) {
                return false;
            } else {
                return isBST(treeRoot.getLeft());
            }
        }
        if (treeRoot.getRight() != null) {
            if (treeRoot.getData().compareTo(treeRoot.getRight().getData()) < 0) {
                return false;
            } else {
                return isBST(treeRoot.getRight());
            }
        }
        return true;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's height is
     * defined as {@code max(left.height, right.height) + 1}. A leaf node has a
     * height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Helper method for height
     * 
     * @param curr
     * @return
     */
    private int height(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        return Math.max(height(curr.getLeft()), height(curr.getRight())) + 1;
    }

    /**
     * Returns the size of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}