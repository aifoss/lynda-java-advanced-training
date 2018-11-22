package common;

/**
 * Created by sofia on 2/19/17.
 */
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
