<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new DVD</title>
</head>
<body>
	<h1>Introduce DVD details</h1>
	<form name="addDVDForm" action="AddDVDController" method="post">
		<table>
			<tr>
				<td>DVD Title</td>
				<td><input type="text" name="dvd_title" /></td>
			</tr>
			<tr>
				<td>DVD Release Year</td>
				<td><input type="text" name="dvd_year" /></td>
			</tr>
			<tr>
				<td>DVD Price</td>
				<td><input type="text" name="dvd_price" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="add" value="Add DVD" /></td>
			</tr>
		</table>
	</form>
</body>
</html>