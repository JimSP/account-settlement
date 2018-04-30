package com.jimsp.accountsettlement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;

@Component
public class AccountSettlementCanonicoToAccountSettlementDto {

	@Autowired
	private DateToInteger dateToInteger;

	public AccountSettlementDto convert(final AccountSettlementCanonico accountSettlementCanonico) {
		final Integer data = dateToInteger.convert(accountSettlementCanonico.getData());
		return new AccountSettlementDto(accountSettlementCanonico.getContaContabil(), data,
				accountSettlementCanonico.getValor());
	}
}
