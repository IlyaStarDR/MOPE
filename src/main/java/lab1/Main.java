package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter low value of boundary");
        int start = scanner.nextInt();
        System.out.println("Enter high value of boundary");
        int end = scanner.nextInt();
        System.out.println("Enter a0");
        int a0 = scanner.nextInt();
        System.out.println("Enter a1");
        int a1 = scanner.nextInt();
        System.out.println("Enter a2");
        int a2 = scanner.nextInt();
        System.out.println("Enter a3");
        int a3 = scanner.nextInt();
        Experiment experiment = new Experiment(start, end, a0, a1, a2, a3);
        experiment.initArrays();
        experiment.createYArray();
        experiment.createXn1();
        experiment.createXn2();
        experiment.createXn3();
        System.out.println("X0 : [" + experiment.getMedian(experiment.getX1()) + ", "
                + experiment.getMedian(experiment.getX2()) + ", "
                + experiment.getMedian(experiment.getX3()) + "]"
        );
        System.out.println("DX : [" + experiment.getDX1() + ", "
                + experiment.getDX2() + ", "
                + experiment.getDX3() + "]"
        );

        System.out.println(experiment.toString());
        System.out.println("Y.et = " + experiment.getYEt());
        System.out.println("Y average = " + experiment.getAverageY());
        System.out.println("Y optimal = " + experiment.getOptimalY());
    }
}
