package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDAOimpl implements OrderDAO {
    private final Connection connection;

    public OrderDAOimpl(Connection connection) {
        this.connection = connection;
    }

    public void createTableGoods() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'Goods'");

            if (!rs.next()) {
                stmt.execute("CREATE TABLE Goods(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        " name VARCHAR(20), price INT)");
            }
        }
    }

    public void createTableClients() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'Clients'");

            if (!rs.next()) {
                stmt.execute("CREATE TABLE Clients(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        " name VARCHAR(20), age INT)");
            }
        }
    }

    public void createTableOrders() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'Orders'");

            if (!rs.next()) {
                stmt.execute("CREATE TABLE Orders(orderID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, clientID INT, "
                        + "clientNAME VARCHAR(20), orderSUM INT, orderDETAILS VARCHAR(100))");
            }
        }
    }

    public void addClient(String name, int age) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Clients (name, age)" +
                " VALUES(?, ?)");
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.executeUpdate();
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> listClients = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Clients")) {
            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setAge(rs.getInt(3));

                listClients.add(client);
            }
        }
        return listClients;
    }

    public void addGood(String name, int price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Goods (name, price)" +
                " VALUES(?, ?)");
        ps.setString(1, name);
        ps.setInt(2, price);
        ps.executeUpdate();
    }

    public List<Good> getAllGoods() throws SQLException {
        List<Good> listGoods = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM Goods")) {
            while (rs.next()) {
                Good item = new Good();

                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setPrice(rs.getInt(3));

                listGoods.add(item);
            }
        }
        return listGoods;
    }

    @Override
    public void addOrder(Client client, Scanner scanner) throws SQLException {
        int orderSum = 0;
        String orderDetails = "";
        boolean mark = true;
        while (mark) {
            System.out.println("1: enter item to your order");
            System.out.println("2: finish order");
            System.out.print("-> ");
            String s = scanner.nextLine();

            switch (s) {
                case "1" -> {
                    System.out.println("Enter item's id to add to your order: ");
                    String itemID = scanner.nextLine();
                    String sqlQuery = "SELECT * FROM Goods WHERE id = ?";
                    try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
                        ps.setString(1, itemID);
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                orderSum += rs.getInt(3);
                                orderDetails += " | name: " + rs.getString(2)
                                        + " | price: " + rs.getString(3) + " || ";
                            }
                        }
                    }
                }
                case "2" -> {
                    mark = false;
                }
            }
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Orders (clientID, clientNAME, orderSUM, orderDetails)" +
                " VALUES(?, ?, ?, ?)");
        ps.setInt(1, client.getId());
        ps.setString(2, client.getName());
        ps.setInt(3, orderSum);
        ps.setString(4, orderDetails);
        ps.executeUpdate();
    }

    @Override
    public void getAllOrders() throws SQLException {

        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT * FROM Orders")) {
                while (rs.next()) {
                    System.out.println("orderID: " + rs.getInt(1)
                            + " | clientID: " + rs.getInt(2)
                            + " | clientName: " + rs.getString(3)
                            + " | orderSUM: " + rs.getInt(4)
                            + " | orderDETAILS: " + rs.getString(5));
                }
            }
        }

    }
}
