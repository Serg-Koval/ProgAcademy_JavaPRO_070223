<%@ page import="java.util.List" %>
<%@ page import="sample.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table>
        <thead>
<%--            <tr>Name</tr>--%>
<%--            <tr>Address</tr>--%>
        </thead>

        <tbody>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");

            response.getWriter().println("<table>");

            response.getWriter().println("<tr><th>" + "Name" + "</th><th>" + "LastName" + "</th><th>" + "Address"
                    + "</th><th>" + "Age" + "</th><th>" + "Category" + "</th></tr>");

            for(Customer customer : customers){
                response.getWriter().println("<tr><td>" + customer.getName() + " " + customer.getLastName() + " "
                        + customer.getAddress() + " " + customer.getAge() + " " + customer.getCategory() + "</td></td>");
            }

            response.getWriter().println("</table>");
        %>
        </tbody>

    </table>

</body>
</html>
