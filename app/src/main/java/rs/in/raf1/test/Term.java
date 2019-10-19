package rs.in.raf1.test;

import java.util.Map;

public class Term {

    static Map<String, Object> termMap;

    private String mId;
    private String mEnglish;
    private String mSerbian;
    private String mDescription;

    public Term() {

    }

    public Term(String id, String eng, String srb, String dsc) {
        mId = id;
        mEnglish = eng;
        mSerbian = srb;
        mDescription = dsc;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getSerbian() {
        return mSerbian;
    }

    public void setSerbian(String serbian) {
        mSerbian = serbian;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }


}
