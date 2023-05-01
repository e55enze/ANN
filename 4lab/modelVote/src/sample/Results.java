package sample;

import models.Bord;
import models.Condorse;
import models.RelativeMajority;

public class Results {

    public String calcResult(String data, int numAlters, int numAgents){
        String res ="";
        int[][] matrix = generateMatrix(data, numAlters, numAgents);
        res = RelativeMajority.modelRelMaj(matrix);
        res += Condorse.modelCondorse(matrix);
        res += Bord.modelBord(matrix);
        return res;
    }

    public int[][] generateMatrix(String data, int numAlters,int numAgents){
        int[][] matrix = new int[numAgents][numAlters];
        String[] dataArr = data.split(";");
        for (int i = 0; i < dataArr.length; i++) {
//            System.out.println(dataArr[i].replaceAll("[^\\d ]",""));
            String newStr = dataArr[i].replaceAll("[^\\d ]","") + " ";
//            System.out.println(newStr);
            String[] parseAlters = newStr.split(" ");
            for (int j = 0; j < parseAlters.length; j++) {
//                System.out.print(parseAlters[j] + " ");
                matrix[i][j] = Integer.parseInt(parseAlters[j]);
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    public void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
