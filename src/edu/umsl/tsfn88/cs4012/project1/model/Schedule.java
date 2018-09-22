/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.model;

public class Schedule {

    /**
     * The auto-generated schedule ID.
     */
    private long mSid;

    /**
     * The student key.
     */
    private long mStudent;

    /**
     * The section key.
     */
    private long mSection;

    public Schedule() {
        mSid = -1;
        mStudent = 0;
        mSection = 0;
    }

    /**
     * @return The auto-generated schedule ID
     */
    public long getSid() {
        return mSid;
    }

    /**
     * @param pSid The auto-generated schedule ID
     */
    public void setSid(long pSid) {
        mSid = pSid;
    }

    /**
     * @return The student key
     */
    public long getStudent() {
        return mStudent;
    }

    /**
     * @param pStudent The student key
     */
    public void setStudent(long pStudent) {
        mStudent = pStudent;
    }

    /**
     * @return The section key
     */
    public long getSection() {
        return mSection;
    }

    /**
     * @param pSection The section key
     */
    public void setSection(long pSection) {
        mSection = pSection;
    }

}
