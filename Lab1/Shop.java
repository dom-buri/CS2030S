/**
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

class Shop {

    private Counter[] counters;

    public Shop(int numOfCounters) {
    	counters = new Counter[numOfCounters];
	    for (int i = 0; i < numOfCounters; i +=1) {
	      counters[i] = new Counter();
	    }   
    }

    public Counter getFirstAvailableCounter() {
      for (int i = 0; i < this.counters.length; i += 1) {
        if (this.counters[i].getAvailable()) {
          return this.counters[i];
        }
      }
      return null;
    }
}
