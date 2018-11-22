package ch06_testing_and_exception_handling;

import java.io.*;

/**
 * Created by sofia on 2/20/17.
 */
public class TestingAndExceptionHandling {

    static final String URI = "/Users/sofia/Projects/workspace/intellij/all-coding-problems/3-course-problems/java-advanced-training/src/ch06_testing_and_exception_handling/files/";

    public static void main(String[] args) throws IOException {

        /******************************************************
         * assert
         ******************************************************/
        System.out.println("=== Using Assert ===");

        String s1 = InputHelper.getInput("Enter a numeric value: ");
        assert checkInput(s1);

        String s2 = InputHelper.getInput("Enter a numeric value: ");
        assert checkInput(s2);

        String op = InputHelper.getInput("Enter + or - or * or /: ");

        double result = 0;

        switch(op) {
            case "+":
                result = MathHelper.addValues(s1, s2);
                break;
            case "-":
                result = MathHelper.subtractValues(s1, s2);
                break;
            case "*":
                result = MathHelper.multiplyValues(s1, s2);
                break;
            case "/":
                result = MathHelper.divideValues(s1, s2);
                break;
            default:
                System.out.println("You entered an incorrect operator");
                return;
        }

        System.out.println("The answer is "+result);
        System.out.println();


        /******************************************************
         * finally
         ******************************************************/
        System.out.println("=== Using Finally ===");

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(URI + "aTextFile.txt");
            br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Executing finally!");
            if (fr != null) {
                fr.close();
            }
            if (br != null) {
                br.close();
            }
        }

        System.out.println("Still alive!");
        System.out.println();


        /******************************************************
         * try with resources
         ******************************************************/
        System.out.println("=== Using Try with Resources ===");

        try (FileReader fr2 = new FileReader(URI + "aTextFile.txt"); BufferedReader br2 = new BufferedReader(fr2);) {
            String s;
            while ((s = br2.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Still alive!");
        System.out.println();


        /******************************************************
         * custom exception
         ******************************************************/
        System.out.println("=== Using Custom Exception ===");

//        ClassLoader classLoader = TestingAndExceptionHandling.class.getClassLoader();
//        System.out.println(classLoader.getResource("ch06_testing_and_exception_handling/TestingAndExceptionHandling.class"));

        String fileContents = MyFileReader.readFile(URI + "file1.txt");
        System.out.println(fileContents);

        try {
            if (fileContents.equals("Right file")) {
                System.out.println("You chose the right file!");
            } else {
                throw new WrongFileException();
            }
        } catch (WrongFileException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean checkInput(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static class InputHelper {

        public static String getInput(String prompt) {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(prompt);
            System.out.flush();

            try {
                return stdin.readLine();
            } catch (Exception e) {
                return "Error: "+e.getMessage();
            }
        }
    }

    public static class MathHelper {

        public static double addValues(String s1, String s2) throws NumberFormatException {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            double result = d1 + d2;
            return result;
        }

        public static double subtractValues(String s1, String s2) throws NumberFormatException {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            double result = d1 - d2;
            return result;
        }

        public static double multiplyValues(String s1, String s2) throws NumberFormatException {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            double result = d1 * d2;
            return result;
        }

        public static double divideValues(String s1, String s2) throws NumberFormatException {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            double result = d1 / d2;
            return result;
        }
    }

    public static class MyFileReader {

        public static String readFile(String filename) throws IOException {
            StringBuilder sb = new StringBuilder();
            try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr); ) {
                String s;
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            return sb.toString();
        }
    }

    public static class WrongFileException extends Exception {

        public static final long serialVersionUID = 42L;

        @Override
        public String getMessage() {
            return "You chose the wrong file!";
        }
    }

}
