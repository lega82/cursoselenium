<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"  import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>


<%
response.setHeader("Cache-Control","private,max-age=86400");
response.setHeader("Accept-Ranges", "*");
%>
 

<html id="invert">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bitacoras en Sucursal </title>


<!-- STYLESHEETS  -->

<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja_LED.css" />


<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- El scroll automatico, ahi­ se ajusta velocidad  -->
<script src="../js/scroll_LED.js"></script>




 <%
  String variable = request.getParameter("sucursal");


 %>

<%
 Informativa bitacoras = new Informativa();
 ArrayList valores = bitacoras.getbitacoras(variable);
 String nombre_suc = bitacoras.getnombre(variable);



 boolean retval = valores.isEmpty();
 if (retval == true) {
       System.out.println("list is empty");
     }




%>


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
     height: 148px !important;
   }
</style>


</head>
<body leftmargin="0" topmargin="0" id="detalle_carga-tabla" style="background: transparent">



<div id="content-bitacoras-llegadas">

<TABLE border="0" cellspacing='0' cellpadding='0' id="bitacorasenproceso1" style="width:320px; height: 40px;">
 <tr>
  <th colspan="7" class="centered"> Bit&aacute;coras en Proceso en Sucursal:    <%= nombre_suc %>      </th>
 </tr>
 <tr class="color-4 centered"> 
    <th class="col1 centered">BITACORA</th>
    <th width="16%">TIPO</th>
    <th width="13%">TRACTO</th>
    <th width="13%">CAJA</th>
    <th width="24%">SEGMENTO</th>
  </tr>
</table>
   

   
<div id="scroller">
  
  
  <TABLE border="0" cellspacing='0' cellpadding='0' id="bitacorasenproceso" style="width:320px">
    
    
<%
 String clave = "";
 String stus="P";
 String color="";
 String tipo="";
 String descripcion = "";
 String status = "";
 String segm = "";
 String segmento = "";
 String tracto = "";
 String caja = "";
 String renglon="tr";
 String fila="";
 String sucur="";


 HashMap site = null;
 if (valores!=null) {
  for (int i=0;i<valores.size();i++) {
   site = (HashMap) valores.get(i);
   clave = site.get("viaje").toString();
   descripcion = site.get("process").toString();
   tracto = site.get("tracto").toString();
   caja = site.get("caja").toString();
   segmento = site.get("segmento").toString();
   segm = site.get("segno").toString();
   status = site.get("estatus").toString();
   sucur = site.get("sucursal").toString();




   if ( descripcion.equals( "C1" ) ){
    tipo ="CARGA";
    color ="#fff";
   }else{
    tipo ="DESCARGA";
    color ="#a52a2a";
   }


   if ( renglon.equals( "tr")){
     fila="tr class='centered'";
     renglon="tr2";
      }else{
      fila="tr class='alt centered'";
      renglon="tr";
      }

   if (status.equals("PROCESO")){
    %>
    <<%=fila%>>
    
    
 
     <td class="col1" vAlign="top"><a href="../jsp/p_tipo_LED.jsp?bita=<%=clave%>&tipo=<%=descripcion%>&sucursal=<%=sucur%>&est=<%=stus%>&segm=<%=segm%>&caja=<%=caja%>" class="texto_normal" target="_self"><font color="#fff"> <%=clave%></font></a></td>

     <td width="16%" vAlign="top"><%=tipo%></td>
      <!--
     <td><font color=<%=color%>> <%=status%></font></td>
-->
     <td class="font-size-09" width="13%" vAlign="top"> <%=tracto%> </td>
     <td class="font-size-09" width="13%" vAlign="top"><%=caja%></td>
     <td class="font-size-09"  width="24%" vAlign="top"> <%=segmento%></td>

    </tr>

<%
   }


  }
 }

 %>


  </div><!--  /scroller END -->

  </table>

 </div>




</body>
</html>
