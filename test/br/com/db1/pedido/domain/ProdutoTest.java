package br.com.db1.pedido.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTest {
	
	@Test
	public void deveRetornarCodigoProdutoEObrigatorio() {
		try {
			new Produto(null, "Caneta", 8.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo c�digo do produto � obrigat�rio.", e.getMessage());
		}
	}
	
	@Test
	public void deveRetornarNomeProdutoEObrigatorio() {
		try {
			new Produto("123", null, 8.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo nome do produto � obrigat�rio.", e.getMessage());
		}
	}
	
	
	@Test
	public void deveRetornarValorProdutoEObrigatorio() {
		try {
			new Produto("1234", "Caneta", null);
		} catch (Exception e) {
			Assert.assertEquals("Campo valor do produto � obrigat�rio.", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmProduto() {
		Produto produto = new Produto("1234", "Caneta", 8.0);
		
		Assert.assertEquals("1234", produto.getCodigo());
		Assert.assertEquals("Caneta", produto.getNome());
		Assert.assertEquals(8.0, produto.getValor(), 0);
		Assert.assertEquals(ProdutoStatus.ATIVO, produto.getStatus());
	}
}
