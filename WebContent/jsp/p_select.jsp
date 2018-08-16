<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	ArrayList valores = bitacoras.getbitacoras(variable);
	String nombre_suc = bitacoras.getnombre(variable);
	
	
	 
	boolean retval = valores.isEmpty();
	if (retval == true) {
	      System.out.println("list is empty");
	    }



	
%>
</head>
<body>
    
<br />

  
<TABLE  width='90%' border="0"  align="center" cellpadding="5" cellspacing="5" id="bitacorasensucursal" class="enproceso">
  <tr>
    <th colspan="6" class="centered"> Bit&aacute;coras en Proceso de Carga/Descarga en Sucursal:    <%= nombre_suc %>      </th>
    
    
  </tr>
	<tr><th class="fondo-1">Bit&aacute;cora</th>
	<th>Tipo</th>
	<th>Estatus</th>
	<th>Tracto</th>
	<th>Caja</th>
	<th>Segmento</th>
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
				    <td><a href="../jsp/p_tipo.jsp?bita=<%=clave%>&tipo=<%=descripcion%>&sucursal=<%=sucur%>&est=<%=stus%>&segm=<%=segm%>&caja=<%=caja%>" class="texto_normal" target="_blank"><font color="#000080"> <%=clave%></font></a></td>
					<td><%=tipo%></td>
					<td><font color=<%=color%>> <%=status%></font></td>
					<td> <%=tracto%></td>
					<td><%=caja%></td>
					<td> <%=segmento%></td>
					

					
				</tr>
				
<%					
			}
						

		}
	}		
	
	%>

			
			
		</table>

</body>
</html>