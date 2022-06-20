package hipertrofia.br.com.modelo;
public class Cartao {
	private int id;
	private int numeroCartao;
	private int Cvc;
	private int dataVencimento;
	
	//Construtor usado para cadastro
	public Cartao(int id, int numeroCartao, int Cvc, int dataVencimento) {
		this.id = id;
		this.numeroCartao = numeroCartao;
		this.Cvc = Cvc;
		this.dataVencimento = dataVencimento;
	}
	//Construtor usado pra atualização
	public Cartao(int numeroCartao, int Cvc, int dataVencimento) {
		this.numeroCartao = numeroCartao;
		this.Cvc = Cvc;
		this.dataVencimento = dataVencimento;
	}
	public int getId() {
		return id;
	}
	public int getNumeroCartao() {
		return numeroCartao;
	}
	public int getCvc() {
		return Cvc;
	}
	public int getDataVencimento() {
		return dataVencimento;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao=numeroCartao;
	}
	public void setCvc(int Cvc) {
		this.Cvc=Cvc;
	}
	public void setDataVencimento(int dataVencimento) {
		this.dataVencimento=dataVencimento;
	}

}
