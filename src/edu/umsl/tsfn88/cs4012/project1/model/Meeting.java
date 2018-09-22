/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.model;

import java.sql.Time;

public class Meeting {

    /**
     * The auto-generated meeting ID.
     */
    private long mMid;

    /**
     * The section key.
     */
    private long mSection;

    /**
     * The weekday number.
     */
    private int mDay;

    /**
     * The meeting start time.
     */
    private Time mStart;

    /**
     * The meeting stop time.
     */
    private Time mStop;

    public Meeting() {
        mMid = -1;
        mSection = -1;
        mDay = 0;
        mStart = null;
        mStop = null;
    }

    /**
     * @return The auto-generated meeting ID
     */
    public long getMid() {
        return mMid;
    }

    /**
     * @param pMid The auto-generated meeting ID
     */
    public void setMid(long pMid) {
        mMid = pMid;
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

    /**
     * @return The weekday number
     */
    public int getDay() {
        return mDay;
    }

    /**
     * @param pDay The weekday number
     */
    public void setDay(int pDay) {
        mDay = pDay;
    }

    /**
     * @return The meeting start time
     */
    public Time getStart() {
        return mStart;
    }

    /**
     * @param pStart The meeting start time
     */
    public void setStart(Time pStart) {
        mStart = pStart;
    }

    /**
     * @return The meeting stop time
     */
    public Time getStop() {
        return mStop;
    }

    /**
     * @param pStop The meeting stop time
     */
    public void setStop(Time pStop) {
        mStop = pStop;
    }

}
