/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.CourseDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.ScheduleDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.SectionDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.StudentDAO;
import edu.umsl.tsfn88.cs4012.project1.model.Course;
import edu.umsl.tsfn88.cs4012.project1.model.Schedule;
import edu.umsl.tsfn88.cs4012.project1.model.Section;
import edu.umsl.tsfn88.cs4012.project1.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the active session
        HttpSession session = req.getSession(false);

        // Get student number from session
        long studentNum = (Long) session.getAttribute("student");

        // Get student model
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getByNum(studentNum).get();

        // Get all known courses
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAll();

        // Create map of course IDs to names
        Map<Long, String> courseNames = new HashMap<>();
        for (Course course : courses) {
            courseNames.put(course.getCid(), course.getName());
        }

        // Get all known course sections
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sections = sectionDAO.getAll();

        // Create map of sections for student
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        Map<Long, Long> userSections = new HashMap<>();
        for (Schedule schedule : scheduleDAO.getAllByStudent(student.getSid())) {
            userSections.put(schedule.getSection(), schedule.getSection());
        }

        // Forward to edit page
        req.setAttribute("courses", courses);
        req.setAttribute("courseNames", courseNames);
        req.setAttribute("sections", sections);
        req.setAttribute("student", student);
        req.setAttribute("userSections", userSections);
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }

}
