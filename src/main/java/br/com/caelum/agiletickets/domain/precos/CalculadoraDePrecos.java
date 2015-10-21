package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			preco = calculaPrecoCinema ( sessao );
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			preco = calculaPrecoBallet(sessao);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculaPrecoOrquestra(sessao);
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	
	private static BigDecimal calculaPrecoCinema ( Sessao sessao )
	{
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
			return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			return sessao.getPreco();
		}		
	}


	private static BigDecimal calculaPrecoBallet ( Sessao sessao )
	{
		return calculaPrecoOrquestra ( sessao );
	}
	
	private static BigDecimal calculaPrecoOrquestra ( Sessao sessao)
	{
		BigDecimal preco; 
		
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
			preco =  sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco =  sessao.getPreco();
		}

		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

}
