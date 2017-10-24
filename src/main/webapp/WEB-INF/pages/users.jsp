<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
${error}
<form>
    <input type="hidden" name="defaultSort" value="${defaultSort}">
    <table class="tg">
        <tr>
            <th width="40">ID</th>
            <th width="120">NAME</th>
            <th width="40">AGE</th>
            <th width="40">IS ADMIN</th>
            <th width="200">CREATED DATE</th>
            <th width="50"></th>
            <th width="50"></th>
        </tr>
        <tr>
            <td><input type="text" name="id" size="8px" value="${id}" maxlength="9"></td>
            <td><input type="text" name="name" size="15px" value="${name}" maxlength="24"></td>
            <td><input type="text" name="age" size="8px" value="${age}" maxlength="9"></td>
            <td><input type="text" name="is_admin" size="8px" value="${is_admin}" maxlength="5"></td>
            <td><input type="text" name="created_date" size="20px" value="${created_date}" maxlength="25"></td>
            <td><button type="submit" value="FIND" name="button">SEARCH</button></td>
            <td><button type="submit" value="ADD/UPDATE" name="button">ADD/UPDATE</button></td>
        </tr>
        <tr>
            <td><button type="submit" value="ID_UP" name="sort">UP</button>
                <button type="submit" value="ID_DOWN" name="sort">DOWN</button></td>
            <td><button type="submit" value="NAME_UP" name="sort">UP</button>
                <button type="submit" value="NAME_DOWN" name="sort">DOWN</button></td>
            <td><button type="submit" value="AGE_UP" name="sort">UP</button>
                <button type="submit" value="AGE_DOWN" name="sort">DOWN</button></td>
            <td><button type="submit" value="ISADMIN_UP" name="sort">UP</button>
                <button type="submit" value="ISADMIN_DOWN" name="sort">DOWN</button></td>
            <td><button type="submit" value="CREATEDDATE_UP" name="sort">UP</button>
                <button type="submit" value="CREATEDDATE_DOWN" name="sort">DOWN</button></td>
            <td colspan="2"><button type="submit" value="RESET FIND" name="button">RESRT</button></td>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.isAdmin()}</td>
                <td>${format.format(user.createdDate)}</td>
                <td><button type="submit" value="${user.id}" name="edit">edit</button></td>
                <td><button type="submit" value="${user.id}" name="delete">delete</button></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
