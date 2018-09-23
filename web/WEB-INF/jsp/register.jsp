<%-- If already logged in... --%>
<c:if test="${not empty sessionScope.student}">
    <%-- ...redirect to index page --%>
    <c:redirect url="/"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Course Scheduler Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form action="doRegister" method="post">
        <h2>Student Registration</h2>
        <p>Please register for an account below</p>
        <input type="text" name="fname" class="form-control" placeholder="First Name">
        <input type="text" name="lname" class="form-control" placeholder="Last Name">
        <input type="text" name="num" class="form-control" placeholder="Student Number">
        <input type="submit" name="btn" class="btn" value="Submit">
        <a href="login">Back</a>
    </form>
</div>
</body>
</html>
