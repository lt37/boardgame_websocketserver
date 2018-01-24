package fr.univtln.m2dapm.boardgame.websocket;

import org.glassfish.tyrus.server.Server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ServerEndpoint(value = "/test")
public class WebsocketServerEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New client " + session.toString());
    }


    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("Received " + message + " from " + session);
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("Closing session " + session.toString());
    }

    public static void main(String[] args) {
        Server server = new Server("localhost", 8025, null, null, WebsocketServerEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
