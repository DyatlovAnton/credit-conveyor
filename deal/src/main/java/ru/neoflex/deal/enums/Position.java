package ru.neoflex.deal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Position {
    WORKER("worker"), MID_MANAGER("mid manager"), TOP_MANAGER("top manager"), OWNER("owner");
    private final String string;
    @JsonValue
    public String getString(){
        return string;
    }
    Position(String string){
        this.string = string;
    }
    @Override
    public String toString(){ return this.string;}
    @JsonCreator
    public static Position fromString(String text){
        for(Position position : Position.values()){
            if(position.toString().equals(text)){
                return position;
            }
        }
        throw new IllegalArgumentException();
    }
}
