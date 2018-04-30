package com.jimsp.accountsettlement.rules;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@Component
public class CountAccountSettlement {

	public BigInteger qtde(final Supplier<Stream<AccountSettlementCanonico>> supplierStreamFiltred) {
		return BigInteger.valueOf(supplierStreamFiltred.get().count());
	}
}
