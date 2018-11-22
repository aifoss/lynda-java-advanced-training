package common;

/**
 * Created by sofia on 2/19/17.
 */
public enum OliveColor {

    BLACK("Black"),
    GREEN("Green"),
    GOLD("Gol");

    private String colorAsString;

    OliveColor(String colorAsString) {
        this.colorAsString = colorAsString;
    }

    @Override
    public String toString() {
        return colorAsString;
    }

}
