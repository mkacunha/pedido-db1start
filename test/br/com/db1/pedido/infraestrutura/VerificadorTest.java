package br.com.db1.pedido.infraestrutura;

import org.junit.Assert;
import org.junit.Test;

public class VerificadorTest {

	@Test
	public void deveRetoranarExceptionQuandoValorNulo() {
		try {
			Verificador.naoNulo(null, "teste");
		} catch (Exception e) {
			Assert.assertEquals("Campo teste é obrigatório.", e.getMessage());
		}
	}

	@Test
	public void naoDeveRetoranarExceptionQuandoValorNaoNulo() {
		Verificador.naoNulo("Valor", "teste");
	}

	@Test
	public void deveRetoranarExceptionQuandoCpfInvalido() {
		try {
			Verificador.cpf("098");
		} catch (Exception e) {
			Assert.assertEquals("CPF 098 é inválido.", e.getMessage());
		}
	}

	@Test
	public void naoDeveRetoranarExceptionQuandoCpfValido() {
		Verificador.cpf("99999999999");
	}
	
	@Test
	public void deveRetoranarExceptionQuandoValorMenorQueZero() {
		try {
			Verificador.maiorQueZero(-1.0, "teste");
		} catch (Exception e) {
			Assert.assertEquals("Campo teste deve ser maior que zero.", e.getMessage());
		}
	}
	
	@Test
	public void deveRetoranarExceptionQuandoValorIgualAZero() {
		try {
			Verificador.maiorQueZero(0.0, "teste");
		} catch (Exception e) {
			Assert.assertEquals("Campo teste deve ser maior que zero.", e.getMessage());
		}
	}

	@Test
	public void naoDeveRetoranarExceptionQuandoValorMaiorQueZero() {
		Verificador.maiorQueZero(10.0, "teste");
	}
}
