<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta name="author" content="Jesus Monzón Heredia ">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width">
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="../favicon/favicon-192px-mercancia-en-piso-icon-trazabilidad-admin.png">

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>Bitacoras en Sucursal </title>
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../estilos/hoja.css">
   <style>

 .onoffswitch {
    position: relative; width: 90px;
    -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
}
.onoffswitch-checkbox {
    display: none;
}
.onoffswitch-label {
    display: block; overflow: hidden; cursor: pointer;
    border: 2px solid #999999; border-radius: 20px;
}
.onoffswitch-inner {
    display: block; width: 200%; margin-left: -100%;
    transition: margin 0.3s ease-in 0s;
}
.onoffswitch-inner:before, .onoffswitch-inner:after {
    display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
    font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
    box-sizing: border-box;
}
.onoffswitch-inner:before {
    content: "ON";
    padding-left: 10px;
    background-color: #48A341; color: #FFFFFF;
}
.onoffswitch-inner:after {
    content: "OFF";
    padding-right: 10px;
    background-color: #FF0808; color: #FFFFFF;
    text-align: right;
}
.onoffswitch-switch {
    display: block; width: 22px; margin: 4px;
    background: #FFFFFF;
    position: absolute; top: 0; bottom: 0;
    right: 56px;
    border: 2px solid #999999; border-radius: 20px;
    transition: all 0.3s ease-in 0s;
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
    margin-left: 0;
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
    right: 0px;
}

  </style>

<script src="../js/devicejs/lib/device.js"></script>

<SCRIPT type="text/javascript">
function valida()

{
    if (asigna.pantalla.options[asigna.pantalla.selectedIndex].value == "0") {

    alert("Por favor, seleccione una opción válida");

    return false;
    }

    return true;
}

function mensaje(){
   
    var pantalla = document.getElementById("seleccion").value;
    
    if (pantalla.indexOf("ON") >= 0) {
    	var r = confirm("Apagar Pantalla S/N");
    	document.getElementById("ON").value="OFF";
    }else{
    	var r = confirm("Encender Pantalla S/N");
    	document.getElementById("ON").value="ON";
    }
    
    
    if (r == true) {
      document.getElementById("form1").submit();
     } 
    else {
   alert("You pressed Cancel!");
	}
    
  }
  
  function capturar() {
       // obtenemos e valor por el numero de elemento
       var porElementos=document.forms["form1"].elements[1].value;
       // Obtenemos el valor por el id
       var porId=document.getElementById("seleccion").value;
       
       // Obtenemos el valor por el Nombre
       //var porNombre=document.getElementsByName("seleccion")[0].value;
       // Obtenemos el valor por el tipo de tag
       //var porTagName=document.getElementsByTagName("select")[0].value;
       // Obtenemos el valor por el nombre de la clase
       //var porClassName=document.getElementsByClassName("formulario_select")[0].value;

       //alert("valores" + porId );


       if (porId.indexOf("ON") >= 0) {
       //alert(" entro  ");
       document.getElementById("resultado").innerHTML= "\
       <input type='checkbox' name='onoffswitch' class='onoffswitch-checkbox' id='myonoffswitch' checked value='ON' onclick='mensaje();' > \
       <label class='onoffswitch-label' for='myonoffswitch'> \
       <span class='onoffswitch-inner'></span> \
       <span class='onoffswitch-switch'></span> \
       </label>";
       
       
       
       }else{
       document.getElementById("resultado").innerHTML= "\
       <input type='checkbox' name='onoffswitch' class='onoffswitch-checkbox' id='myonoffswitch' value='OFF' onclick='mensaje();'  > \
       <label class='onoffswitch-label' for='myonoffswitch'> \
       <span class='onoffswitch-inner'></span> \
       <span class='onoffswitch-switch'></span> \
       </label>";
       }

}
</script>

 <%
 	String variable = request.getParameter("sucursal");
 %>

<%
	Informativa bitacoras = new Informativa();
	ArrayList valores = bitacoras.get_bita_asig(variable);
	String nombre_suc = bitacoras.getnombre(variable);
	ArrayList display = bitacoras.get_display(variable);
	ArrayList displayON = bitacoras.get_display_ONOFF(variable);
	
	boolean retval = valores.isEmpty();
	if (retval == true) {
	      System.out.println("list is empty");
	    }


String disp_off="";
String pantalla_off="";
String nombre_off="";

HashMap disp = null;
HashMap dispON = null;
HashMap site = null;

%>


<meta http-equiv="refresh" content="30; url=../jsp/p_admin.jsp?sucursal=<%=variable%> ">

</head>
<body>
    
