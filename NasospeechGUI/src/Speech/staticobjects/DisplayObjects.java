/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.staticobjects;

import Speech.gui.SubFunctionInternalFrame;

/**
 *
 * @author Tatapower SED
 *
 */
public class DisplayObjects {

    private static boolean isAnnotation;
    private static boolean isKwsSelected;
    private static boolean isPdsThreshold;
    private static boolean isSidTrain;
    private static boolean isSidTest;
    private static boolean isFileBrowser;
    private static boolean workspaceColsed;
    private static  boolean isAddKeyword;

    public static boolean isWorkspaceColsed() {
        return workspaceColsed;
    }

    public static void setWorkspaceColsed(boolean workspaceColsed) {
        DisplayObjects.workspaceColsed = workspaceColsed;
    }

    public static boolean getWorkspaceColsed() {
        return DisplayObjects.workspaceColsed;
    }
    private static SubFunctionInternalFrame annotationObj;
    private static SubFunctionInternalFrame addKeywordObj;
    private static SubFunctionInternalFrame kwsSelectedObj;
    private static SubFunctionInternalFrame pdsThresholdObj;
    private static SubFunctionInternalFrame sidTrainObj;
    private static SubFunctionInternalFrame sidTestObj;
    private static SubFunctionInternalFrame fileBrowser;

    public static boolean isIsFileBrowser() {
        return isFileBrowser;
    }

    public static void setIsFileBrowser(boolean isFileBrowser) {
        DisplayObjects.isFileBrowser = isFileBrowser;
    }

    public static SubFunctionInternalFrame getFileBrowser() {
        return fileBrowser;
    }

    public static void setFileBrowser(SubFunctionInternalFrame fileBrowser) {
        DisplayObjects.fileBrowser = fileBrowser;
    }

    public static boolean isIsSidTrain() {
        return isSidTrain;
    }
    
   public static boolean isIsAddKeyword() {
        return isAddKeyword;
    }

    public static void setisAddKeyword(boolean isAddKeyword) {
        DisplayObjects.isAddKeyword = isAddKeyword;
    }


    public static void setIsSidTrain(boolean isSidTrain) {
        DisplayObjects.isSidTrain = isSidTrain;
    }

    public static boolean isIsSidTest() {
        return isSidTest;
    }

    public static void setIsSidTest(boolean isSidTest) {
        DisplayObjects.isSidTest = isSidTest;
    }

    public static SubFunctionInternalFrame getSidTrainObj() {
        return sidTrainObj;
    }

    public static void setSidTrainObj(SubFunctionInternalFrame sidTrainObj) {
        DisplayObjects.sidTrainObj = sidTrainObj;
    }

    public static SubFunctionInternalFrame getSidTestObj() {
        return sidTestObj;
    }

    public static void setSidTestObj(SubFunctionInternalFrame sidTestObj) {
        DisplayObjects.sidTestObj = sidTestObj;
    }

    public static boolean isIsAnnotation() {
        return isAnnotation;
    }

    public static void setIsAnnotation(boolean isAnnotation) {
        DisplayObjects.isAnnotation = isAnnotation;
    }

    public static boolean isIsKwsSelected() {
        return isKwsSelected;
    }

    public static void setIsKwsSelected(boolean isKwsSelected) {
        DisplayObjects.isKwsSelected = isKwsSelected;
    }

    public static boolean isIsPdsThreshold() {
        return isPdsThreshold;
    }

    public static void setIsPdsThreshold(boolean isPdsThreshold) {
        DisplayObjects.isPdsThreshold = isPdsThreshold;
    }

    public static SubFunctionInternalFrame getAnnotationObj() {
        return annotationObj;
    }

    public static void setAnnotationObj(SubFunctionInternalFrame annotationObj) {
        DisplayObjects.annotationObj = annotationObj;
    }
    
    public static void setAddKeywordObj(SubFunctionInternalFrame addKeywordObj) {
        DisplayObjects.addKeywordObj = addKeywordObj;
    }
    public static SubFunctionInternalFrame getAddKeywordObj() {
       // System.out.println("calling getAddKeywordObj()");
        return addKeywordObj;
    }

    public static SubFunctionInternalFrame getKwsSelectedObj() {
        return kwsSelectedObj;
    }

    public static void setKwsSelectedObj(SubFunctionInternalFrame kwsSelectedObj) {
        DisplayObjects.kwsSelectedObj = kwsSelectedObj;
    }

    public static SubFunctionInternalFrame getPdsThresholdObj() {
        return pdsThresholdObj;
    }

    public static void setPdsThresholdObj(SubFunctionInternalFrame pdsThresholdObj) {
        DisplayObjects.pdsThresholdObj = pdsThresholdObj;
    }

    public static void displayStaticPanle() {
        try {


            if (isIsAnnotation()) {
                try {
                    getAnnotationObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } else if (isIsKwsSelected()) {
                try {
                    getKwsSelectedObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } else if (isIsPdsThreshold()) {
                try {
                    getPdsThresholdObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } else if (isIsSidTrain()) {
                try {
                    getSidTrainObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } else if (isIsSidTest()) {
                try {
                    getSidTestObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } else if (isIsFileBrowser()) {
                try {
                    getFileBrowser().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            } 
            else if(isIsAddKeyword())
            {
                try {
                    getAddKeywordObj().setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
            }else {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    public static boolean processStatus() {

        if (isIsAnnotation() || isIsKwsSelected() || isIsPdsThreshold() || isIsSidTrain() || isIsSidTest() || isIsFileBrowser()|| isIsAddKeyword()) {
            return true;
        }

        return false;
    }
}
