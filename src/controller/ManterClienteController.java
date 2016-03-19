package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import dao.*;
/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/manter_clientes.do")
public class ManterClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome"); // nome do parametro do formulário 
		String pFone = request.getParameter("fone");
		int pId = Integer.parseInt(request.getParameter("id"));
		String pAcao = request.getParameter("acao");

		//Para poder exibir os textos.
		PrintWriter out = response.getWriter();
		
		//inserindo no banco 
		Cliente cliente = new Cliente(pId, pNome, pFone);

		if(pAcao.equals("Inserir")){
			cliente.criar();
			out.println( "Cliente inserido com sucesso");
		}
		if(pAcao.equals("Deletar")){
			cliente.excluir();	
			out.println( "Cliente atualizado com sucesso");
		}
		if(pAcao.equals("ConsultarTodos")){
			
			ClienteDAO dao = new ClienteDAO();
			String resp = dao.consultarTodos();
			out.println("<html><head><title>Todos os clientes</title></head><body>");
			out.println(resp);
			out.println("</body></html>");
		}
		else{ 
			cliente.atualizar();
		//	cliente.carregar();
			out.println( "Cliente atualizado com sucesso");
		}

		//out.println( "id: "+cliente.getId()+"<br>");
		//out.println( "nome: "+cliente.getNome()+"<br>");
		//out.println( "fone: "+cliente.getFone()+"<br>");
		
	}
}


