<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<script>

function showSucursal(str)
{
var xmlhttp;
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
xmlhttp.open("GET","../jsp/p_llegadas_trn.jsp?sucursal="+str);

xmlhttp.send();
}
</script>



<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<!--
<link href="../estilos/foundation.css" rel="stylesheet" type="text/css" />  
-->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">


<link href="../js/select2/select2.css" rel="stylesheet"/>

<script src="../jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>

<script type="text/javascript" src="../js/select2/select2.js"></script>


<script src="../js/devicejs/lib/device.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bitacoras en Sucursal </title>
</head>

<%
	Informativa bitacoras = new Informativa();
	ArrayList valores = bitacoras.get_sucursales_all();




	boolean retval = valores.isEmpty();
	if (retval == true) {
	      System.out.println("list is empty");
	    }

	


%>



<body>

<br />
<table border='0'  align='center' width='90%'  cellspacing='0' cellpadding='0'>
  <tr>
    <td width="40%" align='center' valign="middle">

		<form action=".">

<%
	String sucur = "";
	String stus="P";
	String color="";
	String tipo="";
	String nombre = "";
	String status = "";
	String segm = "";
	String segmento = "";
	String tracto = "";
	String renglon="tr";
	String fila="";




	HashMap site = null;
	if (valores!=null) {
		%>
		<select name="sucursal" onchange="showSucursal(this.value)" id="select2aqui" >
		
		
		<option value="">Selecciona Sucursal..</option>
		<option value="A">ALLS..</option>
			
		<%
		for (int i=0;i<valores.size();i++) {
			site = (HashMap) valores.get(i);
			sucur = site.get("sucursal").toString();
			nombre   = site.get("nombre").toString();

			%>

				<option value=<%=sucur%>><%=nombre%>

<%
			}

		%>
			</select>

		<%
		}
	%>

	</form>

	</td>

    <td width="40%" align='center'  valign=middle>
          <div class="bitacoras-titulo">Pantallas Informativas</div>
    </td>
    <td width="20%" align='center'  valign=middle>
         <!-- LOGO -->
           <div id="technique-one">
            <span>PAQUETEXPRESS</span>
          </div>
    </td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="top">
      <br />
      <div id="txtHint" align="left"></div>
    </td>
  </tr>
</table>



<script>
    $(document).ready(function() { $("#select2aqui").select2(); });
</script>



</body>
</html>