<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proceso de Carga de Moviles de Ruta</title>
</head>
 <%
 String bitacora = request.getParameter("bita");
 String tipo = request.getParameter("tipo");
 String sucursal = request.getParameter("sucursal");
 String status = request.getParameter("est");
 String segmno = request.getParameter("segm");
 String pantalla = request.getParameter("numpant");
 String caja = request.getParameter("caja");
 String tipo_proceso="";
 String est_proceso="";
 
 
 
 Informativa bitacoras = new Informativa();
 ArrayList valores = bitacoras.getdatosbitacora(bitacora,sucursal,segmno);
 String nombre_suc = bitacoras.getnombre(sucursal);
 
 
 
 String user = "PANTALLAS";
 String app = "PANTALLAS INFORMATIVAS";
 String app_opc = "PROCESO CARGA";
 String ipCustom = request.getRemoteAddr();
	
	bitacoras.pro_ins_log(user, sucursal,app,app_opc,ipCustom);
 
 
 
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

 			
 			
 		


 		}
 	}
    
 	String capacidad = bitacoras.get_capacidad(caja);
 	
 	if ( tipo.equals( "C1" ) && (status.equals( "P" ))   ){ 
 		tipo_proceso="Carga";
 		est_proceso="EN PROCESO";
 		
 	}
 	
 	
 	if  (caja.equals(caja1))    { 
 		cajac="1";
 	}
 	
 	if  (caja.equals(caja2))     { 
 		cajac="2";
 	}
 	
 	if (caja.equals("")){
 		cajac="1";
 		
 	}
 	
 	if (cajac.equals("N")){
 		cajac="1";
 		
 	}
 	
 	
%>

<FRAMESET frameSpacing="0" border="0" cols="30%,70%"  scrolling="auto">
<frame src="../jsp/p_info.jsp?&sucursal=<%=nombre_suc%>&proceso=<%=tipo_proceso%>&est=<%=est_proceso%>&bitacora=<%=bitacora%>&ruta=<%=ruta%>&tracto=<%=tracto%>&remolque1=<%=caja%>&remolque2=<%=caja2%>&segmento=<%=segmento%>&salidaE=<%=salidaE%>&tipo=<%=tipo%>&capacidad_caja=<%=capacidad%>&suc=<%=sucursal%>&segmn=<%=segmno%>" scrolling="no" Noresize> 
<FRAMESET rows="10%,83%,7%" frameborder="no" border="0" scrolling="auto">
<frame src="../html/p_cabecera_carga.html" scrolling="no" >
<frame src="../jsp/p_info_detalleC.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=cajac%>&remolque=<%=caja%>&numpant=<%=pantalla%>"  scrolling="auto"> 
<frame src="../jsp/p_totalesC.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=cajac%>&remolque=<%=caja%>"  scrolling="no">
</frameset>
</frameset>
<noframes>
</noframes>




</html>