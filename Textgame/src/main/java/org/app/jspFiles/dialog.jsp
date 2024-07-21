<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="org.app.models.Choice"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>TextGame</title>
    </head>
    <body>
        <p>${question}</p>
        <form name="form1" action="/dialog/" method="POST">
            <c:forEach var="item" items="${choices}">
                <div style="display: flex">
                    <input name="id" type="radio" value="${item.transition}" id="${item.transition}">
                    <label for="${item.transition}">${item.text}</label>
                </div>
            </c:forEach>
            <input type="SUBMIT" value="Подтвердить">
        </form>
    </body>
</html>