package com.jimsp.accountsettlement.rules;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;

@Component
public class ValidateAccount {
	public void validate(final AccountSettlementCanonico accountSettlementCanonico) {
		Assert.notNull(accountSettlementCanonico, "expected accountSettlementCanonico not null.");
		Assert.notNull(accountSettlementCanonico.getContaContabil(), "expected accountSettlementCanonico.contaContabil not null.");
		Assert.isTrue(accountSettlementCanonico.getContaContabil() > 0L, "expected accountSettlementCanonico.contaContabil bigger then 0 (zero).");
		Assert.isTrue(accountSettlementCanonico.getContaContabil() < 10000000000000L, "expected accountSettlementCanonico.contaContabil lass then 10000000000000.");
		Assert.notNull(accountSettlementCanonico.getData(), "expected accountSettlementCanonico.data not null.");
		Assert.notNull(accountSettlementCanonico.getValor(), "expected accountSettlementCanonico.valor not null.");
	}
}
