/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tatapower SED
 *
 */
public class Filters {

    public static List uniqueValueFromList(List inputList) {

        List resList = new ArrayList();

        if (inputList == null) {
            return null;
        }
        for (int i = 0; i < inputList.size(); i++) {
            boolean keyFind = false;
            for (int j = 0; j < resList.size(); j++) {
                if ((inputList.get(i).toString()).equals(resList.get(j).toString())) {
                    keyFind = true;
                    break;
                }

            }
            if (!keyFind) {
                resList.add(inputList.get(i).toString());
            }

        }

        return resList;

    }

    public static int getRandomNumberBetween(int min, int max) {

        //System.out.println(" Min " + min + " " + max);
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        if (randomNumber == min) {
            // Since the random number is between the min and max values, simply add 1
            return min + 1;
        } else {
            return randomNumber;
        }

    }
}
