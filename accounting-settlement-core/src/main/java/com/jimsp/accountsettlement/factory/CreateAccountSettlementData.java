package com.jimsp.accountsettlement.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.model.AccountSettlementData;

@Component
public class CreateAccountSettlementData {

	public AccountSettlementData create(final UUID id, final AccountSettlementCanonico accountSettlementCanonico) {
		final AccountSettlementData accountSettlementData = new AccountSettlementData();
		accountSettlementData.setId(id);
		accountSettlementData.setContaContabil(accountSettlementCanonico.getContaContabil());
		accountSettlementData.setData(accountSettlementCanonico.getData());
		accountSettlementData.setValor(accountSettlementCanonico.getValor());
		return accountSettlementData;
	}
}
