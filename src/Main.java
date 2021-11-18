import dataStructures.*;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
        OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(2, 2);
        bst.insert(3, 3);
        bst.insert(5, 5);
        bst.insert(4, 4);
        bst.insert(1, 1);
        bst.insert(0, 0);
        bst.insert(-1, -1);

        Iterator<Entry<Integer, Integer>> it = bst.iterator();
        while (it.hasNext()){
            Entry<Integer, Integer> e = it.next();
            System.out.println(e.getKey());
        }
    }

}
