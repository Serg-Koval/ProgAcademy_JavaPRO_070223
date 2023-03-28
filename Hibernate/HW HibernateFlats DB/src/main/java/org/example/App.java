package org.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class App {
    static EntityManager emanager;
    static EntityManagerFactory emfactory;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // create connection
            emfactory = Persistence.createEntityManagerFactory("JPATestFlats");
            emanager = emfactory.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add flat");
                    System.out.println("2: view flats");
                    System.out.println("3: filter flats by district");
                    System.out.println("4: filter flats by area");
                    System.out.println("5: filter flats by number of rooms");
                    System.out.println("6: filter flats by price");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            viewFlats(sc);
                            break;
                        case "3":
                            filterByDistrict(sc);
                            break;
                        case "4":
                            filterByArea(sc);
                            break;
                        case "5":
                            filterByRooms(sc);
                            break;
                        case "6":
                            filterByPrice(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                emanager.close();
                emfactory.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void addFlat(Scanner scanner) {
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

        emanager.getTransaction().begin();
        try {
            Flat flat = new Flat(district, address, area, rooms, price);
            emanager.persist(flat);
            emanager.getTransaction().commit();
        } catch (Exception ex) {
            emanager.getTransaction().rollback();
        }
    }

    public static void viewFlats(Scanner scanner) {
        Query query = emanager.createQuery("SELECT flat FROM Flat flat", Flat.class);
        List<Flat> flatList = (List<Flat>) query.getResultList();

        for (Flat flat : flatList) {
            System.out.println(flat);
        }
    }

    public static void filterByDistrict(Scanner scanner) {
        System.out.print("Enter district: ");
        String districtFilter = scanner.nextLine();
        Query query = emanager.createQuery("SELECT flat FROM Flat flat WHERE flat.district = :district", Flat.class);
        query.setParameter("district", districtFilter);

        List<Flat> flatList = (List<Flat>) query.getResultList();

        for (Flat flat : flatList) {
            System.out.println(flat);
        }
    }

    public static void filterByArea(Scanner scanner) {
        System.out.print("Enter minimum area: ");
        String sMinArea = scanner.nextLine();
        double minArea = Double.parseDouble(sMinArea);
        System.out.print("Enter maximum area: ");
        String sMaxArea = scanner.nextLine();
        double maxArea = Double.parseDouble(sMaxArea);

        Query query = emanager.createQuery("SELECT flat FROM Flat flat WHERE flat.area > :minArea AND flat.area < :maxArea", Flat.class);
        query.setParameter("minArea", minArea);
        query.setParameter("maxArea", maxArea);

        List<Flat> flatList = (List<Flat>) query.getResultList();

        for (Flat flat : flatList) {
            System.out.println(flat);
        }
    }

    public static void filterByRooms(Scanner scanner) {
        System.out.print("Enter number of rooms: ");
        String sRooms = scanner.nextLine();
        int rooms = Integer.parseInt(sRooms);
        Query query = emanager.createQuery("SELECT flat FROM Flat flat WHERE flat.rooms = :roomsNumber", Flat.class);
        query.setParameter("roomsNumber", rooms);

        List<Flat> flatList = (List<Flat>) query.getResultList();

        for (Flat flat : flatList) {
            System.out.println(flat);
        }
    }

    public static void filterByPrice(Scanner scanner) {
        System.out.print("Enter maximum price: ");
        String sPrice = scanner.nextLine();
        int price = Integer.parseInt(sPrice);
        Query query = emanager.createQuery("SELECT flat FROM Flat flat WHERE flat.price < :flatPrice", Flat.class);
        query.setParameter("flatPrice", price);

        List<Flat> flatList = (List<Flat>) query.getResultList();

        for (Flat flat : flatList) {
            System.out.println(flat);
        }
    }
}
