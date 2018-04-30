package com.jimsp.accountsettlement.rules;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@Component
public class MapAccountSettlementExtractValue {

	public Supplier<Stream<BigDecimal>> map(final Supplier<Stream<AccountSettlementCanonico>> supplierStream) {
		return () -> supplierStream.get().map(accountSettlementData -> accountSettlementData.getValor());
	}
}
