<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>


<%
response.setHeader("Vary","*");
response.setHeader("Cache-Control","public,must-revalidate,max-age=86400");
response.setHeader("Connection","Keep-Alive");
response.setHeader("Accept-Encoding","gzip, deflate, compress, chunked");
response.setHeader("Accept-Ranges", "*");
response.setHeader("Transfer-Encoding","*");
response.setHeader("Content-Encoding","*");
%>

<!-- 
response.setHeader("Transfer-Encoding","chunked, compress, deflate, gzip");
response.setHeader("Vary","Accept-Language, Accept-Encoding");
response.setHeader("Content-Encoding","gzip");


response.setHeader("Accept-Ranges", "bytes");
response.setHeader("ETag", "2015-05-01-0929-vas0fa33adffa"); 
response.setHeader("Last-Modified", "Tues, 28 Apr 2015 00:00:00 GMT");
response.setHeader("Expires", "Tues, 28 Jul 2015 00:00:00 GMT"); 
 -->
 
 

<html manifest="../estilos/cachethis.appcache" class="no-js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<!-- STYLESHEETS  -->
<!-- 
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />
 -->
<style type="text/css">
    html, body, div, iframe { margin:0; padding:0; }
    iframe { display:block; border:none; }
html,body,div,table,body:empty {
background: black;
background-color: black;
color: white;
} 
</style>






</head>




 <%
 	String sucursal = request.getParameter("sucursal");
    
 %>



<body style="background-color:black">
    
<iframe width="320" height="192" src="../jsp/p_select_LED.jsp?sucursal=<%=sucursal%>" style="border:none"  frameborder="0" marginwidth="0" marginheight="0" scrolling="no" onload="" allowtransparency="true" name="iframe_a" id="iframe" onload='javascript:resizeIframe(this);'></iframe>

</body>
</html>