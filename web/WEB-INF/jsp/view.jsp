<%--@elvariable id="student" type="edu.umsl.tsfn88.cs4012.project1.model.Student"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Week - Course Scheduler</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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
    <h2>Your Week</h2>
    <a class="btn btn-primary" href="edit" role="button">Edit Your Schedule</a>
    <iframe id="calendar-frame" src="widget/calendar" frameborder="0"></iframe>
</div>
</body>
</html>
