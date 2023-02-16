package sample;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "CustomerViewServlet", value = "/CustomerViewServlet")
public class CustomerViewServlet extends HttpServlet {

    private List<Customer> customers = Arrays.asList(new Customer("Alex", "Kyiv", 20, 1, "KKK"),
            new Customer("Serhii", "Kyiv", 22, 1, "SSS"),
            new Customer("Oleg", "Sumy", 23, 3, "XXX"),
            new Customer("Vasyl", "Lviv", 24, 2, "MMM"),
            new Customer("Olena", "Odessa", 25, 3, "YYY"));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("table.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
