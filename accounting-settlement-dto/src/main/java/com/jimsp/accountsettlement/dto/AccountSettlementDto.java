package com.jimsp.accountsettlement.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class AccountSettlementDto {

	@NotNull(message="expected AccountSettlementDto.contaContabil is not null")
	@Min(0)
	@Max(10000000000000L)
	private final Long contaContabil;
	
	@NotNull(message="expected AccountSettlementDto.data is not null")
	private final Integer data;
	
	@NotNull(message="expected AccountSettlementDto.valor is not null")
	private final BigDecimal valor;

	public AccountSettlementDto(@JsonProperty("contaContabil") final Long contaContabil, @JsonProperty("data") final Integer data,
			@JsonProperty("valor") final BigDecimal valor) {
		this.contaContabil = contaContabil;
		this.data = data;
		this.valor = valor;
	}
	
	public Long getContaContabil() {
		return contaContabil;
	}

	public Integer getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaContabil == null) ? 0 : contaContabil.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountSettlementDto other = (AccountSettlementDto) obj;
		if (contaContabil == null) {
			if (other.contaContabil != null)
				return false;
		} else if (!contaContabil.equals(other.contaContabil))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
