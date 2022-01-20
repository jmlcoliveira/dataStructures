package dataStructures.orderedDictionaries;

public class BSTNodeWithParent<K extends Comparable<K>, V> extends BSTNode<K, V> {

    BSTNodeWithParent<K, V> parent;

    public BSTNodeWithParent(K key, V value, BSTNodeWithParent<K, V> left, BSTNodeWithParent<K, V> right, BSTNodeWithParent<K, V> parent) {
        super(key, value, left, right);
        this.parent = parent;
    }
}
