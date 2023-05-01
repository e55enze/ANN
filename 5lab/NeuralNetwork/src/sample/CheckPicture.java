package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CheckPicture {
    public Image img;
    private static double[][] matrix;
    NumberFormat nf = new DecimalFormat("#0.0");

    public int getResult(Image image, MLP mlp, String outputValue) throws IOException {
        PixelReader pr = image.getPixelReader();
        GridPane grid = new GridPane();
        Color[] colors = {Color.BLUE, Color.GRAY,Color.WHITE};
        int rowNum = 28, colNum = 28;
        matrix = new double[rowNum][colNum];
        for(int row = 0; row < rowNum; row++){
            Rectangle recRow = new Rectangle();
            recRow.setFill(colors[2]);
            for(int col = 0; col < colNum; col++){
                double mean = mergingCellsIntoPixel(col, row, pr) / 100;
                System.out.print(nf.format(mean) + "  ");
                Rectangle rec = new Rectangle();
                rec.setWidth(10); rec.setHeight(10);
                if(mean > 0.7) {
                // установка заливки на canvas
                    rec.setFill(colors[0]);
                // запись найденного среднего в матрицу
                }
                else{
                    if (mean > 0.1 & mean <= 0.7)
                    {
                        matrix[row][col] =  mean;
                    }
                    else
                    {
                        rec.setFill(colors[2]);
                    }
                }
				matrix[row][col] =  mean;
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }
        System.out.println("\nMatrix of pixels for input data of NN:");
        printMatrix(matrix);
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 280, 280);
        double[] matrixList = matrixToList(matrix);
        int answer = mlp.getAnswer(matrixList, outputValue, mlp.layers);
        img =  scene.snapshot(null);
        return answer;
    }

    //преобразование двумерного массива в одномерный
    public double[] matrixToList(double[][] matrix){
        int pos =0;
        double[] matrixList = new double[28*28];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixList[pos] = matrix[i][j];
                pos++;
            }
        }
        return matrixList;
    }

    public void printMatrix(double[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(nf.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    // разбиение изображения на пиксели
   public double mergingCellsIntoPixel(int x, int y, PixelReader pr) {
       // среднее значение
       double mean = 0;
       for (int i = y * 10; i < y * 10 + 10; i++) {
           for (int j = x * 10; j < x * 10 + 10; j++) {
               // invert in darkness
               double valDark = (1 - pr.getColor(j, i).getBrightness());
               mean = (double) (mean + valDark);
           }
       }
       return mean;
   }
}
