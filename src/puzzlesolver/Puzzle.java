/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lucasgb
 */
public class Puzzle {
    
    private ArrayList<Puzzle> filhos = new ArrayList<Puzzle>();
    private int nFilhos;
    private int puzzle[][];
    private int x, y, flag;
    
    public Puzzle(int[][] puzzle){
        this.puzzle = puzzle;
        this.nFilhos = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(this.puzzle[i][j] == 0){
                    this.x = i;
                    this.y = j;
                }
            }
        }
    }
    
    public void swap(int[][] matriz, int offSetX, int offSetY){
       int[][] aux = new int[3][3];
       
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                aux[i][j] = matriz[i][j];
            }
        }
       
        int zero = aux[x][y];
        aux[x][y] = aux[x+offSetX][y+offSetY];
        aux[x+offSetX][y+offSetY] = zero;
        filhos.add(new Puzzle(aux));       
        this.nFilhos++;
        PuzzleSolver.totalNodes += this.nFilhos;
    }
   
    
    public void gerarFilhos(){
        Moves m = new Moves();
        m.generateMoves(this);
        
        for(Moves.movements e : m.getMoves()){                  
            if(e == Moves.movements.DOWN){
                swap(puzzle, 1, 0);
            }
            if(e == Moves.movements.UP){
                swap(puzzle, -1, 0);
            }
            if(e == Moves.movements.LEFT){
                swap(puzzle, 0, -1);
            }
            if(e == Moves.movements.RIGHT){
                swap(puzzle, 0, 1);
            }
        }
    }

    /**
     * @return the puzzle
     */
    public int[][] getPuzzle() {
        return puzzle;
    }

    /**
     * @param puzzle the puzzle to set
     */
    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }
    
    public void print(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(" "+this.puzzle[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the filhos
     */
    public ArrayList<Puzzle> getFilhos() {
        return filhos;
    }

    /**
     * @return the nFilhos
     */
    public int getnFilhos() {
        return nFilhos;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}