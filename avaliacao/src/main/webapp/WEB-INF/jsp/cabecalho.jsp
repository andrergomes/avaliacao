<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
	</head>
	<body>
		<table style="width: 100%">
			<tr>
				<td align="left"><h3>Olá ${USUARIO_LOGADO.nome}!</h3></td>
				<td align="right"><a href="controleAcesso?acao=Logout">Logout</a></td>
			</tr>			
		</table>		
	</body>
</html>