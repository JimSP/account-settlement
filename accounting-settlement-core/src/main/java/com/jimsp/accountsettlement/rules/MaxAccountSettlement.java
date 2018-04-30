package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class MaxAccountSettlement {
	
	public BigDecimal max(final Supplier<Stream<BigDecimal>> supplierStream) {
		return supplierStream.get().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).get();
	}
}
