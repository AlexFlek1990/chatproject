package ru.af.controller;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ru.af.hibernate.HibernateUtil;
import ru.af.entity.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MessageHandler extends AbstractHandler {


    final String greeting;
    final String body;

    public MessageHandler() {
        this("Hello World");
    }

    public MessageHandler(String greeting) {
        this(greeting, null);
    }

    public MessageHandler(String greeting, String body) {
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
        List<Message> allMessages = hibernateUtil.getAllMessages();
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Body</td>");
        out.println("<td>Time</td>");
        out.println("<td>User_ID</td>");
        out.println("</tr>");

        for (Message message : allMessages) {
            out.println("<tr>");
            out.println("<td>"+message.getId()+"</td>");
            out.println("<td>"+message.getBody()+"</td>");
            out.println("<td>"+message.getTime()+"</td>");
            out.println("<td>"+message.getUserId()+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");

        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }

}
