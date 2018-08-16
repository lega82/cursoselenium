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
<!--
<link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />  
-->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<!-- 
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
 -->
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">


<script src="../js/devicejs/lib/device.js"></script>


<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- 
<script src="../jQueryAssets/jquery.modern-blink.js" type="text/javascript"></script>

<script type="text/javascript" src="../js/blink.js"></script>
 -->



<!--  Estilos para pantallas angostas -->

<style type="text/css">

@media (max-width: 300px) {
}

</style>


<!--
<style type="text/css" media="screen">
    @font-face {
        font-family: 'Roboto';
        src: url('/windows/fonts/roboto-light.ttf');
    }

   h3,
   h4,
   tracto {
    font-family: Roboto !important;
font-size: 80%;
}
</style>
-->


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
 String salidaR = request.getParameter("salidaR");
 String llegadaE = request.getParameter("llegadaE");
 String capacidad = request.getParameter("capacidad_caja");
 String suc = request.getParameter("suc");
 String segmn = request.getParameter("segmn");
 String cronometro = "";
 String oper1 = request.getParameter("oper1");
 String oper2 = request.getParameter("oper2");
 String oper3 = request.getParameter("oper3");
 
 
 Informativa bitacoras = new Informativa();
 String dif_hora = bitacoras.get_hora(suc);
 
 String etiqueta="";
 String etiqueta2="";
 String flecha="";
 String hora_local="";

 
 
 
 

 
%>

 <script language="javascript" type="text/javascript">


 



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
 
<body id="detalle_bitacora-tripulacion"> 

  <!-- sólo mostramos esto en iPad -->
  <!-- eliminado por lo pronto ya que todo se abre en tabs -->
  <!--
<div align="right" id="regresar">

    <span>
        <A HREF="javascript:history.go(-1)"  class="underline-white"> &lt; Regresar</a> &nbsp; &nbsp;
    </span>
</div>
-->

<TABLE id="infobitacora" border="0">
  <TBODY>
  <TR>
    <th class="padding-0">
        <div class="txt-blanco"><%=sucursal%></h1>
</th>
  </TR>
</TBODY>
</TABLE>


<!--  
    ===============================
    Mostrar Bitácora
    ===============================  
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
    ===============================
    Mostrar Tracto y Remolque
    ===============================  
                                        -->
<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <TR>
    <th colspan="3"><h3 class="color-1">Tracto</h3>
        <tracto class=""><%=tracto%><tracto>
    </Th>
  </TR>
  <TR >
  <!-- Sólo dirá Remolque, sin el 1 -->
    <td colspan="2"><h3 class="color-1">Remolque</h3></td>
    <td class="txt-blanco"><h5><%=remolque1%></h5></td>
    </TR>  
    
    
  
</TBODY>
</TABLE>


<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <TR>
    <th colspan="3"><h3 class="color-1">Salida</h3>
    </Th>
  </TR>
  <tr>
    <td>
  <h6 class="txt-blanco"><%=salidaR%><h6>
    </td>
  </tr>
</TBODY>
</TABLE>




<!-- Tripulacion -->
<TABLE id="infobitacora" class="fondo-1">
  <TBODY>
  <tr>
    <th colspan="4"><h3 class="color-1">Tripulacion</h3></th></tr>
     <tr>
    <td colspan="3"><h6 class="color-1">Titular</h6></td>
    <td colspan="3"><h6 class="txt-blanco"><%=oper1%></h6></td></tr>
    <tr>
    <td colspan="3"><h6 class="color-1">Secundario</h6>
    <td colspan="3"><h6 class="txt-blanco"><%=oper2%></h6></td></tr>
    <tr>
    <td colspan="3"><h6 class="color-1">Admr. de Carga</h6>
    <td colspan="3"><h6 class="txt-blanco"><%=oper3%></h6></td></tr>
  </TBODY>
</TABLE><!-- /Tripulacion -->









</body>
</html>