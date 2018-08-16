<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>


<%@ page language="java"
	import="java.util.ArrayList,java.util.Calendar,java.util.HashMap,java.text.DecimalFormat,pantallas.Informativa"%>

	
<html>
<head>



<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<!-- 
<link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />
 -->  
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<!-- 
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
 -->
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css" />
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">


<script src="../js/devicejs/lib/device.js"></script>       

<style type="text/css">
body,td,th {
    font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
</style>

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
   	if ( tipo.equals( "C1")){
   		tipo_C ="E";
   }
    
   	
	
	String[] valor = bitacoras.get_totales(bitacora,sucursal,tipo,segmento,remolque);
%>

<meta http-equiv="refresh" content="70; url=../jsp/p_totalesC.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=caja%>&remolque=<%=remolque%>">
<META name="GENERATOR" content="MSHTML 9.00.8112.16443">
</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">
	<form>	
		<table border="0" class="detalle_carga">
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
	</tbody>
	    
		</table>
	
	</form>

</body>
</html>