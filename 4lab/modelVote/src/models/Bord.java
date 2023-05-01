package models;

public class Bord {
    public static String modelBord(int[][] matrix){
        String res ="Модель Борда:\n";
        System.out.println();
        int[] array = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                array[j] += matrix[i][j] - 1 -j;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        String[] arr = maxValue(array);
        res += "alter №" + arr[0] +" win, with value " + arr[1];
        return res;
    }

    public static String[] maxValue(int[] alters){
        int pos =0;
        String[] res = new String[2];
        int maxValue = 0;
        for (int i = 0; i < alters.length; i++) {
            if(alters[i] > maxValue){
                maxValue = alters[i];
                pos = i + 1;
                res[0] = pos + "";
                res[1] = maxValue + "";
            }
        }
        return res;
    }
}
