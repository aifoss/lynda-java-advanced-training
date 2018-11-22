package common;

/**
 * Created by sofia on 2/19/17.
 */
public class Olive implements Comparable<Olive> {

    public OliveName name;
    public OliveColor color;

//        public Olive() {}

    public Olive(OliveName name, OliveColor color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "name: " + name.toString() + ", color: " + color.toString();
    }

    @Override
    public int compareTo(Olive other) {
        String s1 = this.getClass().getSimpleName();
        String s2 = other.getClass().getSimpleName();
        return s1.compareTo(s2);
    }

}
