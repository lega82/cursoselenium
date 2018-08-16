<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
  
  <%
response.setHeader("Server","Hola");
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
	<link href="../estilos/hoja_LED.css" rel="stylesheet" type="text/css" />
	<!-- 
	<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
	 -->
	 <!--
	<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">
	-->
	<!--
	<script src="../js/devicejs/lib/device.js"></script>
	-->
	<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>



	<title>Proceso de Descarga de Moviles de Ruta</title>


	<style id="style-1-cropbar-clipper">
	html { font-size: 15px; -webkit-text-size-adjust: 16px; -ms-text-size-adjust: 16px; }
	.en-markup-crop-options {
	    top: 18px !important;
	    left: 50% !important;
	    margin-left: -100px !important;
	    width: 200px !important;
	    border: 2px rgba(255,255,255,.38) solid !important;
	    border-radius: 4px !important;
	}
	.en-markup-crop-options div div:first-of-type {
	    margin-left: 0px !important;
	}
	#yeahghost {
		width: 300px;
		height: 200px;
	}
	div#content {
	    position: fixed;
	    top: 0px;
	    left: 0px;
	    bottom: 25px;
	    width: 320px;
	    height: 192px;
	/*    background: black;*/
	}
	div#content iframe,
	div#content frameset {
	    position: absolute;
	    top: 0;
	    bottom: 0;
	    left: 0;
	    right: 0;
	    width: 320px;
	    height: 192px;
	}
	</style>



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
 ArrayList valores = bitacoras.getdatosbitacora_descarga(bitacora,sucursal,segmno);
 String nombre_suc = bitacoras.getnombre(sucursal);

 String user = "PANTALLAS";
 String app = "PANTALLAS INFORMATIVAS";
 String app_opc = "PROCESO DESCARGA";
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
 	
 	String capacidad = bitacoras.get_capacidad(caja1);
 	
 	if ( tipo.equals( "D1" ) && (status.equals( "P" ))   ){
 		tipo_proceso="Descarga";
 		est_proceso="EN PROCESO";

 	}
 	
 	
 	if  (caja.equals(caja1))    { 
 		cajac="1";
 	}
 	
 	if  (caja.equals(caja2))     { 
 		
 		caja1=caja;
 		cajac="2";
 	}

 	if (caja.equals("")){
 		cajac="1";
 		
 	}
 	
 	if (cajac.equals("N")){
 		cajac="1";
 		
 	}
 	
 	

%>






<frameset cols="96,224,*" rows="192, *" frameborder="no" border="0" scrolling="no">
			<frameset rows="192">
				<frame allowtransparency="true" style="background:#000" class="background-black" src="../jsp/p_info_LED.jsp?&sucursal=<%=nombre_suc%>&proceso=<%=tipo_proceso%>&est=<%=est_proceso%>&bitacora=<%=bitacora%>&ruta=<%=ruta%>&tracto=<%=tracto%>&remolque1=<%=caja%>&remolque2=<%=caja2%>&segmento=<%=segmento%>&llegadaE=<%=llegadaE%>&tipo=<%=tipo%>&capacidad_caja=<%=capacidad%>&suc=<%=sucursal%>&segmn=<%=segmno%>" scrolling="no" Noresize>
			</frameset>
			
			<frameset rows="35,135,22, *" frameborder="no" border="0" scrolling="no">
				<frame allowtransparency="true" style="background:#000" class="background-black" src="../html/p_cabecera_descarga_LED.html"  scrolling="no" noResize="">
				<frame allowtransparency="true" style="background:#000" class="background-black" src="../jsp/p_info_detalleD_LED.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&segmn=<%=segmno%>&tipo=<%=tipo%>&caja=<%=cajac%>&remolque=<%=caja%>&numpant=<%=pantalla%>" name="detalle" frameborder="no" scrolling="auto">
				<frame allowtransparency="true" style="background:#000" class="background-black" src="../jsp/p_totalesD_LED.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=cajac%>&remolque=<%=caja%>" name="total" frameborder="no" scrolling="no">

			</frameset>
	</frameset>





</html>