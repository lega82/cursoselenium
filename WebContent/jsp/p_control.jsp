<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<style type="text/css">
    html, body, div, iframe { margin:0; padding:0;background-color: black; }
    iframe { display:block; border:none; }

</style>


</head>

<body>
 <%
 	String sucursal = request.getParameter("suc");
    String pantalla = request.getParameter("disp");
    
    
    Informativa bitacoras = new Informativa();
    ArrayList valores = bitacoras.getpantalla(sucursal,pantalla);
    
    String power = bitacoras.get_off_disp(sucursal,pantalla);
    
    
    
    
    
    int valor = valores.size();
    
    String clave = "";
	String stus="P";
	String color="";
	String tipo="";
	String status = "";
	String segm = "";
	String segmento = "";
	String tracto = "";
	String caja = "";
	String renglon="tr";
	String fila="";
    
    if (valor > 0) {
    	
    	
    	HashMap site = null;
    	if (valores!=null) {
    		for (int i=0;i<valores.size();i++) {
    			site = (HashMap) valores.get(i);			
    			clave = site.get("viaje").toString();
    			tipo = site.get("process").toString();
    			tracto = site.get("tracto").toString();
    			caja = site.get("caja").toString();
    			segmento = site.get("segmento").toString();
    			segm = site.get("segno").toString();
    			status = site.get("estatus").toString();
    			
    		}
    	}
    }
    
    if (status.equals("PROCESO")){
    	
    	 %>
    	<iframe width="320" height="192" src="../jsp/p_tipo_LED.jsp?bita=<%=clave%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=stus%>&segm=<%=segm%>&numpant=<%=pantalla%>&caja=<%=caja%>" style="border:none"  frameborder="0" marginwidth="0" marginheight="0" scrolling="no" onload="" allowtransparency="false" name="iframe_a" id="iframe" onload='javascript:resizeIframe(this);' name="control"></iframe>
    	<%
    }
    else{
    	
    	if (power.equals("OFF")){
        	System.out.println("entro power .... ");
       	 %>
       	<iframe width="320" height="192" src="../jsp/p_screenblack.jsp?sucursal=<%=sucursal%>&screen=<%=pantalla%>" style="border:none"  frameborder="0" marginwidth="0" marginheight="0" scrolling="no" onload="" allowtransparency="false" name="iframe_a" id="iframe" onload='javascript:resizeIframe(this);' name="control"></iframe>
       	<%
       }else{
    	
    	 %>
     	<iframe width="320" height="192" src="../jsp/p_llegadas_LED.jsp?sucursal=<%=sucursal%>&numpant=<%=pantalla%>" style="border:none"  frameborder="0" marginwidth="0" marginheight="0" scrolling="no" onload="" allowtransparency="false" name="iframe_a" id="iframe" onload='javascript:resizeIframe(this);' name="control"></iframe>
     	<%   
       }
    	
    }
 %>


</body>
</html>