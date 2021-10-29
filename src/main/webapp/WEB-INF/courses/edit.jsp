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
<title>Edit a Course</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

<h1><c:out value="${ course.className}" /></h1>

<form:form action="/courses/${ course.id }" method="post" modelAttribute="course">
<input type="hidden" name="_method" value="put">
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
	
	<input type="submit" class="btn btn-primary" value="Submit"/>

	
</form:form>



<form action="/courses/${ course.id }" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" class= "btn btn-danger" value="Delete">
</form>

<a href="/courses" class="btn btn-warning">Cancel</a>

</body>
</html>