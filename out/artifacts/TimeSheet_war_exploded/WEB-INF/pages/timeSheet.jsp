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
    $(function () {


        var monthlyWorkingDays = 0;
        var monthlyWorkingHours = 0;
        var sickLeave = 0;
        var vacation = 0;
        var overTime = 0;
        var holidayAndNonWorkday = 0;
        var monthlyWorkingHours = 0;
        $('.items').each(function (i , v){
            var status = $(v).find('.selectPicker :selected').val();
            var hour = $(v).find('.hourWork').val();

            if(status == 'R' || status == 'R/' ){
                monthlyWorkingDays ++;
            }
            if(status == 'R' || status == 'R/' ){
                monthlyWorkingHours += parseInt(hour) ;
            }
            if(status == 'S'){
                sickLeave ++;
            }
            if(status == 'V'){
                vacation ++;
            }
            if(status == 'O'){
                overTime += parseInt(hour);
            }
            if(status == 'H' || status == 'W' ){
                holidayAndNonWorkday++;
            }
        });
        $('#workingDaysReportId').val(monthlyWorkingDays);
        $('#workingHoursReportId').val(monthlyWorkingHours);
        $('#sickLiveReportId').val(sickLeave);
        $('#vacationReportId').val(vacation);
        $('#overTimeReportId').val(overTime);
        $('#holidaysAndNonWorkingDaysId').val(holidayAndNonWorkday);

        $('#timeSheetTableİd').dataTable({
            paging: true,
            pageLength: 31,
            searching: true,
            lengthChange: false,
            info: false,
            scrollCollapse: true,
            scrollY: "calc(74vh)"
        });

        $('#submitBtnId').click(function () {
            addTimesheet(globMemberId);
        });


        // var status2 = $('#statusComboId').find('.selectPicker :selected').val();


         /*$('#statusComboId').change(function () {
             var status = this.value;
             if(status === 'O')
             {
                 document.getElementById("hourWork").contentEditable = "true";
             }*/

            <%--var status1 = $('#statusComboId').find('.selectPicker :selected').val();--%>
            <%--if(status1 == 'O' && ${tsl.status == 'O'}){--%>
                <%--$('.hourWork').contenteditable(true);--%>
            <%--} else {--%>
                <%--$('.hourWork').contenteditable(false);--%>

            <%--}--%>


        $('#monthesComboId').change(function () {
            alert('isledi');

            globMonthId = $('#monthesComboId').val();
            getTimeSheetList(globMemberId,globFullName,globMonthId);
        });

        $('#tsTableId_paginate').hide();

        $('#tsTableId_length').hide();

        $('#timeSheetTableİd_length').hide();
        $('.dataTables_info').hide();
        $('.dataTables_paginate').hide();

        // $('.datepicker').datepicker( {
        //     changeMonth: true,
        //     changeYear: true,
        //     showButtonPanel: true,
        //     dateFormat: 'yy-MM',
        //     onClose: function(dateText, inst)
        //     {
        //         $(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));
        //     }
        // });
    });
</script>




