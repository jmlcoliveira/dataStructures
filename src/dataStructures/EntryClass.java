package dataStructures;

import java.io.Serializable;

class EntryClass<K, V> implements Entry<K, V>, Serializable {

    static final long serialVersionUID = 0L;

    // Value stored in the node.
    private V value;

    //Key stored in the node
    private K key;

    // (Pointer to) the previous node.
    private EntryClass<K, V> previous;

    // (Pointer to) the next node.
    private EntryClass<K, V> next;

    protected EntryClass(K key, V value, EntryClass<K, V> thePrevious, EntryClass<K, V> theNext) {
        this.key = key;
        this.value = value;
        previous = thePrevious;
        next = theNext;
    }

    protected EntryClass(K key, V value){
        this(key, value, null, null);
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public void setValue(V newValue) {
        value = newValue;
    }

    public void setKey(K newKey) {
        key = newKey;
    }

    public EntryClass<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(EntryClass<K, V> newPrevious) {
        previous = newPrevious;
    }

    public EntryClass<K, V> getNext() {
        return next;
    }

    public void setNext(EntryClass<K, V> newNext) {
        next = newNext;
    }

}
