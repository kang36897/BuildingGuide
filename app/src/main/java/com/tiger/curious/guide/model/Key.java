package com.tiger.curious.guide.model;

/**
 * Created by bkang016 on 5/14/17.
 */

public class Key {

    public final static int TYPE_NUMBER = 0;
    public final static int TYPE_ALPHABET = 1;
    public final static int TYPE_DELETE_ACTION = 2;

    private String value;

    private int type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Key() {
    }

    public Key(String value) {
        this.value = value;
    }

    public Key(String value, int type) {
        this.value = value;
        this.type = type;
    }

    public Key(int type) {
        this.type = type;
    }

    public static Key getNumber(String value) {
        return new Key(value, TYPE_NUMBER);
    }

    public static Key getLetter(String value) {
        return new Key(value, TYPE_ALPHABET);
    }

    public static Key getAction(int type){
        return new Key(type);
    }

    @Override
    public String toString() {
        return "Key{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
