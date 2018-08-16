<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>


<%@ page language="java"
	import="java.util.ArrayList,java.util.Calendar,java.util.HashMap,java.text.DecimalFormat,pantallas.Informativa"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


<!-- STYLESHEETS  -->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/hoja_LED.css" rel="stylesheet" type="text/css" />

<!-- 
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
 -->
 
<!-- 
<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../jQueryAssets/jquery.modern-blink.js" type="text/javascript"></script>
 -->

<!-- 
<script type="text/javascript" src="../js/blink.js"></script>
 -->

<!-- 
<style type="text/css">
body,td,th {
    font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
</style>
 -->


<META charset="iso-8859-1">
<TITLE>Bit&aacute;coras en Sucursal</TITLE>
 <%

 String bitacora = request.getParameter("bitacora");
 String sucursal = request.getParameter("suc");
 String ruta = request.getParameter("ruta");
 String segmento = request.getParameter("seg");
 String tipo = request.getParameter("tipo");
 String caja = request.getParameter("caja");
 String remolque = request.getParameter("remolque");
 %>

<%
	Informativa bitacoras = new Informativa();
   String tipo_C = "";
   if ( tipo.equals( "D1")){
	   tipo_C ="R";
   }

	String[] valor = bitacoras.get_totales(bitacora,sucursal,tipo,segmento,remolque);
	
%>

<meta http-equiv="refresh" content="55; url=../jsp/p_totalesD_LED.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=caja%>&remolque=<%=remolque%>">
<META name="GENERATOR" content="MSHTML 9.00.8112.16443">
</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">
<div > 

	<form>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="detalle_carga">
		<TBODY>
	<tr class="totales">
	<td>Total</td>
	<td><%=valor[0]%></td> 
	<td><%=valor[1]%></td>
	<td><%=valor[2]%></td>
	<td><%=valor[3]%></td>
    <td><%=valor[4]%></td>
    <td><%=valor[5]%></td>
     <td><%=valor[6]%></td>
	</tr>
	    </TBODY>
		</table>
	
	</form>
	</div>
</body>
</html>