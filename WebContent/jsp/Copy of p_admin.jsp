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


<script src="../js/devicejs/lib/device.js"></script>



 <%
 	String variable = request.getParameter("sucursal");
  
  
 %>

<%
	Informativa bitacoras = new Informativa();
	ArrayList valores = bitacoras.get_bita_asig(variable);
	String nombre_suc = bitacoras.getnombre(variable);
	ArrayList display = bitacoras.get_display(variable);
	
	
	 
	boolean retval = valores.isEmpty();
	if (retval == true) {
	      System.out.println("list is empty");
	    }


String disp_off="";
String pantalla_off="";
String nombre_off="";

HashMap disp = null;
HashMap site = null;
%>
</head>
<body>
    
<br />

  
<TABLE  width='90%' border="0"  align="center" cellpadding="5" cellspacing="5" id="bitacorasensucursal" class="enproceso">
  <tr>
    <th colspan="8" class="centered"> Bit&aacute;coras en Proceso de Carga/Descarga en Sucursal:    <%= nombre_suc %>      </th>
    
    
  </tr>
	<tr><th>Asignado</th><th class="fondo-1">Bit&aacute;cora</th>
	<th>Tipo</th>
	<th>Tracto</th>
	<th>Remolque</th>
	<th>Segmento</th>
	<th>Anden</th>
	<th></th>
	</tr>
	<tr>
	<th colspan="8">
	<select name="pantalla" >
					<option value="">&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;&ndash;</option>
							<%
			for (int n=0;n<display.size();n++) {
				disp = (HashMap) display.get(n);
				pantalla_off = disp.get("disp_id").toString();
				nombre_off   = disp.get("disp_name").toString();
			

			%>
			<option value=<%=pantalla_off%>><%=nombre_off%>
			
			<%
			}
				
		%>
			</select>
			<input type="submit" value="Apagar"></th>		</tr>
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
			 
			
			if ( renglon.equals( "tr")){
				 fila="tr"; 
				 renglon="tr2";
			   }else{
				  fila="tr class='alt'";
				  renglon="tr";
			   }
			
			if (status.equals("PROCESO")){
				%>
				<<%=fila%>>
				   <FORM action="../jsp/p_guarda.jsp" method="post" name="format">
				    
				    
				    
				    <%=columna%> <%=npantalla%> </td>
				    
				    
				    <td><%=clave%></td>
					<td><%=tipo%></td>
					<td> <%=tracto%></td>
					 <td><%=caja%></td>
					
					<td> <%=segmento%></td>
					<td>
					<select name="pantalla" >
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
				<input type="hidden" name="segmento" value=<%=segmento%>>	
				<input type="hidden" name="caja" value=<%=caja%>>
				<input type="hidden" name="sucursal" value=<%=variable%>>
				<input type="hidden" name="p_asig" value=<%=npantalla%>>
				<td> <input type="submit" value="Asignar"></td>
				
				
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