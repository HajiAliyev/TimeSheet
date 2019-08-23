<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TOSHIBA
  Date: 8/4/2019
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function ()
    {
        $('#memberTableId').dataTable();
    })
</script>

<table id="memberTableId" class="display" style="width:100%">
    <thead>
    <tr>
        <th>№</th>
        <th>Name&Surname</th>
    </tr>
    </thead>
    <tbody>

    <c:choose>
        <c:when test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <c:forEach items="${memberList}" var="m" varStatus="Count">
                <tr id="${m.id}">
                    <td>${Count.count}</td>
                    <td class="name">${m.name} ${m.surname}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:when test="${login.role.roleName eq 'ROLE_TEAMLEADER'}">
            <c:forEach items="${teamList}" var="t" varStatus="Count">
                <tr id="${t.id}">
                    <td>${Count.count}</td>
                    <td class="name">${t.name} ${t.surname}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:when test="${login.role.roleName eq 'ROLE_MEMBER'}">
            <tr id="${member.id}">
                <td>${Count.count}</td>
                <td class="name">${member.name} ${member.surname}</td>
            </tr>
        </c:when>
    </c:choose>




    </tbody>
</table>
