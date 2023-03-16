package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            OrderDAOimpl dao = new OrderDAOimpl(connection);
            dao.createTableClients();
            dao.createTableGoods();
            dao.createTableOrders();

            while (true) {
                System.out.println("1: add good");
                System.out.println("2: view goods");
                System.out.println("3: add client");
                System.out.println("4: view clients");
                System.out.println("5: add order");
                System.out.println("6: view orders");
                System.out.print("-> ");

                String s = scanner.nextLine();
                switch (s) {
                    case "1" -> {
                        System.out.print("Enter item's name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter item's price: ");
                        String sPrice = scanner.nextLine();
                        int price = Integer.parseInt(sPrice);

                        dao.addGood(name, price);
                    }
                    case "2" -> {
                        List<Good> goods = dao.getAllGoods();
                        for (Good good : goods) {
                            System.out.println(good);
                        }
                    }
                    case "3" -> {
                        System.out.print("Enter client's name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter client's age: ");
                        String sAge = scanner.nextLine();
                        int age = Integer.parseInt(sAge);

                        dao.addClient(name, age);
                    }
                    case "4" -> {
                        List<Client> clients = dao.getAllClients();
                        for (Client client : clients) {
                            System.out.println(client);
                        }
                    }
                    case "5" -> {
                        System.out.print("Enter client's id: ");
                        String sIdClient = scanner.nextLine();
                        int idClient = Integer.parseInt(sIdClient);
                        Client client = null;
                        for (Client tmpclient : dao.getAllClients()) {
                            if (idClient == tmpclient.getId()) {
                                client = tmpclient;
                            }
                        }
                        dao.addOrder(client,scanner);
                    }
                    case "6" -> {
                        dao.getAllOrders();
                    }
                    default -> {
                        return;
                    }
                }
            }


        }
    }
}
