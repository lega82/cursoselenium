<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%
 String sucursal = request.getParameter("sucursal");
 String pantalla = request.getParameter("numpant");

 Informativa bitacoras = new Informativa();
 ArrayList valores = bitacoras.getpantalla(sucursal,pantalla);
 
 
 int valor = valores.size();
 
 /* Bitacora   */
 if (valor > 0) {
	 System.out.println("tamaño array " + valor);
	 
 

 


 
 String clave = "";
	String stus="P";
	String color="";
	String tipo="";
	String status = "";
	String segm = "";
	String segmento = "";
	String tracto = "";
	String caja = "";
	String renglon="tr";
	String fila="";
	
	
	HashMap site = null;
	if (valores!=null) {
		for (int i=0;i<valores.size();i++) {
			site = (HashMap) valores.get(i);			
			clave = site.get("viaje").toString();
			tipo = site.get("process").toString();
			tracto = site.get("tracto").toString();
			caja = site.get("caja").toString();
			segmento = site.get("segmento").toString();
			segm = site.get("segno").toString();
			status = site.get("estatus").toString();
			
		}
	}
	

	 
	 
	 

if (status.equals("PROCESO")){
	 %>
   <meta http-equiv='refresh' content='0; url=../jsp/p_tipo_LED.jsp?bita=<%=clave%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=stus%>&segm=<%=segm%>&numpant=<%=pantalla%>&caja=<%=caja%>' >

<%

	}

else{
	 %>
	  <meta http-equiv='refresh' content='0; url=../jsp/p_llegadas.jsp?sucursal=<%=sucursal%>&numpant=<%=pantalla%>' >
	 
	   <%
}
/* fin Bitacora   */
 }
 else{
	 %>
	  <meta http-equiv='refresh' content='0; url=../jsp/p_llegadas.jsp?sucursal=<%=sucursal%>&numpant=<%=pantalla%>' >
	 
	   <%
}

%>


</body>
</html>