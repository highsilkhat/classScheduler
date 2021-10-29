<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Manager Dashboard</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

<h1>Namaste, <c:out value="${ instructor.instructorName }"/></h1>

<a href="/logout">Logout</a>


<h3> Class Schedule</h3>

<table class="table table-striped">
	<tr>
		<th>
		Class Name
		</th>
		
		<th>
		Instructor
		</th>
		
		<th>
		Weekday
		</th>
		
		<th>
		Price
		</th>
		
		<th>
		Time
		</th>
		
	</tr>
	
	
<tbody>

<c:forEach var="course" items="${ courses }">
<tr>
	<td>
		<a href="courses/${ course.id }"> <c:out value="${ course.className }"/> </a>
		
		<c:if test="${ course.instructor.id == instructor_id }">
		
		<a href="/courses/${ course.id }/edit" class="btn btn-warning">edit</a>
	
		</c:if> 
	</td>
	
	<td>
	<c:out value="${ course.instructor.instructorName }"/>
	</td>
	
	<td>
	<c:out value="${ course.day }"/>
	</td>
	
	<td>
	$<c:out value="${ course.price }"/>
	</td>
	
	<td>
	<c:out value="${ course.time }" />
	</td>

</c:forEach>

</tbody>

</table>

<a href="/courses/new" class="btn btn-primary">+ new class</a>

</body>
</html>