package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class SumAccountSettlement {

	public BigDecimal soma(final Supplier<Stream<BigDecimal>> supplierStream) {
		return supplierStream.get().reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
}
