package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguro;
import dominio.TipoSeguroDao;

/**
 * Servlet implementation class servletsAgregarSeguro
 */
@WebServlet("/servletsAgregarSeguro")
public class servletsAgregarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletsAgregarSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("parameter") != null) {
	        TipoSeguroDao tip = new TipoSeguroDao();
	        SeguroDao ID= new SeguroDao();
	        String id; 
	        id=ID.UltimoId();
	        request.setAttribute("ID", id);
	        ArrayList<TipoSeguro> tipos = tip.readAllTiposSeguros();

	        request.setAttribute("Tipos", tipos);
	        
	        

			RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");   
	        rd.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
