/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import edu.umsl.tsfn88.cs4012.project1.dao.StudentDAO;
import edu.umsl.tsfn88.cs4012.project1.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/view")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the active session
        HttpSession session = req.getSession(false);

        // Get student number from session
        long studentNum = (Long) session.getAttribute("student");

        // Get student model
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getByNum(studentNum).get();

        // Store model in request
        req.setAttribute("student", student);

        // Forward to view page
        req.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(req, resp);
    }

}
