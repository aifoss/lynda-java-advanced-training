package ch05_collections_framework;

import common.*;

import java.util.*;

/**
 * Created by sofia on 2/19/17.
 */
public class CollectionsFramework {

    public static void main(String[] args) {
        Olive lig = new Ligurio();
        Olive kal = new Kalamata();
        Olive pic = new Picholine();

        /******************************************************
         * hash set
         ******************************************************/
        Set<Olive> hashset = new HashSet<>();

        hashset.add(lig);
        hashset.add(kal);

        printSetSize(hashset);

        hashset.add(pic);

        printSetSize(hashset);

        hashset.add(lig);

        printSetSize(hashset);

        hashset.add(null);

        printSetSize(hashset);

        hashset.remove(lig);

        printSetSize(hashset);

        System.out.println();


        /******************************************************
         * tree set
         ******************************************************/
        Set<Olive> treeset = new TreeSet<>();

        try {
            treeset.add(pic);
            treeset.add(kal);
            treeset.add(lig);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(treeset);
        System.out.println();


        /******************************************************
         * linked set
         ******************************************************/
        LinkedList<Olive> list = new LinkedList<>();

        list.add(new Picholine());
        list.add(new Kalamata());
        list.add(1, new Golden());
        list.addFirst(new Ligurio());

        display(list);

        Olive o1 = list.poll();
        System.out.println(o1.name.toString());
        System.out.println();

        display(list);
    }

    private static void printSetSize(Set<Olive> set) {
        System.out.println("There are "+set.size()+" olives in the set");
    }

    private static void display(Collection<Olive> col) {
        System.out.println("List order:");
        Iterator<Olive> itr = col.iterator();
        while (itr.hasNext()) {
            Olive olive = (Olive) itr.next();
            System.out.println(olive.name.toString());
        }
        System.out.println();
    }

}
