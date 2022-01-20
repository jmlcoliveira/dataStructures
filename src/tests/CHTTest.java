package tests;

import dataStructures.*;
import dataStructures.hashTable.SepChainHashTable;
import dataStructures.orderedDictionaries.BinarySearchTree;
import dataStructures.orderedDictionaries.OrderedDictionary;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CHTTest {

    @Test
    public void insertTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<Integer, Integer>();

        assertTrue(cht.insert(5, 5) == null);
        assertTrue(cht.insert(10, 10) == null);
        assertTrue(cht.insert(-3, -3) == null);
        assertTrue(cht.insert(4, 4) == null);
        assertTrue(cht.insert(9, 9) == null);
        assertTrue(cht.insert(7, 77) == null);
        assertTrue(cht.insert(7, 7) == 77);

        assertEquals(cht.size(), 6);

        Iterator<Entry<Integer, Integer>> it = cht.iteratorEntries();

        OrderedDictionary<Integer, Integer> entries = new BinarySearchTree<>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iteratorEntries();

        assertTrue(itEntries.hasNext());
        assertTrue(itEntries.next().getKey() == -3);
        assertTrue(itEntries.next().getKey() == 4);
        assertTrue(itEntries.next().getKey() == 5);
        assertTrue(itEntries.next().getKey() == 7);
        assertTrue(itEntries.next().getKey() == 9);
        assertTrue(itEntries.next().getKey() == 10);
        assertFalse(itEntries.hasNext());
    }

    @Test
    public void insertRandomTest() {

        //insertRandomElems(cht, 200);
        for (int k = 0; k < 10000; k++) {
            Dictionary<Integer, Integer> cht = new SepChainHashTable<Integer, Integer>(10000);
            int arr[] = new int[10000];
            Random rand = new Random();
            int i = 0;
            while (i < 10000) {
                int e = rand.nextInt();
                cht.insert(e, e);
                arr[i] = e;
                i = cht.size();
            }

            for (i = 0; i < 10000; i++) {
                if (cht.find(arr[i]) == null) {
                    System.out.println(i);
                    System.out.println(arr[i]);
                }
                assert (arr[i] == cht.find(arr[i]));
            }

            Iterator<Entry<Integer, Integer>> it = cht.iteratorEntries();

            int counter = 0;
            while (it.hasNext()) {
                it.next().getKey();
                counter++;
            }
            int size = cht.size();
            assertEquals(cht.size(), counter);
        }
    }

    @Test
    public void findTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<Integer, Integer>();
        assertTrue(cht.isEmpty());
        assertTrue(cht.insert(5, 5) == null);
        assertTrue(cht.find(5) == 5);
        assertTrue(!cht.isEmpty());
        assertTrue(cht.size() == 1);
        assertTrue(cht.insert(10, 10) == null);
        assertTrue(cht.find(10) == 10);
        assertTrue(cht.size() == 2);
        assertTrue(cht.insert(-3, -3) == null);
        assertTrue(cht.find(-3) == -3);
        assertTrue(cht.size() == 3);
        assertTrue(cht.insert(4, 4) == null);
        assertTrue(cht.find(4) == 4);
        assertTrue(cht.size() == 4);
        assertTrue(cht.insert(9, 9) == null);
        assertTrue(cht.find(9) == 9);
        assertTrue(cht.size() == 5);
        assertTrue(cht.insert(7, 77) == null);
        assertTrue(cht.find(7) == 77);
        assertTrue(cht.find(9) == 9);
        assertTrue(cht.size() == 6);
        assertTrue(cht.insert(7, 7) == 77);
        assertTrue(cht.find(7) == 7);
        assertTrue(cht.size() == 6);
        assertEquals(cht.size(), 6);

        Iterator<Entry<Integer, Integer>> it = cht.iteratorEntries();

        OrderedDictionary<Integer, Integer> entries = new BinarySearchTree<Integer, Integer>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iteratorEntries();

        assertTrue(itEntries.hasNext());
        assertTrue(itEntries.next().getKey() == -3);
        assertTrue(itEntries.next().getKey() == 4);
        assertTrue(itEntries.next().getKey() == 5);
        assertTrue(itEntries.next().getKey() == 7);
        assertTrue(itEntries.next().getKey() == 9);
        assertTrue(itEntries.next().getKey() == 10);
        assertFalse(itEntries.hasNext());
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<Integer, Integer>();

        assertTrue(cht.insert(5, 5) == null);
        assertTrue(cht.insert(10, 10) == null);
        assertTrue(cht.insert(-3, -3) == null);
        assertTrue(cht.insert(4, 4) == null);
        assertTrue(cht.insert(9, 9) == null);
        assertTrue(cht.insert(7, 77) == null);
        assertTrue(cht.insert(7, 7) == 77);

        assertEquals(cht.size(), 6);

        assertTrue(cht.remove(5) == 5);
        assertTrue(cht.remove(5) == null);
        assertTrue(cht.remove(10) == 10);
        assertTrue(cht.remove(-3) == -3);
        assertTrue(cht.remove(4) == 4);
        assertTrue(cht.remove(9) == 9);
        assertTrue(cht.remove(7) == 7);

        assertEquals(cht.size(), 0);
        Iterator<Entry<Integer, Integer>> it = cht.iteratorEntries();
        assertTrue(!it.hasNext());

        assertTrue(cht.insert(5, 5) == null);
        assertTrue(cht.insert(10, 10) == null);
        assertTrue(cht.insert(-3, -3) == null);
        assertTrue(cht.insert(4, 4) == null);
        assertTrue(cht.insert(9, 9) == null);
        assertTrue(cht.insert(7, 77) == null);
        assertTrue(cht.insert(7, 7) == 77);

        assertEquals(cht.size(), 6);

        it = cht.iteratorEntries();

        OrderedDictionary<Integer, Integer> entries = new BinarySearchTree<Integer, Integer>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iteratorEntries();

        assertTrue(itEntries.hasNext());
        assertTrue(itEntries.next().getKey() == -3);
        assertTrue(itEntries.next().getKey() == 4);
        assertTrue(itEntries.next().getKey() == 5);
        assertTrue(itEntries.next().getKey() == 7);
        assertTrue(itEntries.next().getKey() == 9);
        assertTrue(itEntries.next().getKey() == 10);
        assertFalse(itEntries.hasNext());
    }

    private static void insertRandomElems(Dictionary<Integer, Integer> cht, int elems) {
        Random rand = new Random();
        int i = 0;
        while (i < elems) {
            int e = rand.nextInt();
            cht.insert(e, e);
            i = cht.size();
        }
    }

}
