<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>


<%@ page language="java"
	import="java.util.ArrayList,java.util.Calendar,java.util.HashMap,pantallas.Informativa"%>
  
  
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
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<!-- STYLESHEETS  -->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/hoja_LED.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<!-- 
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
 -->
 
<!-- SCRIPTS  -->
<link href="../fonts/fontello/css/fontello-embedded.css" rel="stylesheet" type="text/css" />


<!-- SCRIPTS  -->

<META name="GENERATOR" content="MSHTML 9.00.8112.16443">



<title>Soy la barra izquierda</title>

<%

 String sucursal = request.getParameter("sucursal");
 String process = request.getParameter("proceso");
 String estatus = request.getParameter("est");
 String bitacora = request.getParameter("bitacora");
 String ruta = request.getParameter("ruta");
 String tracto = request.getParameter("tracto");
 String remolque1 = request.getParameter("remolque1");
 String remolque2 = request.getParameter("remolque2");
 String segmento = request.getParameter("segmento");
 String salida_E = request.getParameter("salidaE");
 String llegadaE = request.getParameter("llegadaE");
 String tipo = request.getParameter("tipo");
 String capacidad = request.getParameter("capacidad_caja");
 String suc = request.getParameter("suc");
 String segmn = request.getParameter("segmn");
 String cronometro = "";
 
 
 
 Informativa bitacoras = new Informativa();
 String dif_hora = "0"; 
  // bitacoras.get_hora(suc);  
 
 String etiqueta="";
 String etiqueta2="";
 String flecha="";
 String hora_local="";

 
 
 
 
 if (tipo.equals("D1")){
	 
	 etiqueta ="<h5 class='color-1'>Llegada Esperada:</h5>";
	
	 etiqueta2= llegadaE;
	 flecha = "icon-down-big";
	 cronometro = "<h5 class='color-1 float-left line-height-2'>Tiempo <br/>Restante:</h5><div class='v-align'><SPAN id='cronometro'></SPAN></div>";
	 
 }else{
	 
	 etiqueta = "<h5 class='color-1'>Salida Esperada:</h5>";
	 etiqueta2 = salida_E;
	 flecha = "icon-up-big";
	 cronometro = "<h5 class='color-1 float-left line-height-2'>Tiempo <br/>Restante:</h5><div class='v-align'><SPAN id='cronometro'></SPAN></div>";
	 
	 
 }
 
 
%>

 <script language="javascript" type="text/javascript">


  function showvolumen(str)
{

if (str=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;

    }
  }
xmlhttp.open("POST","../jsp/p_volumen_LED.jsp?tipo=<%=tipo%>&bitacora=<%=bitacora%>&segmento=<%=segmento%>&capacidad=<%=capacidad%>&caja=<%=remolque1%>", true);

xmlhttp.send();
t=setTimeout('showvolumen(1)',60000);
}


  function showcronometro(str)
  {

  if (str=="")
    {
    document.getElementById("cronometro").innerHTML="";
    return;
    }
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp1=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp1.onreadystatechange=function()
    {
    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
      {
      document.getElementById("cronometro").innerHTML=xmlhttp1.responseText;

      }
    }
  xmlhttp1.open("POST","../jsp/cronometro_C_LED.jsp?bitacora=<%=bitacora%>&sucursal=<%=suc%>&segmn=<%=segmn%>&tipo=<%=tipo%>", true);

  xmlhttp1.send();
  t=setTimeout('showcronometro(1)',60000);
  }

function startTime(str){
	today=new Date();
	h=today.getHours();
	m=today.getMinutes();
	s=today.getSeconds();
	m=checkTime(m);
	s=checkTime(s);
	document.getElementById('reloj').innerHTML=h+str+":"+m;
	t=setTimeout('startTime(<%=dif_hora%>)',60000);
	}





function checkTime(i)
{if (i<10) {i="0" + i;}return i;}
window.onload=function(){startTime();showvolumen(1);showcronometro(1);}



</script>





  

</head>
 
<body id="detalle_carga-ancho">



<!-- SUCURSAL -->
<div>
   <h6 class="color-1"><%=sucursal%></h6>
</div>



<!-- CARGA O DESCARGA -->
<div class="centered flecha-y-tipo">
    <h2>
          <i class="<%=flecha%>"></i><%=process%>
    </h2>
</div>

<div style="clear: both;"></div><!--  CLEAR -->


<!-- BITACORA -->
<div>
      		<h3 class="color-1">Bit&aacute;cora</h3>
</div>

<div style="clear: both;"></div><!--  CLEAR -->

<div>
    <h3><%=bitacora%></h3>
</div>
<div style="clear: both;"></div>
<div><!-- SEGMENTO -->
        <h3 class="color-1">Segmento</h3>
    <h3><%=segmento%></h3>
</div>
    

      <!-- TRACTO y REMOLQUE -->
<div id="volumenes" style="display: table;">
   <div style="display: table-row;">
      <div style="display: table-cell;">
          <h3 class="color-1">Tracto <span class="color-2">
              <%=tracto%> &nbsp;
              </span>
          </h3>
      </div>
      <div style="display: table-cell;">
          <h3 class="color-1">Rem <span class="color-2 right"><%=remolque1%></span></h3>
      </div>
   </div>
</div>
    
    
    
    
      <!----------------------------------
                CAPACIDAD
          ---------------------------------->
  <div id="volumenes" style="display: table;" class="font-size-4">
    <div style="display: table-row;">
      <div style="display: table-cell;">
        <span class="color-1 font-size-4">Capac: &nbsp;</span>
      </div>
    </div>
    <div style="display: table-row;">
      <div style="display: table-cell;">
        <span class="font-size-4"><%=capacidad%> m&#179; &nbsp;</span>
      </div>
    </div>
 </div>
             
 <div id="volumenes" style="display: table;">

          <div class="color-1 font-size-4">Escan.  Disp.  % </div>
          <!--  ABRIR p_volumen_LED.jsp PARA EDITAR ESTOS TEXTOS -->
          <div id="txtHint" class="center color-2"></div>

 </div>
      <div style="clear: both;"></div><!--  CLEAR -->

      <!----------------------------------
                      SALIDA ESPERADA
          ---------------------------------->
  
    <% hora_local = "<strong id='hora'></strong>";
   
    %>
                                  
<div style="display: table; width: 86px">
   <div style="display: table-row;">
      <div style="display: table-cell;">
          <%=etiqueta%>
      </div>
      <div style="display: table-cell;">
          <h2 id="salida-esperada"><%=etiqueta2%></h2>
      </div>
   </div>
   <div style="display: table-row;">
      <div style="display: table-cell;">
          <h5 class="color-1">Hora Actual</h5>
      </div>
      <div style="display: table-cell;">
          <h2 id="reloj"></h2>
      </div>
   </div>
</div>


 <div style="clear: both;"></div> <!--  CLEAR -->

<div class="color-1"><!--  Tiempo Restante -->
  <%=cronometro%>
</div>

    

</div>

</body>
</html>