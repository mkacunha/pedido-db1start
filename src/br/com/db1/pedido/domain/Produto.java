package br.com.db1.pedido.domain;

import br.com.db1.pedido.infraestrutura.Verificador;

public class Produto {

	private String codigo;

	private String nome;

	private Double valor;

	private ProdutoStatus status;

	public Produto(String codigo, String nome, Double valor) {
		Verificador.naoNulo(codigo, "código do produto");
		Verificador.naoNulo(nome, "nome do produto");
		Verificador.naoNulo(valor, "valor do produto");

		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.status = ProdutoStatus.ATIVO;
	}
	
	public void inativar() {
		if (ProdutoStatus.ATIVO.equals(this.status)) {
			throw new RuntimeException("Produto está " + this.status);
		}
		this.status = ProdutoStatus.INATIVO;
	}
	
	public boolean isAtivo() {
		return  ProdutoStatus.ATIVO.equals(this.status);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public ProdutoStatus getStatus() {
		return status;
	}
}
