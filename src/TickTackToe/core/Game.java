package TickTackToe.core;

import TickTackToe.io.Input;
import TickTackToe.io.Output;

public class Game {
    private final Board board = new Board();
    private final Players players = new Players();
    public void start(){

        Symbol winner = null;

        while (winner == null && !board.isFull()){
            Output.writeNewLine();
            Output.write(board);
            play(players.next());

            winner = play(players.next());

        }

        if(board.isFull()){
            Output.write("No winner");
        }else{
            Output.write(String.format("%s is the winner", winner));
        }



    }
    private Symbol play(Symbol symbol) {
        while (true) {
            try {
                String play = Input.read(String.format("'%s' Play -->", symbol));
                Coord coord = Coord.Parse(play);
                return board.update(symbol, coord);

            } catch (RuntimeException e) {
                Output.write("ERROR: " + e.getMessage());
            }


        }
    }
}
