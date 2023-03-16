package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            FlatDAO dao = new FlatDAOimpl(connection);
            dao.createTable();

            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: view flats");
                System.out.println("3: filter flats by district");
                System.out.println("4: filter flats by area");
                System.out.println("5: filter flats by number of rooms");
                System.out.println("6: filter flats by price");
                System.out.print("-> ");

                String s = scanner.nextLine();
                switch (s) {
                    case "1":
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

                        dao.addFlat(district, address, area, rooms, price);
                        break;
                    case "2":
                        List<Flat> list = dao.getAll();
                        for (Flat flat : list) {
                            System.out.println(flat);
                        }
                        break;
                    case "3":
                        System.out.print("Enter district: ");
                        String districtFilter = scanner.nextLine();
                        List<Flat> filteredListDistrict = dao.filterByDistrict(districtFilter);
                        for (Flat flat : filteredListDistrict) {
                            System.out.println(flat);
                        }
                        break;
                    case "4":
                        System.out.print("Enter minimum area: ");
                        String sMinArea = scanner.nextLine();
                        int minArea = Integer.parseInt(sMinArea);
                        System.out.print("Enter maximum area: ");
                        String sMaxArea = scanner.nextLine();
                        int maxArea = Integer.parseInt(sMaxArea);
                        List<Flat> filteredListArea = dao.filterByArea(minArea, maxArea);
                        for (Flat flat : filteredListArea) {
                            System.out.println(flat);
                        }
                        break;
                    case "5":
                        System.out.print("Enter number of rooms: ");
                        String sRoomsNumber = scanner.nextLine();
                        int roomsNumber = Integer.parseInt(sRoomsNumber);
                        List<Flat> filteredListRooms = dao.filterByRooms(roomsNumber);
                        for (Flat flat : filteredListRooms) {
                            System.out.println(flat);
                        }
                        break;
                    case "6":
                        System.out.print("Enter maximum price: ");
                        String sMaxPrice = scanner.nextLine();
                        int maxPrice = Integer.parseInt(sMaxPrice);
                        List<Flat> filteredListPrice = dao.filterByPrice(maxPrice);
                        for (Flat flat : filteredListPrice) {
                            System.out.println(flat);
                        }
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
