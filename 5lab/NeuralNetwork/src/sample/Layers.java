package sample;

import java.text.DecimalFormat;

public class Layers {
    double[] enter; // ������� �������� ��������
    double[] hidden; // ������� � ������� ����
    double[] output; // �������� ������
    DecimalFormat df = new DecimalFormat("##.#####");
    Weights weights;

    Layers(Weights weightsCoef, double[] enterLayer, double[] hiddenLayer, double[] outputLayer){
        enter = enterLayer;
        hidden = hiddenLayer;
        output = outputLayer;
        weights = weightsCoef;
    }

	// ������ �������� �������� ����
    public void calcValueHiddenLayer(){
        // System.out.println("\n�������� �������� ���� (x*w):");
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = 0;
            for (int j = 0; j < enter.length; j++) {
                hidden[i] += enter[j] * weights.weh[j][i];
            }
            hidden[i] = sigmoid(hidden[i]);
        }
    }
	
	// ������ �������� ��������� ����
    public void calcValueOutputLayer(){
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
            for (int j = 0; j < hidden.length; j++) {
                output[i] += hidden[j] * weights.who[j][i];
            }
            output[i] = sigmoid(output[i]);
        }
    }

    // �������� �������� � ��������� ��������
    public double diffTargetActualValues(int outputValue, double[] targetOutput){
        double diff = 0;
        diff = targetOutput[outputValue] - output[outputValue];
        return diff;
    }

	// ������ ���������� ������ ��������� ����
    public double[] calcErrorOutputLayer(double[] targetOutput){
        //  System.out.println("\n������ �������� ������ �������� ��������� ����:");
        double[] errOutput = new double[output.length];
        for (int i = 0; i < errOutput.length; i++) {
            // derivative of Sigmoid
            double derSigmoid = output[i] * (1 - output[i]);
            errOutput[i] = (targetOutput[i] - output[i]) * derSigmoid;
        }
        return errOutput;
    }
	
	// ������ ������ �������� �������� ����
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

	// ������������� �������
    public double sigmoid(double x){
        int k = 1; // Coefficient
        return 1 / (1 + Math.exp(-x * k));
    }
}