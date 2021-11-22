package dataStructures;

import java.io.Serializable;

class EntryClass<K, V> implements Entry<K, V>, Serializable {

    static final long serialVersionUID = 0L;

    // Value stored in the node.
    private V value;

    //Key stored in the node
    private K key;

    protected EntryClass(K key, V value, EntryClass<K, V> thePrevious, EntryClass<K, V> theNext) {
        this.key = key;
        this.value = value;
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

}
