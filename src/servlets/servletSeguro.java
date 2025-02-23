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
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TipoSeguroDao tip = new TipoSeguroDao();
		request.setAttribute("Tipos", tip.readAllTiposSeguros());
		
		if(request.getParameter("btnFiltrar")!=null)
		{
			SeguroDao sdao = new SeguroDao();
			
			int tipoSeguro = Integer.parseInt(request.getParameter("tipoSeguro"));
            ArrayList<Seguro> listaSeg = sdao.readFiltro(tipoSeguro);            
            
			request.setAttribute("listaSeguros", listaSeg);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
	        rd.forward(request, response);
		}
		if(request.getParameter("Param")!=null)
		{

			SeguroDao sdao = new SeguroDao();
			
            ArrayList<Seguro> listaSeg = sdao.readAll();            
            
			request.setAttribute("listaSeguros", listaSeg);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
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
