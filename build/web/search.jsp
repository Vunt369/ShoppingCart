<%-- 
    Document   : search
    Created on : Feb 16, 2023, 8:10:50 AM
    Author     : NguyenTuanVu
--%>
<!--jsp yeu cau container cung cap mot so yeu cau-->
<%@page import="vunt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <style>
            #logout{
                position: absolute;
                right: 30px;  
            }
        </style>
    </head>
    <body>
        <h1>                
            <font color="red"> Welcome, ${sessionScope.USER.fullName} </font><br/>  
        </h1>
        <form action="logOutController">
            <input id="logout" type="submit" value="Logout" name="btAction" />
        </form>
        <form action="searchController">
            Search value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br>
            <!-- gia tri tra ve sau khi search se la parameter-->
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:if test="${empty requestScope.SEARCH_RESULT}">
                No record matching!!
            </c:if>

            <c:if test="${not empty requestScope.SEARCH_RESULT}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="dto" items="${requestScope.SEARCH_RESULT}" varStatus="counter">
                        <form action="updateControler">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}"/>
                                </td>
                                <td>${dto.fullName}</td>
                                <td>
                                    <input type="checkbox" name="checkRole" value="ADMIN"
                                           <c:if test="${dto.role}">checked</c:if>
                                               >    
                                    </td>

                                    <td>
                                    <c:url var="deleteLink" value="deleteController">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">delete</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update"/>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />                                          
                                </td>
                            </tr> 
                        </form>
                    </c:forEach>

                </tbody>
            </table>  
        </c:if>
    </c:if>
    <%-- <%
         Cookie[] cookies = request.getCookies();
         if (cookies != null) {
             Cookie lastCookies = cookies[cookies.length - 1];
             String username = lastCookies.getName();
     %>
     <font color="red"> 
     Welcome, <%= username%>
     </font>
     <%
         }
     %>
 </form>

    <h1>Search Page</h1>
    <form action="DispatchController">
        Search value <input type="text" name="txtSearchValue"
                            value="<%= request.getParameter("txtSearchValue")%>" /><br>
        <!--            //gia tri tra ve sau khi search se la parameter-->
        <input type="submit" value="Search" name="btAction" />
    </form>
    <%
        String searchValue = request.getParameter("txtSearchValue");
//check first time for search Value
        if (searchValue != null) {
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Password</th>
                <th>Full Name</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;//STT in table

                for (RegistrationDTO dto : result) {
                    String urlRewriting = "DispatchController"
                            + "?btAction=Delete"
                            + "&pk=" + dto.getUsername()
                            + "&lastSearchValue=" + searchValue;
            %>
        <form action="DispatchController">
            <tr>
                <td>
                    <%= ++count%>
                    .</td>
                <td>
                    <%=dto.getUsername()%>
                    <input type="hidden" name="txtUserName" value="<%=dto.getUsername()%>" />
                </td>
                <!--                        ------------------->                   
                <td>
                    <input type="text" name="txtPassword" value="<%=dto.getPassword()%>" />
                </td>
                <!--                        ------------------->                    
                <td>
                    <%= dto.getFullName()%>
                </td>
                <!--------------------->                    
                <td> 
                    <input type="checkbox" name="checkRole" value="ADMIN"
                           <%
                               if (dto.isRole()) {
                           %>
                           checked = "checked"
                           <%
                               }
                           %>
                           />
                    <!--------------------->
                </td>

                <td>
                    <a href="<%= urlRewriting%>">Delete</a>
                </td>
                <!------------------->
                <td>
                    <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                    <input type="submit" value="Update" name="btAction" />
                </td>
            </tr>
        </form>

        <%
            }
        %>
    </tbody>
</table>


<%
} else {
%>
<!--        //tach code, ko bi loi, chi bi loi khi process request-->
<h2>
    No record is matched!!!
</h2>
<%
        }//end of no record is matched
    }//end search Value is not really existed when page is directly accessed
%>    --%>                  

</body>
</html>
