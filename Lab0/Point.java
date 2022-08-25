/**
 * CS2030S Lab 0: Point.java
 * Semester 1, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Dominic Ng (Group 14B)
 */
class Point {
  private double x;
  private double y;

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}