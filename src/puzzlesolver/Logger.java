/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author lucasgb
 */
public class Logger {

    private File arq;
    private BufferedWriter bw = null;
    private FileWriter fw = null;

    public Logger() {
        try {
            arq = new File("src/puzzlesolver/Log.txt");
            arq.delete();
            arq.createNewFile();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeToFile(int[][] matriz) throws IOException {
        try {
            fw = new FileWriter(arq.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("Entrada inicial:\n\n");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    bw.write("" + matriz[i][j]);
                    bw.write(" ");
                }
                bw.write("\n");
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeToFile(ArrayList<Puzzle> p, int nFilhos) throws IOException {
        try {
            fw = new FileWriter(arq.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.append("\nNúmero de filhos: " + nFilhos);
            bw.append("\n");

            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < nFilhos; i++) {
                    for (int j = 0; j < 3; j++) {
                        bw.append("" + p.get(i).getPuzzle()[k][j]);
                        bw.append(" ");
                    }
                    bw.append("      ");
                }
                bw.append("\n");
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    void writeSelectedMove(int[][] matriz, int index) {
        try {
            fw = new FileWriter(arq.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("\nFilho selecionado: " + index + "\n\n");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    bw.write("" + matriz[i][j]);
                    bw.write(" ");
                }
                bw.write("\n");
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void writePath(ArrayList<Puzzle> path) throws IOException {
        try {
            fw = new FileWriter(arq.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("\nPATH: \n\n");
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < path.size(); i++) {
                    for (int j = 0; j < 3; j++) {
                        bw.append("" + path.get(i).getPuzzle()[k][j]);
                        bw.append(" ");
                    }
                    bw.append("      ");
                }
                bw.append("\n");
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void writeTotalNodes(int t, int c){
           try {
            fw = new FileWriter(arq.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("\nTotal de nós expandidos: "+t);            
            bw.write("\nTotal de nós expandidos no caminho: "+c+"\n\n");            

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }    
}