/**
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class Counter {
  private int counterId;
  private boolean available;

  public Counter(int counterId) {
    this.counterId = counterId;
    this.available = true;
  }

  public boolean getAvailable() {
    return this.available;
  }

  public void setAvailabe(boolean available) {
    this.available = available;
  }

  public int getCounterId() {
    return this.counterId;
  }

  @Override
  public String toString() {
    if (available) {
      return String.format("Counter %d is available", this.counterId);
    }
    return String.format("Counter %d is not available", this.counterId);
  }
}