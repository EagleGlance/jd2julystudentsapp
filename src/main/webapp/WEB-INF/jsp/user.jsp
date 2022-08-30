<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Personal User Page</title>
</head>

<style>
    <%--    Add css for table, rows, column here--%>
</style>
<body>

<div>
    <b>
        Hello, ${userName}
    </b>
</div>

<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td>User Id</td>
            <td>User Name</td>
            <td>User Surname</td>
            <td>Birth date</td>
            <%--            <td>Gender</td>--%>
            <td>Is Deleted</td>
            <td>Created</td>
            <td>Changed</td>
            <td>Weight</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.surname}</td>
                <td>${user.birth}</td>
                <td>${user.isDeleted}</td>
                <td><fmt:formatDate value="${user.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${user.modificationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${user.weight}"/></td>
                <td>
                    <button>Edit Profile Info</button>
                </td>
                <td>
                    <button>Delete Account</button>
                </td>
            </tr>
    </table>
</div>

</body>
</html>
