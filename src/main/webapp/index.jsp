<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="refresh" content="0;URL=<c:url value="/users"/>" />
</head>
<body>
<a href="<c:url value="/users"/>">Users</a>
</body>
</html>