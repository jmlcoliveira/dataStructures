package dataStructures;

import java.io.Serializable;

/**
 * Separate Chaining Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class SepChainHashTable<K extends Comparable<K>, V>
        extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleList<>();
        maxSize = capacity;
        currentSize = 0;
    }


    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();

        V v = table[this.hash(key)].insert(key, value);
        if (v == null)
            currentSize++;
        return v;
    }

    @Override
    public V remove(K key) {
        V value = table[this.hash(key)].remove(key);
        if (value != null)
            currentSize--;
        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable {

        static final long serialVersionUID = 0L;

        Entry<K, V> next;
        Entry<K, V> current;
        int counter;
        Iterator<Entry<K, V>> it;
        int posOfIterator;

        EntryIterator() {
            rewind();
        }

        public boolean hasNext() {
            return next != null;
        }

        public final Entry<K, V> next() {
            return nextNode();
        }

        protected Entry<K, V> nextNode() {
            current = next;
            next = findNext();
            return current;
        }

        public void rewind() {
            counter = 0;
            posOfIterator = 0;
            it = table[0].iterator();
            next = findNext();
            current = null;
        }

        protected Entry<K, V> findNext() {
            Entry<K, V> next = null;
            while (next == null || counter > table.length) {
                if (it != null && it.hasNext())
                    next = it.next();
                else {
                    boolean foundNotEmpty = false;
                    while (!foundNotEmpty && counter < table.length) {
                        if (table[counter].isEmpty() || posOfIterator == counter)
                            counter++;
                        else
                            foundNotEmpty = true;
                    }
                    if (!foundNotEmpty)
                        return null;
                    it = table[counter].iterator();
                    posOfIterator = counter;
                    return it.next();
                }
            }
            return next;
        }
    }

    @SuppressWarnings("unchecked")
    protected void rehash() {
        int newSize = nextPrime((int) (table.length * 1.1));
        Dictionary<K, V>[] newTable = (Dictionary<K, V>[]) new Dictionary[newSize];
        for (int i = 0; i < newSize; i++)
            newTable[i] = new OrderedDoubleList<>();

        Iterator<Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            Entry<K, V> e = it.next();
            int newHash = Math.abs(e.getKey().hashCode()) % newSize;
            newTable[newHash].insert(e.getKey(), e.getValue());
        }
        maxSize = (int)(newSize * 0.9);
        table = newTable;
    }
}
































