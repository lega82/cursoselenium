<!DOCTYPE html>
<%@ page language="java"

 import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>


<%
response.setHeader("Vary","*");
response.setHeader("Cache-Control","public,must-revalidate,max-age=86400");
response.setHeader("Connection","Keep-Alive");
response.setHeader("Accept-Encoding","gzip, deflate, compress, chunked");
response.setHeader("Accept-Ranges", "*");
response.setHeader("Transfer-Encoding","*");
response.setHeader("Content-Encoding","*");
%>

<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<!-- STYLESHEETS  -->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />

<!--  
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />

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
 String segmn = request.getParameter("segmn");
 
 String color="black";
 String color_id="";
 
 
 int segundos=0;
 int horas = 0;
 int minutos = 0;
 
 Informativa bitacoras = new Informativa();
 String cronometro_carga = bitacoras.get_cronometro(bitacora,sucursal,segmn);
 segundos = Integer.parseInt(cronometro_carga);
 horas = segundos/3600;
 minutos = (segundos - horas*3600)/60;
 
 if (horas < 1 & minutos <= 30) {
		System.out.println("totales horas cero poner rojo");
		color="red";
		color_id="alertaroja";
		
 }
 
 if (horas < 1 & minutos <= 60 & minutos > 30  ) {
		System.out.println("totales horas cero poner amarillo");
		color="yellow";
		color_id="alertaamarilla";
		
	     }
 
 
 %>

<%
 
    String tipo_C = "";
    if ( tipo.equals( "C1")){
     tipo_C ="E";
   }

 String[] valor = bitacoras.get_totales(bitacora,sucursal,tipo,segmento,remolque);
 
%>


<meta http-equiv="refresh" content="60; url=../jsp/p_totalesC_LED.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=caja%>&remolque=<%=remolque%>&segmn=<%=segmn%>">
<META name="GENERATOR" content="MSHTML 9.00.8112.16443">
</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">

 <form>
  <TABLE bgcolor=<%=color%> width="100%" border="0" cellpadding="0" cellspacing="0" class="detalle_carga">
  <TBODY>

<%
	%>
	
	<tr class="totales" id=<%=color_id%>>
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