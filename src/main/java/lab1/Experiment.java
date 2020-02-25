package lab1;

import java.util.Arrays;

public class Experiment {
    private static final int TWO = 2;
    private static final int NUM_OF_EXPERIMENTS = 8;
    private int start;
    private int end;
    private int a0;
    private int a1;
    private int a2;
    private int a3;
    private double[] x1;
    private double[] x2;
    private double[] x3;
    private double[] y;
    private double[] xn1;
    private double[] xn2;
    private double[] xn3;

    public Experiment(int start, int end, int a0, int a1, int a2, int a3) {
        this.start = start;
        this.end = end;
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        x1 = new double[NUM_OF_EXPERIMENTS];
        x2 = new double[NUM_OF_EXPERIMENTS];
        x3 = new double[NUM_OF_EXPERIMENTS];
        xn1 = new double[NUM_OF_EXPERIMENTS];
        xn2 = new double[NUM_OF_EXPERIMENTS];
        xn3 = new double[NUM_OF_EXPERIMENTS];
        y = new double[NUM_OF_EXPERIMENTS];
    }

    public double getOptimalY() {
        double average = getAverageY();
        double optimal = 0;
        double[] array = getY();
        Arrays.sort(array);
        for (double element : array) {
            if (element < average) {
                optimal =  element;
            }
        }
        return optimal;
    }

    public double getAverageY() {
        double current = 0;
        for (double element : y) {
            current += element;
        }
        return current / NUM_OF_EXPERIMENTS;
    }

    public double getYEt() {
        return a0 + a1 * getMedian(x1) + a2 * getMedian(x2) + a3 * getMedian(x3);
    }

    public void initArrays() {
        for (int i = 0; i < NUM_OF_EXPERIMENTS; i++) {
            x1[i] = start + (int) (Math.random() * end);
            x2[i] = start + (int) (Math.random() * end);
            x3[i] = start + (int) (Math.random() * end);
        }
    }

    public double[] createXn1() {
        for (int i = 0; i < xn1.length; i++) {
            xn1[i] = (x1[i] - getMedian(x1)) / getDX1();
        }
        return xn1;
    }

    public double[] createXn2() {
        for (int i = 0; i < xn2.length; i++) {
            xn2[i] = (x2[i] - getMedian(x2)) / getDX2();
        }
        return xn1;
    }

    public double[] createXn3() {
        for (int i = 0; i < xn3.length; i++) {
            xn3[i] = (x3[i] - getMedian(x3)) / getDX3();
        }
        return xn1;
    }

    public double[] createYArray() {
        for (int i = 0; i < y.length; i++) {
            y[i] = a0 + a1 * x1[i] + a2 * x2[i] + a3 * x3[i];
        }
        return y;
    }

    public double getDX1() {
        return getMax(x1) - getMedian(x1);
    }

    public double getDX2() {
        return getMax(x2) - getMedian(x2);
    }

    public double getDX3() {
        return getMax(x3) - getMedian(x3);
    }

    public double getMedian(double[] array) {
        return (getMax(array) + getMin(array)) / TWO;
    }

    private double getMax(double[] array) {
        double current = array[0];
        for (int i = 1; i < array.length; i++) {
            if (current < array[i]) {
                current = array[i];
            }
        }
        return current;
    }

    private double getMin(double[] array) {
        double current = array[0];
        for (int i = 1; i < array.length; i++) {
            if (current > array[i]) {
                current = array[i];
            }
        }
        return current;
    }

    public int getA0() {
        return a0;
    }

    public void setA0(int a0) {
        this.a0 = a0;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }

    public double[] getX1() {
        return x1;
    }

    public void setX1(double[] x1) {
        this.x1 = x1;
    }

    public double[] getX2() {
        return x2;
    }

    public void setX2(double[] x2) {
        this.x2 = x2;
    }

    public double[] getX3() {
        return x3;
    }

    public void setX3(double[] x3) {
        this.x3 = x3;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public double[] getXn1() {
        return xn1;
    }

    public void setXn1(double[] xn1) {
        this.xn1 = xn1;
    }

    public double[] getXn2() {
        return xn2;
    }

    public void setXn2(double[] xn2) {
        this.xn2 = xn2;
    }

    public double[] getXn3() {
        return xn3;
    }

    public void setXn3(double[] xn3) {
        this.xn3 = xn3;
    }

    @Override
    public String toString() {
        return "X1 : " + Arrays.toString(x1) +
                "\nX2 : " + Arrays.toString(x2) +
                "\nX3  : " + Arrays.toString(x3) +
                "\nY :" + Arrays.toString(y) +
                "\nXN1 : " + Arrays.toString(xn1) +
                "\nXN2 : " + Arrays.toString(xn2) +
                "\nXN3 : " + Arrays.toString(xn3);
    }
}

