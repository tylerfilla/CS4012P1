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
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/doRegister")
public class DoRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameter data
        String numStr = req.getParameter("num");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        // Validate parameter data
        if (numStr == null || numStr.isEmpty() || fname == null || fname.isEmpty() || lname == null) {
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

        // If the student number query returned a result
        if (studentOpt.isPresent()) {
            // Send the user back to the register page
            resp.sendRedirect("register?exists=1");
            return;
        }

        // Model the student
        Student student = new Student();
        student.setNum(num);
        student.setFname(fname);
        student.setLname(lname);

        // Insert student model
        dao.insert(student);

        // Forward to login endpoint
        // The "num" parameter will be reused
        req.getRequestDispatcher("doLogin").forward(req, resp);
    }

}
