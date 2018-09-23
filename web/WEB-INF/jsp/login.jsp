<%-- If already logged in... --%>
<c:if test="${not empty sessionScope.student}">
    <%-- ...redirect to index page --%>
    <c:redirect url="/"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Course Scheduler Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form action="doLogin" method="post">
        <h2>Student Login</h2>
        <p>Please enter your student number</p>
        <input type="text" name="num" class="form-control" placeholder="Student Number">
        <c:if test="${not empty param.nonexistent}">
            <p style="color: red;">That student does not exist</p>
        </c:if>
        <input type="submit" class="btn btn-primary" value="Log In">
        <a href="register">Register</a>
    </form>
</div>
</body>
</html>
