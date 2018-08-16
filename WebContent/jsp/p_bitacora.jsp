<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../imagen/LOGO.ico"  type="image/x-icon" rel="shortcut icon">
<title>Detalle de Guias en Bit&aacute;cora</title>

</head>
 <%
 String bitacora = request.getParameter("bita");
 String sucursal = request.getParameter("sucursal");
 String segmno = request.getParameter("segmeno");
 String caja = request.getParameter("caja");

 System.out.println("valores " + segmno);
 Informativa bitacoras = new Informativa();
 ArrayList valores = bitacoras.getdatosbitacora_op(bitacora,segmno);
 String nombre_suc = bitacoras.getnombre(sucursal);
 

 
 String ruta="";
 String segmento="";
 String tracto="";
 String caja1="N";
 String caja2="N";
 String cajac="N";
 String salidaE="";
 String salidaR="";
 String llegadaE="";
 String capacidad_caja="";
 String oper1="N";
 String oper2="N";
 String oper3="N";
 
 

 HashMap datos = null;
 	if (valores!=null) {
 		for (int i=0;i<valores.size();i++) {
 			datos = (HashMap) valores.get(i);
 			ruta = datos.get("ruta").toString();
 			segmento = datos.get("segmento").toString();
 			tracto = datos.get("tracto").toString();
 			caja1 = datos.get("caja1").toString();
 			caja2 = datos.get("caja2").toString();
 			salidaE = datos.get("salida_esp").toString();
 			salidaR = datos.get("salida_real").toString();
 			llegadaE = datos.get("llegada_esp").toString();
 			oper1    = datos.get("oper1").toString();
 			oper2    = datos.get("oper2").toString();
 			oper3    = datos.get("oper3").toString();
 			
 			
 		


 		}
 	}
    
 	String capacidad = bitacoras.get_capacidad(caja);
 	
 	
 	
 	
 	
%>

<FRAMESET frameSpacing="0" border="0" cols="30%,70%" frameBorder="no" scrolling="auto">
<frame src="../jsp/p_infobitacora.jsp?&sucursal=<%=nombre_suc%>&bitacora=<%=bitacora%>&ruta=<%=ruta%>&tracto=<%=tracto%>&remolque1=<%=caja%>&remolque2=<%=caja2%>&segmento=<%=segmento%>&salidaR=<%=salidaR%>&capacidad_caja=<%=capacidad%>&suc=<%=sucursal%>&segmn=<%=segmno%>&oper1=<%=oper1%>&oper2=<%=oper2%>&oper3=<%=oper3%>" scrolling="no" Noresize> 
<FRAMESET rows="95%" frameborder="no" border="0" scrolling="auto">
<frame src="../jsp/p_detallebitacora.jsp?&sucursal=<%=sucursal%>&bitacora=<%=bitacora%>&caja=<%=caja%>&segmento=<%=segmento%>" frameborder="no" scrolling="auto"> 
</frameset>
</frameset>
<noframes>
</noframes>




</html>