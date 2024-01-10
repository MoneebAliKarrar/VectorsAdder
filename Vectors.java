package any.vectors;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Vector {

    private double[] values;

    public Vector(double[] values) {
        this.values = values;
    }

    public int getlength() {
        return values.length;
    }

    public double[] getvalues() {
        return values;
    }

    public static Vector add(List<Vector> vectors) throws DifferentVectorsLengthsException {
        List<Integer> allLengths = new ArrayList<>();
        for (Vector vector : vectors) {
            allLengths.add(vector.getlength());
        }
        int commonLength = vectors.get(0).getlength();
        for (int vectorLength : allLengths) {
            if (vectorLength != commonLength) {
                throw new DifferentVectorsLengthsException(allLengths, "oh noo!");
            }
        }
        double[] result = new double[commonLength];
        for (Vector vector : vectors) {
            for (int i = 0; i < result.length; i++) {
                result[i] += vector.getvalues()[i];
            }
        }
        return new Vector(result);
    }
}

class DifferentVectorsLengthsException extends Exception {

    private List<Integer> allVectorLengths;

    public DifferentVectorsLengthsException() {
        super();
    }

    public DifferentVectorsLengthsException(List<Integer> allVectorLengths, String message) {
        super(message);
        this.allVectorLengths = allVectorLengths;
    }

    public List<Integer> getAllVectorLengths() {
        return allVectorLengths;
    }

}

public class Vectors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the vectors, one per line. Leave an empty line to finish.");
            try {
                List<Vector> vectors = new ArrayList<>();
                String input;
                while (true) {

                    System.out.println("enter the coefficients of the vectors (,) for separations");
                    input = scanner.nextLine();
                    if (input.isEmpty()) {
                        break;
                    }
                    double[] values;
                    values = parsevector(input);
                    Vector vector = new Vector(values);
                    vectors.add(vector);

                }
                if (vectors.isEmpty()) {
                    System.out.println("No valid vectors were entered.");
                    continue;
                }
                Vector result = Vector.add(vectors);
                System.out.println(Arrays.toString(result.getvalues()));
                printtofile(result);
                System.out.println("The result has been calculated correctly ,it's written to the file result");

                break;
            } catch (DifferentVectorsLengthsException e) {
                List<Integer> allLengths = e.getAllVectorLengths();
                System.out.println(e.getMessage());
                System.out.println("Vector lengths: " + allLengths);
            }

        }

    }

    public static double[] parsevector(String input) {
        List<Double> values = new ArrayList<>();
        String[] parts = input.split(",");
        for (String part : parts) {
            try {
                double value = Double.parseDouble(part);
                values.add(value);
            } catch (NumberFormatException nfe) {
                System.out.println("this is ignored " + part);
            }
        }
        if (values.isEmpty()) {
            return null;
        }
        double[] vector = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            vector[i] = values.get(i);
        }
        return vector;
    }

    public static void printtofile(Vector vector) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
            for (double value : vector.getvalues()) {
                writer.write(Double.toString(value) + "  ");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
