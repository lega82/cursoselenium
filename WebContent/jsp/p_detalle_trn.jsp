<!DOCTYPE txt>
<%@ page language="java"

	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
	

	
<html>
<head>
<link href="../imagen/LOGO.ico"  type="image/x-icon" rel="shortcut icon">
<title>Detalle de Guias en Bit&aacute;cora</title>


<script src="../js/jquery-1.11.2.min.js"></script>


<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<!-- 
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">
 -->
 
<style>
#report1
{
font-family: "Times New Roman", Times, serif;

width:98%;
border-collapse:collapse;
}
#report1 td, 
#report1 th
{
font-size:1em;
border:1px solid #ddd;
padding:2px 2px 2px 2px;
}
#report1 th
{
/*border:1px solid #111;*/
border: none;
font-size:1.1em;
font-weight: normal;
/*text-align:left;*/
padding: 0.3em 0.3em 0.1em 0.3em;
background-color:#0D4E94;
color:#ffffff;
}

#report1 td
{
font-size:1.0em;
/*text-align:left;*/
padding-top:3px;
padding-bottom:2px;
background-color:#ffffff;
color:#000000;
}

#report1 tr.alt td
{
color:#000000;
background-color:#f5f5f5;
}

h1, h2, h3, h4, h5, h6, p, span {
margin: 0;
padding: 0;
}

.inline-block {
  display: inline-block !important;
}

</style>


<script src="../js/devicejs/lib/device.js"></script>


<!-- El scroll automático, ahí se ajusta velocidad  -->

<!-- 
<script src="../js/scroll.js"></script>
 -->


<script src="../jQueryAssets/jquery-1.8.3.js" type="text/javascript"></script>

        

<style type="text/css">
body,td,th {
    font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
</style>



<META charset="iso-8859-1">








 <%

 String bitacora = request.getParameter("bitacora");
 String segmento = request.getParameter("segmento");
 String sucursal = request.getParameter("sucursal");
 String remolque = request.getParameter("remolque");
 

 


 %>

<%
	Informativa bitacoras = new Informativa();
   	String tipo_C = "";
   	
    
  
   	

   	
	
   	
   	
  
	
   		

%>



<META name="GENERATOR" content="MSHTML 9.00.8112.16443">

</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla">
<!-- <div id="scroller">  -->
<div id="guias-para-sucursal"> 

	<form>
	<div id="dvData">
		<TABLE align="center" border="0" cellpadding="0" cellspacing="0" id="report1">
		<TBODY>

<%
	
    String ruta = "";
	String rastreo = "";
	String guia = "";
	String destino = "";
	String descripcion = "";
	String tarifa = "";
	String contenido = "";
	String paquete = "";
	String numero = "";
	String colonia = "";
	String codigo = "";
	String delegacion = "";
	String renglon="tr";
	String fila="";
	String volumen;
	
	String color="";
	String ruta1="N";
	String ruta2="N";
	String rutat="N";
	String guiat="N";
	String bultost="N";
	String pesot="N";
	String volument="N";
	int vguias;
	String fin="N";
		
	
	HashMap site = null;
	HashMap carga = null;
	HashMap total = null;
	
	
	
	   
	   	
	 ArrayList guias = bitacoras.getdetalle_trn(bitacora,segmento,remolque,sucursal);
	 
	 
	   
	   %>
	   <tr><th colspan="10">Bitacora:&nbsp;&nbsp;&nbsp;<%=bitacora%>&nbsp;&nbsp;&nbsp;<input type="button" id="btnExport" value="Excel" /></th></tr>
	   <tr><th colspan="10">Remolque:&nbsp;&nbsp;&nbsp;<%=remolque%>&nbsp;&nbsp;&nbsp; </th></tr>
	   <tr>
	   
	   <th>Guia </th>
	   <th>Destino</th>
	   <th>Rastreo </th>
	   <th>Paquetes </th>
	   <th>Contenido </th>
	   <th>Tarifa </th>
	   <th >Volumen </th>
	  
	   
	   </tr>
	
	   
	   
	   <%
	   	for (int i=0;i<guias.size();i++) {
		       carga = (HashMap) guias.get(i);

		       vguias = guias.size();

			 guia  =   carga.get("guia").toString();
			 destino = carga.get("destino").toString();
			 rastreo =  carga.get("rastreo").toString();
			 remolque = carga.get("remolque").toString();
			 paquete =  carga.get("paquete").toString();
			 contenido = carga.get("contenido").toString();
			 tarifa  = carga.get("tarifa").toString();
			 volumen = carga.get("volumen").toString();





			 if ( renglon.equals( "tr")){
				 fila="tr";
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }








		%>

			 		<<%=fila%>>


					<td><%=guia%></td>
					<td><%=destino%></td>
					<td><%=rastreo%></td>
					<td><%=paquete%></td>
					<td><%=contenido%></td>
					<td><%=tarifa%> <%=numero%></td>
					<td><%=volumen%></td>



				</tr>


<%

	}



	%>

	</tbody>

		</table>
		</div>



    <script>
    $("#btnExport").click(function(e) {
        window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData').html()));
        e.preventDefault();
    });
    </script>

		<br><br>



	</form>
	</div>
</body>
</html>