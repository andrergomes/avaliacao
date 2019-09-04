<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cadastro Usuário</title>
		<script src="js/jquery-1.11.3.min.js"></script>
	</head>
	<body>
		<c:import url="cabecalho.jsp"></c:import>		
		<form action="usuario" method="post">
			<div align="center" style="width: 100%; height: 80%">					
				<table style="position: relative; top: 30%;">
					<tr>
						<td align="right"><label for="nome">Nome</label></td>
						<td><input type="text" name="nome" id="nome" value="" maxlength="30" required="required" /></td>
					</tr>
					<tr>
						<td align="right"><label for="login">Login</label></td>
						<td><input type="text" name="login" id="login" value="" title="Login não deve ficar em branco e ter apenas letras, números e sublinhado." pattern="\w+" maxlength="10" required="required" /></td>
					</tr>
					<tr>
						<td align="right"><label for="senha">Senha</label></td>
						<td><input type="password" name="senha" id="senha" value="" title="Senha deve ter ao menos 8 caracteres, incluindo MAIÚSCULA/minúscula e números" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onchange="validarSenha();" maxlength="8" required="required" /></td>
					</tr>
					<tr>
						<td align="right"><label for="repetirSenha">Repetir Senha</label></td>
						<td><input type="password" name="repetirSenha" id="repetirSenha" value="" title="Por favor repita a senha acima" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onchange="validarRepetirSenha();" maxlength="8" required="required" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="right"><input type="submit" name="botaoSalvar" id="botaoSalvar" value="Salvar" /></td>
					</tr>
				</table>
			</div>				
			<input type="hidden" name="acao" value="CadastrarUsuario" />				
		</form>		
		
		<c:import url="rodape.jsp"></c:import>
	</body>
	<script type="text/javascript">
		function validarSenha() {
			this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
			
			if(this.checkValidity()) form.repetirSenha.pattern = this.value;
		}
		
		function validarRepetirSenha() {
			this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
		}
	</script>
	
</html>