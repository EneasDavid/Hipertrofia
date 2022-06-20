package hipertrofia.br.com.modelo;
public class Pedido {
	 
	private int id;
	private String referenciaP;
	private String tamanhoP;
	private float precoP;
	private int metodoPagamento;
	
	//Construtor usado pra atualização
	public Pedido(int id, String referenciaP, String tamanhoP, float precoP, int metodoPagamento) {
		this.id = id;
		this.referenciaP = referenciaP;
		this.tamanhoP = tamanhoP;
		this.precoP = precoP;
		this.metodoPagamento = metodoPagamento;
	}

	//Construtor usado para cadastro
	public Pedido(String referenciaP, String tamanhoP, float precoP, int metodoPagamento) {
		this.referenciaP = referenciaP;
		this.tamanhoP = tamanhoP;
		this.precoP = precoP;
		this.metodoPagamento = metodoPagamento;
	}

	public int getId() {
		return id;
	}

	public String getReferenciaP() {
		return referenciaP;
	}
	public String getTamanhoP() {
		return tamanhoP;
	}
	public float getPrecoP() {
		return precoP;
	}
	public int getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setReferenciaP(String referenciaP) {
		this.referenciaP = referenciaP;
	}
	public void setTamanhoP(String tamanhoP) {
		this.tamanhoP = tamanhoP;
	}
	public void setPrecoP(float precoP) {
		this.precoP = precoP;
	}
	public void setMetodoPagamento(int metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
		
}
