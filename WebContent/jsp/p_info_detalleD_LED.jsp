<!DOCTYPE html>
<%@ page language="java"

	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
  
<%
response.setHeader("Vary","*");
response.setHeader("Cache-Control","public,must-revalidate,max-age=86400");
response.setHeader("Connection","Keep-Alive");
response.setHeader("Accept-Encoding","gzip, deflate, compress, chunked");
response.setHeader("Accept-Ranges", "*");
response.setHeader("Transfer-Encoding","*");
response.setHeader("Content-Encoding","*");
%>


<html manifest="../estilos/cachethis.appcache" class="no-js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<!-- STYLESHEETS  -->
<!--
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
-->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />


<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../js/queryloader2.min.js" type="text/javascript"></script>
<script src="../js/script.js" type="text/javascript"></script>


<!-- El scroll automático, ahí se ajusta velocidad  -->
<script src="../js/scroll_LED.js"></script>



<style type="text/css">
    html, body, div, iframe { margin:0; padding:0; }
    iframe { display:block; border:none; }
html,body,div,table,body:empty {
background: black;
background-color: black;
color: white;
} 
</style>



<META charset="iso-8859-1">


<TITLE>Bit&aacute;coras en Sucursal</TITLE>



 <%

 String bitacora = request.getParameter("bitacora");
 String sucursal = request.getParameter("suc");
 String ruta = request.getParameter("ruta");
 String segmento = request.getParameter("seg");
 String segmn = request.getParameter("segmn");
 String tipo = request.getParameter("tipo");
 String caja = request.getParameter("caja");
 String remolque = request.getParameter("remolque");
 String pantalla = request.getParameter("numpant");
 %>

<%
	Informativa bitacoras = new Informativa();
   String tipo_C = "";
   if ( tipo.equals( "D1")){
	   tipo_C ="R";
   }

    String fdescarga = bitacoras.get_finDescarga(sucursal,pantalla,bitacora,remolque,segmn);
	ArrayList destinos = bitacoras.get_dest_carga(bitacora,remolque,segmento,tipo_C,caja);
	String valida = bitacoras.get_valida_disp(bitacora,tipo,pantalla,remolque);
	
	if (valida.equals("N")){
		 %>
         <script type="text/javascript"> 
         	parent.location.href="../jsp/p_control.jsp?suc=<%=sucursal%>&disp=<%=pantalla%>";
         </script>
		  <%
	}
	
	if ( fdescarga.equals( "F" ) ){ 
		 %>
		  <script type="text/javascript"> 
         	parent.location.href="../jsp/p_control.jsp?suc=<%=sucursal%>&disp=<%=pantalla%>";
          </script>
		   <%
		
		}
	
	 
%>

<meta http-equiv="refresh" content="45" url="../jsp/p_info_detalleD_LED.jsp?bitacora=<%=bitacora%>&suc=<%=sucursal%>&ruta=<%=ruta%>&seg=<%=segmento%>&tipo=<%=tipo%>&caja=<%=caja%>&remolque=<%=remolque%>&numpant=<%=pantalla%>">

<META name="GENERATOR" content="MSHTML 9.00.8112.16443">
</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">

<div id="scroller"> 

	<form>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="detalle_carga">
		<TBODY>

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
String guias_c = "0";
int guias_rem = 0;
String bultos ="0";
int bultos_rem = 0;
String bultos_esc = "0";
float porc =0;
String renglon="tr";
String fila="";
String paq_exd="";
String guia_exd="";
String exd="EXD";
int exdente=0;
int total_guia=0;
int total_guia_rem=0;
int total_guia_emb=0;
int total_bultos=0;
int total_bultos_esc=0;
int  total_bultos_rem=0;
float porcen=0;

	HashMap site = null;
	HashMap carga = null;
	for (int r=0;r<destinos.size();r++){
	   	site = (HashMap) destinos.get(r);
	   	destino = site.get("destino").toString();
	   
	   ArrayList guias = bitacoras.get_descarga_bitacora(bitacora,destino,segmento,remolque);
	 		  	
	   	for (int i=0;i<guias.size();i++) {
		       carga = (HashMap) guias.get(i);
		
			 nguias =      carga.get("guias").toString();
			 bultos =      carga.get("bultos").toString();
			 guias_c = bitacoras.get_descarga_guias(bitacora,destino,segmento,remolque);
			 guias_rem = Integer.parseInt(nguias) - Integer.parseInt(guias_c);
			 bultos_esc = bitacoras.get_descarga_paquetes(bitacora,destino,segmento,remolque);
			 bultos_rem = Integer.parseInt(bultos) - Integer.parseInt(bultos_esc);
			 porc =(float) ((Integer.parseInt(bultos_esc) * 100)/Integer.parseInt(bultos));
			 paq_exd = bitacoras.get_descarga_paquetes_exd(bitacora,destino,segmento,remolque);
			 
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
					<td> <%=guias_rem%> </td>
					<td> <%=guias_c%> </td>
					<td> <%=bultos%> </td>		
					<td> <%=bultos_rem%> </td>
					<td> <%=bultos_esc%> </td>
					<td> <%=porc%> </td>


				</tr>

<%
    
if (Integer.parseInt(paq_exd) > 0){
	 System.out.println("valores " + paq_exd);

guia_exd = bitacoras.get_descarga_guias_exd(bitacora,destino,segmento,remolque);
	%>
	<<%=fila%>>
	<td class="fila_excedente"> <%=destino%> </td>
	<td class="fila_excedente"> <%=exdente%> </td>
	<td class="fila_excedente"> <%=exdente%> </td>
	<td class="fila_excedente"> <%=guia_exd%> </td>
	<td class="fila_excedente"> <%=exdente%> </td>
	<td class="fila_excedente"> <%=exdente%> </td>
	<td class="fila_excedente"> <%=paq_exd%> </td>
	<td class="porcentaje_excedente"> <%=exd%></td>
	</tr>

	 <%
}
	total_guia= total_guia + Integer.parseInt(nguias);
	total_guia_rem = total_guia_rem + guias_rem;
	total_guia_emb = total_guia_emb + Integer.parseInt(guias_c);
	total_bultos =  total_bultos + Integer.parseInt(bultos);
	total_bultos_rem = total_bultos_rem + bultos_rem;
	total_bultos_esc = total_bultos_esc + Integer.parseInt(bultos_esc);
	   	
	   	}
		
		if (total_bultos > 0){
			
			porcen = (float) (total_bultos_esc * 100 / total_bultos);		
		}else{
			
			porcen=0;
		}
			
 }
	bitacoras.pro_ins_disp(bitacora, sucursal,tipo,segmento,remolque,Integer.toString(total_guia),Integer.toString(total_guia_rem),Integer.toString(total_guia_emb),Integer.toString(total_bultos),Integer.toString(total_bultos_rem),Integer.toString(total_bultos_esc),Float.toString(porcen)); 

	%>
	    </TBODY>
		</table>
	
	</form>
	</div>
</body>
</html>