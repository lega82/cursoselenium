<!DOCTYPE html>
<html id="invert">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Bit&aacute;coras en Transito</title>


<!-- STYLESHEETS  -->

<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />
<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- El scroll automatico, ahi­ se ajusta velocidad  -->
<script src="../js/scroll_LED.js"></script>


<style type="text/css">

html,body,div,table,body:empty {
background: black;
background-color: black;
color: white;
}
body,td,th {
    font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
  #scroller {
     width: 320px;
     height: 160px !important;
   }
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

	ArrayList valores = bitacoras.getbitacoras_llegada(sucursal,sucursal);
	String nombre_suc = bitacoras.getnombre(sucursal);

	String power = bitacoras.get_off_disp(sucursal,pantalla);
 	System.out.println("Screen llegadas power OFF .... " +power);

 	if (power.equals("OFF")){
   	  %>
        <script type="text/javascript">
        parent.location.href="../jsp/p_control.jsp?suc=<%=sucursal%>&disp=<%=pantalla%>";
        </script>
		  <%
   }


	ArrayList process = bitacoras.getpantalla(sucursal,pantalla);
	 String disp_clave = "";
	 String disp_tipo ="";
	 String disp_tracto ="";
	 String disp_caja ="";
	 String disp_segmento ="";
	 String disp_segm ="";
	 String disp_status ="";

	int valor = process.size();
	HashMap site = null;

	if (process!=null) {
		for (int i=0;i<process.size();i++) {
			site = (HashMap) process.get(i);
			disp_clave = site.get("viaje").toString();
			disp_tipo = site.get("process").toString();
			disp_tracto = site.get("tracto").toString();
			disp_caja = site.get("caja").toString();
			disp_segmento = site.get("segmento").toString();
			disp_segm = site.get("segno").toString();
			disp_status = site.get("estatus").toString();

		}
	}

	if(!disp_status.equals("SALIDA")){

		if (valor > 0) {
		%>
		<meta http-equiv='refresh' content='0; url=../jsp/p_control.jsp?suc=<%=sucursal%>&disp=<%=pantalla%>' >
		<%
		}
	}






%>



<script type="text/javascript">
</script>
<meta http-equiv="refresh" content="60; url=../jsp/p_llegadas_LED.jsp?sucursal=<%=sucursal%>&numpant=<%=pantalla%>">
</head>

<body leftmargin="0" topmargin="0" id="detalle_carga-tabla" style="background: transparent">

<div id="content-bitacoras-transito">
  <form>
     <TABLE id="bitacorasenproceso1" style="width:320px">
      <thead>
      	<tr>
            <th colspan="4" class="centered">
                Bit&aacute;coras en Transito <%= nombre_suc %>
            </th>
            <th colspan="3" class="color-5 centered">
                Guias:
            </th>
        </tr>
    	<tr class="color-5 centered p_llegadas_LED">
            <th>VIAJE</th>
        	<th>SEGMENTO</th>
            <th>Hora Planeada</th>
            <th>Salida Real</th>
        	<th>EAD</th>
        	<th>OCU</th>
            <th>TB</th>
        	<th>TOTAL</th>
    	</tr>
      </thead>
      </table>




<div id="scroller">
      <table>


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
	String guia_trb ="";
	int total_bultos=0;
	int total_guias=0;
	


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
			guia_ead    = viajes.get("guias_ead").toString();
			bulto_ead   = viajes.get("bultos_ead").toString();
			volumen_ead = viajes.get("volumen_ead").toString();
			guia_ocu    = viajes.get("guias_ocu").toString();
			bulto_ocu   = viajes.get("bultos_ocu").toString();
			volumen_ocu = viajes.get("volumen_ocu").toString();
			bulto_trb =   viajes.get("bultos_trb").toString();

			 if ( renglon.equals( "tr")){
				 fila="tr" ;
				 renglon="tr2";
			   }else{
				  fila="tr class='alt centered p_llegadas_LED'";
				  renglon="tr";
			   }

			 guia_trb="0";
			 if (Integer.parseInt(bulto_trb)>0){

				 guia_trb = bitacoras.get_count_trasb(viaje,caja1,segmento,sucursal);


			 }
			 
			 total_guias = Integer.parseInt(guia_ead) + Integer.parseInt(guia_ocu) +  Integer.parseInt(guia_trb);

				%>

				<<%=fila%> class="centered p_llegadas_LED">

				    <td class="col1">
                        <a href="../jsp/p_bitacora.jsp?bita=<%=viaje%>&caja=<%=caja1%>&sucursal=<%=suc_dest%>&segmeno=<%=segmno%>" class="texto_normal" target="_blank"><font color="#fff"> <%=viaje%></font></a>
                    </td>
                    <td class=""><%=segmento%></td>
<!-- 				<td class="font-size-09"><%=f_salida%> <%=h_salida%></td>  -->
                    <td class="green"><%=h_planeada%></td>
 					<td class="red"><%=h_salida%></td>
				    <td><%=guia_ead%></td>
				    <td class=""><%=guia_ocu%></td>
				    <td class=""><%=guia_trb%></td>
				    <td class=""><%=total_guias%></td>
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
	</div>

	</form>
</div>

</body>
</html>