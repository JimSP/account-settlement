package com.jimsp.accountsettlement.canonico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public final class AccountSettlementCanonico implements Serializable{
	
	private static final long serialVersionUID = -4965668613368312617L;
	
	private final Long contaContabil;
	private final Date data;
	private final BigDecimal valor;

	public AccountSettlementCanonico(final Long contaContabil, final Date data,
			final BigDecimal valor) {
		this.contaContabil = contaContabil;
		this.data = data;
		this.valor = valor;
	}

	public Long getContaContabil() {
		return contaContabil;
	}

	public Date getData() {
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
		AccountSettlementCanonico other = (AccountSettlementCanonico) obj;
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
