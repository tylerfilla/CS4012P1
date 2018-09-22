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
import java.util.Optional;

@WebServlet(urlPatterns = "/doLogin")
public class DoLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameter data
        String numStr = req.getParameter("num");

        // Validate parameter data
        if (numStr == null || numStr.isEmpty()) {
            resp.getWriter().print("Invalid parameters");
            resp.sendError(418);
            return;
        }

        // Decode parameter data
        long num = Long.parseLong(numStr);

        // Create data access object
        StudentDAO dao = new StudentDAO();

        // Query for existing student number
        Optional<Student> studentOpt = dao.getByNum(num);

        // If the student number query did not return a result
        if (!studentOpt.isPresent()) {
            // Send the user back to the login page
            resp.sendRedirect("login?nonexistent=1");
            return;
        }

        // Get active session (or create it if needed)
        HttpSession session = req.getSession(true);

        // Store student number in session
        session.setAttribute("student", num);

        // Redirect to index page
        resp.sendRedirect("/");
    }

}
