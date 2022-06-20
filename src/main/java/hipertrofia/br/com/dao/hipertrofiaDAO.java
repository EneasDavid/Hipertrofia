package hipertrofia.br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hipertrofia.br.com.modelo.*;
import hipertrofia.br.com.factory.FabricaConexao;

@SuppressWarnings("unused")

public class hipertrofiaDAO {
	//Cartão
	public void cadastrarCartao(Cartao cartao) throws SQLException{
		// Isso é uma sql comum, os ? são os parametros que nás vamos adicionar na base de dados

		String comandoSQL = "INSERT INTO cartao (numeroCartao,cvc,dataVencimento) VALUES (?,?,?);" ;
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Adiciona o valor do primeiro parametro da sql
			pstm.setInt(1, cartao.getNumeroCartao());
			
			//Adicionar o valor do segundo parametro da sql
			pstm.setInt(2, cartao.getCvc());
			
			//Adiciona o valor do terceiro parametro da sql
			pstm.setInt(3, cartao.getDataVencimento());
			
			//Executa a sql para inserção dos dados
			 pstm.executeUpdate();
			 
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void removerPorIdCartao(int id, boolean zerar) {
		String comandoSQL = "DELETE FROM cartao WHERE id = ?;" ;
		String comandoSQLZerar="truncate table cartao;";
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			if(zerar) {
				/*
				 * Se zerar for verdadeiro ele vai dá um trucante na tabela excluindo tudo,até os metadados
				Cria uma declaração preparada (PreparedStatment), classe usada para executar a query*/
				pstm=rede.prepareStatement(comandoSQLZerar);
			}else {
				//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
				 pstm = rede.prepareStatement(comandoSQL);
				 pstm.setInt(1, id);
			}
			 pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void atualizarCartao(Cartao cartao) {
		String comandoSQL = "UPDATE cartao SET numeroCartao = ?, cvc = ?, dataVencimento = ? WHERE id = ?";
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Adiciona o valor do primeiro parametro da sql
			pstm.setInt(4, cartao.getId());
			
			//Adiciona o valor do primeiro parametro da sql
			pstm.setInt(1, cartao.getNumeroCartao());
			
			//Adicionar o valor do segundo parametro da sql
			pstm.setInt(2, cartao.getCvc());
			
			//Adiciona o valor do terceiro parametro da sql
			pstm.setInt(3, cartao.getDataVencimento());
			
			//Executa a sql para inserção dos dados
			pstm.execute();
					
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public List<Cartao> getMetodoPagamento(){
		String comandoSQL = "SELECT*FROM cartao;" ;
		List<Cartao> metodoPagamento = new ArrayList<>();
		Connection rede=null;
		PreparedStatement pstm=null;
		//Classe que vai recuperar os dados do banco de dados
		ResultSet metodoDePagamentos=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Classe que vai recuperar os dados do banco de dados
			metodoDePagamentos=pstm.executeQuery();
			
			//Enquanto existir dados no banco de dados, faça
			 while(metodoDePagamentos.next()){
				 Cartao cartoes= new Cartao(0,0,0);
				 
				 //Recupera o id do banco e atribui ele ao objeto
				 cartoes.setId(metodoDePagamentos.getInt("id"));
				 
				 //Recupera o número do cartão do banco e atribui ele ao objeto
				 cartoes.setNumeroCartao(metodoDePagamentos.getInt("numeroCartao"));
				 
				 //Recupera o CVC do banco e atribui ele ao objeto
				 cartoes.setCvc(metodoDePagamentos.getInt("cvc"));
				 
				 //Recupera a data de validade do banco e atribui ela ao objeto
				 cartoes.setDataVencimento(metodoDePagamentos.getInt("dataVencimento"));
				 
				 //Adiciono o contato recuperado, a lista de contatos
				 metodoPagamento.add(cartoes);
			 }				 
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
		return metodoPagamento;
	}
	public Cartao selecionarTodosCartao(int id) {
		Cartao cartao= null;
		Connection rede=null;
		PreparedStatement pstm=null;
		String comandoSQL = "SELECT numeroCartao,cvc,dataVencimento FROM CARTAO WHERE ID =?";
		ResultSet buscaCartao=null;
		try {
			//Cria uma conexeção com o banco
			rede= FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Passa o id pro comando SQL
			pstm.setInt(1, id);	
			
			//Classe que vai recuperar os dados do banco de dados
			buscaCartao=pstm.executeQuery();
			while (buscaCartao.next()) {
				int numeroCartao = buscaCartao.getInt("numeroCartao");
				int cvc = buscaCartao.getInt("cvc");
				int vencimento = buscaCartao.getInt("dataVencimento");
				cartao = new Cartao (id, numeroCartao, cvc, vencimento);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
		return cartao;
	}
	
	//Produto
	public void cadastrarProduto(Produto produto) {
		String comandoSQL = "INSERT INTO produto (referencia,tamanho,preco) VALUES (?,?,?);" ;
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			 //Cria uma conexeção com o banco
			 rede= FabricaConexao.criaConexaoComMySQL();
			 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = rede.prepareStatement(comandoSQL);
			 
			 //Adiciona o valor do primeiro parametro da sql
			 pstm.setString(1, produto.getReferencia());
			 
			 //Adicionar o valor do segundo parametro da sql
			 pstm.setString(2, produto.getTamanho());
			
			 //Adicionar o valor do terceiro parametro da sql
			 pstm.setFloat(3, produto.getPreco());
			
			 //Executa a sql para inserção dos dados
			 pstm.execute();
			 
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void removerPorIdProduto(int id, boolean zerar) {
		String comandoSQL = "DELETE FROM produto WHERE id = ?;" ;
		String comandoSQLZerar="truncate table produto;";
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			if(zerar) {
				/*
				 * Se zerar for verdadeiro ele vai dá um trucante na tabela excluindo tudo,até os metadados
				Cria uma declaração preparada (PreparedStatment), classe usada para executar a query*/
				pstm=rede.prepareStatement(comandoSQLZerar);
			}else {
				//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
				 pstm = rede.prepareStatement(comandoSQL);
				 pstm.setInt(1, id);
			}
			 pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void atualizarProduto(Produto produto) {
		Connection rede=null;
		PreparedStatement pstm=null;
		String comandoSQL = "UPDATE produto SET referencia = ?, tamanho = ?, preco = ? WHERE id = ?";
		try {
			 //Cria uma conexeção com o banco
			 rede= FabricaConexao.criaConexaoComMySQL();
			 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = rede.prepareStatement(comandoSQL);
			 
			 //Adiciona o valor do primeiro parametro da sql
			 pstm.setInt(4, produto.getId());
			 
			 //Adiciona o valor do primeiro parametro da sql
			 pstm.setString(1, produto.getReferencia());
			 
			 //Adicionar o valor do segundo parametro da sql
			 pstm.setString(2, produto.getTamanho());
			
			 //Adicionar o valor do terceiro parametro da sql
			 pstm.setFloat(3, produto.getPreco());
			
			 //Executa a sql para inserção dos dados
			 pstm.execute();
			 
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public List<Produto> getEstoque(){
	
		String comandoSQL = "SELECT*FROM produto;" ;
		List<Produto> estoque = new ArrayList<>();
		Connection rede=null;
		PreparedStatement pstm=null;
		
		//Classe que vai recuperar os dados do banco de dados
		ResultSet ConferirEstoque=null;
				try {
					//Cria uma conexão com o banco de dados
					rede=FabricaConexao.criaConexaoComMySQL();
					
					//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
					pstm=rede.prepareStatement(comandoSQL);
					
					//Classe que vai recuperar os dados do banco de dados
					ConferirEstoque=pstm.executeQuery();
					
					//Enquanto existir dados no banco de dados, faça
					 while(ConferirEstoque.next()){
						 Produto produtos= new Produto(0,null,null,0);
						//recupera o número do cartão do banco e atribui ele ao objeto
						 produtos.setId(ConferirEstoque.getInt("id"));
						 //recupera o número do cartão do banco e atribui ele ao objeto
						 produtos.setReferencia(ConferirEstoque.getString("referencia"));						 
						 //Recupera o CVC do banco e atribui ele ao objeto
						 produtos.setPreco(ConferirEstoque.getFloat("preco"));
						//Recupera o CVC do banco e atribui ele ao objeto
						 produtos.setTamanho(ConferirEstoque.getString("tamanho"));
						 
						 //Adiciono o contato recuperado, a lista de contatos
						 estoque.add(produtos);
					 }				 
				}catch (Exception e){
					e.printStackTrace();
				}finally {
					//Pra fechar as conexões
					try{
						 if(pstm != null){
							 pstm.close();
						 }
						 if(rede != null){
							 rede.close();
						 }
					 }catch(Exception e){
						 e.printStackTrace();
					}
				}
		return estoque;
		}
	public Produto selecionarTodosProdutos(int id) {
		Produto produto= null;
		Connection rede=null;
		PreparedStatement pstm=null;
		String comandoSQL = "SELECT referencia,tamanho,preco FROM PRODUTO WHERE ID =?";
		ResultSet buscaProduto=null;
		try {
			//Cria uma conexeção com o banco
			rede= FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Passa o id pro comando SQL
			pstm.setInt(1, id);	
			
			//Classe que vai recuperar os dados do banco de dados
			buscaProduto=pstm.executeQuery();
			while (buscaProduto.next()) {
				String referencia = buscaProduto.getString("referencia");
				String tamanho = buscaProduto.getString("tamanho");
				float preco = buscaProduto.getFloat("preco");
				produto = new Produto(id, referencia, tamanho, preco);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
		return produto;
	}
	
	//Pedido
	public void cadastrarPedido(Pedido pedido) {
		String comandoSQL = "INSERT INTO pedido nomeProduto,tamanhoP,precoP,formaPagamento VALUES ?,?,?,?;" ;
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			 //Cria uma conexeção com o banco
			 rede= FabricaConexao.criaConexaoComMySQL();
			 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = rede.prepareStatement(comandoSQL);
			 
			//Adiciona o valor do primeiro parametro da sql
			 pstm.setString(1, pedido.getReferenciaP());
			 
			 //Adicionar o valor do segundo parametro da sql
			 pstm.setString(2, pedido.getTamanhoP());
			
			 //Adicionar o valor do terceiro parametro da sql
			 pstm.setFloat(3, pedido.getPrecoP());
			 
			 //Adicionar o valor do quarto parametro da sql
			 pstm.setInt(4, pedido.getMetodoPagamento());
			 
			 //Executa a sql para inserção dos dados
			 pstm.execute();
		
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void removerPorIdPedido(int id, boolean zerar) {
		String comandoSQL = "DELETE FROM pedido WHERE id = ?;" ;
		String comandoSQLZerar="truncate table pedido;";
		Connection rede=null;
		PreparedStatement pstm=null;
		try {
			//Cria uma conexão com o banco de dados
			rede=FabricaConexao.criaConexaoComMySQL();
			if(zerar) {
				/*
				 * Se zerar for verdadeiro ele vai dá um trucante na tabela excluindo tudo,até os metadados
				Cria uma declaração preparada (PreparedStatment), classe usada para executar a query*/
				pstm=rede.prepareStatement(comandoSQLZerar);
			}else {
				//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
				 pstm = rede.prepareStatement(comandoSQL);
				 pstm.setInt(1, id);
			}
			 pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public void atualizarPedido(Pedido pedido) {
		Connection rede=null;
		PreparedStatement pstm=null;
		String comandoSQL = "UPDATE pedido SET nomeProduto = ?, tamanhoP = ?, precoP = ? formaPagamento = ? WHERE id = ?";
		try {
			 //Cria uma conexeção com o banco
			 rede= FabricaConexao.criaConexaoComMySQL();
			 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = rede.prepareStatement(comandoSQL);
			 
			//Adiciona o valor do primeiro parametro da sql
			 pstm.setInt(4, pedido.getId());
			 
			 //Adiciona o valor do primeiro parametro da sql
			 pstm.setString(1, pedido.getReferenciaP());
			 
			 //Adicionar o valor do segundo parametro da sql
			 pstm.setString(2, pedido.getTamanhoP());
			
			 //Adicionar o valor do terceiro parametro da sql
			 pstm.setFloat(3, pedido.getPrecoP());
			
			 //Adicionar o valor do terceiro parametro da sql
			 pstm.setFloat(4, pedido.getMetodoPagamento());
			 
			 //Executa a sql para inserção dos dados
			 pstm.execute();
			 
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
	public List<Pedido> getOrdens(){	
		String comandoSQL = "SELECT*FROM pedidos;" ;
		List<Pedido> pedidos = new ArrayList<>();
		Connection rede=null;
		PreparedStatement pstm=null;
		
		//Classe que vai recuperar os dados do banco de dados
		ResultSet pedido=null;
				try {
					//Cria uma conexão com o banco de dados
					rede=FabricaConexao.criaConexaoComMySQL();
					
					//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
					pstm=rede.prepareStatement(comandoSQL);
					
					//Classe que vai recuperar os dados do banco de dados
					pedido=pstm.executeQuery();
					
					//Enquanto existir dados no banco de dados, faça
					 while(pedido.next()){
						 Pedido ordem= new Pedido(0, null, null,0,0);
						//recupera o número do cartão do banco e atribui ele ao objeto
						 ordem.setId(pedido.getInt("id"));
						 //recupera a referencia do banco e atribui ele ao objeto
						 ordem.setReferenciaP(pedido.getString("referencia"));
						 //Recupera o tamanho do banco e atribui ele ao objeto
						 ordem.setTamanhoP(pedido.getString("tamanho"));
						//Recupera o tamanho do banco e atribui ele ao objeto
						 ordem.setPrecoP(pedido.getFloat("preco"));
						//Recupera o tamanho do banco e atribui ele ao objeto
						 ordem.setMetodoPagamento(pedido.getInt("metodoPagamento"));

						 //Adiciono o contato recuperado, a lista de contatos
						 pedidos.add(ordem);
					 }				 
				}catch (Exception e){
					e.printStackTrace();
				}finally {
					//Pra fechar as conexões
					try{
						 if(pstm != null){
							 pstm.close();
						 }
						 if(rede != null){
							 rede.close();
						 }
					 }catch(Exception e){
						 e.printStackTrace();
					}
				}
		return pedidos;
	}
	public Pedido selecionarTodosPedidos(int id) {
		Pedido pedido= null;
		Connection rede=null;
		PreparedStatement pstm=null;
		String comandoSQL = "SELECT nomeProduto,tamanhoP,precoP,formaPagamento FROM PEDIDO WHERE ID =?";
		ResultSet buscaPedidos=null;
		try {
			//Cria uma conexeção com o banco
			rede= FabricaConexao.criaConexaoComMySQL();
			
			//Cria uma declaração preparada (PreparedStatment), classe usada para executar a query
			pstm=rede.prepareStatement(comandoSQL);
			
			//Passa o id pro comando SQL
			pstm.setInt(1, id);	
			
			//Classe que vai recuperar os dados do banco de dados
			buscaPedidos=pstm.executeQuery();
			while (buscaPedidos.next()) {
				String referencia = buscaPedidos.getString("nomeProduto");
				String tamanho = buscaPedidos.getString("tamanhoP");
				float preco = buscaPedidos.getFloat("precoP");
				int formaPagamento= buscaPedidos.getInt("formaPagamento");
				pedido = new Pedido(id, referencia, tamanho, preco, formaPagamento);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//Pra fechar as conexões
			try{
				 if(pstm != null){
					 pstm.close();
				 }
				 if(rede != null){
					 rede.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			}
		}
		return pedido;
	}
	
}