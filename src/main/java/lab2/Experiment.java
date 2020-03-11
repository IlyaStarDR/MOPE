package lab2;

public class Experiment {
    private final static int numOfVariant = 21;
    private final static int x1min = 10;
    private final static int x1max = 40;
    private final static int x2min = 10;
    private final static int x2max = 60;
    private final static int[][] xn = {{-1, -1}, {1, -1}, {-1, 1}};
    private final static int m = 6;

    public Experiment() {
        experiment();
    }

    private static double sigmaTetra() {
        return Math.sqrt((double) (2 * (2 * m - 2)) / (m * (m - 4)));

    }

    private int getYMin() {
        return (20 - numOfVariant) * 10;
    }

    private int getYMax() {
        return (30 - numOfVariant) * 10;
    }

    private double[] getAverageY(double[][] array) {
        double[] average = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            double s = 0;
            for (int j = 0; j < array[i].length; j++) {
                s += array[i][j];
            }
            average[i] = (s / array[i].length);
        }
        return average;
    }

    private double[] getDispersion(double[][] array) {
        double[] dispersion = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            double s = 0;
            for (int j = 0; j < array[i].length; j++) {
                s += (j - getAverageY(array)[i]) * (j - getAverageY(array)[i]);
            }
            dispersion[i] = (s / array[i].length);
        }
        return dispersion;
    }

    private double getFuv(double u, double v) {
        if (u >= v) {
            return u / v;
        } else return v / u;
    }

    private double getDeterminant(double x11, double x12, double x13, double x21, double x22, double x23, double x31, double x32, double x33) {
        return x11 * x22 * x33 + x12 * x23 * x31
                + x32 * x21 * x13 - x13 * x22 * x31
                - x32 * x23 * x11 - x12 * x21 * x33;
    }

    private double[][]
    getY() {
        double[][] y = new double[3][6];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[i].length; j++) {
                y[i][j] = getYMin() + (int) (Math.random() * getYMax());
            }
        }
        return y;
    }

    private void experiment() {
        double[][] y = getY();

        double[] fuv = new double[3];
        fuv[0] = getFuv(getDispersion(y)[0], getDispersion(y)[1]);
        fuv[1] = getFuv(getDispersion(y)[2], getDispersion(y)[0]);
        fuv[2] = getFuv(getDispersion(y)[2], getDispersion(y)[1]);

        double[] tetra = new double[3];
        tetra[0] = ((double) (m - 2) / m) * fuv[0];
        tetra[1] = ((double) (m - 2) / m) * fuv[1];
        tetra[2] = ((double) (m - 2) / m) * fuv[2];

        double[] ruv = new double[3];
        ruv[0] = (Math.abs(tetra[0] - 1)) / sigmaTetra();
        ruv[1] = (Math.abs(tetra[1] - 1)) / sigmaTetra();
        ruv[2] = (Math.abs(tetra[2] - 1)) / sigmaTetra();

        int rkr = 2;
        for (double element : ruv) {
            if (element > rkr) {
                System.out.println("Error, try later!");
            }
        }

        double[] avY = getAverageY(y);
        double mx1 = (double) (xn[0][0] + xn[1][0] + xn[2][0]) / 3;
        double mx2 = (double) (xn[0][1] + xn[1][1] + xn[2][1]) / 3;
        double my = (avY[0] + avY[1] + avY[2]) / 3;

        double a1 = (double) (xn[0][0] * xn[0][0] + xn[1][0] * xn[1][0] + xn[2][0] * xn[2][0]) / 3;
        double a2 = (double) (xn[0][0] * xn[0][1] + xn[1][0] * xn[1][1] + xn[2][0] * xn[2][1]) / 3;
        double a3 = (double) (xn[0][1] * xn[0][1] + xn[1][1] * xn[1][1] + xn[2][1] * xn[2][1]) / 3;

        double a11 = (xn[0][0] * avY[0] + xn[1][0] * avY[1] + xn[2][0] * avY[2]) / 3;
        double a22 = (xn[0][1] * avY[0] + xn[1][1] * avY[1] + xn[2][1] * avY[2]) / 3;

        double b0 = getDeterminant(my, mx1, mx2, a11, a1, a2, a22, a2, a3)
                / getDeterminant(1, mx1, mx2, mx1, a1, a2, mx2, a2, a3);
        double b1 = getDeterminant(1, my, mx2, mx1, a11, a2, mx2, a22, a3)
                / getDeterminant(1, mx1, mx2, mx1, a1, a2, mx2, a2, a3);
        double b2 = getDeterminant(1, mx1, my, mx1, a1, a11, mx2, a2, a22)
                / getDeterminant(1, mx1, mx2, mx1, a1, a2, mx2, a2, a3);

        double yPr1 = b0 + b1 * xn[0][0] + b2 * xn[0][1];
        double yPr2 = b0 + b1 * xn[1][0] + b2 * xn[1][1];
        double yPr3 = b0 + b1 * xn[2][0] + b2 * xn[2][1];

        double dx1 = (double) Math.abs(x1max - x1min) / 2;
        double dx2 = (double) Math.abs(x2max - x2min) / 2;

        double x10 = (double) (x1max + x1min) / 2;
        double x20 = (double) (x2max + x2min) / 2;

        double koef0 = b0 - (b1 * x10 / dx1) - (b2 * x20 / dx2);
        double koef1 = b1 / dx1;
        double koef2 = b2 / dx2;

        double yP1 = koef0 + koef1 * x1min + koef2 * x2min;
        double yP2 = koef0 + koef1 * x1max + koef2 * x2min;
        double yP3 = koef0 + koef1 * x1min + koef2 * x2max;

        System.out.println("Matrix of planning for m = 6");
        for (double[] doubles : y) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }

        System.out.println("Experimental value of Romanos");
        for (double element : ruv) {
            System.out.println(element);
        }
        System.out.printf("Naturalized \na0 = %f.3\na1 = %f.3\na2 = %f.3", koef0, koef1, koef2);
        System.out.printf("\nY practice \ny1 = %f.3\ny2 = %f.3\ny3 = %f.3\nY avr = %f.3, %f.3, %f.3", yPr1, yPr2, yPr3, avY[0], avY[1], avY[2]);
        System.out.printf("\nY pr normal = %f.3, %f.3, %f.3", yP1, yP2, yP3);
        System.out.println();
    }
}
