<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	<body>		
		<form action="controleAcesso" method="post">
			<div align="center" style="width: 100%; height: 100%">
				<table style="position: relative; top: 30%;">
					<tr>
						<td><label for="login">Login</label></td>
						<td><input type="text" name="login" id="login" value="" maxlength="10" required="required" /></td>
					</tr>
					<tr>
						<td><label for="senha">Senha</label></td>
						<td><input type="password" name="senha" id="senha" value="" maxlength="8" required="required" /></td>
					</tr>
					<c:if test="${ERRO ne ''}">
						<tr>						
							<td align="center" colspan="2"><span style="color: red;">${ERRO}</span></td>						
						</tr>
					</c:if>
					<tr>
						<td align="center" colspan="2"><input type="submit" value="Entrar" /></td>
					</tr>
				</table>
			</div>								
			<input type="hidden" name="acao" value="Login" />			
		</form>
	</body>
</html>