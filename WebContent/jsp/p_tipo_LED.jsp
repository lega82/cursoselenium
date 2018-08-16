<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />

<style type="text/css">
html,body,div,table,body:empty {
background: black;
background-color: black;
color: white;
} 
</style>

<title>Insert title here</title>


<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../js/queryloader2.min.js" type="text/javascript"></script>
<script src="../js/script.js" type="text/javascript"></script>


</head>
 <%
 String bitacora = request.getParameter("bita");
 String tipo = request.getParameter("tipo");
 String sucursal = request.getParameter("sucursal");
 String status = request.getParameter("est");
 String segmno = request.getParameter("segm");
 String pantalla = request.getParameter("numpant");
 String caja = request.getParameter("caja");
 

 System.out.println("caja " + caja);
 
%>
<body style="background: transparent">
 <%
if ( tipo.equals( "C1" ) && (status.equals( "P" ))   ){ 
	 %>
	  out.println("<meta http-equiv='refresh' content='0; url=../jsp/p_carga_LED.jsp?bita=<%=bitacora%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=status%>&segm=<%=segmno%>&caja=<%=caja%>&numpant=<%=pantalla%>' >");
	 
	   <%
	
	}
 
 
 if ( tipo.equals( "D1" ) && (status.equals( "P" ))   ){ 
	 %>
	  out.println("<meta http-equiv='refresh' content='0; url=../jsp/p_descarga_LED.jsp?bita=<%=bitacora%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=status%>&segm=<%=segmno%>&caja=<%=caja%>&numpant=<%=pantalla%>' >");
	 
	   <%
	
	}
 
 %>


</body>
</html>