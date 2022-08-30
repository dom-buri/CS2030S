/**
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

class Shop {

    private Counter[] counters;

    public Shop(Counter[] counters) {
        this.counters = counters;
    }
    
    public Counter[] getCounters() {
        return this.counters;
    }
}