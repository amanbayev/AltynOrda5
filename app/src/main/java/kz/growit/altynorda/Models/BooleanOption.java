package kz.growit.altynorda.Models;

/**
 * Created by Талгат on 28.11.2015.
 */
public class BooleanOption {
    private String key;
    private boolean value;

    public BooleanOption(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public BooleanOption() {
    }

    public String getKey() {
        return key;
    }

    public boolean isValue() {
        return value;
    }
}
