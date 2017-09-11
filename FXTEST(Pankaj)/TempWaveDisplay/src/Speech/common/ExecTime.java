/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

/**
 *
 * @author Tatapower SED
 *
 */
public class ExecTime {

    private final long start;

    /**
     * Create a stopwatch object.
     */
    public ExecTime() {
        start = System.currentTimeMillis();
    }

    /**
     * Return elapsed time (in seconds) since this object was created.
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}