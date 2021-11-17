import dataStructures.*;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
        OrderedDictionary<Integer, Integer> ol = new BinarySearchTree<>();
        ol.insert(2, 2);
        ol.insert(3, 3);
        ol.insert(5, 5);
        ol.insert(4, 4);
        ol.insert(1, 1);
        ol.insert(0, 0);
        ol.insert(-1, -1);
    }
}
