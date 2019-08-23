<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: TOSHIBA
Date: 8/2/2019
Time: 4:30 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>A TIMESHEET</title>
<%--/JS lerin hansinin harda verilmesinin ferqi boyukdur --%>

    <script type="text/javascript" src="js/jquery/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-latest.js"<%-- charset="UTF-8--%>></script>
    <script type="text/javascript" src="js/jquery/jquery.layout-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <%--CSS lerin FERQI YOXDUR--%>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link rel="stylesheet" type="text/css" href="css/layout.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"/>
<%--
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
--%>

      <script type="text/javascript">
      $(function () {
          $('#memberBtnId').click(function () {
              $('#memberDataId').show();
              <c:choose>
              <c:when test="${login.role.roleName eq 'ROLE_ADMIN'}">
              getMemberList();
              </c:when>
              <c:when test="${login.role.roleName eq 'ROLE_TEAMLEADER'}">
              var teamLeaderId = ${login.member.id}; <%--.teamLeader--%>
              getTeamListByTeamLeaderId(teamLeaderId);
              </c:when>
              <c:when test="${login.role.roleName eq 'ROLE_MEMBER'}">
              var memberId = ${login.member.id};
              getMemberById(memberId);
              </c:when>
              </c:choose>
          });
      })
      </script>

  </head>

  <body>

  <%
      HttpSession session1 = request.getSession(false);
  if(session.getAttribute("login") == null || session.getAttribute("login").equals("") ) {
      response.sendRedirect("login.jsp");
  }
  %>

  <div class="wrapper">
    <header  style="outline:1px solid; min-height: 50px;text-align: center">TimeSheet
        <div style="float: right";>
            Welcome, ${login.member.name} ${login.member.surname} &nbsp;
            <a href="logout.jsp"  class="btn btn-info btn-lg" style="float: right; font-size: 14px">Log Out </a>
        </div>

    </header>
    <div class="container">
      <div class="row" style=" min-height: 500px;">
        <div class="col-md-4" >
          <%--EAST--%>
          <input type="button" value="Members" id="memberBtnId" class="btnDesign"> <br>
          <div id="memberDataId" style="display: none">
          </div>
        </div>

        <div class="col-md-8">

          <%--CENTER--%>

          <%--<div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Dropdown button
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </div>--%><br>


            <br>
            <div id="timeSheetDataId" style="display: none">

          </div>
        </div>

      </div>
    </div>
    <div  style="text-align: center">Copyright Haci</div>
  </div>
  </body>

</html>
