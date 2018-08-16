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
 	 
 	Informativa bitacoras = new Informativa();
 	
       
 	String power = bitacoras.get_off_disp(sucursal,pantalla);
 	
 	
 	if (power.equals("ON")){
   	  %>
        <script type="text/javascript"> 
        parent.location.href="../jsp/p_control.jsp?suc=<%=sucursal%>&disp=<%=pantalla%>";
        </script>
		  <%
   }

 %>

<meta http-equiv="refresh" content="30; url=../jsp/p_screenblack.jsp?sucursal=<%=sucursal%>&screen=<%=pantalla%>">
</head>

<body>

</body>
</html>