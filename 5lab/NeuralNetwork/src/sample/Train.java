package sample;

import javafx.scene.image.Image;
import mnist.MnistMatrix;

import java.io.IOException;

public class Train {
    public static Image img;
    public MLP goTrain() throws IOException {
        MLP mlp = new MLP();
        return mlp;
    }

	// преобразование в double матрицы из датасета
    public static double[] mnistMatrixToDouble(final MnistMatrix mnistMatrices){
        double[] matrix = new double[28*28];
        int pos = 0;
        for (int r = 0; r < mnistMatrices.getNumberOfRows(); r++) {
            for (int c = 0; c < mnistMatrices.getNumberOfColumns(); c++) {
                double value = (double) mnistMatrices.getValue(r,c)/256;
                matrix[pos] = value;
                pos++;
            }
        }
        return matrix;
    }

    public static void printMnistMatrix(final MnistMatrix mnistMatrices){
        System.out.println(mnistMatrices.getLabel());
        for (int r = 0; r < mnistMatrices.getNumberOfRows(); r++) {
            for (int c = 0; c < mnistMatrices.getNumberOfColumns(); c++) {
                System.out.print(mnistMatrices.getValue(r, c) +" ");
            }
            System.out.println();
        }
        System.out.println("Complete");
    }
}