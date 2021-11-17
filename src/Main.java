import dataStructures.*;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
        Dictionary<Integer, String> l = new SepChainHashTable<>();

        l.insert(8, "6");
        l.insert(6, "5");
        l.insert(4, "3");
        l.insert(10, "2");
        l.insert(5, "7");
        l.insert(7, "1");
        l.insert(9, "4");
        l.insert(3, "6");
        l.insert(6, "5");
        l.insert(12, "3");
        l.insert(1, "2");
        l.insert(0, "7");
        l.insert(2, "1");
        l.insert(4, "4");

        Iterator<Entry<Integer, String>> it = l.iterator();

        while (it.hasNext()){
            Entry<Integer, String> e = it.next();
            System.out.printf("Key: %d; Value: %s\n",e.getKey(), e.getValue());
        }

        OrderedList<String> ol = new OrderedDoubleList<>();
        ol.add("b");
        ol.add("c");
        ol.add("e");
        ol.add("d");
        ol.add("a");
    }
}
