<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploaded Items</title>
        <link rel="stylesheet" type="text/css" href="theme.css">
    </head>
    <body>
        <h1>Uploaded Items</h1>
        <div class="container">
        <c:choose>
            <c:when test="${not empty error}">
                <p><c:out value="${error}" /></p>
            </c:when>
            <c:when test="${not empty items}">
                <table border="1" style="border-collapse:collapse;" cellpadding="5px">
                    <tr align="left"><th>Date</th><th>Value</th><th>Description</th></tr>
                    <c:forEach items="${items}" var="entry">
                        <tr>
                            <td>
                                <fmt:formatDate value="${entry.value.date}"
                                    pattern="M/d/yy h:mm a z" />
                            </td>
                            <td align="right">
                                <fmt:formatNumber value="${entry.value.amount}"
                                    type="currency" />
                            </td>
                            <td>
                                <c:out value="${entry.value.description}" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No content found.</p>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>
