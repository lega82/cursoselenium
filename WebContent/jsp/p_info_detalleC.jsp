<!DOCTYPE html>
<%@ page language="java"

	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
	
	
	
	
<html>
<head>
<meta name="viewport" content="width = device-width, initial-scale = 1.0, minimum-scale = 1.0" />

<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />  
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">


<script src="../js/devicejs/lib/device.js"></script>


<!-- El scroll automático, ahí se ajusta velocidad  -->


<script src="../js/scroll.js"></script>


        

<style type="text/css">
body,td,th {
    font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
</style>



<META charset="iso-8859-1">


<TITLE>Bit&aacute;coras en Sucursal</TITLE>



<script src="../jQueryAssets/jquery-1.8.3.js" type="text/javascript"></script>


 <%

 String bitacora = request.getParameter("bitacora");
 String sucursal = request.getParameter("suc");
 String ruta = request.getParameter("ruta");
 String segmento = request.getParameter("seg");
 String tipo = request.getParameter("tipo");
 String caja = request.getParameter("caja");
 String remolque = request.getParameter("remolque");
 String pantalla = request.getParameter("numpant");

 


 %>

<%
	Informativa bitacoras = new Informativa();
   	String tipo_C = "";
   	if ( tipo.equals( "C1")){
   		tipo_C ="E";
   }
    
   	String fcarga = bitacoras.get_finCarga(sucursal,pantalla,bitacora,remolque,segmento);
	
   	
   	//*System.out.println("remoluwe " + remolque);*/
   	
   	if ( fcarga.equals( "SALIDA" ) ){ 
		 %>
		  <meta http-equiv='refresh' content='0; url=../jsp/p_llegadas.jsp?sucursal=<%=sucursal%>&numpant=<%=pantalla%>' >
		 
		   <%
		
		}
	
   	
   	
   	String trasbordo = bitacoras.get_suc_trasbordo(sucursal);
	
   	
	ArrayList destinos = bitacoras.get_dest_carga(bitacora,remolque,segmento,tipo_C,caja);
	
	
		
	
%>

<meta http-equiv="refresh" content="60; url=../jsp/p_info_detalleC.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=caja%>&remolque=<%=remolque%>&numpant=<%=pantalla%>">

<META name="GENERATOR" content="MSHTML 9.00.8112.16443">

</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">
<div id="scroller"> 

	<form>
		<table class="detalle_carga">
		<tbody>

<%
	String destino = "";
    String dest = "";
	String stus="P";
	String color="";
	String descripcion = "";
	String tracto = "";
	String nombre_suc = "";
	String nbultos = "0";
	String nguias = "0";
	String nguias_c = "0";
	String nguiasc = "0";
	String guias_c = "0";
	int guias_alm = 0;
	String bultos ="0";
	String bultos_esc ="0";
	int bultos_alm =0;
	String bultos_e = "0";
	float porc =0;
	String renglon="tr";
	String fila="";
	String paq_exd="";
	String guia_exd="";
	String exd="EXD";
	int exdente=0;	
	int total_guia=0;
	int total_guia_alm=0;
	int total_guia_emb=0;
	int total_bultos=0;
	int total_bultos_alm=0;
	int total_bultos_esc=0;
	float total_porc=0;
	int nguias_total=0;
	int bultos_total=0;
	int nguia;
	
	HashMap site = null;
	HashMap carga = null;
	
	for (int r=0;r<destinos.size();r++){
	   	site = (HashMap) destinos.get(r);
	   	destino = site.get("destino").toString();
	   
		/**--String trasbordo_des = bitacoras.get_suc_trasbordo(destino);*/
	   

	   	//**-- String acopio = bitacoras.get_suc_acopio(trasbordo_des);   sucursal 70*//
	   	
	   	String acopio = "";
	   	
	   
	   	
	   
	   ArrayList guias = bitacoras.get_carga_bitacora(sucursal,trasbordo,destino);
		  
	 	  
	   
	   		for (int i=0;i<guias.size();i++) {
		       carga = (HashMap) guias.get(i);
			
			 nguias =      carga.get("guias").toString();
			 nguiasc =     carga.get("guias_c").toString();
			 bultos =      carga.get("bultos").toString();
			 bultos_e =    carga.get("bultos_esc").toString();
			 
			 /* Obtiene valor de guia    */
			 
			 nguia = Integer.parseInt(nguias);
			 if (nguia==0) {
				continue;				 
			 }
			 guias_c = bitacoras.get_carga_guias(bitacora,destino,segmento,sucursal,trasbordo,remolque);
			 
			 nguias_total = Integer.parseInt(nguias)+Integer.parseInt(guias_c);
			 
			 guias_alm = Integer.parseInt(nguias) - Integer.parseInt(nguiasc);
			 bultos_esc = bitacoras.get_carga_paquetes(bitacora,destino,segmento,sucursal,trasbordo,remolque);
			 bultos_total = Integer.parseInt(bultos) + Integer.parseInt(bultos_esc);
			 bultos_alm = Integer.parseInt(bultos) - Integer.parseInt(bultos_e);
			 
			 if(bultos_alm > 0){
				 porc =(float) ((Integer.parseInt(bultos_esc) * 100)/ Integer.parseInt(bultos));
				 }
			 
			 
			 
			 if (nguias.equals(guias_c) & Integer.parseInt(guias_c) > 0 ){
					
				 porc =100; 
				 
			 }
			 
			 paq_exd = bitacoras.get_carga_paquetes_exd(bitacora,destino,segmento,sucursal,trasbordo,remolque);	 
			 if ( renglon.equals( "tr")){
				 fila="tr"; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }
		
		%>
		
			
					<<%=fila%>>
					<td class="left"> <%=destino%></td>
					<td> <%=nguias%></td>
					<td> <%=guias_alm%> </td>
					<td> <%=guias_c%> </td>
					<td> <%=bultos%> </td>		
					<td> <%=bultos_alm%> </td>
					<td> <%=bultos_esc%> </td>
					<td> <%=porc%> </td>
				</tr>
				
				

<%
if (Integer.parseInt(paq_exd) > 0){
guia_exd = bitacoras.get_carga_guias_exd(bitacora,destino,segmento,sucursal,trasbordo,remolque);
	%>
  <tr class="fila_excedente">
	<td class="left"> <%=destino%> </td>
	<td> <%=exdente%> </td>
	<td> <%=exdente%> </td>
	<td> <%=guia_exd%> </td>
	<td> <%=exdente%> </td>
	<td> <%=exdente%> </td>
	<td>  <%=paq_exd%> </td>
	<td class="porcentaje_excedente">  <%=exd%> </td>
	</tr>
	 <%
}
	total_guia= total_guia + Integer.parseInt(nguias);
	total_guia_alm= total_guia_alm +  guias_alm;
	total_guia_emb =  total_guia_emb + Integer.parseInt(guias_c);
	total_bultos = total_bultos + Integer.parseInt(bultos);
	total_bultos_alm =  total_bultos_alm + bultos_alm;
	total_bultos_esc = total_bultos_esc + Integer.parseInt(bultos_esc);	
	   	} 	
	   	 
if (total_bultos > 0){
				
				total_porc = (float) (total_bultos_esc * 100 / total_bultos);
				}else{
					total_porc=0;
				}
	   	
 }
	bitacoras.pro_ins_disp(bitacora, sucursal,tipo,segmento,remolque,Integer.toString(total_guia),Integer.toString(total_guia_alm),Integer.toString(total_guia_emb),Integer.toString(total_bultos),Integer.toString(total_bultos_alm),Integer.toString(total_bultos_esc),Float.toString(total_porc));

	%>
	
	</tbody>
	    
		</table>
	
	</form>
	</div>
</body>
</html>