/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // If this is an HTTP request
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            // Filter it specially
            doFilterHttp((HttpServletRequest) req, (HttpServletResponse) resp, chain);
        } else {
            // Leave everything else alone
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private void doFilterHttp(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // Pardon all login-related paths
        String requestURI = req.getRequestURI();
        if (requestURI.startsWith("/login") || requestURI.startsWith("/register") || requestURI.startsWith("/doLogin")
                || requestURI.startsWith("/doLogout") || requestURI.startsWith("/doRegister")) {
            chain.doFilter(req, resp);
            return;
        }

        // Get the active session (or create one if needed)
        HttpSession session = req.getSession(true);

        // If session does not contain a student number
        if (session.getAttribute("student") == null) {
            // The student has not logged in yet, so redirect to login page
            resp.sendRedirect("login");
            return;
        }

        // The student is logged in!
        chain.doFilter(req, resp);
    }

}
