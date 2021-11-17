package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;
import dataStructures.exceptions.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

    // The root of the tree.
    protected BSTNode<K, V> root;

    // Number of entries in the tree.
    protected int currentSize;

    public BinarySearchTree() {
        root = null;
        currentSize = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return currentSize;
    }

    // If there is an entry in the dictionary whose key is the
    // specified key, returns its value; otherwise, returns null.
    public V find(K key) {
        BSTNode<K, V> node = this.findNode(root, key);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    protected BSTNode<K, V> findNode(BSTNode<K, V> node, K key) {
        if (node == null)
            return null;
        else {
            int compResult = key.compareTo(node.getKey());
            if (compResult == 0)
                return node;
            else if (compResult < 0)
                return this.findNode(node.getLeft(), key);
            else
                return this.findNode(node.getRight(), key);
        }
    }

    protected BSTNode<K, V> findNode(K key, PathStep<K, V> lastStep) {
        BSTNode<K, V> node = root;
        while (node != null) {
            int compResult = key.compareTo(node.getKey());
            if (compResult == 0)
                return node;
            else if (compResult < 0) {
                lastStep.set(node, true);
                node = node.getLeft();
            } else {
                lastStep.set(node, false);
                node = node.getRight();
            }
        }
        return null;
    }

    // Returns the entry with the smallest key in the dictionary.
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();
        return this.minNode(root).getEntry();
    }

    // Requires: node != null.
    protected BSTNode<K, V> minNode(BSTNode<K, V> node) {
        if (node.getLeft() == null)
            return node;
        else
            return this.minNode(node.getLeft());
    }

    // Returns the entry with the largest key in the dictionary.
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();
        return this.maxNode(root).getEntry();
    }

    // Requires: node != null.
    protected BSTNode<K, V> maxNode(BSTNode<K, V> node) {
        if (node.getRight() == null)
            return node;
        else
            return this.maxNode(node.getRight());
    }

    public V insert(K key, V value) {
        PathStep<K, V> lastStep = new PathStep<>(null, false);
        BSTNode<K, V> node = this.findNode(key, lastStep);
        if (node == null) {
            BSTNode<K, V> newLeaf = new BSTNode<>(key, value);
            this.linkSubtree(newLeaf, lastStep);
            currentSize++;
            return null;
        } else {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
    }

    // Links a new subtree, rooted at the specified node, to the tree.
    // The parent of the old subtree is stored in lastStep.
    protected void linkSubtree(BSTNode<K, V> node,
                               PathStep<K, V> lastStep) {
        if (lastStep.parent == null)
            // Change the root of the tree.
            root = node;
        else
            // Change a child of parent.
            if (lastStep.isLeftChild)
                lastStep.parent.setLeft(node);
            else
                lastStep.parent.setRight(node);
    }

    // Returns the node with the smallest key
    // in the tree rooted at the specified node.
    // Moreover, stores the last step of the path in lastStep.
    // Requires: theRoot != null.
    protected BSTNode<K, V> minNode(BSTNode<K, V> theRoot,
                                    PathStep<K, V> lastStep) {
        BSTNode<K, V> node = theRoot;
        while (node.getLeft() != null) {
            lastStep.set(node, true);
            node = node.getLeft();
        }
        return node;
    }

    // If there is an entry in the dictionary whose key is the
    // specified key, removes it from the dictionary and returns
    // its value; otherwise, returns null.
    public V remove(K key) {
        PathStep<K, V> lastStep = new PathStep<K, V>(null, false);
        BSTNode<K, V> node = this.findNode(key, lastStep);
        if (node == null)
            return null;
        else {
            V oldValue = node.getValue();
            if (node.getLeft() == null)
                // The left subtree is empty.
                this.linkSubtree(node.getRight(), lastStep);
            else if (node.getRight() == null)
                // The right subtree is empty.
                this.linkSubtree(node.getLeft(), lastStep);
            else {
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                lastStep.set(node, false);
                BSTNode<K, V> minNode = this.minNode(node.getRight(), lastStep);
                node.setEntry(minNode.getEntry());
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), lastStep);
            }

            currentSize--;
            return oldValue;
        }
    }

    // Returns an iterator of the entries in the dictionary
    // which preserves the key order relation.
    public Iterator<Entry<K, V>> iterator() {
        return new BSTKeyOrderIterator<>(root);
    }

    static class BSTKeyOrderIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

        BSTNode<K, V> root;
        BSTNode<K, V> current;
        BSTNode<K, V> next;
        PathStep<K, V> lastStep;

        BSTKeyOrderIterator(BSTNode<K, V> root) {
            this.root = root;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Entry<K, V> next() throws NoSuchElementException {
            if (next == null) throw new NoSuchElementException();
            current = next;
            next = findNext();
            return current.getEntry();
        }

        protected BSTNode<K, V> findNext() {
            //TODO
        }

        @Override
        public void rewind() {
            //TODO
        }
    }


    protected static class PathStep<K extends Comparable<K>, V> {
        // The parent of the node.
        public BSTNode<K, V> parent;
        // The node is the left or the right child of parent.
        public boolean isLeftChild;

        public PathStep(BSTNode<K, V> theParent, boolean toTheLeft) {
            parent = theParent;
            isLeftChild = toTheLeft;
        }

        public void set(BSTNode<K, V> newParent, boolean toTheLeft) {
            parent = newParent;
            isLeftChild = toTheLeft;
        }
    }
}
