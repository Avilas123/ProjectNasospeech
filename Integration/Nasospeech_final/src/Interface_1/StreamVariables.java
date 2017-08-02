/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_1;

import java.util.logging.Logger;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class StreamVariables {

    private double second;
    private static final Logger LOG = Logger.getLogger(StreamVariables.class.getName());

    public void setSecondStarts(double second) {

        this.second = second;
    }

    public StreamVariables() {
    }

    public double getSecondStarts() {
        return this.second;
    }
}
