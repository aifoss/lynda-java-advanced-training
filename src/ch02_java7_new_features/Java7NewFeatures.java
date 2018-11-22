package ch02_java7_new_features;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sofia on 2/19/17.
 */
public class Java7NewFeatures {

    public static void main(String[] args) {

        /******************************************************
         * numeric literal
         ******************************************************/
        int numberOfOlives = 1_000_000_000;
        NumberFormat formatter = NumberFormat.getInstance();
        System.out.println(formatter.format(numberOfOlives));
        System.out.println();


        /******************************************************
         * simplified generics
         ******************************************************/
        Olive o1 = new Olive("Kalamata", 0x000000);
        Olive o2 = new Olive("Picholine", 0x00FF00);
        Olive o3 = new Olive("Ligurio", 0x000000);

        List<Olive> olives = new ArrayList<>();
        olives.add(o1);
        olives.add(o2);
        olives.add(o3);

        System.out.println(olives);
        System.out.println();


        /******************************************************
         * strings in switch
         ******************************************************/
        Random generator = new Random();
        int index = generator.nextInt(3);
        System.out.println("random value: "+index);

        Olive o = olives.get(index);

        switch(o.name) {
            case "Kalamata":
                System.out.println("It's Greek!");
                break;
            case "Picholine":
                System.out.println("It's French!");
                break;
            case "Ligurio":
                System.out.println("It's Italian!");
                break;
            default:
                System.out.println("I don't know where it's from!");
                break;
        }
    }

    static class Olive {

        public String name = "Kalamata";
        public long color = 0x000000;

        public Olive() {
        }

        public Olive(String name) {
            this.name = name;
        }

        public Olive(String name, long color) {
            this(name);
            this.color = color;
        }

        public String toString() {
            return "name: " + this.name + ": " + "color: " + this.color;
        }
    }

}
