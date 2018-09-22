/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.model;

public class Course {

    /**
     * The auto-generated course ID.
     */
    private long mCid;

    /**
     * The course number.
     */
    private long mNum;

    /**
     * The course name.
     */
    private String mName;

    public Course() {
        mCid = -1;
        mNum = 0;
        mName = null;
    }

    /**
     * @return The auto-generated course ID
     */
    public long getCid() {
        return mCid;
    }

    /**
     * @param pCid The auto-generated course ID
     */
    public void setCid(long pCid) {
        mCid = pCid;
    }

    /**
     * @return The course number
     */
    public long getNum() {
        return mNum;
    }

    /**
     * @param pNum The course number
     */
    public void setNum(long pNum) {
        mNum = pNum;
    }

    /**
     * @return The course name
     */
    public String getName() {
        return mName;
    }

    /**
     * @param pName The course name
     */
    public void setName(String pName) {
        mName = pName;
    }

}
