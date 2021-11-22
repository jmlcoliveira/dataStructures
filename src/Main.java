import dataStructures.*;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
       OrderedDoubleDictionary<Integer, Integer> od = new OrderedDoubleDictionary<>();
       od.insert(1, 1);
       od.insert(3, 3);
       od.insert(2, 2);

       Iterator<Entry<Integer, Integer>> it = od.iterator();
       while(it.hasNext()){
           System.out.println(it.next().getKey());
       }
    }

}
