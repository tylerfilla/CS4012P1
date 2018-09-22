<%--@elvariable id="student" type="edu.umsl.tsfn88.cs4012.project1.model.Student"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Week - Course Scheduler</title>
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
    <h2>Your Week <a class="btn btn-primary" href="edit" role="button">Edit</a></h2>
    <iframe id="calendar-frame" src="widget/calendar" frameborder="0"></iframe>
</div>
</body>
</html>
