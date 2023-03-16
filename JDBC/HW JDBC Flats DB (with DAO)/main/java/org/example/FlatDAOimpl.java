package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlatDAOimpl implements FlatDAO {
    private final Connection connection;

    public FlatDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'dao111'");

            if (!rs.next()) {
                stmt.execute("CREATE TABLE dao111 (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        " district VARCHAR(20), address VARCHAR(20), area INT, rooms_number INT, price INT)");
            }
        }
    }

    @Override
    public void addFlat(String district, String address, double area, int rooms, int price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO dao111 (district, address, area, rooms_number, price)" +
                " VALUES(?, ?, ?, ?, ?)");
        ps.setString(1, district);
        ps.setString(2, address);
        ps.setDouble(3, area);
        ps.setInt(4, rooms);
        ps.setInt(5, price);
        ps.executeUpdate();
    }

    @Override
    public List<Flat> createFlatsList(ResultSet rs) throws SQLException {
        List<Flat> list = new ArrayList<>();

        while (rs.next()) {
            Flat flat = new Flat();

            flat.setId(rs.getInt(1));
            flat.setDistrict(rs.getString(2));
            flat.setAddress(rs.getString(3));
            flat.setArea(rs.getDouble(4));
            flat.setRooms(rs.getInt(5));
            flat.setPrice(rs.getInt(6));

            list.add(flat);
        }
        return list;
    }

    @Override
    public List<Flat> getAll() throws SQLException {
        List<Flat> list = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM dao111")) {
            list = this.createFlatsList(rs);
        }
        return list;
    }

    @Override
    public List<Flat> filterByDistrict(String districtFilter) throws SQLException {
        List<Flat> filteredList = new ArrayList<>();
        String sql = "SELECT * FROM dao111 WHERE district = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, districtFilter);
            try (ResultSet rs = statement.executeQuery()) {
                filteredList = this.createFlatsList(rs);
            }
        }
        return filteredList;
    }

    @Override
    public List<Flat> filterByArea(int minArea, int maxArea) throws SQLException {
        List<Flat> filteredList = new ArrayList<>();
        String sql = "SELECT * FROM dao111 WHERE area >= ? AND area <= ?";

        if(minArea > maxArea) {
            System.out.println("Wrong filter parameters!!!");
            return filteredList;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, minArea);
            statement.setInt(2, maxArea);
            try (ResultSet rs = statement.executeQuery()) {
                filteredList = this.createFlatsList(rs);
            }
        }
        return filteredList;
    }

    @Override
    public List<Flat> filterByRooms(int roomsNumber) throws SQLException {
        List<Flat> filteredList = new ArrayList<>();
        String sql = "SELECT * FROM dao111 WHERE rooms_number = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomsNumber);
            try (ResultSet rs = statement.executeQuery()) {
                filteredList = this.createFlatsList(rs);
            }
        }
        return filteredList;
    }

    @Override
    public List<Flat> filterByPrice(int maxPrice) throws SQLException {
        List<Flat> filteredList = new ArrayList<>();
        String sql = "SELECT * FROM dao111 WHERE price <= ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maxPrice);
            try (ResultSet rs = statement.executeQuery()) {
                filteredList = this.createFlatsList(rs);
            }
        }
        return filteredList;
    }
}

