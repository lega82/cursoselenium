<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bitacoras en Sucursal </title>


 <%
 	
    
  
 %>

<%
	Informativa bitacoras = new Informativa();
	String bitacora = request.getParameter("bitacora");
	String sucursal = request.getParameter("sucursal");
	String segmn = request.getParameter("segmn");
	String tipo = request.getParameter("tipo");
	String cronometro = "";
	
	int segundos=0;
	int horas = 0;
	int minutos = 0;
	
	
	
	
	
	 
	
	 if (tipo.equals("C1")){
	
	     String cronometro_carga = bitacoras.get_cronometro(bitacora,sucursal,segmn);
	     segundos = Integer.parseInt(cronometro_carga);
	     horas = segundos/3600;
	     minutos = (segundos - horas*3600)/60;
	     
	     cronometro = horas+":"+minutos;
	    
	 }else{
		 
		
		 String cronometro_dcarga = bitacoras.get_cronometroD(bitacora,sucursal,segmn);
		 
		 segundos = Integer.parseInt(cronometro_dcarga);
	     horas = segundos/3600;
	     minutos = (segundos - horas*3600)/60;
	     
	     cronometro = horas+":"+minutos;
		 
		 
	 }
	 
	
	 
	
	
%>
<script type="text/javascript">
	
</script>

</head>
<body>



	<h3 class="color-1"> <%=cronometro%></h3>
	
	
	
	
	
	
</body>
</html>