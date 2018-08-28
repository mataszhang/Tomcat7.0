<%--
  Created by IntelliJ IDEA.
  User: MATAS
  Date: 2018/8/28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    for (int i = 0; i < 20000; i++) {
        out.println("s");
    }

    if (true) {
        throw new RuntimeException("error");
    }
%>
</body>
</html>
