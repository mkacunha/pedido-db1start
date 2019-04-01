package br.com.db1.pedido.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.db1.pedido.infraestrutura.Verificador;

public class Pedido {
	
	private static final int QUANTIDADE_MAXIMA_ITENS = 10;
	
	private String codigo;
	
	private PedidoStatus status;
	
	private LocalDateTime dataStatus;
	
	private Cliente cliente;
	
	private List<PedidoHistorico> historicos = new ArrayList<>();
	
	private List<PedidoItem> itens = new ArrayList<>();

	public Pedido(String codigo, Cliente cliente) {
		Verificador.naoNulo(codigo, "código");
		Verificador.naoNulo(cliente, "cliente");
		this.verificarClienteAtivo();
		
		
		this.codigo = codigo;
		this.cliente = cliente;
		this.novoHistoricoStatus();
	}

	public void adicionarItem(Produto produto, Double quantidade) {
		this.verificarStatusPedidoParaAlterar();
		
		if (this.itens.size() == QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Quantidade máxima de itens excedida: " + QUANTIDADE_MAXIMA_ITENS);
		}
		
		this.itens.add(new PedidoItem(produto, quantidade));
	}
	
	public void removerItem(Produto produto) {
		this.verificarStatusPedidoParaAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}
	
	public void faturar() {
		if (!PedidoStatus.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está  " + this.status);
		}
		
		if (this.itens.size() == 0 || this.itens.size() > QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Pedido deve ter no minímo 1 item e no máximo 10 itens. Quantidade atual: " + this.itens.size());
		}
		
		this.verificarClienteAtivo();
		
		this.status = PedidoStatus.FATURADO;
	}
	
	public void cancelar() {
		this.verificarStatusPedidoParaAlterar();		
		this.status = PedidoStatus.CANCELADO;
	}
	
	public void reabrir() {
		if (!PedidoStatus.CANCELADO.equals(this.status)) {
			throw new RuntimeException("Pedido está " + this.status);
		}
		
		this.status = PedidoStatus.ABERTO;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<PedidoHistorico> getHistoricos() {
		return Collections.unmodifiableList(historicos);		
	}

	public List<PedidoItem> getItens() {
		return Collections.unmodifiableList(itens);
	}
	
	public LocalDateTime getDataStatus() {
		return dataStatus;
	}
	
	private void novoHistoricoStatus() {
		PedidoHistorico historico = new PedidoHistorico(this.status);		
		this.historicos.add(historico);
		this.dataStatus = historico.getData();
	}
	
	private void verificarClienteAtivo() {
		if (!cliente.isAtivo()) {
			throw new RuntimeException("Cliente " + cliente.getNome() + " está inativo");
		}
	}
	
	private void verificarStatusPedidoParaAlterar() {
		if (!PedidoStatus.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está  " + this.status);
		}
	}
}
