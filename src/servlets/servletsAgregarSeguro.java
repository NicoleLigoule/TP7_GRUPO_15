package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguroDao;

@WebServlet("/servletsAgregarSeguro")
public class servletsAgregarSeguro extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletsAgregarSeguro() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoSeguroDao tip = new TipoSeguroDao();
        SeguroDao ID = new SeguroDao();
        String id = ID.UltimoId();
        request.setAttribute("ID", id);
        request.setAttribute("Tipos", tip.readAllTiposSeguros());
        RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger los datos del formulario
        String descripcion = request.getParameter("descripcion");
        int tipoSeguro = Integer.parseInt(request.getParameter("tipoSeguro"));
        double costoContratacion = Double.parseDouble(request.getParameter("costoContratacion"));
        double costoAsegurado = Double.parseDouble(request.getParameter("costoMaximo"));

        // Crear un objeto Seguro y asignarle los valores
        Seguro seguro = new Seguro();
        seguro.setDescripcion(descripcion);
        seguro.setTipoSeguro(tipoSeguro);
        seguro.setCostoContratacion(costoContratacion);
        seguro.setCostoAsegurado(costoAsegurado);

        // Insertar el seguro en la base de datos
        SeguroDao seguroDao = new SeguroDao();
        boolean insertado = seguroDao.insert(seguro);

        // Redirigir según el resultado de la inserción
        if (insertado) {
            response.sendRedirect("servletSeguro?Param=1"); // Redirigir al listado de seguros
        } else {
            request.setAttribute("error", "Error al agregar el seguro.");
            RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
            rd.forward(request, response);
        }
    }
}
