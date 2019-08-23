<%--
  Created by IntelliJ IDEA.
  User: TOSHIBA
  Date: 8/8/2019
  Time: 6:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
session.removeAttribute("login");
session.invalidate();
response.sendRedirect("login.jsp");

%>