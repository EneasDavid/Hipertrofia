package hipertrofia.br.com.modelo;

public class Produto {
	
	private int id;
	private String referencia;
	private String tamanho;
	private float preco;
	
	//Construtor usado para cadastro
	public Produto(String referencia, String tamanho, float preco) {
		this.referencia = referencia;
		this.tamanho = tamanho;
		this.preco = preco;
	}
	
	//Construtor usado pra atualização
	public Produto(int id, String referencia, String tamanho, float preco) {
		this.id = id;
		this.referencia = referencia;
		this.tamanho = tamanho;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	public String getReferencia() {
		return referencia;
	}
	public String getTamanho() {
		return tamanho;
	}
	public float getPreco() {
		return preco;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setReferencia(String referencia) {
		this.referencia=referencia;
	}
	public void setTamanho(String tamanho) {
		this.tamanho=tamanho;
	}
	public void setPreco(float preco) {
		this.preco=preco;
	}
	
}
