package br.com.db1.pedido.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PedidoItemTest {
	
	private Produto produto;
	
	@Before
	public void init() {
		produto = new Produto("123", "Caneta", 10.0);
	}
	
	@Test
	public void deveRetornarProdutoEObrigatorio() {
		try {
			new PedidoItem(null, 8.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo produto é obrigatório.", e.getMessage());
		}
	}

	@Test
	public void deveRetornarQuantidadeEObrigatorio() {
		try {
			new PedidoItem(produto, null);
		} catch (Exception e) {
			Assert.assertEquals("Campo quantidade é obrigatório.", e.getMessage());
		}
	}

	@Test
	public void deveRetornarQuantidadeveSerMaiorQueZero() {
		try {
			new PedidoItem(produto, 0.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo quantidade deve ser maior que zero.", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmPedidoItem() {
		PedidoItem item = new PedidoItem(produto, 2.0);;
		Assert.assertEquals(produto, item.getProduto());
		Assert.assertEquals(2.0, item.getQuantidade(),  0);
		Assert.assertEquals(produto.getValor(), item.getValorUnitario(), 0);
		Assert.assertEquals(20.0, item.getValorTotal(), 0);
	}
}
