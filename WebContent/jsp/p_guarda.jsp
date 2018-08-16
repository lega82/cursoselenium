<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page language="java" 	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa,mx.com.paquetexpress.informativa.core.DisplayHandler"%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<style type="text/css">
    html, body, div, iframe { margin:0; padding:0; }
    iframe { display:block; border:none; }

</style>


 <%
     String bitacora = request.getParameter("bitacora");
 	 String tipo = request.getParameter("process");
     String segmento = request.getParameter("segmento");
     String caja = request.getParameter("caja");
     String pantalla = request.getParameter("pantalla");
     String sucursal = request.getParameter("sucursal");
     String p_asig = request.getParameter("p_asig");
     
     
     
     Informativa bitacoras = new Informativa();
     /*
     bitacoras.pro_ins_pantalla(bitacora,sucursal,pantalla);
     bitacoras.pro_upd_pantalla(pantalla,bitacora,tipo,sucursal,segmento,caja);
     */

     DisplayHandler displayHandler = new DisplayHandler();

     String rs = displayHandler.handlerDisplay(bitacora, tipo, caja, segmento, sucursal, pantalla);
    
     
     String bitacoraA="";
     String tipoA="";
     String cajaA="";
     String segmentoA="";
     String[] parametros;
     bitacoraA = rs;
     String on="ON";
     String var1="I";
     
     bitacoras.pro_null_pantalla(sucursal,pantalla);
     bitacoras.pro_ins_pantalla(bitacora,sucursal,pantalla);
     bitacoras.pro_upd_pantalla(pantalla,bitacora,tipo,sucursal,segmento,caja);
     
     if (!var1.equals("I")){
    	
    	%>
    <script type="text/javascript">
    var mensaje="La Pantalla "
    var str2=" esta Asignada ala bitacora "
    var str3="	Desea Reemplazarla S / N ?"
    var s="<%=pantalla%>";
    var bita ="<%=bitacoraA%>";
	var r = mensaje.concat(s,str2,bita,str3)
    var x=window.confirm(r)
 
    
	if (x)
   		
    	window.location="../jsp/p_confirma.jsp?bitacora=<%=bitacora%>&process=<%=tipo%>&segmento=<%=segmento%>&caja=<%=caja%>&pantalla=<%=pantalla%>&sucursal=<%=sucursal%>&confirma=Y&p_asig=<%=p_asig%>";
	else
	    window.location="../jsp/p_confirma.jsp?bitacora=<%=bitacora%>&process=<%=tipo%>&segmento=<%=segmento%>&caja=<%=caja%>&pantalla=<%=pantalla%>&sucursal=<%=sucursal%>&confirma=N&p_asig=<%=p_asig%>";

	</script>

     <%
     }else{
    	 
    	 
    	 if(!p_asig.isEmpty()){
    		 bitacoras.pro_upd_busy(sucursal,p_asig);
        	
         } 
    	 bitacoras.pro_screen_off(on,sucursal,pantalla);
    	 
     }

     


     /*
     String busy = displayHandler.handlerDisplayBusy(bitacoraA, tipoA, cajaA, segmentoA, bitacora, tipo, caja, segmento, sucursal, pantalla);
     */


 %>
 <meta http-equiv="refresh" content="0; url=../jsp/p_admin.jsp?sucursal=<%=sucursal%>">

</head>

<body>





</body>
</html>