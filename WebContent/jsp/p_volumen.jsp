<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	String tipo = request.getParameter("tipo");
	String bitacora = request.getParameter("bitacora");
	String segmento = request.getParameter("segmento");
	String capacidad = request.getParameter("capacidad");
	String caja = request.getParameter("caja");
	
	int cap=0;	
	int vol_scan=0;
	int disp=0;
	int total_porc=0;
	
	
	String volumen_scan = bitacoras.get_volumen(tipo,bitacora,segmento,caja);
	
	
	cap = Integer.parseInt(capacidad);
	vol_scan = Integer.parseInt(volumen_scan);
	disp = cap - vol_scan;
	
	total_porc = Math.round((vol_scan * 100)/cap);
	
	
%>
<script type="text/javascript">
	
</script>

</head>
<body>

  <TABLE  border="0" >
  <TBODY>
  <TR><td><%=volumen_scan%>&nbsp;&nbsp;&nbsp;</td><td><%=disp%>&nbsp;&nbsp;&nbsp;</td><td><%=total_porc%></td></TR>
  </TBODY>
  </TABLE>
	
	
	
	
</body>
</html>