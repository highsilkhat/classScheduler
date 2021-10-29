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
<title>Create a Course</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

<h1>Create a Course</h1>

<form:form action="/course/post" method="post" modelAttribute="course">

<form:hidden path="instructor" value="${ instructor.id }"/>

<p>
	<form:label class= "form-label" path = "className">Name</form:label>
	<form:errors path= "className"/>
	<form:input class= "form-control" path="className"/>
	
</p>
<p>

	<form:label class= "form-label" path = "day">Weekday</form:label>
	<form:errors path= "day"/>
	<form:input class= "form-control" path="day"/>
</p>
<p>
	<form:label class= "form-label" path = "price">Drop-in Price</form:label>
	<form:errors path= "price"/>
	<form:input type="number" class= "form-control" path="price"/>
</p>

<p>

	<form:label class= "form-label" path = "description">Description</form:label>
	<form:errors path= "description"/>
	<form:textarea class= "form-control" path="description"/>
	
</p>

<p> 
	<form:label class= "form-label">Time</form:label>
	<form:errors path= "time"/>
	<form:input type="datetime-local"  forclass= "form-control" path="time"/>

</p>

<a href="/courses" class="btn btn-danger">Cancel</a>

<input type="submit" class="btn btn-primary" value="Submit"/>

</form:form>

</body>
</html>