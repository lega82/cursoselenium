<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa,mx.com.paquetexpress.informativa.core.DisplayHandler"%>
<html>
<head>
<style type="text/css">
    html, body, div, iframe { margin:0; padding:0;background-color: black; }
    iframe { display:block; border:none; }

</style>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<style type="text/css">
    html, body, div, iframe { margin:0; padding:0; }
    iframe { display:block; border:none; }

</style>


 <%
 	 
     
 	 String pantalla = request.getParameter("screen");
 	 String sucursal = request.getParameter("sucursal");
 	 String onoffswitch = request.getParameter("encendido");
 	 
 	String display="";
 	Informativa bitacoras = new Informativa(); 
 	 
 	
 	
 	
 	
 	
 	
 	
 	
 	if (onoffswitch.equals("ON")) {
 		String on="ON";
 		
 		if(pantalla.length() <6 ){
 	 		display = pantalla.substring(0, 2);
 	 	}else{
 	 		display = pantalla.substring(0, 3);
 	 	}
 		
 		
 		bitacoras.pro_screen_off(on,sucursal,display);
 	  
 	}else{
 		String off="OFF";
 	 	 
 		if(pantalla.length() <5 ){
 	 		display = pantalla.substring(0, 2);
 	 	}else{
 	 		display = pantalla.substring(0, 3);
 	 	}
 	 	 bitacoras.pro_upd_busy(sucursal,display);
 	     bitacoras.pro_off_pantalla(sucursal,display);
 	     bitacoras.pro_screen_off(off,sucursal,display);
 		
 	}
 	
 	
 	
       


 %>

<meta http-equiv="refresh" content="0; url=../jsp/p_admin.jsp?sucursal=<%=sucursal%>">
</head>

<body>





</body>
</html>