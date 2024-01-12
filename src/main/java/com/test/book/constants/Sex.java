package com.test.book.constants;

public enum Sex {


    FEMALE("女"),MALE("男");

    private String value;

    Sex(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
