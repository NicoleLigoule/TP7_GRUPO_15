<%@page import="dominio.Seguro"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav>
    <a href="Inicio.jsp">Inicio</a>
    <a href="AgregarSeguro.jsp">Agregar Seguro</a>
    <a href="servletSeguro?Param=1">Listar Seguros</a>
    
	</nav>


<% 
	ArrayList<Seguro> listaSeguros = null;
	if(request.getAttribute("listaSeguros")!=null)
	{
		listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaSeguros");
	}

 %>


<table border="1" id="table_id" class="display" >
    <thead>
        <tr>
            <th>ID Seguro</th>
            <th>Descripci�n Seguro</th>
            <th>Descripci�n Tipo Seguro</th>
            <th>Costo Contrataci�n</th>
            <th>Costo M�ximo Asegurado</th>
        </tr>
    </thead>
    <tbody>
       <%  if(listaSeguros != null)
		for(Seguro seg : listaSeguros) 
		{
	%>
		<tr>  
		    <form name="formSeguros">
				<td><%=seg.getIdSeguro() %></td> 
				<td><%=seg.getDescripcion() %></td>   
				<td><%=seg.getTipoSeguro() %></td>
				<td><%=seg.getCostoContratacion() %></td>   
				<td><%=seg.getCostoAsegurado() %></td>
			</form> 
		</tr>
	<%  } %>
    </tbody>
</table>


</body>
</html>