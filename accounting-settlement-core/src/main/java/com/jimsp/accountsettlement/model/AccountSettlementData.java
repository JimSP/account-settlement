package com.jimsp.accountsettlement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountSettlementData implements Serializable {

	private static final long serialVersionUID = -7470864579027437583L;

	@Id
	private UUID id;
	
	@Column
	private Long contaContabil;
	
	@Column
	private Date data;
	
	@Column
	private BigDecimal valor;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(Long contaContabil) {
		this.contaContabil = contaContabil;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AccountSettlementData other = (AccountSettlementData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountSettlementData [id=" + id + ", contaContabil=" + contaContabil + ", data=" + data + ", valor="
				+ valor + "]";
	}
}
