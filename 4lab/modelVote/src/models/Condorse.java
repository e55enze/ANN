package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Condorse {

    public static String modelCondorse(int[][] matrix){
        String res = "";
        res += clearWinner(0, matrix) +"\n";
        res += clearWinner(1,matrix)+"\n";
        res += modelSimpson(matrix);
        return res;
    }

    public static String clearWinner(int flag, int[][] matrix){
        String res ="";
        if(flag == 0)
            res = "Явный победитель:\n";
        else
            res = "Правило Копланда:\n";
        int[] sumArr = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length - 1; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int count_i = 0;
                int count_j = 0;
                for (int k = 0; k < matrix.length; k++) {
                    if(matrix[k][i] < matrix[k][j]){
                        count_i += 1;
                    }
                    else{
                        if(matrix[k][j] < matrix[k][i]){
                            count_j += 1;
                        }
                    }
                }
                if(count_i > count_j){
                    sumArr[i] += 1;
                    if(flag ==1 )
                        sumArr[j] -= 1;
                }
                else{
                    if(count_j > count_i){
                        sumArr[j] += 1;
                        if(flag == 1)
                            sumArr[i] -=1;
                    }
                }
            }
        }
        System.out.println();
        String[] max = maxValue(sumArr);
        int maxValue = Integer.parseInt(max[1]);
        int index = Integer.parseInt(max[0]);
        if(!max[2].equals("paradox")) {
            res += "[";
            for (int i = 0; i < sumArr.length; i++) {
                if (i < sumArr.length - 1)
                    res += sumArr[i] + ", ";
                if (i == sumArr.length - 1)
                    res += sumArr[i] + "]\n";
                System.out.print(sumArr[i] + "  ");
            }
            res += "alter №" + index + " win, with value " + maxValue;
        }
        else {
            String paradox ="";
            for (int i = index; i < sumArr.length; i++) {
                if(maxValue == sumArr[i])
                    paradox += " " + i+1 +", ";
            }
            res += paradox + " wins";

        }
        return res+"\n";
    }

    public static String[] maxValue(int[] alters){
        int pos =0;
        String[] res = new String[3];
        int maxValue = 0;
        res[2] = "";
        for (int i = 0; i < alters.length; i++) {
            if(alters[i] > maxValue){
                maxValue = alters[i];
                pos = i + 1;
                res[0] = pos + "";
                res[1] = maxValue + "";
            }
        }
        int numMax =0;
        for (int i = 0; i < alters.length; i++) {
            if(alters[i] == maxValue){
               numMax++;
            }
        }
        if(numMax>1)
            res[2] ="paradox";
        return res;
    }

    public static String modelSimpson(int[][] matrix){
        String res = "Модель симпсона:\n";
        int[] arrAssessment = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length ; i++) {
            List<Integer> arrayCount = new ArrayList<>();
            for (int j = 0 ; j < matrix[0].length; j++) {
                if(i == j) {

                }
                else {
                    int count_i = 0;
                    for (int k = 0; k < matrix.length; k++) {
                        if(matrix[k][i] < matrix[k][j])
                            count_i++;
                    }
                    arrayCount.add(count_i);
                }
            }
            int num = i +1;
            res += "mass count for" + num + " alter = " + Arrays.toString(arrayCount.toArray())+"\n";
            int[] arr = listToInt(arrayCount);
            arrAssessment[i] = minValue(arr);
        }
        res += "mass_assessments" + Arrays.toString(arrAssessment) + "\n";
        String[] dataMax = maxValue(arrAssessment);
        res += "alter №" + dataMax[0] + " win, with value " + dataMax[1] + "\n";
        return res;
    }

    public static int[] listToInt(List<Integer> list){
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int minValue(int[] alters){
        int minValue =10;
        for (int i = 0; i < alters.length; i++) {
            if(alters[i] < minValue){
                minValue = alters[i];
            }
        }
        return minValue;
    }
}
