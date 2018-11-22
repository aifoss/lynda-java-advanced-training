package ch03_advanced_class_structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 2/19/17.
 */
public class AdvancedClassStructures {

    public static void main(String[] args) throws Exception {

        /******************************************************
         * static initializer
         ******************************************************/
        System.out.println("=== Static Initializer ===");
        List<OliveJar.Olive> staticOlives = OliveJar.staticOlives;
        printOlives(staticOlives);


        /******************************************************
         * instance field initializer
         ******************************************************/
        System.out.println("=== Instance Field Initializer ===");
        List<OliveJar.Olive> instanceOlives = new OliveJar(3, OliveJar.OliveName.GOLDEN, 0xDA9100).instanceOlives;
        printOlives(instanceOlives);


        /******************************************************
         * member class
         ******************************************************/
        System.out.println("=== Member Class ===");
        OliveJar jar = new OliveJar();
        jar.addOlive("Golden", 0x000000);
        jar.addOlive("Golden", 0x000000);
        jar.addOlive("Golden", 0x000000);
        jar.reportOlives();


        /******************************************************
         * local inner class
         ******************************************************/
        System.out.println("=== Local Inner Class ===");
        jar.reportOlivesWithLocalInnerClass();


        /******************************************************
         * anonymous inner class
         ******************************************************/
        System.out.println("=== Anonymous Inner Class ===");
        jar.reportOlivesWithAnonClass();
    }

    private static void printOlives(List<OliveJar.Olive> olives) {
        System.out.println("printing...");
        for (OliveJar.Olive o : olives) {
            System.out.println("It's a "+o.name+" olive!");
        }
        System.out.println();
    }


    public static class OliveJar {

        public static List<Olive> staticOlives;
        public List<Olive> instanceOlives;

        /******************************************************
         * static initializer
         ******************************************************/
        static {
            System.out.println("static initializer...");
            staticOlives = new ArrayList<>();
            staticOlives.add(new Olive(OliveName.KALAMATA, 0x000000));
            staticOlives.add(new Olive(OliveName.PICHOLINE, 0x00FF00));
            staticOlives.add(new Olive(OliveName.LIGURIO, 0x000000));
        }

        /******************************************************
         * instance field initializer
         ******************************************************/
        {
            System.out.println("instance field initializer...");
            instanceOlives = new ArrayList<>();
            instanceOlives.add(new Olive("Golden", 0xDA9100));
        }

        public OliveJar() {
            System.out.println("constructor...");
        }

        public OliveJar(int nOlives, String name, long color) {
            System.out.println("constructor with params...");
            for (int i = 0; i < nOlives; i++) {
                instanceOlives.add(new Olive(name, color));
            }
        }

        public OliveJar(int nOlives, OliveName name, long color) {
            System.out.println("constructor with params...");
            for (int i = 0; i < nOlives; i++) {
                instanceOlives.add(new Olive(name, color));
            }
        }

        public void addOlive(String name, long color) {
            instanceOlives.add(new Olive(name, color));
        }

        public void reportOlives() {
            System.out.println("reporting...");
            for (Olive o : instanceOlives) {
                System.out.println("It's a " + o.name + " olive!!");
            }
            System.out.println();
        }

        /******************************************************
         * local inner class
         ******************************************************/
        public void reportOlivesWithLocalInnerClass() {
            class JarLid {
                public void open() {
                    System.out.println("JarLid");
                    System.out.println("Twist, twist, twist...");
                    System.out.println("Pop!");
                }
            }

            new JarLid().open();

            reportOlives();
        }

        /******************************************************
         * anonymous inner class
         ******************************************************/
        public void reportOlivesWithAnonClass() {
            new Object() {
                public void open() {
                    System.out.println("Something");
                    System.out.println("Twist, twist, twist...");
                    System.out.println("Pop!");
                }
            }.open();

            reportOlives();
        }

        /******************************************************
         * enum
         ******************************************************/
        public enum OliveName {
            KALAMATA("Kalamata"),
            LIGURIO("Ligurio"),
            PICHOLINE("Picholine"),
            GOLDEN("Golden");

            private String nameAsString;

            OliveName(String nameAsString) {
                this.nameAsString = nameAsString;
            }

            @Override
            public String toString() {
                return nameAsString;
            }
        }

        /******************************************************
         * member class
         ******************************************************/
        static class Olive {

            public static final long BLACK = 0x000000;

            public OliveName name;
            public long color = BLACK;

            public Olive() {
            }

            public Olive(OliveName name) {
                this.name = name;
            }

            public Olive(String name) {
                this.name = OliveName.valueOf(name.toUpperCase());
            }

            public Olive(OliveName name, long color) {
                this(name);
                this.color = color;
            }

            public Olive(String name, long color) {
                this(OliveName.valueOf(name.toUpperCase()), color);
            }

            public String toString() {
                return "name: " + this.name + ": " + "color: " +  this.color;
            }
        }
    }

}