<div>
    <div style="display: inline-block" >
    <label for="monthesComboId">Choose month:  </label>
        <select id="monthesComboId" change="" class="monthComboBox">
                <option value="0" selected disabled >Choose Month: </option>
                <c:forEach items="${monthesList}" var="ml">
                <option id="${m.id}" value="${ml.id}"> ${ml.id}.  ${ml.name}</option>
                </c:forEach>
        </select>

    <%--<div style="display: inline-block">--%>
        <%--&lt;%&ndash;<input type="text" id="dateId" class="datepicker" placeholder="Date"/>&ndash;%&gt;--%>
            <%--<label for="chooseMonthId">Choose month:</label> <input type="month" id="chooseMonthId" name="chooseMonth"  min="2019-05" value="2019-05">--%>
    <%--</div> <br>--%>
    </div>
    &nbsp;
    <div class="fullname" style="display:inline">fullname</div>


    <div>
        <table id="timeSheetTableİd" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Month</th>
                    <th>Day</th>
                    <th>Status</th>
                    <th>Description</th>
                    <th>Hour</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${timeSheetList}" var="tsl" varStatus="Count">


                    <tr class="items">
                        <td id="countRow" value="${Count.count}">${Count.count}</td>
                        <td class="month" id="${tsl.monthes.id}">${tsl.monthes.name}</td>
                        <td class="day" value="${tsl.day}">${tsl.day}</td>
                        <td class="status" value="">
                            <select id="statusComboId_${Count.count}" class="selectPicker"
                                    onchange="if ((this.value) === 'O')
                                    {
                                    alert(this.value);
                                    $('#inputHour_${Count.count}').attr('disabled', false)
                                    }
                                    else
                                    {
                                    $('#inputHour_${Count.count}').attr('disabled', true)
                                    }"> <%--select baglanir--%>

                                <option value="NF" selected disabled >Not Fill</option>
                                <option <c:if test="${tsl.status == 'R'}"> selected </c:if> value="R">Regular Day </option>
                                <option <c:if test="${tsl.status == 'S'}"> selected </c:if> value="S">Sick Leave</option>
                                <option <c:if test="${tsl.status == 'W'}"> selected </c:if> value="W">Weekend</option>
                                <option <c:if test="${tsl.status == 'H'}"> selected </c:if> value="H">Holiday</option>
                                <option <c:if test="${tsl.status == 'V'}"> selected </c:if> value="V">Vacation</option>
                                <option <c:if test="${tsl.status == 'R/'}"> selected </c:if> value="R/">Short Day</option>
                                <option <c:if test="${tsl.status == 'O'}"> selected </c:if> value="O">Overtime</option>
                            </select>
                        <td class="description" contenteditable="true" >${tsl.description}</td>
                        <td>
                        <input  class="hourWork"  id="inputHour_${Count.count}" disabled="true" type="text" <%--disabled="disabled"--%> value="${tsl.hour}" >
                        </td>



                    </tr>


                </c:forEach>
                <%--<c:forEach begin="1" end="${daysInMonth}" var="dm">
                    <tr ${timeSheetList[dm].id}>
                        <td class="month" id = "${monthId+1}">${mName} </td>
                        <td class="day">${dm}</td>
                        <td class="status">
                            <select <c:if test="${dm != dayOfMonth}"> disabled </c:if> id="statusComboId" class="selectPicker">
                                <option value="NF" selected disabled >Not Fill</option>

                                    &lt;%&ndash;<option <c:if test="${timeSheetList[dm].status.name == status.name}"> selected </c:if> value="${sl.id}">${sl.name}</option>&ndash;%&gt;

                                <option <c:if test="${timeSheetList[dm].status.name == 'R'}"> selected </c:if> value="R">Regular day </option>
                                <option <c:if test="${timeSheetList[dm].status.name == 'S'}"> selected </c:if> value="S">Sick Leave</option>
                                <option <c:if test="${timeSheetList[dm].status.name == 'W'}"> selected </c:if> value="W">Weekend</option>
                                <option <c:if test="${timeSheetList[dm].status.name == 'H'}"> selected </c:if> value="H">Holiday</option>
                                <option <c:if test="${timeSheetList[dm].status.name == 'V'}"> selected </c:if> value="V">Vacation</option>
                                <option <c:if test="${timeSheetList[dm].status.name == 'R/'}"> selected </c:if> value="R/">Short day</option>

                            </select>
                        </td>

                        <td class="description" <c:if test="${dm == dayOfMonth}" > contenteditable="true" </c:if> >${timeSheetList[dm].description}</td>

                        <td id="saveBtn" <c:if test="${dm != dayOfMonth}" > </c:if> >
                            <div>
                                <input type="button" id="addTimeSheetBtnId" value="Save">
                            </div>
                        </td>

                        &lt;%&ndash;<td>${t.r}</td>
                        <td class="monthName">${t.monthes.name}</td>
                        <td class="day">${t.day}</td>
                        <td class="statusName">
                        <select id="statusComboViewId"disabled>
                            <option >${t.status.name}</option>
                        </select>
                        </td>
                        <td class="description">${t.description}</td>&ndash;%&gt;
                    </tr>
                </c:forEach>--%>


            </tbody>
            <tfoot>
                <tr>
                    <th>№</th>
                    <th>Month</th>
                    <th>Day</th>
                    <th>Status</th>
                    <th>Description</th>
                    <th>Hour</th>
                </tr>
            </tfoot>
        </table>

        <div id="submitBtnId">
            <input type="button" value="SUBMIT" >
        </div>
        <br>
        <br>

        <table>
            <tr>
                <td><label float="left"  for="workingDaysReportId">Monthly Working Days (Regular and Short days):</label></td>
                <td><input float="right" id="workingDaysReportId" name="workingDaysReport" type="text" readonly></td>
            </tr>
            <tr>
                <td><label style="text-align: left" for="workingHoursReportId">Monthly Working Hours (Regular and Short days):</label></td>
                <td><input float="right" id="workingHoursReportId" name="workingHoursReport" type="text" readonly></td>
            </tr>
            <tr>
                <td><label style="text-align: left" for="sickLiveReportId">Sick Leave: </label></td>
                <td><input float="right" id="sickLiveReportId" name="sickLiveReport" type="text" readonly></td>
            </tr>
            <tr>
                <td><label style="text-align: left" for="vacationReportId">Vacation: </label></td>
                <td><input float="right" id="vacationReportId" name="vacationReport" type="text" readonly></td>
            </tr>
            <tr>
                <td>            <label style="text-align: left" for="overTimeReportId">Over Time (Hours): </label>
                </td>
                <td>            <input float="right" id="overTimeReportId" name="overTimeReport" type="overTimeReport" readonly>
                </td>
            </tr>
            <tr>
                <td>            <label style="text-align: left" for="holidaysAndNonWorkingDaysId">Holidays and Non-working days (No Vacation):</label>
                </td>
                <td>            <input float="right" id="holidaysAndNonWorkingDaysId" name="holidaysAndNonWorkingDays" type="text" readonly>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td>            <label style="text-align: left" for="monthlyWorkHoursId">Monthly Working Hours: </label>--%>
                <%--</td>--%>
                <%--<td>            <input float="right" id="monthlyWorkHoursId" name="holidaysAndNonWorkingDays" type="text" readonly>--%>
                <%--</td>--%>
            <%--</tr>--%>
        </table>

    </div>
    </div>