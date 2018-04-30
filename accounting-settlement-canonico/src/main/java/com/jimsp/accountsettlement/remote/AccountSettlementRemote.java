package com.jimsp.accountsettlement.remote;

import java.util.List;
import java.util.UUID;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.canonico.SummaryAccountSettlementCanonico;

public interface AccountSettlementRemote {
	
	public UUID createAccount(final AccountSettlementCanonico accountSettlementCanonico);

	public AccountSettlementCanonico getAccountById(final UUID id);

	public List<AccountSettlementCanonico> getAccountByContaContabil(final Long contaContabil);

	public SummaryAccountSettlementCanonico summary(final Long contaContabil);

}
