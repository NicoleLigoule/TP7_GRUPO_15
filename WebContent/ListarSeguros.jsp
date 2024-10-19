<%@page import="dominio.Seguro"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Seguros</title>
</head>
<body>
	<nav>
    <a href="Inicio.jsp">Inicio</a>
    <a href="servletsAgregarSeguro?parameter=1">Agregar Seguros</a>
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
            <th>Descripción Seguro</th>
            <th>Descripción Tipo Seguro</th>
            <th>Costo Contratación</th>
            <th>Costo Máximo Asegurado</th>
        </tr>
    </thead>
    <tbody>
       <%  if(listaSeguros != null)
		for(Seguro seg : listaSeguros) 
		{
	%>
		<tr>
		    <form name="formSeguros">
				<td style="text-align: center;"><%=seg.getIdSeguro() %></td>  
				<td style="text-align: center;"><%=seg.getDescripcion() %></td>   
				<td style="text-align: center;"><%=seg.getTipoSeguroDescripcion() %></td>
				<td style="text-align: center;"><%=seg.getCostoContratacion() %></td>   
				<td style="text-align: center;"><%=seg.getCostoAsegurado() %></td>
			</form> 
		</tr>
	<%  } %>
    </tbody>
</table>


</body>
</html>