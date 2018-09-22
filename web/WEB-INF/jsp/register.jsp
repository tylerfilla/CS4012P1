<%-- If already logged in... --%>
<c:if test="${not empty sessionScope.student}">
    <%-- ...redirect to index page --%>
    <c:redirect url="/"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Course Scheduler Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="panel">
        <form action="doRegister" method="post">
            <h2>Student Registration</h2>
            <p>Please fill out this form to register</p>
            <input type="text" name="fname" class="form-control" placeholder="First Name">
            <input type="text" name="lname" class="form-control" placeholder="Last Name">
            <input type="text" name="num" class="form-control" placeholder="Student Number">
            <input type="submit" name="btn" class="btn" value="Complete Registration">
        </form>
    </div>
</div>
</body>
</html>
