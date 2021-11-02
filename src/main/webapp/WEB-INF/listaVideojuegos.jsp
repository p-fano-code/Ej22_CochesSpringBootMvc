<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Listado de juegos:</h1>
	<h2>${listado}</h2>
	<table align="center" border="1">
		<tr>
			<th>ID</th>
			<th>TITULO</th>
			<th>GENERO</th>
			<th>YEAR</th>
		</tr>
		<!-- JSTL muy util para recorrer un array pasado desde el controlador -->
		<c:forEach  var="p" items="${listado}">
			<tr>
				<td>${p.id}</td>
				<td>${p.nombre}</td>
				<td>${p.notaMedia}</td>
				<td>${p.compania}</td>
			</tr>
		</c:forEach>
	</table>
	<form action="detalle" method="post">
		Id juego: <input type="text" name="id"><br> <input
			type="submit" value="Pulsame">
	</form>
	<a href="adicionar">Añade un videojuego</a>
</body>
</html>