package sample;

import java.text.DecimalFormat;

public class Layers {
    double[] enter; // входные значения нейронов
    double[] hidden; // нейроны в скрытом слое
    double[] output; // выходной нейрон
    DecimalFormat df = new DecimalFormat("##.#####");
    Weights weights;

    Layers(Weights weightsCoef, double[] enterLayer, double[] hiddenLayer, double[] outputLayer){
        enter = enterLayer;
        hidden = hiddenLayer;
        output = outputLayer;
        weights = weightsCoef;
    }

	// расчёт значений скрытого слоя
    public void calcValueHiddenLayer(){
        // System.out.println("\nЗначения скрытого слоя (x*w):");
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = 0;
            for (int j = 0; j < enter.length; j++) {
                hidden[i] += enter[j] * weights.weh[j][i];
            }
            hidden[i] = sigmoid(hidden[i]);
        }
    }
	
	// расчёт значений выходного слоя
    public void calcValueOutputLayer(){
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
            for (int j = 0; j < hidden.length; j++) {
                output[i] += hidden[j] * weights.who[j][i];
            }
            output[i] = sigmoid(output[i]);
        }
    }

    // разность целевого и реального значений
    public double diffTargetActualValues(int outputValue, double[] targetOutput){
        double diff = 0;
        diff = targetOutput[outputValue] - output[outputValue];
        return diff;
    }

	// расчёт количества ошибки выходного слоя
    public double[] calcErrorOutputLayer(double[] targetOutput){
        //  System.out.println("\nРасчёт значений ошибки нейронов выходного слоя:");
        double[] errOutput = new double[output.length];
        for (int i = 0; i < errOutput.length; i++) {
            // derivative of Sigmoid
            double derSigmoid = output[i] * (1 - output[i]);
            errOutput[i] = (targetOutput[i] - output[i]) * derSigmoid;
        }
        return errOutput;
    }
	
	// Расчёт ошибки нейронов скрытого слоя
    public double[] calcErrorHiddenLayer(double[] errOutput){
        double[] errHidden = new double[hidden.length];
        for (int j = 0; j < errHidden.length; j++) {
            for (int k = 0; k < errOutput.length; k++) {
                errHidden[j] += errOutput[k] * weights.who[j][k];
            }
            errHidden[j] = hidden[j] * (1 - hidden[j]) * errHidden[j];
        }
        return errHidden;
    }

	// Сигмоидальная функция
    public double sigmoid(double x){
        int k = 1; // Coefficient
        return 1 / (1 + Math.exp(-x * k));
    }
}