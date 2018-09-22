/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.model;

public class Student {

    /**
     * The auto-generated student ID.
     */
    private long mSid;

    /**
     * The school-assigned ID number.
     */
    private long mNum;

    /**
     * The first or given name.
     */
    private String mFname;

    /**
     * The last or family name.
     */
    private String mLname;

    public Student() {
        mSid = -1;
        mNum = 0;
        mFname = null;
        mLname = null;
    }

    /**
     * @return The auto-generated student ID
     */
    public long getSid() {
        return mSid;
    }

    /**
     * @param pSid The auto-generated student ID
     */
    public void setSid(long pSid) {
        mSid = pSid;
    }

    /**
     * @return The school-assigned ID number
     */
    public long getNum() {
        return mNum;
    }

    /**
     * @param pNum The school-assigned ID number
     */
    public void setNum(long pNum) {
        mNum = pNum;
    }

    /**
     * @return The first or given name
     */
    public String getFname() {
        return mFname;
    }

    /**
     * @param pFname The first or given name
     */
    public void setFname(String pFname) {
        mFname = pFname;
    }

    /**
     * @return The last or family name
     */
    public String getLname() {
        return mLname;
    }

    /**
     * @param pLname The last or family name
     */
    public void setLname(String pLname) {
        mLname = pLname;
    }

}
