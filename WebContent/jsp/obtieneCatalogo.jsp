<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,bean.JavBranchRecords"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	String clientId = "18025";
	JavBranchRecords branchRecords = new JavBranchRecords();
	ArrayList valores = branchRecords.getLovRecords(clientId);
	System.out.println("valores " + valores);
%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form>
		<table>
			<thead>
				<tr>
					<th>Clave</th>
					<th>Descripción</th>
				</tr>
			</thead>
			<tbody>
<%	
	String clave = "";
	String descripcion = "";
	HashMap site = null;
	if (valores!=null) {
		for (int i=0;i<valores.size();i++) {
			site = (HashMap) valores.get(i);			
			clave = site.get("siteId").toString();
			descripcion = site.get("siteName").toString();
%>
				<tr>
					<td><%=clave%></td>
					<td><%=descripcion %></td>
				</tr>
<%			
		}
	}			
%>
			
			</tbody>
		</table>
	</form>
</body>
</html>