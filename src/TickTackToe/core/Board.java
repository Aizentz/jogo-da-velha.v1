package TickTackToe.core;


import com.sun.source.tree.BreakTree;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Objects;

public class Board {

    public static final int SIZE = 3;

    private final Symbol[][] matrix = new Symbol[SIZE][SIZE];

    public Board(){
        for(Symbol[] symbols : matrix){
            Arrays.fill(symbols, Symbol.NONE);
        }
    }


    @Override
    public String toString() {

        StringWriter sw = new StringWriter();
        PrintWriter out  = new PrintWriter(sw);

        for (int i = 0; i < SIZE; i++) {
            boolean first = true;
            for (int j = 0; j < SIZE; j++) {
                if(!first){
                    out.print(" | ");
                }
                out.print(matrix [i][j]);
                first = false;
            }
            out.println();
            if(i + 1 < SIZE){
                out.println("---------");
            }

        }

        return sw.toString();
    }

    public Symbol update(Symbol symbol, Coord coord){
        Objects.requireNonNull(symbol);
        Objects.requireNonNull(coord);
        if(symbol == Symbol.NONE){
            throw new IllegalArgumentException("NONE cannot be added to board");
        }
        if(matrix[coord.i()][coord.j()] != Symbol.NONE){
            throw new IllegalArgumentException("play is not possible!");
        }
        matrix[coord.i()][coord.j()] = symbol;
        return findSequence();
    }

    public boolean isFull(){
        for(int i = 0; i < SIZE; i++ ){
            for( int j = 0; j < SIZE; j++){
                if(matrix[i][j] == Symbol.NONE){
                    return false;
                }
            }
        }
        return true;
    }
    private Symbol findSequence(){
        Symbol symbol = findSequenceRows();
        if (symbol != null){
            return symbol;
        }

        symbol = findSequenceColumns();
        if(symbol != null){
            return symbol;
        }
        return findSequenceInDiagonal();
    }
    private Symbol findSequenceRows(){
        for(int i = 0; i < SIZE; i++){
            Symbol symbol = findSequenceInRows(i);
            if(symbol != null){
                return symbol;
            }

        }
        return null;

    }
    private Symbol findSequenceInRows(int i){
        if(matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2] && matrix[i][0] != Symbol.NONE){
            return matrix[i][0];
        }
        return null;
    }
    private Symbol findSequenceInColumns(int j) {
        if (matrix[0][j] == matrix[1][j] && matrix[1][j] == matrix[2][j] && matrix[j][0] != Symbol.NONE) {
            return matrix[0][j];
        }
        return null;
    }
    private Symbol findSequenceColumns(){
        for(int j = 0; j < SIZE; j++) {
            Symbol symbol = findSequenceInColumns(j);
            if (symbol != null) {
                return symbol;
            }

        }
        return null;
    }

    private Symbol findSequenceInDiagonal(){
        if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[0][0] != Symbol.NONE){
            return matrix[0][0];
        }
        if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[0][2] != Symbol.NONE){
            return matrix[0][2];
        }
        return null;
    }




}
