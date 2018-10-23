package ru.af.controller;


import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ru.af.hibernate.HibernateUtil;
import ru.af.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserHandler extends AbstractHandler {


    final String greeting;
    final String body;

    public UserHandler() {
        this("Hello World");
    }

    public UserHandler(String greeting) {
        this(greeting, null);
    }

    public UserHandler(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
            ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        HibernateUtil hibernateUtil = new HibernateUtil();
        List<User> allUsers = hibernateUtil.getAllUsers();
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Name</td>");
        out.println("</tr>");

        for (User user : allUsers) {
            out.println("<tr>");
            out.println("<td>"+user.getId()+"</td>");
            out.println("<td>"+user.getName()+"</td>");
            out.println("</tr>");

        }
        out.println("</table>");

        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }

}



