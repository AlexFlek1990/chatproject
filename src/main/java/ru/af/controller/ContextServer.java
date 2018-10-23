package ru.af.controller;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class ContextServer {
    public static void main( String[] args ) throws Exception {
        Server server = new Server(8080);

        ContextHandler userContextHandler = new ContextHandler("/user");
        userContextHandler.setHandler(new UserHandler());

        ContextHandler messageContextHandler = new ContextHandler("/message");
        messageContextHandler.setHandler(new MessageHandler());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { userContextHandler, messageContextHandler});

        server.setHandler(contexts);

        server.start();
        server.join();
    }
}
