package ch08_io_streams;

import java.io.*;
import java.util.Scanner;

/**
 * Created by sofia on 2/20/17.
 */
public class IOStreams {

    static final String URI = "/Users/sofia/Projects/workspace/intellij/all-coding-problems/3-course-problems/java-advanced-training/src/ch08_io_streams/files/";

    public static void main(String[] args) {

        /******************************************************
         * reading and writing byte streams
         ******************************************************/
        System.out.println("=== Reading and Writing Byte Streams ===");

        try (FileInputStream in = new FileInputStream(URI + "flower.jpg"); FileOutputStream out = new FileOutputStream(URI + "newflower.jpg")) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();


        /******************************************************
         * reading and writing character streams
         ******************************************************/
        System.out.println("=== Reading and Writing Character Streams ===");

        try (FileReader in = new FileReader(URI + "textfile.txt"); FileWriter out = new FileWriter(URI + "newfile.txt")) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();


        /******************************************************
         * using buffered streams
         ******************************************************/
        System.out.println("=== Using Buffered Streams ===");

        try (BufferedReader in = new BufferedReader(new FileReader(URI + "hamlet.xml")); BufferedWriter out = new BufferedWriter(new FileWriter(URI + "newhamlet.xml"))) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("All done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();


        /******************************************************
         * scanning text
         ******************************************************/
        System.out.println("=== Scanning Text ===");

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(URI + "tokenized.txt")))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            System.out.println("All done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        /******************************************************
         * reading and writing byte streams
         ******************************************************/

    }

}
