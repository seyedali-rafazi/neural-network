package org.example.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<double[]> loadTrainingData(String filename) {
        List<double[]> data = new ArrayList<>();
        try (BufferedReader dataReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                data.add(new double[]{x, y});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
