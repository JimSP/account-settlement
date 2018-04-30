package com.jimsp.accountsettlement.converter;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.model.AccountSettlementData;

@Component
public class AccountSettlementDataToAccountSettlementCanonico {

	public AccountSettlementCanonico accountSettlementCanonicoToAccountSettlementData(
			final AccountSettlementData accountSettlementData) {
		return new AccountSettlementCanonico(accountSettlementData.getContaContabil(), accountSettlementData.getData(),
				accountSettlementData.getValor());
	}
}
