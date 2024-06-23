package org.example.NeuralNetwork;

public class Backpropagation {
    private double learningRate;

    public Backpropagation(double learningRate) {
        this.learningRate = learningRate;
    }

    public void updateWeightsAndBiases(double[][] weightsHO, double[] biasO, double[][] weightsIH, double[] biasH,
                                       double[] outputs, double[] hidden, double[] outputErrors, double[] hiddenErrors, double[] inputs) {
        // Update weights and biases for output layer
        for (int i = 0; i < weightsHO.length; i++) {
            for (int j = 0; j < weightsHO[i].length; j++) {
                weightsHO[i][j] += learningRate * outputErrors[i] * Activators.sigmoidDerivative(outputs[i]) * hidden[j];
            }
            biasO[i] += learningRate * outputErrors[i] * Activators.sigmoidDerivative(outputs[i]);
        }

        // Update weights and biases for hidden layer
        for (int i = 0; i < weightsIH.length; i++) {
            for (int j = 0; j < weightsIH[i].length; j++) {
                weightsIH[i][j] += learningRate * hiddenErrors[i] * Activators.sigmoidDerivative(hidden[i]) * inputs[j];
            }
            biasH[i] += learningRate * hiddenErrors[i] * Activators.sigmoidDerivative(hidden[i]);
        }
    }

    public double[] calculateOutputErrors(double[] targets, double[] outputs) {
        double[] outputErrors = new double[targets.length];
        for (int i = 0; i < targets.length; i++) {
            outputErrors[i] = targets[i] - outputs[i];
        }
        return outputErrors;
    }

    public double[] calculateHiddenErrors(double[] outputErrors, double[][] weightsHO) {
        double[] hiddenErrors = new double[weightsHO[0].length];
        for (int i = 0; i < hiddenErrors.length; i++) {
            double error = 0;
            for (int j = 0; j < outputErrors.length; j++) {
                error += outputErrors[j] * weightsHO[j][i];
            }
            hiddenErrors[i] = error;
        }
        return hiddenErrors;
    }
}
