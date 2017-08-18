<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>

<h1>Welcome to stallionsCC.com</h1>

<c:if test="${not empty requestScope.persons}">
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Country</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${requestScope.persons}" var="person">
					<c:url value="/editPerson" var="editURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<c:url value="/deletePerson" var="deleteURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<tr>
						<td><c:out value="${person.id}"></c:out></td>
						<td><c:out value="${person.name}"></c:out></td>
						<td><c:out value="${person.age}"></c:out></td>
						<td><c:out value="${person.country}"></c:out></td>
						<td><a
							href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
						<td><a
							href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

<%-- 
<spring:url value="/person/addPerson" var="addURL"></spring:url>
<a href="${addURL }">Add Person</a>


<h1>List User</h1>
<table border="1">
	<thead>
		<tr>
			<th>Name</th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	<thead>
		<tr>
			<th>Age</th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	<thead>
		<tr>
			<th>Country</th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resp }" var="person">
			<tr>
				<td>${person.name }</td>
				<td>${person.age }</td>
				<td>${person.country }</td>
				<td>
					<spring:url value="/person/update/${person.name }" var="updateURL" />
					<a href="${updateURL }">Update</a>
				</td>
				<td>
					<spring:url value="/person/delete/${person.name }" var="deleteURL" />
					<a href="${deleteURL }">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
 --%>
</body>
</html>