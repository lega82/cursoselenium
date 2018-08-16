<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>


<%@ page language="java"
	import="java.util.ArrayList,java.util.Calendar,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>

<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css" />
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">

<script src="../js/devicejs/lib/device.js"></script>

<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>

<script src="../jQueryAssets/jquery.modern-blink.js" type="text/javascript"></script>

<script type="text/javascript" src="../js/blink.js"></script>






<!--  Estilos para pantallas angostas -->

<style type="text/css">
@media (max-width: 300px) {

    body,td,th {
     font-family: "futura_ltcn_btlight", "Futura LtCn BT", Arial, sans-serif;
    }
    
    h5 {
      line-height: 1.1;
      font-size: 76px;
      letter-spacing: -3px;
    }
    
    h6, hora {
      font-size: 96px;
      letter-spacing: -5px;
      margin-top: 5px;
      margin-bottom: 5px;
    }
    
    status {
     display: block;
    }
    
    #infobitacora {
    margin-bottom: 10px;    
    }
    
    #infobitacora td {
    line-height: 1.6;
    }
    
    ruta, bitacora {
     float: left;
     clear: left;
    }
    
    #reloj, ruta {
      margin: 0;
    }
}

</style>





<META name="GENERATOR" content="MSHTML 9.00.8112.16443">

<title>Insert title here</title>

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
 String dif_hora = bitacoras.get_hora(suc);
 
 String etiqueta="";
 String etiqueta2="";
 String flecha="";
 String hora_local="";

 
 
 
 
 if (tipo.equals("D1")){
	 
	 etiqueta ="<h3 id='llegada-esperada' class='color-1'>Llegada Esperada:</h3>";
	 
	
	 etiqueta2= llegadaE;
	 flecha = "fa fa-arrow-down fa-fw fa-2x";
	 cronometro = "<TR id='tiempo-restante'><TD colspan='1'>Tiempo restante:</td><td><hora class='js-blink-furiously'><DIV id='cronometro'></DIV></hora></td></TR>";
	 
 }else{
	 
	 etiqueta = "<h3 id='salida-esperada' class='color-1'>Salida Esperada:</h3>";
	 etiqueta2 = salida_E;
	 flecha = "fa fa-arrow-up fa-fw fa-2x";
	 cronometro = "<TR id='tiempo-restante'><TD colspan='1'>Tiempo restante:</td><td><hora class='js-blink-furiously'><DIV id='cronometro'></DIV></hora></td></TR>";
	 
	 
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
xmlhttp.open("POST","../jsp/p_volumen.jsp?tipo=<%=tipo%>&bitacora=<%=bitacora%>&segmento=<%=segmento%>&capacidad=<%=capacidad%>&caja=<%=remolque1%>", true);

xmlhttp.send();
t=setTimeout('showvolumen(1)',30000);
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
  xmlhttp1.open("POST","../jsp/cronometro_C.jsp?bitacora=<%=bitacora%>&sucursal=<%=suc%>&segmn=<%=segmn%>&tipo=<%=tipo%>", true);

  xmlhttp1.send();
  t=setTimeout('showcronometro(1)',30000);
  }

function startTime(str){
	today=new Date();
	h=today.getHours();
	m=today.getMinutes();
	s=today.getSeconds();
	m=checkTime(m);
	s=checkTime(s);
	document.getElementById('reloj').innerHTML=h+str+":"+m;
	t=setTimeout('startTime(<%=dif_hora%>)',30000);
	}





function checkTime(i)
{if (i<10) {i="0" + i;}return i;}
window.onload=function(){startTime();showvolumen(1);showcronometro(1);}



</script>





<!-- slabtext stylesheet -->
<link href="../js/slabtext/css/slabtext.css" rel="stylesheet" type="text/css" />
    

<!-- <script src="slabtext/js/jquery.slabtext.min.js"></script> -->
<script src="../js/slabtext/js/jquery.slabtext.js"></script>


<script>
    // Function to slabtext the H1 headings
    function slabTextHeadlines() {
        $("h5").slabText({
            // Don't slabtext the headers if the viewport is under 380px
            "viewportBreakpoint":280
        });
    };
    
    // Called one second after the onload event for the demo (as I'm hacking the 
    // fontface load event a bit here)
    // Please do not do this in a production environment - you should really use
    // google WebFont loader events (or something similar) for better control
    $(window).load(function() {
        // So, to recap... don't actually do this, it's nasty!
        setTimeout(slabTextHeadlines, 1);
    });
</script>  
  
  
  


</head>
 
<body id="detalle_carga-ancho">

<div align="right" id="regresar">
    <span>
        <A HREF="javascript:history.go(-1)"  class="underline-white"> &lt; Regresar</a> &nbsp; &nbsp;
    </span>
</div>


<div><h1><%=sucursal%></h1></div>


<TABLE id="infobitacora" border="0">
  <TBODY>
  <TR>
    <th id="flecha" class="padding-0"><i class="<%=flecha%>"></i><status><%=process%></status></th>
  </TR>
</TBODY>
</TABLE>


<!--  
    --------------------------------
    Mostrar Bitácora
    --------------------------------  
                                        -->
   
<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <TR>
    <th><bitacora>
      <h3 class="color-1">Bit&aacute;cora</h3></bitacora></th>
  </TR>
  <TR>
  <td style="text-align: center; vertical-align: middle;"><h5><%=bitacora%></h5></TD>
    </TR>    
</TBODY>
</TABLE>

<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <TR>
    <th><h3 class="color-1">Segmento</h3></th>
    </TR>  
  <TR>
    <TD valign="baseline"><h5><%=segmento%></h5></TD></TR>
</TBODY>
</TABLE>
    


<!--  
    --------------------------------
    Mostrar Tracto y Remolque
    --------------------------------  
                                        -->
<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <TR>
    <th colspan="3"><h3 class="color-1">Tracto</h3><tracto><%=tracto%><tracto></Th></TR>
  <TR >
  <!-- Sólo dirá Remolque, sin el 1 -->
    <td colspan="2"><h3 class="color-1">Remolque</h3></td>
    <td><h5><%=remolque1%></h5></td>
    </TR>  
    
    
  <TR id="volumenes" class="fondo-3">
    <TD valign="top">
        <h4>
            <span class="color-2">Capacidad:</span>
            <br/>  
            <span class="color-azul-paquetexpress font-size-1">
                 <strong><%=capacidad%></strong>
            </span> 
        </h4>
    </TD>
    <TD colspan="2" valign="top">
        <h4>
            <span class="color-2">Escaneado: &nbsp;&nbsp; Volumen Disp:</span>
            <span class="color-azul-paquetexpress">
              <strong>
                  <div id="txtHint" align="center"></div>
              </strong>
            </span>
       </h4>
   </TD>
  </TR>
</TBODY>
</TABLE>





<!--  
    -------------------------------------------------
    Mostrar Tiempos de Salida, Hora y Tiempo Restante
    -------------------------------------------------  
    
    <% hora_local = "<strong id='hora'></strong>";
   
       
    %>                                                       -->
    
<TABLE id="infobitacora" class="fondo-1">
 <TBODY>
  <TR>
   <th><h3 class="color-1"><%=etiqueta%></h3></th><TD><hora><%=etiqueta2%></hora></TD>
 </TR>  
   <TR>
    <td colspan="2"><h3 class="">Hora Actual</h3>&nbsp;<DIV id="reloj"></DIV>
 </td>
    </TR>  
    <%=cronometro%>  
  </TBODY>
</TABLE>


</body>
</html>