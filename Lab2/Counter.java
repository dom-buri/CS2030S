/**
 * @author Dominic Ng (Group 14B)
 * @version CS2030S AY22/23 Semester 1
 */

class Counter {
  private static int count = 0;
  private int counterId;
  private boolean available;

  public Counter() {
    this.counterId = count;
    this.available = true;
    count++;
  }

  public boolean getAvailable() {
    return available;
  }

  public void makeAvailable() {
    this.available = true;
  }

  public void makeUnavailable() {
    this.available = false;
  }

  @Override
  public String toString() {
    return String.format("S%d", this.counterId);
  }
}
