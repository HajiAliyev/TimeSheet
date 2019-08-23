<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TOSHIBA
  Date: 4/29/2019
  Time: 4:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0" selected disabled >Not Fill</option>
<c:forEach items="${statusList}" var="sl">
    <option value="${sl.id}">${sl.name}</option>
</c:forEach>