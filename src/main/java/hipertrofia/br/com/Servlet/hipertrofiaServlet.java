package hipertrofia.br.com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hipertrofia.br.com.modelo.*;
import hipertrofia.br.com.dao.hipertrofiaDAO;

/**
 * Servlet implementation class hipertrofiaServlet
 */
@WebServlet("/")
public class hipertrofiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private hipertrofiaDAO dao;
	
	public void init () {
		dao= new hipertrofiaDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String acao = request.getServletPath();
		/*Lista de ações
		 * 
		 * Cadastros
		 * cartão
		 * vendedor
		 * Peça (apenas vendedor pode fazer isso - login)
		 *
		 * Edição
		 * cartão - Opção de excluir
		 * vendedor - Opção de excluir
		 * Peça - Opção de excluir
		 *
		 * Comprar - Usa Cartão(Opção de excluir | Opção de cadastrar) e Peça
		 * */
		try {
			System.out.println(acao);
			switch (acao) {
			/*Cadastro
			 * redirecionamento pro Formularios
			 * */
			case "/novoFormularioCartao":
				novoFormularioCartao(request, response);
				break;
			case "/novoFormularioProduto":
				novoFormularioProduto(request, response);
				break;
			case "/novoFormularioPedido":
				novoFormularioPedido(request, response);
				break;
			//Metodos de criação
			case "/novoPedido":
				adicionarPedido(request, response);
				break;
			case "/novoProduto":
				adicionarProduto(request, response);
				break;
			case "/novoCartao":
				adicionarCartao(request, response);
				break;
			//Listar
			case "/ListarPedido":
				listarPedido(request, response);
				break;
			case "/ListarProduto":
				listarProduto(request, response);
				break;
			case "/ListarCartao":
				listarCartao(request, response);
				break;
			/*Editar
			Formularios*/
			case "/FormularioEditarProduto":
				formularioEditarProduto(request, response);
				break;
			case "/FormularioEditarCartao":
				formularioEditarCartao(request, response);
				break;
			case "/editarCartao":
				editarCartao(request, response);
				break;
			case "/editarProduto":
				editarProduto(request, response);
				break;
			case "/editarPedido":
				editarPedido(request, response);
				break;
			//Excluir
			case "/excluirCartao":
				excluirCartao(request, response);
				break;
			case "/excluirProduto":
				excluirProduto(request, response);
				break;
			case "/excluirPedido":
				excluirPedido(request, response);
				break;
			default:
				listarProduto(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	//Inicio - Página index
	//Formularios
	private void novoFormularioCartao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cadastroCartao.jsp");
		dispatcher.forward(request, response);
	}
	private void novoFormularioProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cadastroProduto.jsp");
		dispatcher.forward(request, response);
	}
	private void novoFormularioPedido(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		Produto existeProduto = dao.selecionarTodosProdutos(index);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/pedido.jsp");
		request.setAttribute("pedido", existeProduto);
		dispatcher.forward(request, response);		
	}
	//Metodos de Edição
	private void formularioEditarProduto(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		Produto existeProduto = dao.selecionarTodosProdutos(index);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cadastroProduto.jsp");
		System.out.println(existeProduto.getReferencia());
		request.setAttribute("produto", existeProduto);
		dispatcher.forward(request, response);

	}
	private void formularioEditarCartao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		Cartao existeCartao= dao.selecionarTodosCartao(index);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cadastroCartao.jsp");
		request.setAttribute("cartao", existeCartao);
		dispatcher.forward(request, response);

	}
	//Adicionar
	private void adicionarProduto(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("Tentativa de cadastro de produto");
		var referencia = request.getParameter("referencia");
		var tamanho = request.getParameter("tamanho");
		var preco = Float.parseFloat(request.getParameter("preco"));
		Produto cadastrarProduto= new Produto(referencia,tamanho,preco);
		dao.cadastrarProduto(cadastrarProduto);
		response.sendRedirect("inicio");
	}
	private void adicionarCartao(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("Tentativa de cadastro de cartão");
		var numeroCartao = Integer.parseInt(request.getParameter("numeroCartao"));
		var cvc = Integer.parseInt(request.getParameter("cvc"));
		var validade = Integer.parseInt(request.getParameter("validade"));
		System.out.println("\n"+numeroCartao+"\n"+cvc+"\n"+validade);
		Cartao cadastrarCartao= new Cartao(numeroCartao,cvc,validade);
		dao.cadastrarCartao(cadastrarCartao);
		response.sendRedirect("inicio");
	}
	private void adicionarPedido(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("Tentativa de cadastro de Pedido");
		var referencia = request.getParameter("referencia");
		var tamanho = request.getParameter("tamanho");
		var preco = Float.parseFloat(request.getParameter("preco"));
		var numeroCartao = Integer.parseInt(request.getParameter("numeroCartao"));
		Pedido cadastrarPedido= new Pedido(referencia,tamanho,preco,numeroCartao);
		dao.cadastrarPedido(cadastrarPedido);
		response.sendRedirect("inicio");
	}
	
	//Metodos de listagem
	private void listarCartao(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		List<Cartao> getMetodoP = dao.getMetodoPagamento();
		request.setAttribute("cartoes", getMetodoP);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/listarCartao.jsp");
		dispatcher.forward(request, response);
	}
	private void listarProduto(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		List<Produto> getEstoque = dao.getEstoque();
		request.setAttribute("estoque", getEstoque);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}
	private void listarPedido(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		List<Pedido> getOrdens = dao.getOrdens();
		request.setAttribute("pedido", getOrdens);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cadastroCartao.jsp");
		dispatcher.forward(request, response);
	}
	//Editar
	//Metodos de Edição
	private void editarCartao(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		var numeroCartao = Integer.parseInt(request.getParameter("numeroCartao"));
		var cvc = Integer.parseInt(request.getParameter("cvc"));
		var validade = Integer.parseInt(request.getParameter("validade"));
		Cartao cadastrarCartao= new Cartao(index,numeroCartao,cvc,validade);
		dao.atualizarCartao(cadastrarCartao);
		response.sendRedirect("listarCartao");
	}
	private void editarProduto(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		var referencia = request.getParameter("referencia");
		var tamanho = request.getParameter("tamanho");
		var preco = Float.parseFloat(request.getParameter("preco"));
		Produto atualizarProduto= new Produto(index,referencia,tamanho,preco);
		dao.atualizarProduto(atualizarProduto);
		response.sendRedirect("listaPedido");
	}
	private void editarPedido(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int index = Integer.parseInt(request.getParameter("id"));
		var referencia = request.getParameter("referencia");
		var tamanho = request.getParameter("tamanho");
		var preco = Float.parseFloat(request.getParameter("preco"));
		var numeroCartao = Integer.parseInt(request.getParameter("numeroCartao"));
		Pedido atualizarPedido= new Pedido(index,referencia, tamanho, preco,numeroCartao );
		dao.atualizarPedido(atualizarPedido);
		response.sendRedirect("listaPedido");
	}
	
	//Metodos de excluir
	private void excluirPedido(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.removerPorIdPedido(id, false);
		response.sendRedirect("ListarPedido");
	}
	private void excluirProduto(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.removerPorIdProduto(id, false);
		response.sendRedirect("listarProduto");
	}
	private void excluirCartao(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.removerPorIdCartao(id, false);
		response.sendRedirect("ListarCartao");
	}
}
