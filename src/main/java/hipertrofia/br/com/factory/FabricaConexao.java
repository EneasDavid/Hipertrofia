package hipertrofia.br.com.factory;

//Importar os recursos para a conexão e a mensagem de confirmação de conexão
import java.sql.*;

public class FabricaConexao {
	/*Revisão de B.D
	 * 
	 * acesso ao bd pelo terminal -> 
	 * 
	 * mysql -uroot -p
	 * 123456 -> senha do b.d
	 * 
	 * criando o banco de dados
	 * 
	 * create database agenda;
	 * use agenda;
	 * create table contatos (id int primary key auto_increment, nome varchar (20) not null, idade int not null, dataCadastro data not null);
	 *
	 *desc contatos;
	 * 
	 * use agenda;
	 * select*from contatos;
	 * 
	 * truncante table contatos; - zera os auto incrementes (zera toda a tabela)
	 * 
	 *  drop database agenda; -exclui o banco de dados
	 * */
	
	//Nome do usuario do MySQL 
	private static final String USERNAME="root";
	
	//Senha do MySQL
	private static final String PASSWORD="123456";
	
	/*Dados dde caminho:
	 * Porta = jdbc:mysql://localhost:3306/
	 * Nome da base de dados = hipertrofia
	 * */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hipertrofia?rootTimezone=true&serverTimezone=UTC";
	
	public static Connection criaConexaoComMySQL() throws Exception{
		Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
		Connection conexao = DriverManager.getConnection(DATABASE_URL,USERNAME, PASSWORD); //Cria a conexão com o banco de dados
		return conexao;
	}
	 public static void main (String [] args) throws Exception {
		 Connection rede=criaConexaoComMySQL(); //Recupera uma conexão com o banco de dados
		 //Testa se a conexão é nula
		 if (rede!=null) {
			 System.out.println("Conexão bem sucedida!\nSobre:"+rede);
			 rede.close(); //Apos o teste, encerra a conexão
		 }
	 }
	
	
	   /**
	   * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário e senha fornecidos
	   * @param username
	   * @param senha
	   * @return uma conexão com o banco de dados
	   * @throws Exception
	   */
}
