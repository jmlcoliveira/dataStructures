package dataStructures;

import java.io.Serializable;

class BSTNode<K extends Comparable<K>, V> implements Serializable {

    // Entry stored in the node.
    private EntryClass<K, V> entry;
    // (Pointer to) the left child.
    private BSTNode<K, V> leftChild;
    // (Pointer to) the right child.
    private BSTNode<K, V> rightChild;

    protected BSTNode(K key, V value, BSTNode<K, V> left,
                   BSTNode<K, V> right) {
        entry = new EntryClass<K, V>(key, value);
        leftChild = left;
        rightChild = right;
    }

    protected BSTNode(K key, V value) {
        this(key, value, null, null);
    }

    protected EntryClass<K,V> getEntry( ){
        return entry;
    }

    protected K getKey( ){
        return entry.getKey();
    }

    protected V getValue( ){
        return entry.getValue();
    }

    protected BSTNode<K,V> getLeft( ){
        return leftChild;
    }
    protected BSTNode<K,V> getRight( ){
        return rightChild;
    }
    protected void setEntry( EntryClass<K,V> newEntry ){
        entry = newEntry;
    }

    protected void setEntry( K newKey, V newValue ){
        entry.setKey(newKey);
        entry.setValue(newValue);
    }

    protected void setKey( K newKey ){
        entry.setKey(newKey);
    }
    protected void setValue( V newValue ){
        entry.setValue(newValue);
    }

    protected void setLeft( BSTNode<K,V> newLeft ){
        leftChild = newLeft;
    }
    protected void setRight( BSTNode<K,V> newRight ){
        rightChild = newRight;
    }
    // Returns true if the node is a leaf.
    protected boolean isLeaf( ){
        return leftChild == null && rightChild == null;
    }
}
