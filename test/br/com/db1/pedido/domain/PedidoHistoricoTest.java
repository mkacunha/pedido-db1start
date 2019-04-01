package br.com.db1.pedido.domain;

import org.junit.Assert;
import org.junit.Test;

public class PedidoHistoricoTest {

	@Test
	public void deveRetornarStatusEObrigatorio() {
		try {
			new PedidoHistorico(null);
		} catch (Exception e) {
			Assert.assertEquals("Campo status é obrigatório.", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmHistorico() {
		PedidoHistorico historico = new PedidoHistorico(PedidoStatus.ABERTO);
		Assert.assertEquals(PedidoStatus.ABERTO, historico.getStatus());
		Assert.assertNotNull(historico.getData());
	}
}
