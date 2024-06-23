package org.example;

import org.example.Data.DataLoader;
import org.example.NeuralNetwork.NeuralNetwork;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(1, 10, 1, 0.1);
        List<double[]> trainingData = DataLoader.loadTrainingData("data/sine_data.csv");

        // Train the network
        int epochs = 20000;
        for (int i = 0; i < epochs; i++) {
            for (double[] data : trainingData) {
                double[] input = {data[0]};
                double[] target = {data[1]};
                nn.train(input, target);
            }
        }

        // Test the network
        System.out.println("Testing the network:");
        for (double x = 0; x <= 2 * Math.PI; x += 0.5) {
            double[] input = {x};
            double[] output = nn.feedForward(input);
            System.out.printf("sin(%.2f) = %.4f (Actual: %.4f)%n", x, output[0], Math.sin(x));
        }
    }
}