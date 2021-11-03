<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Bienvenido, ${usuario.usuario}</h3>
	<h1>Listado de juegos:</h1>
	<table align="center" border="1">
		<tr>
			<th>ID</th>
			<th>TITULO</th>
			<th>GENERO</th>
			<th>YEAR</th>
		</tr>
		<!-- JSTL muy util para recorrer un array pasado desde el controlador -->
		<c:forEach items="${listado}" var="li" >
			<tr>
				<td>${li.id}</td>
				<td>${li.nombre}</td>
				<td>${li.notaMedia}</td>
				<td>${li.compania}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<a type="button" href="verFormulario">Modificar</a>
			</td>
		</tr>
	</table>
	<form action="detalle" method="post">
		Id juego: <input type="text" name="id"><br> <input
			type="submit" value="Pulsame">
	</form>
	<a href="adicionar">Añade un videojuego</a>
</body>
</html>