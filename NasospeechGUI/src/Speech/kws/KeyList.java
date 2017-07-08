/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeyList {

    private List keywordsList;
    private HashMap keywordsColor;
    private List endTimeList;
    private String keyUniqueSelected;
    private List startTimeList;
    private List fileContent;

    public List getFileContent() {
        return fileContent;
    }

    public void setFileContent(List fileContent) {
        this.fileContent = fileContent;
    }

    public List getStartTimeList() {
        return startTimeList;
    }

    public void setStartTimeList(List startTimeList) {
        this.startTimeList = startTimeList;
    }

    public String getKeyUniqueSelected() {
        return keyUniqueSelected;
    }

    public void setKeyUniqueSelected(String keySelected) {
        this.keyUniqueSelected = keySelected;
    }

    public HashMap getKeywordsColor() {
        return keywordsColor;
    }

    public void setKeywordsColor(HashMap keywordsColor) {
        this.keywordsColor = keywordsColor;
    }

    public List getEndTimeList() {
        return endTimeList;
    }

    public void setEndTimeList(List keywordsEndTime) {
        this.endTimeList = keywordsEndTime;
    }

    public List getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List keywordsList) {
        this.keywordsList = keywordsList;
    }
}
