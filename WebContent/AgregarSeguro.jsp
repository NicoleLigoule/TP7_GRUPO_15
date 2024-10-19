<%@page import="dominio.TipoSeguro"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    nav a {
        margin-right: 15px;
        text-decoration: none;
    }
    h1 {
        /* Color por defecto */
    }
    .form-group {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
    }
    .form-label {
        flex: 0 0 150px;
        font-weight: bold;
    }
    .form-field {
        flex: 0,5 50%;
        padding: 8px;
    }
    .button-group {
        margin-top: 5px;
        margin-left: 150px;
    }
    input[type="submit"] {
        padding: 10px 15px;
        border: 1px solid black;
        cursor: pointer;
        width: 100px;
        margin-bottom: 5px;
    }
    input[type="submit"]:first-child {
        margin-left: 0;
    }
</style>
</head>
<body>
	<nav>
    <a href="Inicio.jsp" style="text-decoration: underline;">Inicio</a>
    <!-- <form action="servletsAgregarSeguro" method="post"> -->
    <form action="servletsAgregarSeguro" method="post" style="display: inline;">
        <button type="submit" style="background: none; border: none; color: #800080; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">
        Agregar Seguros</button>
    <a href="servletSeguro?Param=1" style="text-decoration: underline;">Listar Seguros</a>
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
	<div class="form-group">
            <label class="form-label">Tipo de Seguro:</label>
            <select name="tipoSeguro" id="tipoSeguro" required>

            <% 
            
        	ArrayList<TipoSeguro> Tipos = null;
        	if(request.getAttribute("Tipos")!=null)
        	{
        		Tipos = (ArrayList<TipoSeguro>) request.getAttribute("Tipos");
        	}
 
                // Iterar sobre la lista y crear los <option> dinámicamente
                for (TipoSeguro tipo : Tipos) {
            %>
                <option value="<%= String.valueOf(tipo.getIdTipo()) %>"><%= tipo.getDescripcion() %></option>
            <% 
                } 
            %>
        </select>
        </div>
        
         <div class="form-group">
            <label class="form-label">Costo contratación:</label>
            <!-- <input type="text" name="costoContratacion" class="form-field" required /> -->
             <input type="number" name="costoContratacion" class="form-field" step="0.01" min="0" required />
        </div>

        <div class="form-group">
            <label class="form-label">Costo Máximo Asegurado:</label>
            <!-- <input type="text" name="costoMaximo" class="form-field" required /> -->
             <input type="number" name="costoMaximo" class="form-field" step="0.01" min="0" required />
            
        </div>

        <div class="button-group">
            <input type="submit" name="btnAgregarSeguro" value="Aceptar" />
        </div>
  
   </form>
   
   
   
</body>
</html>