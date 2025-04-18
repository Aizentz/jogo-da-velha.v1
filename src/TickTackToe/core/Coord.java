package TickTackToe.core;

import TickTackToe.util.ValidationUtils;


import java.util.Objects;

public record Coord(int i, int j) {

    public Coord{
        ValidationUtils.require(i >= 0 && i <= Board.SIZE, "I is out of range PORRA");
        ValidationUtils.require(j >= 0 && j <= Board.SIZE, "J is out of range PORRA");
    }

    public static Coord Parse(String text){
        Objects.requireNonNull(text);
        String[] tokens = text.split(",");
        if(tokens.length != 2){
            throw new  IllegalArgumentException("Invalid format");
        }
        try {
            return new Coord( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid number");
        }

    }
}
