package com.exoty.chat;

import sfs2x.client.entities.Room;
import sfs2x.client.requests.CreateRoomRequest;
import sfs2x.client.requests.JoinRoomRequest;
import sfs2x.client.requests.RoomSettings;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static JavaSimpleConnector connector = new JavaSimpleConnector();
    private static Scanner scanner = new Scanner(System.in);

    public static void login() {
        System.out.print("Type your username: ");
        String username = scanner.nextLine().trim();

        System.out.println("Default Password for all user: 123");
        System.out.print("Type your password: ");
        String password = scanner.nextLine().trim();

        connector.login(username, password);
    }

    public static void main(String[] args) {
        connector.connect();
        System.out.println("type '/h' for help");

        login();

        while (true) {
            String input = scanner.nextLine();
            input = input.trim();
            switch (input) {
                case "/h":
                    showHelp();
                    break;
                case "/connect":
                    connector.connect();
                    break;
                case "/disconnect":
                    connector.disconnect();
                    break;
                case "/login":
                    login();
                    break;
                case "/logout":
                    connector.logout();
                    break;
                case "/chat":
                    System.out.print("type msg: ");
                    String msg = scanner.nextLine();
                    connector.sendMsg(msg);
                    break;

                case "/chat-to":
                    System.out.print("send to username: ");
                    String username = scanner.nextLine();
                    System.out.print("type msg        : ");
                    msg = scanner.nextLine();
                    connector.sendPrivateMsg(username, msg);
                    break;

                case "/user-list":
                    connector.sendUserList();
                    break;

                //=============
                case "/create-room":
                    System.out.print("type room name: ");
                    String name = scanner.nextLine();
                    connector.getSfs().send(new CreateRoomRequest(new RoomSettings(name)));
                    break;

                case "/list-room":
                    List<Room> rooms = connector.getSfs().getRoomList();
                    System.err.println("size of rooms: " + rooms.size());
                    rooms.forEach(room -> {
                        System.err.println("id room: " + room.getGroupId());
                        System.err.println("name room: " + room.getName());
                    });
                    break;

                case "/join-room":
                    System.out.print("type room name: ");
                    name = scanner.nextLine();
                    connector.getSfs().send(new JoinRoomRequest(name));
                    break;
                case "/quit":
                    System.exit(0);
                    break;
                default:
                    showHelp();
                    break;
            }
        }
    }

    private static void showHelp() {
        System.err.println("===== Using below command");
        System.err.println("/h : help");
        System.err.println("/connect : connect");
        System.err.println("/disconnect : disconnect");
        System.err.println("/login : login");
        System.err.println("/logout : logout");
        System.err.println("/chat : send msg");
        System.err.println("/chat-to : send Private msg");
        System.err.println("/user-list : list all user online");

        System.err.println();
        System.err.println("/-------");
        System.err.println("/list-room");
        System.err.println("/create-room");
        System.err.println("/join-room");

        System.err.println("/quit");
    }
}
