/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/doEditCourse")
public class DoEditCourse extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect back
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
