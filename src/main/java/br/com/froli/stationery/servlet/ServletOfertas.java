package br.com.froli.stationery.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.froli.stationery.db.JPAUtil;
import br.com.froli.stationery.model.Carrinho;
import br.com.froli.stationery.model.Produto;
import br.com.froli.stationery.repository.Produtos;

/**
 * Servlet implementation class ServletCarrinho
 */
@WebServlet(name = "ofertas", urlPatterns = { "/ofertas" })
public class ServletOfertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CARRINHO_KEY = "stationary.carrinho";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
		Carrinho carrinho = (Carrinho) session.getAttribute(CARRINHO_KEY);
		if (carrinho == null) {
			carrinho = new Carrinho();
			session.setAttribute(CARRINHO_KEY, carrinho);
		}
		EntityManager manager = JPAUtil.getEntityManager();
		Produtos produtos = new Produtos(manager);
		
		Produto produto = null;
		String msg = null;
		String idProduto = request.getParameter("Add");
		if(idProduto != null) {
			produto = produtos.porId(Long.parseLong(idProduto));
			carrinho.add(produto);
			msg = "Produto adicionado ao carrinho com sucesso!";
		}
		
		List<Produto> listaProdutos = produtos.emOferta();
		//List<Produto> listaProdutos = produtos.todos();
		
		ServletContext sc = getServletContext();
		RequestDispatcher dispatcher = sc.getRequestDispatcher("/WEB-INF/templates/ofertas.jsp");
		request.setAttribute("Produtos", listaProdutos);
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
		
	}		

	public String getServletInfo() {
		return "Servlet de exibição dos produtos em oferta";
	}

}
