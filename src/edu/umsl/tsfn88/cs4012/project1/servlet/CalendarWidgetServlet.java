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

                // Show the course name
                display.setName(course.getName());

                // Get start and stop times for course section meeting
                LocalTime start = meeting.getStart().toLocalTime();
                LocalTime stop = meeting.getStop().toLocalTime();

                // Calculate calendar offset and span values
                // These are used to compute displacement on the screen
                final int SECONDS_IN_HOUR = 60 * 60;
                display.setHourOffset((start.toSecondOfDay() % SECONDS_IN_HOUR) / (float) SECONDS_IN_HOUR);
                display.setHourSpan((stop.toSecondOfDay() - start.toSecondOfDay()) / (float) SECONDS_IN_HOUR);

                // Map the meeting display
                meetings.computeIfAbsent(meeting.getDay(), k -> new HashMap<>());
                meetings.get(meeting.getDay()).put(start.getHour(), display);
            }
        }

        // Store meetings map in request
        req.setAttribute("meetings", meetings);

        // Forward to widget view page
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
         * The offset.
         */
        private float mHourOffset;

        /**
         * The span.
         */
        private float mHourSpan;

        public DisplayedMeeting() {
            mName = "";
            mHourOffset = 0;
            mHourSpan = 0;
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

    }

}
