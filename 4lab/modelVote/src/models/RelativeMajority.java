package models;

public class RelativeMajority {

    public static String modelRelMaj(int[][] matrix){
        String res = "Мажоритарная избирательная модель:\n";
        for (int i = 0; i < matrix.length; i++) {
            String[] resultArr = maxValue(matrix[i]);
            int pos = Integer.parseInt(resultArr[0]);
            int max = Integer.parseInt(resultArr[1]);
            res += "alter №" + pos + " win, with number of votes " + max + "\n";
        }
        return res+"\n";
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
