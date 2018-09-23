<%--@elvariable id="courses" type="java.util.List"--%>
<%--@elvariable id="courseNames" type="java.util.Map"--%>
<%--@elvariable id="sections" type="java.util.List"--%>
<%--@elvariable id="student" type="edu.umsl.tsfn88.cs4012.project1.model.Student"--%>
<%--@elvariable id="userSections" type="java.util.Map"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Your Schedule - Course Scheduler</title>
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
    <h2>Your Week So Far</h2>
    <div class="btn-group">
        <a class="btn btn-primary" href="view" role="button">Done Editing</a>
        <button type="button" class="btn" data-toggle="modal" data-target="#modalAddSchedule">Add/Remove Courses</button>
        <button type="button" class="btn" data-toggle="modal" data-target="#modalAddCourse">Define a New Course</button>
        <button type="button" class="btn" data-toggle="modal" data-target="#modalAddSection">Define a New Section</button>
    </div>
    <iframe id="calendar-frame" src="widget/calendar?edit=1" frameborder="0"></iframe>
</div>
<div class="modal" id="modalAddSchedule" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <form action="doEditSchedule" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">New Course Section</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="addScheduleSelect">Select sections below:</label>
                        <select multiple name="section" id="addScheduleSelect" class="form-control">
                            <c:forEach var="section" items="${sections}">
                                <c:set var="already" value="${not empty userSections[section.sid]}"/>
                                <option value="${section.sid}">${courseNames[section.course]}, section ${section.num}</option>
                                <c:remove var="already"/>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" name="action" value="add" class="btn btn-primary">Add selected sections</button>
                    <button type="submit" name="action" value="rem" class="btn btn-secondary">Remove selected sections</button>
                    <button class="btn btn-secondary" data-dismiss="modal" onclick="this.form.reset()">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal" id="modalAddCourse" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <form action="doAddCourse" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">New Course</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="addCourseName">Enter the course name:</label>
                        <input type="text" name="name" id="addCourseName" class="form-control" placeholder="Course Name">
                    </div>
                    <div class="form-group">
                        <label for="addCourseNum">Enter the course number:</label>
                        <input type="text" name="num" id="addCourseNum" class="form-control" placeholder="Course Number">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Save changes</button>
                    <button class="btn btn-secondary" data-dismiss="modal" onclick="this.form.reset()">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal" id="modalAddSection" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <form action="doAddSection" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">New Course Section</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="addSectionCourse">Choose a course:</label>
                        <select name="course" id="addSectionCourse" class="form-control">
                            <c:forEach var="course" items="${courses}">
                                <option value="${course.cid}">${course.name} (${course.num})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="addSectionName">Enter a section number:</label>
                        <input type="text" name="num" id="addSectionName" class="form-control" placeholder="Section Number">
                    </div>
                    <div class="form-group">
                        <label for="addSectionDays">Choose meeting days:</label>
                        <select multiple name="days" id="addSectionDays" class="form-control">
                            <option value="0">Sunday</option>
                            <option value="1">Monday</option>
                            <option value="2">Tuesday</option>
                            <option value="3">Wednesday</option>
                            <option value="4">Thursday</option>
                            <option value="5">Friday</option>
                            <option value="6">Saturday</option>
                        </select>
                    </div>
                    <label>Pick a start time:</label>
                    <div class="form-inline form-group">
                        <select name="startHour" class="form-control">
                            <c:forEach var="i" begin="1" end="12">
                                <option value="${i}" <c:if test="${i == 12}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        :
                        <select name="startMinute" class="form-control">
                            <c:forEach var="i" begin="0" end="59">
                                <option value="${i}" <c:if test="${i == 0}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        <select name="startAMPM" class="form-control">
                            <option value="AM">AM</option>
                            <option value="PM" selected>PM</option>
                        </select>
                    </div>
                    <label>Pick a stop time:</label>
                    <div class="form-inline form-group">
                        <select name="stopHour" class="form-control">
                            <c:forEach var="i" begin="1" end="12">
                                <option value="${i}" <c:if test="${i == 1}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        :
                        <select name="stopMinute" class="form-control">
                            <c:forEach var="i" begin="0" end="59">
                                <option value="${i}" <c:if test="${i == 15}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        <select name="stopAMPM" class="form-control">
                            <option value="AM">AM</option>
                            <option value="PM" selected>PM</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Save changes</button>
                    <button class="btn btn-secondary" data-dismiss="modal" onclick="this.form.reset()">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
