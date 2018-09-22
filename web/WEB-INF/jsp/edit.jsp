<%--@elvariable id="courseNames" type="java.util.Map"--%>
<%--@elvariable id="sections" type="java.util.List"--%>
<%--@elvariable id="student" type="edu.umsl.tsfn88.cs4012.project1.model.Student"--%>
<%--@elvariable id="userSections" type="java.util.Map"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Your Schedule - Course Scheduler</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        html, body {
            height: 100%;
        }
        #login-bar {
            text-align: right;
        }
        #root-container {
            height: 100%;
            display: flex;
            flex-flow: column;
        }
        #calendar-frame {
            width: 100%;
            flex: 2;
        }
    </style>
</head>
<body>
<div id="root-container" class="container">
    <h4 id="login-bar">Hi, ${student.fname}. <a class="btn btn-primary" href="doLogout" role="button">Log Out</a></h4>
    <form action="doEditSchedule" method="post">
        <div class="form-group">
            <label for="addScheduleSelect">Add course sections:</label>
            <select multiple name="section" id="addScheduleSelect" class="form-control">
                <c:forEach var="section" items="${sections}">
                    <c:set var="already" value="${not empty userSections[section.sid]}"/>
                    <option value="${section.sid}">${courseNames[section.course]}, section ${section.num}&nbsp;<c:if test="${already}">(in progress)</c:if></option>
                    <c:remove var="already"/>
                </c:forEach>
            </select>
        </div>
        <button name="action" class="btn btn-primary" type="submit" value="add">Add Selected</button>
        <button name="action" class="btn btn-primary" type="submit" value="rem">Remove Selected</button>
        <button class="btn" type="reset">Deselect All</button>
    </form>
    <h2>Your Week So Far</h2>
    <iframe id="calendar-frame" src="widget/calendar?edit=1" frameborder="0"></iframe>
</div>
</body>
</html>
