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
        
        String descripcion = request.getParameter("descripcion");
        int tipoSeguro = Integer.parseInt(request.getParameter("tipoSeguro"));
        double costoContratacion = Double.parseDouble(request.getParameter("costoContratacion"));
        double costoAsegurado = Double.parseDouble(request.getParameter("costoMaximo"));

       
        Seguro seguro = new Seguro();
        seguro.setDescripcion(descripcion);
        seguro.setTipoSeguro(tipoSeguro);
        seguro.setCostoContratacion(costoContratacion);
        seguro.setCostoAsegurado(costoAsegurado);

        
        SeguroDao seguroDao = new SeguroDao();
        boolean insertado = seguroDao.insert(seguro);

        
        if (insertado) {
            TipoSeguroDao tip = new TipoSeguroDao();
            SeguroDao ID = new SeguroDao();
            String id = ID.UltimoId();
            request.setAttribute("ID", id);
            request.setAttribute("Tipos", tip.readAllTiposSeguros());
            request.setAttribute("correcto", "El seguro se ha agregado correctamente.");
            RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
            rd.forward(request, response); 
        } else {
            request.setAttribute("error", "Error al agregar el seguro.");
            RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
            rd.forward(request, response);
        }
    }
}
