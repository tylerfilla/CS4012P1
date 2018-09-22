/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.*;
import edu.umsl.tsfn88.cs4012.project1.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/widget/calendar")
public class CalendarWidgetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the active session
        HttpSession session = req.getSession(false);

        // Get student number from session
        long studentNum = (Long) session.getAttribute("student");

        // Get student model
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getByNum(studentNum).get();

        // A map of meetings to display
        Map<Integer, Map<Integer, DisplayedMeeting>> meetings = new HashMap<>();

        // Maximum meeting hours
        // These will be used to compute the calendar display range
        long earliestStartHour = 23;
        long latestStopHour = 0;

        // Iterate schedule for student
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        for (Schedule schedule : scheduleDAO.getAllByStudent(student.getSid())) {
            // Look up course section
            SectionDAO sectionDAO = new SectionDAO();
            Section section = sectionDAO.getBySid(schedule.getSection()).get();

            // Look up course, itself
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getByCid(section.getCourse()).get();

            // Iterate course section meetings
            MeetingDAO meetingDAO = new MeetingDAO();
            for (Meeting meeting : meetingDAO.getAllBySection(section.getSid())) {
                // The meeting display
                DisplayedMeeting display = new DisplayedMeeting();

                // Show the course name and section number
                display.setName(course.getName());
                display.setSectionId(section.getSid());
                display.setSectionNum(section.getNum());

                // Get start and stop times for course section meeting
                LocalTime start = meeting.getStart().toLocalTime();
                LocalTime stop = meeting.getStop().toLocalTime();

                // Find maximum meeting hours
                if (start.getHour() < earliestStartHour) {
                    earliestStartHour = start.getHour();
                }
                if (stop.getHour() > latestStopHour) {
                    latestStopHour = stop.getHour();
                }

                // Calculate calendar offset and span values
                // These are used to compute displacement on the screen
                final int SECONDS_IN_HOUR = 60 * 60;
                display.setHourOffset((start.toSecondOfDay() % SECONDS_IN_HOUR) / (float) SECONDS_IN_HOUR);
                display.setHourSpan((stop.toSecondOfDay() - start.toSecondOfDay()) / (float) SECONDS_IN_HOUR);

                // Store formatted time strings
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                display.setStartTimeString(start.format(formatter));
                display.setStopTimeString(stop.format(formatter));

                // Map the meeting display
                meetings.computeIfAbsent(meeting.getDay(), k -> new HashMap<>());
                meetings.get(meeting.getDay()).put(start.getHour(), display);
            }
        }

        // Start and stop hours
        // Show one more hour on either end
        long startHour = Math.max(0, earliestStartHour - 1);
        long stopHour = Math.min(23, latestStopHour + 1);

        // Forward to widget view page
        req.setAttribute("startHour", startHour);
        req.setAttribute("stopHour", stopHour);
        req.setAttribute("meetings", meetings);
        req.getRequestDispatcher("/WEB-INF/jsp/widget/calendar.jsp").forward(req, resp);
    }

    /**
     * A meeting to display on the calendar.
     */
    public static class DisplayedMeeting {

        /**
         * The course name.
         */
        private String mName;

        /**
         * The course section ID.
         */
        private long mSectionId;

        /**
         * The course section number.
         */
        private long mSectionNum;

        /**
         * The offset.
         */
        private float mHourOffset;

        /**
         * The span.
         */
        private float mHourSpan;

        /**
         * The start time string.
         */
        private String mStartTimeString;

        /**
         * The stop time string.
         */
        private String mStopTimeString;

        public DisplayedMeeting() {
            mName = "";
            mSectionId = 0;
            mSectionNum = 0;
            mHourOffset = 0;
            mHourSpan = 0;
            mStartTimeString = "";
            mStopTimeString = "";
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

        /**
         * @param pSectionId The course section ID
         */
        public void setSectionId(long pSectionId) {
            mSectionId = pSectionId;
        }

        /**
         * @return The course section ID
         */
        public long getSectionId() {
            return mSectionId;
        }

        /**
         * @param pSectionNum The course section number
         */
        public void setSectionNum(long pSectionNum) {
            mSectionNum = pSectionNum;
        }

        /**
         * @return The course section number
         */
        public long getSectionNum() {
            return mSectionNum;
        }

        /**
         * @return The hour offset
         */
        public float getHourOffset() {
            return mHourOffset;
        }

        /**
         * @param pHourOffset The hour offset
         */
        public void setHourOffset(float pHourOffset) {
            mHourOffset = pHourOffset;
        }

        /**
         * @return The hour span
         */
        public float getHourSpan() {
            return mHourSpan;
        }

        /**
         * @param pHourSpan The hour span
         */
        public void setHourSpan(float pHourSpan) {
            mHourSpan = pHourSpan;
        }

        /**
         * @return The start time string
         */
        public String getStartTimeString() {
            return mStartTimeString;
        }

        /**
         * @param pStartTimeString The start time string
         */
        public void setStartTimeString(String pStartTimeString) {
            mStartTimeString = pStartTimeString;
        }

        /**
         * @return The start time string
         */
        public String getStopTimeString() {
            return mStopTimeString;
        }

        /**
         * @param pStopTimeString The stop time string
         */
        public void setStopTimeString(String pStopTimeString) {
            mStopTimeString = pStopTimeString;
        }

    }

}
