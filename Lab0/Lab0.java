import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 1, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Dominic Ng (Group 14B)  
 */

class Lab0 {

  public static double estimatePi(int numOfPoints, int seed) {
    int n = 0;
    Circle c = new Circle(new Point(0.5, 0.5), 0.5);
    RandomPoint.setSeed(seed);
    for (int i = 0; i < numOfPoints; i ++) {
      RandomPoint randomPoint = new RandomPoint(0, 1, 0, 1);
      if (c.contains(randomPoint)) {
        n += 1;
      }
    }
    return 4.0 * n / numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
