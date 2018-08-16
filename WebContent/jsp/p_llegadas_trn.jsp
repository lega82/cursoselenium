<!DOCTYPE html>
<html>
<head>
<link href="../imagen/LOGO.ico"  type="image/x-icon" rel="shortcut icon">
<title>Bit&aacute;coras en Transito</title>


<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<!--
 <link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />
 --> 
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="../estilos/hoja.css" />
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">

<script src="../js/devicejs/lib/device.js"></script>
<!--
<script src="jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
-->


<!--  Estilos para pantallas angostas -->

<style type="text/css">

</style>





<META charset="iso-8859-1">

<TITLE> Bitacoras en Sucursal</TITLE>


<META name="GENERATOR" content="MSHTML 9.00.8112.16443">


</head>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>

 <%
 	String sucursal = request.getParameter("sucursal");
 	String pantalla = request.getParameter("numpant");
    
 	
	 if ( sucursal.equals( "A")){
		 sucursal = "%";
		 
	 }
 	
 
 	
 	
 	
 %>
 
<%
	Informativa bitacoras = new Informativa();
	
	ArrayList valores = bitacoras.getbitacoras_llegada_trn(sucursal,sucursal);
	String nombre_suc = bitacoras.getnombre(sucursal);
	
	
	
	String user = "PLANEACION EAD";
   	String app = "PLANEACION EAD";
   	String app_opc = "MERCANCIA EN TRANSITO";
   	String ipCustom = request.getRemoteAddr();
   	
   	bitacoras.pro_ins_log(user, sucursal,app,app_opc,ipCustom);

	boolean retval = valores.isEmpty();
	if (retval == true) { %>
	     <script>alert("No hay información de viajes en Transito para esta sucursal ");</script>;
	   
        
	    <%  
	    }
	 
	
     
%>
<script type="text/javascript">
	
</script>

</head>
<body id="bitacoras-en-sucursal-v-escritorio">
 <script language="JavaScript">
<!--// evito que se cargue en otro frame
if (top.location != self.location)top.location = self.location;
//-->
</script>

	<form>
		<TABLE border="0" cellspacing='0' cellpadding='0'    id="bitacorasensucursal" style="width:100%">
  	  <tr id="titulollegadas" align="center"><th  colspan="13">Bit&aacute;coras en Transito  <%= nombre_suc %> </th></tr>
	<tr><th>Viaje</th>
	<th>Tracto</th>
	<th>Remolque</th>
	<th>Sucursal</th>
	<th>Segmento</th>
	<th>Fecha<br/>Salida</th>
	<th>Hora<br/>Salida</th>
	<th>Guias<br/>Locales</th>
	<th>Guias<br/>Trasbordo</th>
	<th>Total<br/>Guias</th>
	<th>Paquetes<br/>Locales</th>
	<th>Paquetes <br/>Trasbordo</th>
	<th>Total <br/>Paquetes</th>
	
	
	</tr>
	
	
<%	
	String viaje = "";
	String stus="P";
	String color="";
	String tipo="";
	String descripcion = "";
	String status = "";
	String segm = "";
	String segmento = "";
	String tracto = "";
	String caja1="N";
	String caja2="N";
	String f_salida="";
	String h_salida="";
	String f_planeada="";
	String h_planeada="";
	String f_esp="";
	String h_esp="";
	String renglon="tr";
	String fila="";
	String segmno="";
	String suc_dest="";
	String guia_ead="";
	String bulto_ead="";
	String volumen_ead="";
	String guia_ocu="";
	String bulto_ocu ="";
	String volumen_ocu ="";
	String bulto_trb ="";
	String guias ="";
	String guias_t ="";
	String paquetes ="";
	String paquetes_t ="";
	int total_guias=0;
	int total_paq=0;
	
	
	
	HashMap viajes = null;
	if (valores!=null) {
		for (int i=0;i<valores.size();i++) {
			viajes = (HashMap) valores.get(i);			
			viaje = viajes.get("viaje").toString();
			tracto = viajes.get("tracto").toString();
			caja1 = viajes.get("caja").toString();
			descripcion = viajes.get("nombre").toString();
			segmento = viajes.get("segmento").toString();
			f_planeada = viajes.get("f_planeada").toString();
			h_planeada = viajes.get("h_planeada").toString();
			f_salida = viajes.get("f_salida").toString();
			h_salida = viajes.get("h_salida").toString();
			f_esp = viajes.get("f_esp").toString();
			h_esp = viajes.get("h_esp").toString();
			segmno = viajes.get("segmno").toString();
			suc_dest = viajes.get("suc_dest").toString();
			guias = viajes.get("guias").toString();
			guias_t    = viajes.get("guias_t").toString();
			paquetes   = viajes.get("paquetes").toString();
			paquetes_t = viajes.get("paquetes_t").toString();
			
			
			
			 if ( renglon.equals( "tr")){
				 fila="tr" ; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt' ";
				  renglon="tr";
			   }
			 total_guias  = Integer.parseInt(guias) + Integer.parseInt(guias_t);
			 total_paq  = Integer.parseInt(paquetes) + Integer.parseInt(paquetes_t);
			 
				%>
				
				<<%=fila%>>
			  
				    <td><a href="../jsp/p_bitacora.jsp?bita=<%=viaje%>&caja=<%=caja1%>&sucursal=<%=suc_dest%>&segmeno=<%=segmno%>" class="texto_normal" target="_blank"><font color="#000080"> <%=viaje%></font></a></td>
				     <td align="center"><%=tracto%></td>
				     <td><a href="../jsp/p_detalle_trn.jsp?bitacora=<%=viaje%>&segmento=<%=segmento%>&remolque=<%=caja1%>&sucursal=<%=suc_dest%>" class="texto_normal" target="_blank"><font color="#000080"> <%=caja1%></font></a></td>
				     <td align="center"><%=descripcion%></td>
				     <td align="center"><%=segmento%></td>
				     <td align="center"><%=f_salida%></td>
				     <td align="center"><%=h_salida%></td>
				     <td align="center"><font color="#0000cd"><%=guias%></font></td>
				     <td align="center"><font color="#800000"><%=guias_t%></font></td>
				     <td align="center"><font color="#0000cd"><b><%=total_guias%></b></font></td>
				     <td align="center"><font color="#800000"><%=paquetes%></font></td>
				     <td align="center"><font color="#0000cd"><%=paquetes_t%></font></td>
				     <td align="center"><font color="#800000"><b><%=total_paq%></b></font></td>
				     
				     
					
					
				</tr>
				
<%					
			
						

		}
		
	}
	else{
		%>
		<h1>dddd</h1>
		
		<%
	}
	
	%>
    		
			
		</table>
	
	</form>
</body>
</html>