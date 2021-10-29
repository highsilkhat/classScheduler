<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a Task</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

<h1><c:out value="${ course.className }"/></h1>

<h2>Day: <c:out value="${ course.day }"/></h2>

<h2>Cost: $<c:out value="${ course.price }"/></h2>

<p><c:out value="${ course.description }"/></p>

<a href="/courses" class="btn btn-success"> All Classes</a>


<p>-------------------------------------------------------------</p>

<h4>Students</h4>

<c:forEach var="student" items="${ course.students }">

<p><c:out value="${ student.studentName }"/> - <c:out value="${ student.email }"/></p>

</c:forEach>


<h2>Add Students to Class</h2>

<h3>New Student</h3>

<form:form action="/student/post" method="post" modelAttribute="student">



<p>
	<form:label class= "form-label" path = "studentName">Student Name</form:label>
	<form:errors path= "studentName"/>
	<form:input class= "form-control" path="studentName"/>
	
</p>
<p>

	<form:label class= "form-label" path = "email">Email</form:label>
	<form:errors path= "email"/>
	<form:input class= "form-control" path="email"/>
</p>

	

<input type="submit" class="btn btn-primary" value="Submit"/>

</form:form>

<form:form action="/join" method="post" path = "student" modelAttribute="courseStudent">

<form:hidden path="course" value="${ course.id }"/>

	<label class= "form-label">Choose Student</label>
	<form:select path= "student">
		<c:forEach var="student" items= "${ students }">
			<option value = "${ student.id }"> ${ student.studentName } - ${ student.email }</option>
		</c:forEach>
	</form:select>
	
	<input type="submit" class="btn btn-primary" value="Submit"/>
	
</form:form>


	
</body>
</html>