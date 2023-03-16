package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/JDBCHomeWork_DB_flats";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            initDB(connection);

            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: delete flat");
                System.out.println("3: view flats");
                System.out.println("4: filter flats by number of rooms");
                System.out.println("5: filter flats by price");
                System.out.print("-> ");

                String s = scanner.nextLine();
                switch (s) {
                    case "1" -> addFlat(connection, scanner);
                    case "2" -> deleteFlat(connection, scanner);
                    case "3" -> viewAll(connection);
                    case "4" -> filterFlatsByRooms(connection, scanner);
                    case "5" -> filterFlatsByPrice(connection, scanner);
                    default -> {
                        return;
                    }
                }
            }
        }
    }

    public static void initDB(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'JDBCHomeWork_DB_flats'");

            if (!rs.next()) {
                stmt.execute("CREATE TABLE JDBCHomeWork_DB_flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        " district VARCHAR(20), address VARCHAR(20), area INT, rooms_number INT, price INT)");
            }
        }
    }

    public static void addFlat(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter district: ");
        String district = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter area: ");
        String sArea = scanner.nextLine();
        double area = Double.parseDouble(sArea);
        System.out.print("Enter number of rooms: ");
        String sRooms = scanner.nextLine();
        int rooms = Integer.parseInt(sRooms);
        System.out.print("Enter price: ");
        String sPrice = scanner.nextLine();
        int price = Integer.parseInt(sPrice);

        PreparedStatement ps = connection.prepareStatement("INSERT INTO JDBCHomeWork_DB_flats (district, address, area, rooms_number, price)" +
                " VALUES(?, ?, ?, ?, ?)");
        ps.setString(1, district);
        ps.setString(2, address);
        ps.setDouble(3, area);
        ps.setInt(4, rooms);
        ps.setInt(5, price);
        ps.executeUpdate();
    }

    private static void deleteFlat(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter flat id: ");
        int id = Integer.parseInt(scanner.nextLine());

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM JDBCHomeWork_DB_flats WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    private static void viewAll(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM JDBCHomeWork_DB_flats");
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.print(md.getColumnName(i) + "\t\t");
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        }
    }

    public static void filterFlatsByRooms(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter number of rooms: ");
        String sRooms = scanner.nextLine();
        int rooms = Integer.parseInt(sRooms);

        String sqlRequest = "SELECT * FROM JDBCHomeWork_DB_flats WHERE rooms_number = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setInt(1, rooms);
            try (ResultSet rs = ps.executeQuery()) {
                for (; rs.next(); ) {
                    System.out.println("Flats with " + rooms + " rooms:");
                    System.out.println("id: " + rs.getString(1) + " | district: " + rs.getString(2)
                            + " | address: " + rs.getString(3) + " | area: " + rs.getString(4)
                            + " | rooms: " + rs.getString(5) + " | price: " + rs.getString(6));
                }
            }
        }
    }

    public static void filterFlatsByPrice(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter maximum price: ");
        String sPrice = scanner.nextLine();
        int price = Integer.parseInt(sPrice);

        String sqlRequest = "SELECT * FROM JDBCHomeWork_DB_flats WHERE price < ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setInt(1, price);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Flats with price < " + price + ":");
                for (; rs.next(); ) {
                    System.out.println("id: " + rs.getString(1) + " | district: " + rs.getString(2)
                            + " | address: " + rs.getString(3) + " | area: " + rs.getString(4)
                            + " | rooms: " + rs.getString(5) + " | price: " + rs.getString(6));
                }
            }
        }
    }
}
