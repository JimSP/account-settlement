package com.jimsp.accountsettlement.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SummaryAccountSettlementDto {

	private final BigDecimal soma;
	private final BigDecimal min;
	private final BigDecimal max;
	private final BigDecimal media;
	private final BigInteger qtde;

	public SummaryAccountSettlementDto(@JsonProperty("soma") final BigDecimal soma,
			@JsonProperty("min") final BigDecimal min,
			@JsonProperty("max") final BigDecimal max,
			@JsonProperty("media") final BigDecimal media,
			@JsonProperty("qtde") final BigInteger qtde) {
		this.soma = soma;
		this.min = min;
		this.max = max;
		this.media = media;
		this.qtde = qtde;
	}

	public BigDecimal getSoma() {
		return soma;
	}

	public BigDecimal getMin() {
		return min;
	}
	
	public BigDecimal getMax() {
		return max;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public BigInteger getQtde() {
		return qtde;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((qtde == null) ? 0 : qtde.hashCode());
		result = prime * result + ((soma == null) ? 0 : soma.hashCode());
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
		SummaryAccountSettlementDto other = (SummaryAccountSettlementDto) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (qtde == null) {
			if (other.qtde != null)
				return false;
		} else if (!qtde.equals(other.qtde))
			return false;
		if (soma == null) {
			if (other.soma != null)
				return false;
		} else if (!soma.equals(other.soma))
			return false;
		return true;
	}
}
