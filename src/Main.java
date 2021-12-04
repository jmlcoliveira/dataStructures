import dataStructures.*;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
       OrderedDictionary<Integer, String> od = new OrderedDoubleDictionary<>();
       od.insert(0, "zero");
       od.insert(1, "one");
       od.insert(2, "two");

       Iterator<Integer> itKeys = od.iteratorKeys();
       Iterator<String> itValues = od.iteratorValues();
        while(itKeys.hasNext()){
           System.out.printf("%d : %s\n", itKeys.next(), itValues.next());
       }
    }

}
