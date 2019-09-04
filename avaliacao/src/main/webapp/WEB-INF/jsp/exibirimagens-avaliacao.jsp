<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Avaliação</title>
		<script src="js/jquery-1.11.3.min.js"></script>
	</head>
<body>
	<c:import url="cabecalho.jsp"></c:import>
	
	<form id="finalizarForm" action="pesquisa" method="post">
		<div align="center">
			<h1>Houve reconstrução na imagem abaixo?</h1>
			<table>
				<thead>
					<tr>
						<td align="center" colspan="2"><span id="contadorImagens"></span>&nbsp;&nbsp;/&nbsp;&nbsp;<span id="totalImagens"></span></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" colspan="2"><span id="imagem"></span></td>
					</tr>
					<tr>
						<td align="center"><input type="button" value="Sim" /></td>
						<td align="center"><input type="button" value="Não" /></td>
					</tr>
				</tbody>
			</table>			
		</div>		
		<input type="hidden" name="acao" value="FinalizarPesquisa">
		<input type="hidden" id="resposta" name="resposta" value="" >
	</form>
	
	<c:import url="rodape.jsp"></c:import>
</body>
<script type="text/javascript">
	var arrayNomesImagens;
	var nomesImagens = '${nomesImagens}';
	var index = 0;
	var resposta = "";
		
	if(nomesImagens !== ' ') {
		arrayNomesImagens = nomesImagens.split(",");
		
		exibirContador(index + 1);
		exibirTotal(arrayNomesImagens.length);
		trocarImagem(arrayNomesImagens[index]);	
	}
	
	$(":button").click(function() {		
		if(index < arrayNomesImagens.length - 1) {
			montarRetorno(arrayNomesImagens[index], $(this).val(), false);
			index++;
			exibirContador(index + 1);
			trocarImagem(arrayNomesImagens[index]);					
			
		} else {
			montarRetorno(arrayNomesImagens[index], $(this).val(), true);
			$("#finalizarForm").submit();
		}
	});
	
	function trocarImagem(nomeImagem) {
		var url = '${pageContext.request.contextPath}' + "/image/" + '${numeroAleatorio}' + "/" + nomeImagem;
		$("#imagem").html("");
		$("#imagem").prepend('<img src="'+ url + '" />');
	}
	
	function exibirContador(num) {
		$("#contadorImagens").html(num);	
	}
	
	function exibirTotal(total) {
		$("#totalImagens").html(total);
	}
	
	function montarRetorno(nomeImagem, avaliacao, ultimo) {
		resposta = resposta.concat(nomeImagem).concat(",").concat(avaliacao === 'Sim' ? true : false);
		
		if(!ultimo) {
			resposta = resposta.concat(";");
			
		} else {
			$("#resposta").val(resposta);
		}
	}
		
</script>
</html>