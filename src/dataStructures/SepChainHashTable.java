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

    private final double LOAD_FACTOR = 0.5;


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

        /**
         * Next element to be returned
         */
        Entry<K, V> next;

        /**
         * Last returned element
         */
        Entry<K, V> current;

        /**
         * Current index in the collision table
         */
        int counter;

        /**
         * Iterator of collision table with elements
         */
        Iterator<Entry<K, V>> it;

        /**
         * Position of the iterator in the collision table
         */
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

        /**
         * Finds the next Entry to be returned
         * @return an Entry if there is one or <code>null</code> if none was found
         */
        protected Entry<K, V> findNext() {
            Entry<K, V> next = null;
            while (next == null || counter > table.length) {
                if (it != null && it.hasNext())
                    next = it.next(); //return next Entry off an iterator of collisions
                else {
                    boolean foundNotEmpty = false;
                    //Search entire table until a not empty position is found
                    while (!foundNotEmpty && counter < table.length) {
                        //posOfIterator is needed to know which was last position of iterator
                        //which has already been iterated but that position is collision table is not empty
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
        //iterate over all Entries and save them in the position of the new hash
        while (it.hasNext()) {
            Entry<K, V> e = it.next();
            int newHash = Math.abs(e.getKey().hashCode()) % newSize;
            newTable[newHash].insert(e.getKey(), e.getValue());
        }
        maxSize = (int)(newSize * LOAD_FACTOR);
        table = newTable;
    }
}
































