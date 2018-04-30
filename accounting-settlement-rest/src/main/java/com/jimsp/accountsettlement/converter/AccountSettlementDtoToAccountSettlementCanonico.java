package com.jimsp.accountsettlement.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.jimsp.accountsettlement.canonico.AccountSettlementCanonico;
import com.jimsp.accountsettlement.dto.AccountSettlementDto;

@Component
public class AccountSettlementDtoToAccountSettlementCanonico {

	public AccountSettlementCanonico convert(final AccountSettlementDto accountDto) {
		try {
			final Date data = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(accountDto.getData()));
			return new AccountSettlementCanonico(accountDto.getContaContabil(), data, accountDto.getValor());
		} catch (ParseException e) {
			throw new IllegalArgumentException("expected accountDto.data format AAAAMMDD.");
		}
	}
}
