package org.example.NeuralNetwork;

import java.util.Random;

public class NeuralNetwork {
    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;
    private double[][] weightsIH;
    private double[][] weightsHO;
    private double[] biasH;
    private double[] biasO;
    private double learningRate;
    private Backpropagation backpropagation;



    // Constructor: Initializes the neural network with specified architecture.
    // Sets up weight matrices and bias arrays with random Gaussian values.
    // This random initialization provides a starting point for learning.
    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, double learningRate) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        this.learningRate = learningRate;
        this.backpropagation = new Backpropagation(learningRate);


        Random rand = new Random();

        // Initialize weights and biases
        weightsIH = new double[hiddenNodes][inputNodes];
        weightsHO = new double[outputNodes][hiddenNodes];
        biasH = new double[hiddenNodes];
        biasO = new double[outputNodes];

        for (int i = 0; i < hiddenNodes; i++) {
            for (int j = 0; j < inputNodes; j++) {
                weightsIH[i][j] = rand.nextGaussian();
            }
            biasH[i] = rand.nextGaussian();
        }

        for (int i = 0; i < outputNodes; i++) {
            for (int j = 0; j < hiddenNodes; j++) {
                weightsHO[i][j] = rand.nextGaussian();
            }
            biasO[i] = rand.nextGaussian();
        }
    }


    public double[] feedForward(double[] inputs) {
        double[] hidden = new double[hiddenNodes];
        double[] outputs = new double[outputNodes];

        // Calculate hidden layer
        for (int i = 0; i < hiddenNodes; i++) {
            double sum = 0;
            for (int j = 0; j < inputNodes; j++) {
                sum += inputs[j] * weightsIH[i][j];
            }
            hidden[i] = Activators.sigmoid(sum + biasH[i]);
        }

        // Calculate output layer
        for (int i = 0; i < outputNodes; i++) {
            double sum = 0;
            for (int j = 0; j < hiddenNodes; j++) {
                sum += hidden[j] * weightsHO[i][j];
            }
            outputs[i] = Activators.sigmoid(sum + biasO[i]);
        }

        return outputs;
    }

    public void train(double[] inputs, double[] targets) {
        // Feed forward
        double[] hidden = new double[hiddenNodes];
        double[] outputs = new double[outputNodes];

        // Calculate hidden layer
        for (int i = 0; i < hiddenNodes; i++) {
            double sum = 0;
            for (int j = 0; j < inputNodes; j++) {
                sum += inputs[j] * weightsIH[i][j];
            }
            hidden[i] = Activators.sigmoid(sum + biasH[i]);
        }

        // Calculate output layer
        for (int i = 0; i < outputNodes; i++) {
            double sum = 0;
            for (int j = 0; j < hiddenNodes; j++) {
                sum += hidden[j] * weightsHO[i][j];
            }
            outputs[i] = Activators.sigmoid(sum + biasO[i]);
        }

        // Backpropagation
        double[] outputErrors = backpropagation.calculateOutputErrors(targets, outputs);
        double[] hiddenErrors = backpropagation.calculateHiddenErrors(outputErrors, weightsHO);

        backpropagation.updateWeightsAndBiases(weightsHO, biasO, weightsIH, biasH, outputs, hidden, outputErrors, hiddenErrors, inputs);
    }}
