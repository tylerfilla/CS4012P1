/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.CourseDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.MeetingDAO;
import edu.umsl.tsfn88.cs4012.project1.dao.SectionDAO;
import edu.umsl.tsfn88.cs4012.project1.model.Course;
import edu.umsl.tsfn88.cs4012.project1.model.Meeting;
import edu.umsl.tsfn88.cs4012.project1.model.Section;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

@WebServlet(urlPatterns = "/doAddCourse")
public class DoAddCourse extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Model and map the new course
        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course();
        course.setName(req.getParameter("name"));
        course.setNum(Long.parseLong(req.getParameter("num")));
        courseDAO.insert(course);

        // Redirect back
        resp.sendRedirect("edit");
    }

}
