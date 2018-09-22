/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.ScheduleDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.StudentDAO;
import edu.umsl.tsfn88.cs4012.project1.model.Schedule;
import edu.umsl.tsfn88.cs4012.project1.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/doEditSchedule")
public class DoEditSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);

        // Redirect back
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);

        // Redirect back to edit page
        resp.sendRedirect("edit");
    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("add".equals(req.getParameter("action"))) {
            doAddSchedule(req, resp);
        } else if ("rem".equals(req.getParameter("action"))) {
            doRemoveSchedule(req, resp);
        }
    }

    private void doAddSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the active session
        HttpSession session = req.getSession(false);

        // Get student number from session
        long studentNum = (Long) session.getAttribute("student");

        // Get student model
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getByNum(studentNum).get();

        // Iterate over given section numbers
        for (String sectionStr : req.getParameterValues("section")) {
            long sectionNum = Long.parseLong(sectionStr);

            // Insert new schedule mapping for section
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            Schedule schedule = new Schedule();
            schedule.setSection(sectionNum);
            schedule.setStudent(student.getSid());
            scheduleDAO.insert(schedule);
        }
    }

    private void doRemoveSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the active session
        HttpSession session = req.getSession(false);

        // Get student number from session
        long studentNum = (Long) session.getAttribute("student");

        // Get student model
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getByNum(studentNum).get();

        // Iterate over given section numbers
        for (String sectionStr : req.getParameterValues("section")) {
            long sectionNum = Long.parseLong(sectionStr);

            // Create schedule data access object
            ScheduleDAO scheduleDAO = new ScheduleDAO();

            // Delete all schedule mappings that apply
            for (Schedule schedule : scheduleDAO.getAllByStudent(student.getSid())) {
                if (schedule.getSection() == sectionNum) {
                    scheduleDAO.delete(schedule);
                }
            }
        }
    }

}
