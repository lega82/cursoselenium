<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">
  function resizeIframe(obj) {
    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
  }
</script>

</head>
 <%
 String bitacora = request.getParameter("bita");
 String tipo = request.getParameter("tipo");
 String sucursal = request.getParameter("sucursal");
 String status = request.getParameter("est");
 String segmno = request.getParameter("segm");
 String pantalla = request.getParameter("numpant");
 String caja = request.getParameter("caja");
 

 System.out.println("caja " + caja);
 
%>
<body>
 <%
if ( tipo.equals( "C1" ) && (status.equals( "P" ))   ){ 
	 %>
    out.println("<meta http-equiv='refresh' content='0; url=../jsp/p_carga.jsp?bita=<%=bitacora%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=status%>&segm=<%=segmno%>&caja=<%=caja%>&numpant=<%=pantalla%>' >");
	
	 <%
	
	}
 
 
 if ( tipo.equals( "D1" ) && (status.equals( "P" ))   ){ 
	 %>
	  out.println("<meta http-equiv='refresh' content='0; url=../jsp/p_descarga.jsp?bita=<%=bitacora%>&tipo=<%=tipo%>&sucursal=<%=sucursal%>&est=<%=status%>&segm=<%=segmno%>&caja=<%=caja%>&numpant=<%=pantalla%>' >");
	 
	 <%
	
	}
 
 %>


</body>
</html>