/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lucasgb
 */
public class Heuristicas {

    private ArrayList<Float> distanceList;
    private ArrayList<Integer> listaValAdj;
    private ArrayList<Float> listaManhattan;
    private ArrayList<Float> listaEuclideana;

    public int calculateEuclideanDistance(ArrayList<Puzzle> filhos) {
        listaEuclideana = new ArrayList<Float>();

        float distancia = 0;
        for (int k = 0; k < filhos.size(); k++) {
            distancia = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (filhos.get(k).getPuzzle()[i][j] == 1) {
                        distancia += Math.sqrt((Math.abs(i - 0) ^ 2) + (Math.abs(j - 0) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 2) {
                        distancia += Math.sqrt((Math.abs(i - 0) ^ 2) + (Math.abs(j - 1) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 3) {
                        distancia += Math.sqrt((Math.abs(i - 0) ^ 2) + (Math.abs(j - 2) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 4) {
                        distancia += Math.sqrt((Math.abs(i - 1) ^ 2) + (Math.abs(j - 0) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 5) {
                        distancia += Math.sqrt((Math.abs(i - 1) ^ 2) + (Math.abs(j - 1) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 6) {
                        distancia += Math.sqrt((Math.abs(i - 1) ^ 2) + (Math.abs(j - 2) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 7) {
                        distancia += Math.sqrt((Math.abs(i - 2) ^ 2) + (Math.abs(j - 0) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 8) {
                        distancia += Math.sqrt((Math.abs(i - 2) ^ 2) + (Math.abs(j - 1) ^ 2));
                    } else if (filhos.get(k).getPuzzle()[i][j] == 0) {
                        distancia += Math.sqrt((Math.abs(i - 2) ^ 2) + (Math.abs(j - 2) ^ 2));
                    }

                }
            }
            listaEuclideana.add(distancia);
        }

        int minIndex = listaEuclideana.indexOf(Collections.min(listaEuclideana));

        return minIndex;

    }

    public int calculateManhattanDistance(ArrayList<Puzzle> filhos) {
        listaManhattan = new ArrayList<Float>();

        float distancia = 0;
        for (int k = 0; k < filhos.size(); k++) {
            distancia = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (filhos.get(k).getPuzzle()[i][j] == 1) {
                        distancia += Math.abs(i - 0) + Math.abs(j - 0);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 2) {
                        distancia += Math.abs(i - 0) + Math.abs(j - 1);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 3) {
                        distancia += Math.abs(i - 0) + Math.abs(j - 2);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 4) {
                        distancia += Math.abs(i - 1) + Math.abs(j - 0);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 5) {
                        distancia += Math.abs(i - 1) + Math.abs(j - 1);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 6) {
                        distancia += Math.abs(i - 1) + Math.abs(j - 2);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 7) {
                        distancia += Math.abs(i - 2) + Math.abs(j - 0);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 8) {
                        distancia += Math.abs(i - 2) + Math.abs(j - 1);
                    } else if (filhos.get(k).getPuzzle()[i][j] == 0) {
                        distancia += Math.abs(i - 2) + Math.abs(j - 2);
                    }

                }
            }
            listaManhattan.add(distancia);
        }

        int minIndex = listaManhattan.indexOf(Collections.min(listaManhattan));

        return minIndex;

    }

    public int desempata(int minIndex, ArrayList<Puzzle> filhos) {
        ArrayList<Puzzle> henrique = new ArrayList<Puzzle>();
        ArrayList<Integer> aux = new ArrayList<Integer>();

        Float compara = listaManhattan.get(minIndex);
        henrique.add(filhos.get(minIndex));

        for (int i = 0; i < listaManhattan.size(); i++) {
            if ((listaManhattan.get(i).equals(compara)) && (i != minIndex)) {
                filhos.get(i).setFlag(i);
                henrique.add(filhos.get(i));
            }
        }
        return test(minIndex, henrique);
//        if (henrique.size() > 1) {
//            for (int i = 0; i < henrique.size(); i++) {
//                henrique.get(i).gerarFilhos();
//                aux.add(calculateManhattanDistance(henrique.get(i).getFilhos()));
//            }           
//            int index = aux.indexOf(Collections.min(aux));
//            return henrique.get(index).getFlag();
//        }
//        return minIndex;
    }

    private int test(int minIndex, ArrayList<Puzzle> filhos){         
        ArrayList<Integer> aux = new ArrayList<Integer>();

        if (filhos.size() > 1) {
            for (int i = 0; i < filhos.size(); i++) {
                filhos.get(i).gerarFilhos();
                aux.add(calculateManhattanDistance(filhos.get(i).getFilhos()));
            }           
            int index = aux.indexOf(Collections.min(aux));
            test(index, filhos);
            return filhos.get(index).getFlag();
        }
        return minIndex;
    }
    
    public int setValorAdjCor(ArrayList<Puzzle> filhos) {
        listaValAdj = new ArrayList<Integer>();

        int pecasCertas = 0;
        int pecasAdj = 0;

        for (int k = 0; k < filhos.size(); k++) {
            if (filhos.get(k).getPuzzle()[0][0] == 1) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[0][0] == 2 || filhos.get(k).getPuzzle()[0][0] == 4) {
                pecasAdj++;
            }
            if (filhos.get(k).getPuzzle()[0][1] == 2) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[0][1] == 1 || filhos.get(k).getPuzzle()[0][1] == 5 || filhos.get(k).getPuzzle()[0][1] == 3) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[0][2] == 3) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[0][2] == 2 || filhos.get(k).getPuzzle()[0][2] == 6) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[1][0] == 4) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[1][0] == 1 || filhos.get(k).getPuzzle()[1][0] == 7 || filhos.get(k).getPuzzle()[1][0] == 5) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[1][1] == 5) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[1][1] == 2 || filhos.get(k).getPuzzle()[1][1] == 4 || filhos.get(k).getPuzzle()[1][1] == 6 || filhos.get(k).getPuzzle()[1][1] == 8) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[1][2] == 6) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[1][2] == 0 || filhos.get(k).getPuzzle()[1][2] == 3 || filhos.get(k).getPuzzle()[1][2] == 5) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[2][0] == 7) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[2][0] == 4 || filhos.get(k).getPuzzle()[2][0] == 8) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[2][1] == 8) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[2][1] == 7 || filhos.get(k).getPuzzle()[2][1] == 0 || filhos.get(k).getPuzzle()[2][1] == 5) {
                pecasAdj++;
            }

            if (filhos.get(k).getPuzzle()[2][2] == 0) {
                pecasCertas++;
            } else if (filhos.get(k).getPuzzle()[2][2] == 6 || filhos.get(k).getPuzzle()[2][2] == 8) {
                pecasAdj++;
            }

            listaValAdj.add(pecasAdj + pecasCertas);
        }
        int minIndex = listaValAdj.indexOf(Collections.min(listaValAdj));

        return minIndex;
    }

    public int somaHeuristicas(ArrayList<Puzzle> filhos) {
        distanceList = new ArrayList<Float>();

        calculateManhattanDistance(filhos);
        setValorAdjCor(filhos);

        for (int i = 0; i < filhos.size(); i++) {
            distanceList.add(listaManhattan.get(i) + listaValAdj.get(i));
        }

        int minIndex = distanceList.indexOf(Collections.min(distanceList));

        return minIndex;
    }

    public void print() {
        for (Float f : distanceList) {
            System.out.println(f);
        }
    }

    /**
     * @return the distanceList
     */
    public ArrayList<Float> getDistanceList() {
        return distanceList;
    }
}
