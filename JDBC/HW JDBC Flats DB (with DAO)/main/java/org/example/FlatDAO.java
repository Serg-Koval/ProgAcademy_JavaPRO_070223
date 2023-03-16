package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface FlatDAO {
    void createTable() throws SQLException;
    void addFlat(String district, String address, double area, int rooms, int price) throws SQLException;
    List<Flat> createFlatsList(ResultSet rs) throws SQLException;
    List<Flat> getAll() throws SQLException;
    List<Flat> filterByDistrict(String district) throws SQLException;
    List<Flat> filterByArea(int minArea, int maxArea) throws SQLException;
    List<Flat> filterByRooms(int roomsNumber) throws SQLException;
    List<Flat> filterByPrice(int maxPrice) throws SQLException;
//    void filterFlats(Scanner scanner) throws SQLException;

}
