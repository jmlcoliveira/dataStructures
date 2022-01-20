import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.BinarySearchTree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> avl = new BinarySearchTree<>();
        avl.insert(16, 4);
        avl.insert(5, 0);
        avl.insert(18, 7);
        avl.insert(1, 8);
        avl.insert(8, 1);
        avl.remove(18);

        Iterator<Entry<Integer, Integer>> it = avl.breadthIterator();
        while (it.hasNext())
            System.out.println(it.next().getKey());

        System.out.println(avl.isBalanced());
    }
}
