<%-- 
    Document   : shopping
    Created on : Mar 1, 2023, 9:04:37 AM
    Author     : NguyenTuanVu
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>BOOK STORE</h1>
        <c:if test = "${empty requestScope.Books}">
            <h2> No Record Matched. </h2>
        </c:if>

        <c:if test="${not empty requestScope.Books}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>   
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${requestScope.Books}" varStatus="counter">
                    <form action="addToCardController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${book.sku}</td>
                            <td>${book.name}</td>
                            <td>${book.price}</td>
                            <td> <input type="submit" name="btAction" value="Add to Cart" form="addToCardController"/> </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
            
<!--    <form action="cartController">
        <input type="submit" value="View Your Cart" name="btAction" />
    </form>-->

    <%--       <%

            ProductDAO dao = new ProductDAO();
            dao.printProduct();
            List<ProductDTO> table = dao.getProducts();
            if (table != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ProductDTO dto : table) {
                %>
            <form action="DispatchController">
                <tr>
                    <td><%=dto.getSku()%>
                    </td>
                    <td><%=dto.getName()%>
                        <input type="hidden" name="txtProductId" value="<%=dto.getName()%>" />
                    </td>
                    <td><%=dto.getPrice()%></td>
                    <td><input type="submit" value="Add Book To Your Cart" name="btAction" /></td>
                </tr>
            </form>
        </tbody>

        <%
            }
        %>
        <%
            }
        %>
    </table>--%>

</body>
</html>
