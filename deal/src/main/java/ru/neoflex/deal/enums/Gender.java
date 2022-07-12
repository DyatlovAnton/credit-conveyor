package ru.neoflex.deal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE("male"), FEMALE("female"), NON_BINARY("non binary");
    private final String string;
    Gender(String string){
        this.string = string;
    }
    @Override
    public String toString(){ return this.string;}
    @JsonCreator
    public static Gender fromString(String text){
        for(Gender gender : Gender.values()){
            if(gender.toString().equals(text)){
                return gender;
            }
        }
        throw new IllegalArgumentException();
    }
}
