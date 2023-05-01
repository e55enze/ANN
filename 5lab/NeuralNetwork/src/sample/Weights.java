package sample;

import java.text.DecimalFormat;
import java.util.Random;

public class Weights {
    DecimalFormat df = new DecimalFormat("##.#####");
    double a = -0.5;
    double b =  0.5;
    double mu; // коэффициент обучения
    double[][] weh; // связи между нейронами от входного к скрытому (input -> hidden)
    double[][] who; // связи между нейронами скрытого и выходного слоя (hidden -> output)
    Random rnd = new Random();
    Weights(int enters, int hidden, int output, double trainingCoefficient){
        weh = initWeightsEH(enters, hidden);
        who = initWeightsHO(hidden,output);
        mu = trainingCoefficient;
    }

    // генерация весовых коэффициентов Enter -> Hidden
    public double[][] initWeightsEH(int enters, int hidden){
        double[][] weightsEH = new double[enters][hidden];
        for (int i = 0; i < enters; i++){
            for (int j = 0; j < hidden; j++) {
                weightsEH[i][j] = a + rnd.nextDouble() * (b - a);
            }
        }
        return weightsEH;
    }

    // генерация весовых коэффициентов Hidden -> Output
    public double[][] initWeightsHO(int hidden, int output){
        double[][] weightsEH = new double[hidden][output];
        for (int j = 0; j < hidden; j++){
            for (int k = 0; j < output; j++) {
                weightsEH[j][k] = a + rnd.nextDouble() * (b - a);
            }
        }
        return weightsEH;
    }

    public void weightsCorrection(double[] errOutput, double[] errHidden, Layers layers){
        outputLayerWeightsCorrection(errOutput, layers);
        hiddenLayerWeightsCorrection(errHidden, layers);
    }

    public void outputLayerWeightsCorrection(double[] errOutput, Layers layers){
        // System.out.println("\nКоррекция весов выходного слоя:");
        // Коррекция весов между скрытым и выходным слоем
        for (int i = 0; i < who.length; i++) {
            for (int j = 0; j < who[i].length; j++) {
                double deltaE = mu * layers.hidden[i] * errOutput[j];
                who[i][j] = who[i][j] + deltaE;
            }
        }
    }

    public void hiddenLayerWeightsCorrection(double[] errHidden, Layers layers){
        // System.out.println("\nКоррекция весов скрытого слоя:");
        // Коррекция весов между входным и скрытым слоем
        for (int i = 0; i < weh.length; i++) {
            for (int j = 0; j < weh[i].length; j++) {
                double deltaW = mu * layers.enter[i] * errHidden[j];
                //  weh[i][j] = weh[i][j] - weh[i][j] * deltaW;
                weh[i][j] = weh[i][j] + deltaW;
            }
        }
    }

    public void printWeights(double[][] weights){
        int length = 0;
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                System.out.print(df.format(weights[i][j]) + "  ");
                length++;
            }
        }
    }
}