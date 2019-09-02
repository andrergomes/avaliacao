<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Instruções</title>
	</head>
	<body>
		<c:import url="cabecalho.jsp"></c:import>
	
		<h1 align="center">Avaliação de Reconstrução de Imagens</h1>
		<h4 align="center">
			<br><br><br>
			Esta pesquisa consiste em apresentar ao usuário algumas imagens para que este identifique quais das imagens foram submetidas à reconstrução, ou seja, o usuário deve analisar as imagens individualmente e, usando sua percepção (procurando falhas, distorções, quebra no padrão, etc), conseguir identificar as imagens que foram reconstruídas. Temos como objetivo avaliar para quais tipos de texturas a reconstrução usada é mais eficiente e, para isso, tomaremos como base os dados coletados nessa pesquisa. 
			<br><br>Veja um exemplo: 
			<br><br>
		</h4>
		<div align="center">
			<table>
				<tr>
					<td align="center"><img src= "images/example1_image17.png"></td>					
					<td align="center"><img src="images/example2_image17_1_mask.png"></td>
					<td align="center"><img src="images/example3_image17_1_mod.png"></td>
				</tr>
				<tr>
					<td align="center">Imagem Original</td>					
					<td align="center">Região Selecionada para Reconstrução</td>
					<td align="center">Imagem Reconstruída</td>
				</tr>
			</table>	 
		</div>				
		<br><br>
		<h5 align="left"> 
			<br><br><br>
			Observações importantes:
			<br>- Ao clicar em "Iniciar", o usuário terá acesso às imagens e terá que selecionar uma das duas opções disponíveis("Sim" ou "Não"),&nbsp;fazendo isso,&nbsp;ele será direcionado para a próxima imagem automaticamente.  <br>- O tamanho da região selecionada para reconstrução é sempre a mesma (como a do exemplo),&nbsp;por�m,&nbsp;a posi��o da regi�o selecionada pode variar de imagem para imagem.<br>- Na imagem usada como exemplo a �rea reconstru�da � identificada facilmente,&nbsp;porém,&nbsp;dependendo da imagem,&nbsp;a reconstrução pode não ser tão perceptível.  
			<br>
		</h5>
		<h5 align="center"><br><br>Obrigado por sua colaboração!</h5>
		<form action="pesquisa" method="post">
			<input type="hidden" name="acao" value="IniciarPesquisa" />
			<p align="center"><input type="submit" id="botaoIniciar" value="Iniciar" /></p>
		</form>
		
		<c:import url="rodape.jsp"></c:import>
	</body>
</html>