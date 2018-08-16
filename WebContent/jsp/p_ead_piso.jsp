<!DOCTYPE txt>
<%@ page language="java"

	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
	

	
<html>
<head>
<link href="../imagen/LOGO.ico"  type="image/x-icon" rel="shortcut icon">
<title>Detalle de Guias en Sucursal
</title>


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
font-size:1.5em;
font-weight: normal;
/*text-align:left;*/
padding: 0.3em 0.3em 0.1em 0.3em;
background-color:#0D4E94;
color:#ffffff;
}

#report1 td
{
font-size:.8em;
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

  String sucursal = request.getParameter("sucursal");

  

 


 %>

<%
	Informativa bitacoras = new Informativa();
   	String tipo_C = "";
   	
   	String user = "PLANEACION EAD";
   	String app = "PLANEACION EAD";
   	String app_opc = "MERCANCIA EN PISO";
   	String ipCustom = request.getRemoteAddr();
   	
   	bitacoras.pro_ins_log(user, sucursal,app,app_opc,ipCustom);

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
	String bultos = "";
	String descripcion = "";
	String tarifa = "";
	String contenido = "";
	String calle = "";
	String numero = "";
	String colonia = "";
	String codigo = "";
	String delegacion = "";
	String renglon="tr";
	String fila="";
	String cliente;
	String ruta1="N";
	String ruta2="N";
	String rutat="N";
	String guiat="N";
	String bultost="N";
	String pesot="N";
	String volument="N";
	int vguias;
	String fin="N";
	String color="";
	String bitacora="";
	
	HashMap site = null;
	HashMap carga = null;
	HashMap total = null;
	  
	String nombre = bitacoras.getnombresite(sucursal);   	
	ArrayList guias = bitacoras.getdetalle_ead(sucursal);
	ArrayList totales = bitacoras.get_totales_ead(sucursal);
	  
	   
	   %>
	   <tr><th colspan="11">PLANEACION EAD:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btnExport" value="Excel" /></th></tr>
	   <tr><th colspan="11">SUCURSAL&nbsp;&nbsp;<%=nombre%>&nbsp;  </th></tr>
	   <tr>
	   <th>Ruta</th> 
	   <th>Guia </th>
	   <th>Paq</th>
	   <th>Cliente </th>
	   <th>Descripcion </th>
	   <th>Tarifa </th>
	   <th>Contenido </th>
	   <th >Calle </th>
	   <th>Colonia </th>
	   <th>Codigo Postal </th>
	   <th>Bitacora </th>
	   
	 
	   
	   </tr>
	   <%
	   	for (int i=0;i<guias.size();i++) {
		       carga = (HashMap) guias.get(i);
		       
		       vguias = guias.size();

		     
			 ruta  =        carga.get("ruta").toString();
			 
			 rastreo = carga.get("rastreo").toString();
			 guia =       carga.get("guia").toString();
			 cliente =       carga.get("cliente").toString();
			 bultos =      carga.get("bultos").toString();
			 descripcion =     carga.get("descripcion").toString();
			 tarifa  = carga.get("tarifa").toString();
			 contenido	= carga.get("contenido").toString();
			 calle  = carga.get("calle").toString();
			 numero  = carga.get("numero").toString();
			 colonia	= carga.get("colonia").toString();
			 codigo  = carga.get("codigo").toString();
			 delegacion =carga.get("delegacion").toString();
			 bitacora =carga.get("bitacora").toString();
			
	   	      
		
			 if ( renglon.equals( "tr")){
				 fila="tr"; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }
			 
			
			 
			if (ruta1.equals("N")) {
				ruta1=ruta;
			}
			
			if (ruta2!="N"){
				if (ruta!=ruta2){
					ruta1=ruta2;
					ruta2="N";
				}
			}
			 
			
		    if (ruta.equals(ruta1)){
		    	
		    	
		    	
		    	if (vguias==i+1){ 
		    		
		    		for (int c=0;c<totales.size();c++) {
			    		total = (HashMap) totales.get(c);
			    		
			    		rutat = total.get("ruta").toString();
			    		
			    		
			    		if (ruta1.equals(rutat)){
			    			guiat = total.get("guias").toString();
			    			bultost = total.get("bultos").toString();
			    			pesot   = total.get("peso").toString();
			    			volument = total.get("volumen").toString();
			    		}
			    		
			    			
			    		} 		
			    	   
			    	   
			    	 
			       }
		    	
		    	
		    }else{
		    	
		    	
		    	
		    	
		    	for (int c=0;c<totales.size();c++) {
		    		total = (HashMap) totales.get(c);
		    		
		    		rutat = total.get("ruta").toString();
		    		
		    		
		    		if (ruta1.equals(rutat)){
		    			ruta1="N";
		    			ruta2=ruta;
		    			guiat = total.get("guias").toString();
		    			bultost = total.get("bultos").toString();
		    			pesot   = total.get("peso").toString();
		    			volument = total.get("volumen").toString();
		    		}
		    			
		    					
		    		} 		
		    	
		    	
		    	if (vguias==i+1){
		    		fin = "Y";
		    	}
		    	
		    	%>
		    	<tr ><td colspan="11"><h2><font color="blue" >TOTAL  GUIAS&nbsp;&nbsp;&nbsp;<%=guiat%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PAQUETES&nbsp;&nbsp;&nbsp;<%=bultost %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PESO&nbsp;&nbsp;&nbsp;<%=pesot%>&nbsp;kg&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VOLUMEN&nbsp;&nbsp;&nbsp;<%=volument%>&nbsp;M3</font></h2> </td></tr>
		    	<tr><td colspan="11">&nbsp;</td></tr>
		    
		    <% 
		    }
				
		
		%>
		
			 		
			 		<<%=fila%>>
					<td> <%=ruta%></td>
					
					<td><%=guia%></td>
					<td><%=bultos%></td>
					<td><%=cliente%></td>
					<td><%=descripcion%></td>
					<td><%=tarifa%></td>	
					<td><%=contenido%></td>	
					<td><%=calle%> <%=numero%></td>	
					<td><%=colonia%></td>	
					<td><%=codigo%> </td>
					<td><%=bitacora%></td>
					
						
					
					
				</tr>
			

<%
   
    
	}
	   
	   if (fin.equals("Y")){
		   for (int c=0;c<totales.size();c++) {
	    		total = (HashMap) totales.get(c);
	    		
	    		rutat = total.get("ruta").toString();
	    		
	    		
	    		if (ruta.equals(rutat)){
	    			ruta1="N";
	    			ruta2=ruta;
	    			guiat = total.get("guias").toString();
	    			bultost = total.get("bultos").toString();
	    			pesot   = total.get("peso").toString();
	    			volument = total.get("volumen").toString();
	    			
	    		}
	    			
	    					
	    		} 		
		   
		   
		   
	   }
	   

	%>
	<tr class='alt'><td colspan="11"><h2><font color="blue" >TOTAL  GUIAS&nbsp;&nbsp;&nbsp;<%=guiat%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PAQUETES&nbsp;&nbsp;&nbsp;<%=bultost %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PESO&nbsp;&nbsp;&nbsp;<%=pesot%>&nbsp;kg&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VOLUMEN&nbsp;&nbsp;&nbsp;<%=volument%>&nbsp;M3</font></h2> </td></tr>
	
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