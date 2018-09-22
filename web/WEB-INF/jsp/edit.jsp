<%--@elvariable id="student" type="edu.umsl.tsfn88.cs4012.project1.model.Student"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Your Schedule - Course Scheduler</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    Hi, ${student.fname}. <a href="doLogout">Log Out</a>
    <div class="container">
        <iframe src="widget/calendar.jsp?edit=1" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>
