<!DOCTYPE html>
<%@ page language="java"
  import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>




<html>
<head>
<meta name="viewport"
  content="width = device-width, initial-scale = 1.0, minimum-scale = 1.0" />

<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet"
  media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<!--
<link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />  
-->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet"
  type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet"
  type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<!-- 
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">
 -->

<script src="../js/devicejs/lib/device.js"></script>


<!-- El scroll automático, ahí se ajusta velocidad  -->

<!-- 
<script src="../js/scroll.js"></script>
 -->


<script src="../jQueryAssets/jquery-1.8.3.js" type="text/javascript"></script>



<style type="text/css">
body, td, th {
  font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
}
</style>



<META charset="iso-8859-1">


<TITLE>Bit&aacute;coras en Sucursal</TITLE>




<%

 String bitacora = request.getParameter("bitacora");
 String sucursal = request.getParameter("sucursal");
 String caja = request.getParameter("caja");
 String segmento = request.getParameter("segmento");

 


 %>

<%
	Informativa bitacoras = new Informativa();
   	String tipo_C = "";
   	
    
  
   	

   	
	
   	
   	
  
	
   		

%>



<META name="GENERATOR" content="MSHTML 9.00.8112.16443">

</head>
<!--  
<body leftmargin="0" topmargin="0" id="body-guias-para-sucursal">
-->



<body leftmargin="0" topmargin="0" id="">
  <!-- <div id="scroller">  -->


  <div id="">

    <form>




      <!-- 
=======================================

Guías para Sucursal

=======================================
 -->


      <TABLE width="100%" border="0" cellpadding="0" cellspacing="0"  id="background-degradado">
        <tbody>

          <%
	String suc = "";
    String dest = "";
	String color="";
	String volumen = "";
	String nombre_suc = "";
	String guiast = "";
	String bultos ="";
	String renglon="tr";
	String fila="";
	String g_ead = "";
	String b_ead = "";
	String v_ead = "";
	String g_ocu = "";
	String b_ocu = "";
	String v_ocu = "";
		
	
	HashMap site = null;
	HashMap carga = null;
	
	
	
	   
	   	
	   ArrayList guias = bitacoras.getdetalle_destino(bitacora,caja,sucursal);
	   
	   ArrayList trasbordo = bitacoras.getdetalle_trasbordo(bitacora,caja,segmento,sucursal);
	   
	  
	   
	   %>
          <tr>
            <th id="guias-para-sucursal-header" class="padding-0" colspan="11">Guías para Sucursal &nbsp; 
             
           
            </th>
          </tr>
          <tr>
            <th colspan="5">TOTAL DE GUIAS</th>
            <th colspan="3">GUIAS EAD&nbsp;&nbsp;<a href="../jsp/p_detalle_ruta.jsp?bitacora=<%=bitacora%>&caja=<%=caja%>&sucursal=<%=sucursal%>"   class="texto_normal" target="_blank"><font color="#ffffff"> <i class="fa fa-search"></i></font> </a></th>
            <th colspan="3">GUIAS OCURRE&nbsp;&nbsp;<a href="../jsp/p_detalle_ocurre.jsp?bitacora=<%=bitacora%>&caja=<%=caja%>&sucursal=<%=sucursal%>"   class="texto_normal" target="_blank"><font color="#ffffff"> <i class="fa fa-search"></i></font> </a></th>
          </tr>
          
          <tr>
            <th colspan="2">Sucursal</th>
            <th>Guías</th>
            <th>Paquetes</th>
            <th>Volumen</th>
            <th>Guias</th>
            <th>Paquetes</th>
            <th>Volumen</th>
            <th>Guias</th>
            <th>Paquetes</th>
            <th>Volumen</th>
          </tr>
          


          <%
	   	for (int i=0;i<guias.size();i++) {
		       carga = (HashMap) guias.get(i);
			
			 suc  =        carga.get("suc").toString();
			 nombre_suc = carga.get("nombre").toString();
			 guiast =       carga.get("guias").toString();
			 bultos =      carga.get("bultos").toString();
			 volumen =     carga.get("volumen").toString();
			 g_ead  = carga.get("g_ead").toString();
			 b_ead	= carga.get("b_ead").toString();
			 v_ead  = carga.get("v_ead").toString();
			 g_ocu  = carga.get("g_ocu").toString();
			 b_ocu	= carga.get("b_ocu").toString();
			 v_ocu  = carga.get("v_ocu").toString();
			 
			 
			
	   	
		
			 if ( renglon.equals( "tr")){
				 fila="tr"; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }
			 
			 
			 
			 
           
          
		
		%>


          <<%=fila%>>


          <td><%=suc%></td>
          <td><%=nombre_suc%></td>
          <td><%=guiast%></td>
          <td><%=bultos%></td>
          <td><%=volumen%></td>
          <td><%=g_ead%></td>
          <td><%=b_ead%></td>
          <td><%=v_ead%></td>
          <td><%=g_ocu%></td>
          <td><%=b_ocu%></td>
          <td><%=v_ocu%></td>
          <tr></tr>


          <%
   
	}
		
      

	%> </<%=fila%>>
        </tbody>

      </table>

      <br> <br>





      <!-- 
=======================================

Guías para Transbordar

=======================================
 -->



      <%
		if ( trasbordo.isEmpty() ){
			System.out.println("ENTRO AQUI  ");
		}else{
			%>




      <TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
        id="background-degradado">
        <TBODY>
          <tr>
            <th colspan="11" id="guias-para-sucursal-header">Guías
              para Trasbordar</th>
          </tr>
          <tr>
            <th colspan="5">TOTAL DE GUIAS</th>
            <th colspan="3">GUIAS EAD</th>
            <th colspan="3">GUIAS OCURRE</th>
          </tr>
          <tr>
            <th colspan="2">Sucursal</th>
            <th>Guias</th>
            <th>Paquetes</th>
            <th>Volumen</th>
            <th>Guias</th>
            <th>Paquetes</th>
            <th>Volumen</th>
            <th>Guias</th>
            <th>Paquetes</th>
            <th>Volumen</th>
          </tr>

          <%
	   	for (int i=0;i<trasbordo.size();i++) {
		       carga = (HashMap) trasbordo.get(i);
			
			 suc  =        carga.get("suc").toString();
			 nombre_suc = carga.get("nombre").toString();
			 guiast =       carga.get("guias").toString();
			 bultos =      carga.get("bultos").toString();
			 volumen =     carga.get("volumen").toString();
			 g_ead  = carga.get("g_ead").toString();
			 b_ead	= carga.get("b_ead").toString();
			 v_ead  = carga.get("v_ead").toString();
			 g_ocu  = carga.get("g_ocu").toString();
			 b_ocu	= carga.get("b_ocu").toString();
			 v_ocu  = carga.get("v_ocu").toString();
			 
			 %>



          <%
		
			 if ( renglon.equals( "tr")){
				 fila="tr"; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }
			 
			 
			 
			 
           
          
		
		%>


          <<%=fila%>>
          <td><%=suc%></td>
          <td><%=nombre_suc%></td>
          <td><%=guiast%></td>
          <td><%=bultos%></td>
          <td><%=volumen%></td>
          <td><%=g_ead%></td>
          <td><%=b_ead%></td>
          <td><%=v_ead%></td>
          <td><%=g_ocu%></td>
          <td><%=b_ocu%></td>
          <td><%=v_ocu%></td>
          <tr></tr>



          <%
   
	}
		
 
	    

	%> <% 	
		}
		%> </<%=fila%>>
        </tbody>

      </table>
    </form>
  </div>
</body>
</html>