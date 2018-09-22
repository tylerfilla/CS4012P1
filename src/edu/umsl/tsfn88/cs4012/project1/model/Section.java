/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.model;

public class Section {

    /**
     * The auto-generated section ID.
     */
    private long mSid;

    /**
     * The course key.
     */
    private long mCourse;

    /**
     * The section number.
     */
    private long mNum;

    public Section() {
        mSid = -1;
        mCourse = 0;
        mNum = 0;
    }

    /**
     * @return The auto-generated section ID
     */
    public long getSid() {
        return mSid;
    }

    /**
     * @param pSid The auto-generated section ID
     */
    public void setSid(long pSid) {
        mSid = pSid;
    }

    /**
     * @return The course key
     */
    public long getCourse() {
        return mCourse;
    }

    /**
     * @param pCourse The course key
     */
    public void setCourse(long pCourse) {
        mCourse = pCourse;
    }

    /**
     * @return The section number
     */
    public long getNum() {
        return mNum;
    }

    /**
     * @param pNum The section number
     */
    public void setNum(long pNum) {
        mNum = pNum;
    }

}
