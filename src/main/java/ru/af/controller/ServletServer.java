package ru.af.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import ru.af.entity.User;
import ru.af.hibernate.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletServer {
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(UserServlet.class, "/*");

        server.start();
        server.join();
    }

    @SuppressWarnings("serial")
    public static class UserServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            HibernateUtil hu =new HibernateUtil();
            List<User> allUsers = hu.getAllUsers();
            response.getWriter().println("<table");
            response.getWriter().println("<tr>");
            response.getWriter().println("<td>ID</td>");
            response.getWriter().println("<td>Name</td>");
            response.getWriter().println("</tr>");
            for (User user : allUsers) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>"+user.getId()+"</td>");
                response.getWriter().println("<td>"+user.getName()+"</td>");
                response.getWriter().println("</tr>");

            }
            response.getWriter().println("</table>");
        }
    }
}