<br />
<FORM action="../jsp/p_screenoff.jsp" method="post" name="format"  id="form1">
  
<TABLE  width='95%' border="0"  align="center" cellpadding="5" cellspacing="5" id="bitacorasensucursal" class="enproceso">
  <tr>
    <th colspan="9" class="centered"> Bit&aacute;coras en Proceso de Carga/Descarga en Sucursal:    <%= nombre_suc %>      </th>
    
    
  </tr>
	<tr><th>Asignado</th><th class="fondo-1">Bit&aacute;cora</th>
	<th>Tipo</th>
	<th>Tracto</th>
	<th>Remolque</th>
	<th>Segmento</th>
	<th>Anden</th>
	<th>
	
	
	
	Screen Off:&nbsp;&nbsp;<select name="screen" id="seleccion" onchange="capturar();" >
					<option value="" >&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;</option>
							<%
			for (int n=0;n<displayON.size();n++) {
				dispON = (HashMap) displayON.get(n);
				pantalla_off = dispON.get("disp_id").toString();
				nombre_off   = dispON.get("disp_name").toString();
			

			%>
			
			<option value=<%=pantalla_off%>><%=nombre_off%>
			<%
			}
				
		%>
			</select></th>
			
			<input type="hidden" name="encendido" id="ON">
			<input type="hidden" name="sucursal" value=<%=variable%>>
			<th><div class="onoffswitch" id="resultado"></div>
			</th>	
			</form>
	</tr>
	
	
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
	String pantalla="";
	String nombre="";
	String npantalla="";
	String columna="";
	
	
	
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
			npantalla = site.get("numpantalla").toString();
			
			
			if(npantalla.isEmpty()){
				
				columna ="<td>";
			}else{
				columna ="<td class='asignado'>";
			}
				
			
			if ( descripcion.equals( "C1" ) ){
				
				tipo ="CARGA";
				color ="#000080";
			}else{
				tipo ="DESCARGA";
				color ="#a52a2a";
			}
			 
			int segundos=0;
			int horas = 0;
			int minutos = 0;
			
			if ( descripcion.equals( "C1" ) & status.equals("PROCESO") ){	
				System.out.println("ENTRO 1");
				
			    String cronometro_carga = bitacoras.get_cronometro(clave,variable,segm);
			    segundos = Integer.parseInt(cronometro_carga);
		        horas = segundos/3600;
		        minutos = (segundos - horas*3600)/60;
			  //  System.out.println("bitacora "+clave+"-----"+"cronometro_carga  " +horas +"horas : minutos " +minutos);
			
			  if (horas < 1 & minutos <= 30) {
				//System.out.println("horas cero poner rojo");
				 fila="tr class='trafficlightred' ";
				
			     }else{
			     fila="tr class='' ";
			     }
			 

			  if (horas < 1 & minutos <= 60 & minutos > 30  ) {
					//System.out.println("horas cero poner amarillo");
					 fila="tr class='alt trafficlightyellow' ";
					
				     }
			  
			 
			
			}else{
				fila="tr class='' ";
			}
			
			
			//if ( renglon.equals( "tr")){
		    //		 fila="tr"; 
			//	 renglon="tr2";
			//   }else{
			//	  fila="tr class='alt'";
		   //		  renglon="tr";
		//	   }
			
			
			if (status.equals("PROCESO")){
				%>
				<<%=fila%>>
				   <FORM action="../jsp/p_guarda.jsp" method="post" name="format" >
				    
				    
				    
				    <%=columna%> <%=npantalla%> </td>
				    
				    
				    <td><%=clave%></td>
					<td><%=tipo%></td>
					<td> <%=tracto%></td>
					 <td><%=caja%></td>
					
					<td> <%=segmento%></td>
					<td >
					<select name="pantalla" required>
					<option value="">&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;</option>
							<%
			for (int n=0;n<display.size();n++) {
				disp = (HashMap) display.get(n);
				pantalla = disp.get("disp_id").toString();
				nombre   = disp.get("disp_name").toString();
			

			%>
			<option value=<%=pantalla%>><%=nombre%>
			
			<%
			}
				
		%>
			</select>
			
			
					</td>
				<input type="hidden" name="bitacora" value=<%=clave%>>	
		    	<input type="hidden" name="process" value=<%=descripcion%>>	
				
				<input type="hidden" name="p_asig" value=<%=npantalla%>>
				<td colspan="2"> <input type="submit" value="Asignar"  ></td>
				
				
				</form>
				</tr>
				
<%				
			
			}
						

		}
	}		
	
	%>

			
			
		</table>

</body>
</html>