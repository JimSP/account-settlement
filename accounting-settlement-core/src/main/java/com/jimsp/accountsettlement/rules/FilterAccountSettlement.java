package com.jimsp.accountsettlement.rules;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@Component
public class FilterAccountSettlement {

	public Supplier<Stream<AccountSettlementCanonico>> filter(
			final Supplier<Stream<AccountSettlementCanonico>> supplierStream) {
		return () -> supplierStream.get().filter(accountSettlementCanonico -> accountSettlementCanonico != null);
	}
}
