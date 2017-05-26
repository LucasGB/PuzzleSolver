/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author lucasgb
 */
public class PuzzleSolver {

    private static ArrayList<Puzzle> path = new ArrayList<Puzzle>();
    private static int index;
    public static int totalNodes;

    private static int[][] goal = new int[3][3];

//    private static boolean isValid(Puzzle puzzle) {
//
//        for (Puzzle p : path) {
//            if (Arrays.deepEquals(puzzle.getPuzzle(), p.getPuzzle())) {
//                return false;
//            }
//        }
//        return true;
//        
//        
//        
//    }

    private static boolean isValid(Puzzle puzzle) {
        int c;
        for (Puzzle p : path) {
             c = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (puzzle.getPuzzle()[i][j] == p.getPuzzle()[i][j]) {
                        c++;
                    }
                }
            }
            if (c == 9) {
                System.out.println("ROLA");
                return false;
            }             
        }
        return true;
    }

    private static boolean isSolved(Puzzle puzzle) {
        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle.getPuzzle()[i][j] == goal[i][j]) {
                    c++;
                }
            }
        }
        if (c == 9) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Logger log = new Logger();
        goal[0][0] = 1;
        goal[0][1] = 2;
        goal[0][2] = 3;
        goal[1][0] = 4;
        goal[1][1] = 5;
        goal[1][2] = 6;
        goal[2][0] = 7;
        goal[2][1] = 8;
        goal[2][2] = 0;

        Scanner input = new Scanner(new File("src/puzzlesolver/puzzle.txt"));

        input = new Scanner(new File("src/puzzlesolver/puzzle.txt"));
        int[][] matriz = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (input.hasNextInt()) {
                    matriz[i][j] = input.nextInt();
                }
            }
        }
        log.writeToFile(matriz);
        Puzzle puzzle = new Puzzle(matriz);
        Heuristicas h = new Heuristicas();

        puzzle.gerarFilhos();

        while (!isSolved(puzzle)) {
            System.out.println("Começa WHILE");
            for(int i = 0; i <  puzzle.getnFilhos(); i++){
                System.out.println("Filho "+i);
                puzzle.getFilhos().get(i).print();
            }
            path.add(puzzle);

            log.writeToFile(puzzle.getFilhos(), puzzle.getnFilhos());

            index = h.calculateManhattanDistance(puzzle.getFilhos());

            index = h.desempata(index, puzzle.getFilhos());
            if(puzzle.getFilhos().get(index).getnFilhos() == 0){
                puzzle.getFilhos().get(index).gerarFilhos();
            }

            
            //Valida se o puzzle no index já foi visitado e retorna um que não foi
            while (!isValid(puzzle.getFilhos().get(index))) {
                System.out.println("BATATA");
                puzzle.getFilhos().get(index).print();
                puzzle.getFilhos().remove(index);
                index = h.calculateManhattanDistance(puzzle.getFilhos());
            }

            log.writeSelectedMove(puzzle.getFilhos().get(index).getPuzzle(), index);
            puzzle = puzzle.getFilhos().get(index);
        }
        path.add(puzzle);
        
        log.writePath(path);
        
        int c = 0;
        for(int i = 0; i < path.size()-1; i++){
            c += path.get(i).getnFilhos();
        }        
        log.writeTotalNodes(totalNodes, c);

    }

}
