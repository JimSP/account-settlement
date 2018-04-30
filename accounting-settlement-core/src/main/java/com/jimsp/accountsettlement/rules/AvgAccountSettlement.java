package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Component;

@Component
public class AvgAccountSettlement {

	public BigDecimal media(final BigDecimal soma, final BigInteger qtde) {

		if (BigInteger.ZERO.equals(qtde) || qtde == null || soma == null) {
			return BigDecimal.ZERO;
		} else {
			return soma.divide(BigDecimal.valueOf(qtde.longValue()));
		}
	}
}
