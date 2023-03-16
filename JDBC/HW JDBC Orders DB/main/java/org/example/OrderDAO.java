package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public interface OrderDAO {

    void createTableGoods() throws SQLException;
    void createTableClients() throws SQLException;
    void createTableOrders() throws SQLException;
    void addClient(String name, int age) throws SQLException;
    List<Client> getAllClients() throws SQLException;
    void addGood(String name, int price) throws SQLException;
    List<Good> getAllGoods() throws SQLException;
    void addOrder(Client client, Scanner scanner) throws SQLException;
    void getAllOrders() throws  SQLException;

}
