/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.MeetingDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.SectionDAO;
import edu.umsl.tsfn88.cs4012.project1.model.Meeting;
import edu.umsl.tsfn88.cs4012.project1.model.Section;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

@WebServlet(urlPatterns = "/doAddSection")
public class DoAddSection extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get course ID
        long courseId = Long.parseLong(req.getParameter("course"));

        // Map the new section
        SectionDAO sectionDAO = new SectionDAO();
        Section section = new Section();
        section.setNum(Long.parseLong(req.getParameter("num")));
        section.setCourse(courseId);
        sectionDAO.insert(section);

        // Get raw selected time data
        int startHour = Integer.parseInt(req.getParameter("startHour"));
        int startMinute = Integer.parseInt(req.getParameter("startMinute"));
        String startAMPM = req.getParameter("startAMPM");
        int stopHour = Integer.parseInt(req.getParameter("stopHour"));
        int stopMinute = Integer.parseInt(req.getParameter("stopMinute"));
        String stopAMPM = req.getParameter("stopAMPM");

        // Handle PM hours
        if ("PM".equals(startAMPM)) {
            startHour = (startHour % 12) + 12;
        }
        if ("PM".equals(stopAMPM)) {
            stopHour = (stopHour % 12) + 12;
        }

        // Process start and stop time
        // This uses deprecated functionality (but that can be easily fixed)
        Time startTime = new Time(startHour, startMinute, 0);
        Time stopTime = new Time(stopHour, stopMinute, 0);

        // Iterate selected meeting days and insert mappings
        MeetingDAO meetingDAO = new MeetingDAO();
        for (String dayStr : req.getParameterValues("days")) {
            // Build a meeting object for the section
            Meeting meeting = new Meeting();
            meeting.setStart(startTime);
            meeting.setStop(stopTime);
            meeting.setSection(section.getSid());
            meeting.setDay(Integer.parseInt(dayStr));
            meetingDAO.insert(meeting);
        }

        // Redirect back
        resp.sendRedirect("edit");
    }

}
