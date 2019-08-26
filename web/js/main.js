var globMemberId = '';
var globMonthId = '';
var globFullName = '';
$(function () {
    $('#monthesComboId').change(function () {


        // alert('isledi');

        // globMonthId = $('#monthesComboId').val();
        // getTimeSheetList(globMemberId,globFullName,globMonthId);
    });
    //alert('Success');

    /*$('#memberBtnId').click(function () { //MEMBER BTN
        $('#memberDataId').show();
        getMemberList();
        /!*$('#patientTableId').show();
        $('#doctorDataBtnId').hide();*!/
    });
    $('#timeSheetDataBtnId').click(function () {
        $('#timeSheetDataId').show();
        getTimeSheetList();
    });*/





        $(document).on('click', '#memberTableId tr', function () {
            var d = new Date();
            var monthId = d.getMonth()+1;
            globMonthId = monthId;
            var rowId = $(this).attr('id');
            globMemberId = rowId;
            $('#timeSheetDataId').show();
            var fullname = $(this).find('.name').text();
            globFullName = fullname;
            //alert(fullname);
            getTimeSheetList(rowId, fullname, monthId);
            // getReport();

            //     $('#monthesComboId').on('change',function () {
            //         alert('isledi');
            //
            //         globMonthId = $('#monthesComboId').val();
            //         getTimeSheetList(globMemberId,fullname,globMonthId);
            // });



        });
    /*$('chooseMonthId').change(function () {
        var monthId = ;
    })*/

    // $("#monthesComboId").on('change',function() {
    //         var monthComboId = $(this).closest('option').find('#monthesComboId :selected').attr("id");
    //         alert(monthComboId);
    //     });




});




function getMemberList() {
        $.ajax({
            url: 'cs?action=getMemberList',
            type: 'GET',
            dataType: 'html',
            success: function (response) {
                $('#memberDataId').html(response);
            },
            error: function (response) {
                alert('Error');
            }
        });
}

function getMemberById(memberId) {

        $.ajax({
            url: 'cs?action=getMemberById',
            type: 'GET',
            data: 'memberId='+ memberId,
            dataType: 'html',
            success: function (response) {
                $('#memberDataId').html(response)
            },
            error: function (response) {
                alert('Error');
            }
        });
}

function getTeamListByTeamLeaderId(teamLeaderId) {
        $.ajax({
            url: 'cs?action=getTeamListByTeamLeaderId',
            type: 'GET',
            data: 'teamLeaderId='+ teamLeaderId,
            dataType: 'html',
            success: function (response) {
                $('#memberDataId').html(response);
            },
            error: function (response) {
                alert('Error');
            }
        });

}

function getTimeSheetList(rowId, globFullname, monthId) {

    if (rowId != null && monthId != null) {
        var monthId = globMonthId;
        var data ={
            rowId:rowId,
            monthId:monthId
        };

        $.ajax({
            type: 'GET',
            url: 'cs?action=getTimeSheetlist',
            data: data,
            dataType: 'html',
            success: function (data) {
                $('#timeSheetDataId').html(data);
                //alert(rowId);
                $('.fullname').text(globFullName);
            },
            error: function () {
                alert('Error');
            }
        });
    }
}

function timeSheetDatePicker() {
    var currentDate = new Date();
    $('#dateId').datepicker("setDate",currentDate);
    var cd = $('.datepicker').val();
    globMonthId = cd;
}

function addTimesheet(globMemberId)
{
    var memberId = globMemberId;
    var data = {};
    // alert(memberId);


    $('.items').each(function (i , v){
        var monthId = $(v).find('.month').attr('id');
        var day = $(v).find('.day').text();
        var status = $(v).find('.selectPicker :selected').val();
        var description = $(v).find('.description').text();
        var hour = $(v).find('.hourWork').val();

        data[i]= {monthId:monthId, day:day, status:status, description:description, hour:hour}
        if(monthId == 0 || day == 0 || status.trim() =="" ) {
            alert('Data is empty!');
            return;
        }
    });
console.log(data);



    $.ajax({
        type: 'POST',
        url: 'cs?action=editTimeSheet',
        data: {"myArray": JSON.stringify(data), "memberId":memberId},
        success: function () {
            alert('Timesheet is successfully added!');
            getTimeSheetList(globMemberId,globFullName,globMonthId);
        },
        error: function () {
            alert('Problem! Timesheet is not successfully added!')
        }
    });
}

function getStatusCombo() {
    $.ajax({
        url: 'cs?action=getStatusCombo',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#statusComboId').html(data);
        }
    });
}

function getMonthesCombo(){
    $.ajax({
        url: 'cs?action=getMonthesCombo',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#statusComboId').html(data);
        }
    });
}

function  getReport(){
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
            monthlyWorkingHours += hour ;
        }
        if(status == 'S'){
            monthlyWorkingDays ++;
        }
        if(status == 'V'){
            vacation ++;
        }
        if(status == 'O'){
            overTime += hour;
        }
        if(status == 'H' || status == 'W' ){
            holidayAndNonWorkday++;
        }
    });
    $('#workingDaysReportId').text(monthlyWorkingDays);
    $('#workingHoursReportId').text(monthlyWorkingHours);
    $('#sickLiveReportId').text(sickLeave);
    $('#vacationReportId').text(vacation);
    $('#overTimeReportId').text(overTime);
    $('#holidaysAndNonWorkingDaysId').text(holidayAndNonWorkday);
}
