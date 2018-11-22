package ch04_reflection_api;

import common.Ligurio;
import common.Olive;
import common.OliveColor;
import common.OliveName;

import java.lang.reflect.Constructor;

/**
 * Created by sofia on 2/19/17.
 */
public class ReflectionAPI {

    public static void main(String[] args) {

        /******************************************************
         * using Class class
         ******************************************************/
        Olive o = new Olive(OliveName.PICHOLINE, OliveColor.GREEN);
        Class<?> c = o.getClass();

        System.out.println(c);
        System.out.println(c.getName());
        System.out.println(c.getSimpleName());
        System.out.println();


        /******************************************************
         * instantiating classes dynamically
         ******************************************************/
        Constructor<?>[] constructors = c.getConstructors();
        System.out.println("Number of constructors: "+constructors.length);

        Constructor<?> con = constructors[0];
        Class<?>[] paramTypes = con.getParameterTypes();
        System.out.print("Param types: ");
        for (Class<?> paramType : paramTypes) {
            System.out.print(paramType.getSimpleName() + " ");
        }
        System.out.println();

        Object obj;

        try {
            obj = con.newInstance(OliveName.KALAMATA, OliveColor.BLACK);
            System.out.println(obj);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();


        /******************************************************
         * navigating inheritance trees
         ******************************************************/
        o = new Ligurio();

        c = o.getClass();
        System.out.println("Class name: "+c.getSimpleName());

        Class<?> sup = c.getSuperclass();
        System.out.println("Super name: "+sup.getSimpleName());

        Class<?> top = sup.getSuperclass();
        System.out.println("Top name: "+top.getSimpleName());

        Package p = c.getPackage();
        System.out.println("Package: "+p.getName());
    }

}
