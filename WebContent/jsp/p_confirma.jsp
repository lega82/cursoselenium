<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa,mx.com.paquetexpress.informativa.core.DisplayHandler"%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<style type="text/css">
    html, body, div, iframe { margin:0; padding:0; }
    iframe { display:block; border:none; }

</style>


 <%
 
 
 String bitacora = request.getParameter("bitacora");
 String tipo = request.getParameter("process");
 String segmento = request.getParameter("segmento");
 String caja = request.getParameter("caja");
 String pantalla = request.getParameter("pantalla");
 String sucursal = request.getParameter("sucursal");
 String confirma = request.getParameter("confirma");
 String p_asig = request.getParameter("p_asig");
 	 
 Informativa bitacoras = new Informativa();
 
 DisplayHandler displayHandler = new DisplayHandler();

 String rs = displayHandler.handlerDisplay(bitacora, tipo, caja, segmento, sucursal, pantalla);
 String bitacoraA="";
 String[] parametros;
 bitacoraA = rs;
 
 if(confirma.equals("Y")){
	 parametros = bitacoras.get_parametro(bitacoraA,pantalla);
	 if (!parametros[0].equals("")){
		 String busy = displayHandler.handlerDisplayBusy(bitacoraA, parametros[0], parametros[1], parametros[2], bitacora, tipo, caja, segmento, sucursal, pantalla);
		 bitacoras.pro_upd_busy(sucursal,p_asig);
	 }else{
		 bitacoras.pro_ins_pantalla(bitacora,sucursal,pantalla);
	     bitacoras.pro_upd_pantalla(pantalla,bitacora,tipo,sucursal,segmento,caja);
	 }
 }
     

     
    	%>
 <meta http-equiv="refresh" content="0; url=../jsp/p_admin.jsp?sucursal=<%=sucursal%> ">

</head>

<body>
</body>
</html>