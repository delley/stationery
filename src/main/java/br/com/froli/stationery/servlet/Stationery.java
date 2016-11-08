package br.com.froli.stationery.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Stationery
 */
//@WebServlet(name = "stationery", urlPatterns = { "/entrada" })
public class Stationery extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Stationery.class.getName());
       
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/index.html");
		if(dispatcher == null) {
			LOGGER.log(Level.INFO, "[Stationery] dispatcher = null");
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
		} else {
			LOGGER.log(Level.INFO, "[Stationery] dispatcher OK");
			// TODO: verificar o uso dessa variavels
			HttpSession session = request.getSession();
			dispatcher.forward(request, response);
		}
	}

	@Override
	public String getServletInfo() {
		return "Servlet que direciona o cliente para página de abertura da loja";
	}
}
