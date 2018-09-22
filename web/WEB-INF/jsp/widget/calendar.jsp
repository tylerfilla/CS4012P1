<%--@elvariable id="meetings" type="java.util.Map<Integer, java.util.Map<Integer, edu.umsl.tsfn88.cs4012.project1.servlet.CalendarWidgetServlet$DisplayedMeeting>>"--%>
<%--@elvariable id="startHour" type="long"--%>
<%--@elvariable id="stopHour" type="long"--%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        .calendar-left-col {
            width: 200px;
        }
        .calendar-head-col {
            width: 150px;
            text-align: center;
        }
        .calendar-data-row {
            height: 80px;
        }
        .calendar-data-col {
            padding: 0 !important;
            width: 150px;
            height: 100%;
        }
        .meeting-box-out {
            position: relative;
            height: 100%;
        }
        .meeting-box {
            position: absolute;
            padding: 4px;
            width: calc(100% - 8px);
            margin-left: 4px;
            margin-right: 4px;
            z-index: 2;
            border: 1px solid #d0d0d0;
            background: #e0e0e0;
            text-align: center;
            overflow: hidden;
        }
    </style>
</head>
<body>
<table class="table table-striped table-hover" style="margin: 0;">
    <thead>
    <tr>
        <th class="calendar-left-col"></th>
        <th class="calendar-head-col">Sunday</th>
        <th class="calendar-head-col">Monday</th>
        <th class="calendar-head-col">Tuesday</th>
        <th class="calendar-head-col">Wednesday</th>
        <th class="calendar-head-col">Thursday</th>
        <th class="calendar-head-col">Friday</th>
        <th class="calendar-head-col">Saturday</th>
    </tr>
    </thead>
</table>
<c:if test="${startHour >= stopHour}">
    <h4 style="text-align: center;">Nothing Scheduled</h4>
</c:if>
<c:if test="${startHour < stopHour}">
    <table class="table table-striped table-hover">
        <tbody>
        <c:forEach var="hour24" begin="${startHour}" end="${stopHour}">
            <c:choose>
                <c:when test="${hour24 == 0}">
                    <c:set var="loHour" value="12:00 AM"/>
                    <c:set var="hiHour" value="1:00 AM"/>
                </c:when>
                <c:when test="${hour24 < 11}">
                    <c:set var="loHour" value="${hour24}:00 AM"/>
                    <c:set var="hiHour" value="${hour24 + 1}:00 AM"/>
                </c:when>
                <c:when test="${hour24 == 11}">
                    <c:set var="loHour" value="11:00 AM"/>
                    <c:set var="hiHour" value="12:00 PM"/>
                </c:when>
                <c:when test="${hour24 == 12}">
                    <c:set var="loHour" value="12:00 PM"/>
                    <c:set var="hiHour" value="1:00 PM"/>
                </c:when>
                <c:when test="${hour24 < 23}">
                    <c:set var="loHour" value="${hour24 % 12}:00 PM"/>
                    <c:set var="hiHour" value="${hour24 % 12 + 1}:00 PM"/>
                </c:when>
                <c:when test="${hour24 == 23}">
                    <c:set var="loHour" value="11:00 PM"/>
                    <c:set var="hiHour" value="12:00 AM"/>
                </c:when>
            </c:choose>
            <tr class="calendar-data-row">
                <th class="calendar-left-col">${loHour} - ${hiHour}</th>
                <c:forEach var="day" begin="0" end="6">
                    <td class="calendar-data-col">
                        <c:set var="meeting" value="${meetings[day][hour24]}"/>
                        <c:if test="${not empty meeting}">
                            <div class="meeting-box-out" style="top: ${meeting.hourOffset * 100}%;">
                                <div class="meeting-box" style="height: calc(${meeting.hourSpan * 100}% + ${meeting.hourSpan}px);">
                                    <c:if test="${not empty param.edit}">
                                        <div style="position: absolute; z-index: 3;">
                                            <a href="<c:url value="/doEditSchedule?section=${meeting.sectionId}&action=rem"/>">x</a>
                                        </div>
                                    </c:if>
                                    <p>
                                        From ${meeting.startTimeString}<br>
                                        To ${meeting.stopTimeString}<br>
                                        <i>${meeting.name}<br>(s. ${meeting.sectionNum})</i>
                                    </p>
                                </div>
                            </div>
                        </c:if>
                        <c:remove var="meeting"/>
                    </td>
                </c:forEach>
            </tr>
            <c:remove var="loHour"/>
            <c:remove var="hiHour"/>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
