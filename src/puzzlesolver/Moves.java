/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.ArrayList;

/**
 *
 * @author lucasgb
 */
public class Moves {
    
    private ArrayList<movements> moves = new ArrayList<movements>();

    /**
     * @return the moves
     */
    public ArrayList<movements> getMoves() {
        return moves;
    }
    
    public enum movements{
        UP, DOWN, RIGHT, LEFT;
    }    
    
    public void generateMoves(Puzzle puzzle){
        if(puzzle.getX() == 0){
            getMoves().add(movements.DOWN);
        }
        else if(puzzle.getX() == 1){
            getMoves().add(movements.UP);
            getMoves().add(movements.DOWN);
        }
        else if(puzzle.getX() == 2){
            getMoves().add(movements.UP);
        }
        
        if(puzzle.getY() == 0){
            getMoves().add(movements.RIGHT);
        }
        else if(puzzle.getY() == 1){
            getMoves().add(movements.LEFT);
            getMoves().add(movements.RIGHT);
            
        }
        else if(puzzle.getY() == 2){
            getMoves().add(movements.LEFT);
        }
    }
    
    public void print(){
        for(movements e : moves){
            System.out.println(e);    
        }
        
    }
}
