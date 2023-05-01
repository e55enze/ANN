package sample;

import mnist.MnistDataReader;
import mnist.MnistMatrix;
import java.io.IOException;
import java.text.DecimalFormat;

public class MLP {
    DecimalFormat df = new DecimalFormat("##.#####");
    int numHidden = 250; // количество нейронов в скрытом слое
    double mu = 0.4; // learning rate of 0.01 - 0.4
    static double globalError;
    static double localError;
    double[] errOutput;
    double[] errHidden;
    Layers layers;

    MLP() throws IOException {
        String filePath = "B:\\Idea_Projects\\Study\\NeuralNetwork\\src\\data\\";
//        MnistMatrix[] mnistMatrix10k = new MnistDataReader().readData(filePath+"t10k-images.idx3-ubyte",filePath+"t10k-labels.idx1-ubyte");
		// 60 k images
        MnistMatrix[] mnistMatrix = new MnistDataReader().readData(filePath+"train-images.idx3-ubyte",filePath+"train-labels.idx1-ubyte");
        Train.printMnistMatrix(mnistMatrix[0]);
        System.out.println("Amount of matrix (pictures) in the training set: " + (int) (mnistMatrix.length));
        System.out.println();
        double[] hidden = new double[numHidden];
        double[] output = new double[10];
        double[] matrix = Train.mnistMatrixToDouble(mnistMatrix[0]);
        double[] enters = matrix;
		// Весовые коэффициенты слоев
        Weights weights = new Weights(enters.length, hidden.length, output.length, mu);
        layers = new Layers(weights, enters, hidden, output);
        layers.enter = enters;
        int epoch = 1; // значение эпохи
        int[] numbers = new int[10];
        double errorValue = 0.15;
        globalError = 1;
        localError = 0;
        int iter = 0;

		// цикл обучения нейросети
        while (iter < mnistMatrix.length || globalError < errorValue) {
            int outputValue = mnistMatrix[iter].getLabel();
            layers.enter = Train.mnistMatrixToDouble(mnistMatrix[iter]);
            layers.calcValueHiddenLayer();
            layers.calcValueOutputLayer();
            double[] targetOutput = new double[output.length];
			// присвоение значения 1 тому выходному нейрону для данной цифру
            targetOutput[outputValue] = 1;
            double diff = layers.diffTargetActualValues(outputValue, targetOutput);
            localError += diff;
            globalError = localError / epoch;

            int getMaxPos = getPosMaxOutput(layers);
            if(epoch % 1000 == 0) {
                System.out.println("Epoch " + epoch);
                System.out.println("globalError = " + df.format(globalError));
                System.out.println("difference (target - actual)  = " + df.format(diff));
            }
            if (getMaxPos == outputValue)
                numbers[outputValue]++;
            errOutput = layers.calcErrorOutputLayer(targetOutput);
            errHidden = layers.calcErrorHiddenLayer(errOutput);
            weights.weightsCorrection(errOutput, errHidden, layers);
            epoch++;

            if(globalError < errorValue)
                break;
            iter++;
        }
        System.out.println("NN training with the training set has been completed!");

        int rdNum = 0;
        for (int i = 0; i <numbers.length ; i++) {
            System.out.println(i + " : " + numbers[i]);
            rdNum += numbers[i];
        }
        System.out.println("Amount of numbers, which has been truth defined: " +  rdNum);
        System.out.println("\nrights / epochs");
        System.out.println(rdNum +" / " + epoch);
        System.out.println("global error value Err = " + globalError);
        System.out.println("Hidden layer neurons num: " + hidden.length);
        System.out.println("Coefficient of learning (m'u) = " + mu );
    }

	//    получение ответа при распознавании числа
    public int getAnswer(double[] input, String outputValue, Layers layers){
        int answer = 0;
        layers.enter = input;
        layers.calcValueHiddenLayer();
        layers.calcValueOutputLayer();
        if(outputValue!="" && outputValue != null){
            int outputValueInt = Integer.parseInt(outputValue);
            double[] targetOutput = new double[layers.output.length];
            for (int i = 0; i < layers.output.length; i++) {
                targetOutput[i] = 0;
            }
            // присвоение значения 1 выходному нейрону, который отвечает за данную цифру
            targetOutput[outputValueInt] = 1;
            for (int i = 0; i < layers.output.length; i++) {
                System.out.print(df.format(layers.output[i]) + "  ");
            }
            System.out.println("\nOutput value of neuron for number: "
                    + outputValueInt + " | y = " + df.format(layers.output[outputValueInt]));
            errOutput = layers.calcErrorOutputLayer(targetOutput);
            errHidden = layers.calcErrorHiddenLayer(errOutput);
            System.out.println("Error value = " + df.format(
                    targetOutput[outputValueInt] - layers.output[outputValueInt]));
            System.out.println("\nCorrection of weights has been completed");
            layers.weights.weightsCorrection(errOutput,errHidden, layers);
        }
        answer = getPosMaxOutput(layers);
        return answer;
    }

	//    поиск максимального значения в выходном слое
    public int getPosMaxOutput(Layers layers){
        int pos = 0; double maxValue = 0;
        for (int i = 0; i < layers.output.length; i++) {
            if(layers.output[i] > maxValue){
                maxValue = layers.output[i];
                pos = i;
            }
        }
        return pos;
    }
}
