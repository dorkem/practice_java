package java_practice.chat.server.command;

import java_practice.chat.server.Session;

import java.io.IOException;

public interface Command {
    void execute(String[] args, Session session) throws IOException;
}