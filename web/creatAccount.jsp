<%-- 
    Document   : creatAcount
    Created on : Mar 2, 2023, 9:08:36 AM
    Author     : NguyenTuanVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createAccountController" method="POST">
            <c:set var="err" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="" />(6 - 20 chars)<br>
            <c:if test="${not empty err.userNameLengthError}">
                <font color="red" > ${err.userNameLengthError}</font>
            </c:if><br>
            Password* <input type="password" name="txtPassword" value="" />(6-30 chars) <br>
            <c:if test="${not empty err.passwordLengthError}">
                <font color="red"> ${err.passwordLengthError}</font>
            </c:if><br>
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <c:if test="${not empty err.confirmNotMatch}">
                <font color="red"> ${err.confirmNotMatch}</font>
            </c:if><br>
            Full name <input type="text" name="txtFullname" value="" />(2-50 chars) <br>
            <c:if test="${not empty err.fullNameLengthError}">
                <font color="red"> ${err.fullNameLengthError}</font>
            </c:if><br>
            <input type="submit" value="Create New Account" name="btAction" /><br>
            <input type="reset" value="Reset" /><br>
            <c:if test="${not empty err.usenameIsExisted}">
                <font color="red"> ${err.usenameIsExisted}</font>
            </c:if>
        </form>
    </body>
</html>
