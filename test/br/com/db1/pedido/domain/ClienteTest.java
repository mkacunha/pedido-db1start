package br.com.db1.pedido.domain;

import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {
	
	@Test
	public void deveRetornarNomeEObrigatorio() {
		try {
			new Cliente(null, "123");
		} catch (Exception e) {
			Assert.assertEquals("Campo nome do cliente é obrigatório.", e.getMessage());
		}
	}
	
	
	@Test
	public void deveRetornarCpfEObrigatorio() {
		try {
			new Cliente("João", null);
		} catch (Exception e) {
			Assert.assertEquals("Campo CPF do cliente é obrigatório.", e.getMessage());
		}
	}
	
	@Test
	public void deveRetornarCpfEInvalido() {
		try {
			new Cliente("João", "098");
		} catch (Exception e) {
			Assert.assertEquals("CPF 098 é inválido.", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmCliente() {
		Cliente cliente = new Cliente("João", "99999999999");
		
		Assert.assertEquals("João", cliente.getNome());
		Assert.assertEquals("99999999999", cliente.getCpf());
		Assert.assertEquals(ClienteStatus.ATIVO, cliente.getStatus());
	}
	
	@Test
	public void deveRetornarClienteAtivo() {
		Cliente cliente = new Cliente("João", "99999999999");
		
		Assert.assertTrue(cliente.isAtivo());
	}
}
