<%@page import="dominio.TipoSeguro"%>
<%@page import="dominio.TipoSeguroDao"%>
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
    <a href="servletsAgregarSeguro?parameter=1">Agregar Seguro</a>
    <a href="servletSeguro?Param=1">Listar Seguros</a>
    
	</nav>
	<br>
	<h1><b>Agregar Seguros</b></h1>
	<br>
	<form action="servletSeguro" method="post">
  <div class="form-group">
       <% String ID=" ";
	if(request.getAttribute("ID")!=null){
		 ID=(String)request.getAttribute("ID");
	}
	%>
    <label class="form-label">Id Seguro:</label>
    <span>
        <%=ID%>
    </span> 
  </div>
	<div class="form-group">
            <label class="form-label">Descripción:</label>
            <input type="text" name="descripcion" class="form-field" required />
    </div>	
	
    Tipo de Seguros:&nbsp;    
<select name="tipoSeguro" id="tipoSeguro">

            <% 
            
        	ArrayList<TipoSeguro> Tipos = null;
        	if(request.getAttribute("Tipos")!=null)
        	{
        		Tipos = (ArrayList<TipoSeguro>) request.getAttribute("Tipos");
        	}
 // Supongo que esta función devuelve una lista de tipos de seguros
                
                // Iterar sobre la lista y crear los <option> dinámicamente
                for (TipoSeguro tipo : Tipos) {
            %>
                <option value="<%= String.valueOf(tipo.getIdTipo()) %>"><%= tipo.getDescripcion() %></option>
            <% 
                } 
            %>
        </select>
</body>
</html>