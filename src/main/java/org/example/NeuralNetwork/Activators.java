package org.example.NeuralNetwork;


// Sigmoid activation function and its derivative.
// sigmoid(x) squashes input to range (0,1), useful for neural network outputs.
// sigmoidDerivative(x) is used in backpropagation for gradient calculation.

public class Activators {

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDerivative(double x) {
        return x * (1 - x);
    }



}
