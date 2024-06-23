package org.example.SinGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SineDataGenerator {
    public static void main(String[] args) {
        String filename = "sine_data.csv";
        double start = 0;
        double end = 2 * Math.PI;
        int desiredDataPoints = 20000;

        // Calculate step size to get at least 20,000 data points
        double step = (end - start) / (desiredDataPoints - 1);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            int count = 0;
            for (double x = start; x <= end; x += step) {
                double y = Math.sin(x);
                writer.printf("%.8f,%.8f%n", x, y);
                count++;
            }
            System.out.println("Sine data has been written to " + filename);
            System.out.println("Total data points generated: " + count);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
